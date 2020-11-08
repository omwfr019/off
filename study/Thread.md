Thread 스레드
==============


### 정의
: 하나의 프로세스 내부에서 독립적으로 실행되는 하나의 작업 단위. <br/>
운영체제에 의해 관리되는 하나의 작업 혹은 태스크.

* JVM에 의해 하나의 프로세스 발생. main() 안의 실행문 들이 하나의 스레드. <br/>
* main() 이외에 다른 스레드를 만들려면 Thread 클래스를 상속하거나 Runnable 인터페이스를 구현. <br/>
* 스레드 간 정보를 주고받을 수 있음 (프로세스는 불가) <br/>


### 스레드 생성 주기
New -> Start() -> Runnable (<-> Blocked) -> Run() -> Running (<-> Blocked) -> 실행 -> Dead
* New : 스레드가 생성되었지만 스레드가 아직 실행할 준비가 되지 않음. <br/>
* Runnable 상태 : 스레드가 실행되기위한 준비 상태. 스케줄링을 기다림. <br/>
  CPU를 점유하고 있지 않으며 실행Running을 하기 위해 대기하고 있는 상태. (=Ready 상태)
* Running 상태 : 스케줄러에 의해 선택된 스레드가 실행되는 단계. <br/>
  CPU를 점유. JVM만이 호출 가능. <br/>
  Runnable에 있는 여러 스레드 중 우선 순위를 가진 스레드가 결정되는 JVM이 자동으로 run() 메서드를 호출하여 스레드가 Running 상태로 진입.
* Dead 상태 : Running 상태에서 스레드가 모두 실행되고 난 후 완료 상태. (=Done 상태. 종료 상태)
* Blocked 상태 : 스레드가 작업을 완수하지 못하고 잠시 작업을 멈추는 단계. <br/>
  CPU 점유권을 상실한 상태. <br/>
  wait() 메서드에 의해 Blocked 상태가 된 스레드는 notify() 메서드가 호출되면 Runnable 상태로 감. <br/>
  sleep(시간) 메서드에 의해 Blocked 상태가 된 스레드는 지정된 시간이 지나면 Runnable 상태로 감.


### 스레드의 상태 6가지
!["img"](https://t1.daumcdn.net/cfile/tistory/2431B74F5964517D32)
1. New : 스레드가 생성되었지만 스레드가 아직 실행할 준비가 되지 않았음.
2. Runnable : 스레드가 실행되고 있거나 실행 준비되어 스케줄링은 기다리는 상태.
3. Waiting : 다른 스레드가 notify(), notifyAll()을 불러주기 기다리고 있는 상태 (동기화).
4. Timed_Waiting : 스레드가 sleep(n) 호출로 인해 n 밀리초동안 잠을 자고 있는 상태.
5. Block : 스레드가 I/O 작업을 요청하면 자동으로 스레드를 Block 상태로 만듦.
6. Terminated : 스레드가 종료된 상태.


### Thread 제공 메서드
* Thread 생성자
  + Thread(String s) : 스레드 이름
  + Thread(Runnable r) : 인터페이스 객체
  + Thraed(Runnable r, String s) : 인터페이스 객체와 스레드 이름
* Thread 메서드
  + static void sleep(long mesc) throws Interrupted Exception : mesc에 지정된 밀리초 동안 대기
  + String getName() 
  + void setName(String s) : 스레드의 이름을 s로 설정
  + void start() : 스레드를 시작 run() 메서드 호출
  + int getPriority() : 스레드의 우선 순위를 반환
  + void setPriority(int p) : 스레드의 우선 순위를 p값으로
  + boolean isAlive() : 스레드가 시작되었고 아직 끝나지 않았으면 true, 끝났으면 false 반환
  + void join() throws InterruptedException : 스레드가 끝날 때까지 대기
  + void run() : 스레드가 실행할 부분 기술 (오버라이딩 기술)
  + void suspend() : 스레드 일시정지
  + void resume() : 일시 정지된 스레드를 다시 시작
  + void yield() : 다른 스레드에게 실행 상태를 양보하고 자신은 준비 상태로


### Thread 생성
1. Thread 클래스 상속
    ```java
    public class ThreadEx extends Thread {
      public void run() {
        try {
          for(int i=0; i<10; i++) {
            Thread.sleep(500);
            System.out.println(i);
          }
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      }
    }
    public static void main(String args[]) {
      ThreadEx ex1 = new ThreadEx();
      ThreadEx ex2 = new ThreadEx();
      
      ex1.run();
      ex2.run(); // 0123...0123...
      
      ex1.start();
      ex2.start(); // 00112233...
    }
    ```
2. Runnable 인터페이스 구현
  : JDK 라이브러리 인터페이스. run() 메서드만 정의되어 있음.
  ```java
  public class RunnableTest implements Runnable {
    public void run() {
      try {
        for(int i=0; i<10; i++) {
          Thread.sleep(500);
          System.out.println(i);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  public static void main(String args[]) {
    RunnableTest Obj1 = new RunnableTest();
    RunnableTest Obj2 = new RunnableTest();
    
    Thread th1 = new Thread(Obj1);
    Thread th2 = new Thread(Obj2);
    
    th1.start();
    th2.start();
  }
  ```


### Daemon Thread
: 동일한 프로세스 안에서 다른 스레드의 수행을 돕는 스레드로 다른 스레드를 서비스 해주면서 다른 스레드가 모두 종료되면 자신도 종료되는 스레드.
* 프로그램이 종료되는 것을 막지 않음.
* ex) 가비지 컬렉터, 메인 스레드
* 스레드를 생성하고 setDaemon(true)를 설정하면 됨.
* 스레드가 시작하기 전에 설정해야 함.
```java
public class ThreadEx extends Thread {
  public void run() {
    try {
      System.out.println("Daemon Thread Start!");
      sleep(10000);
      System.out.println("Daemon Thread End!");
    } catch (Exception e) { }
  }
  
  public static void main(String args[]) {
    ThreadEx th = new ThreadEx();
    th.setDaemon(true);
    th.start();
    System.out.println("Maini Method End!");
  }
}
```



### 우선순위Priority
: 2개 이상의 스레드가 동작 중일 때 우선 순위를 부여하여 우선 순위가 높은 스레드에게 실행의 우선권 부여.
* 우선 순위를 지정하기 위한 상수 제공
  + static final int MAX_PRIORITY : 우선순위 10 - 가장 높은 우선 순위
  + static final int MIN_PRIORITY : 우선순위 1 - 가장 낮은 우선 순위
  + static final int NORM_PRIORITY : 우선순위 5 - 보통의 우선 순위
* 스레드 우선 순위는 변경 가능
  + void setPriority(int priority)
  + int getPriority()
* main() 스레드의 우선 순위 값 : 초기값=5
* JVM의 스케줄링 규칙
  + 철저한 우선 순위 기반
  + 가장 높은 우선 순위의 스레드가 우선적으로 스케줄링
  + 동일한 우선 순위의 스레드는 돌아가면서 스케줄링 (라운드 로빈)
```java
public class PriorityTest extends Thread {
  Priority(String str) { super(str); }
  
  public void run() {
    try {
      for (int i=0; i<10; i++) {
        Thread.sleep(1000);
        System.out.println(getName() + i + "번째 수행");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class ThreadEx {
  public static void main(String args[]) {
    PriorityTest t1 = new PriorityTest("우선 순위가 낮은 스레드");
    PriorityTest t2 = new PriorityTest("우선 순위가 높은 스레드");
    t1.setPriority(Thread.MIN_PRIORITY);  // 최소 우선 순위 지정
    t2.setPriority(Thread.MAX_PRIORITY);  // 최대 우선 순위 지정
    t1.start;
    t2.start;
  }
}
```


### 스레드 종료
1. run() 메서드에 예외처리에 return을 넣어 스스로 종료.
2. interrupt() 메서드를 호출해 InterruptedException을 발생시켜 타 스레드에서 강제 종료
```java
public class ThreadInterrupt extends Thread {
  ThreadInterrupt(String str) { super(str); }
  
  public void run() {
    try {
      for (int i=0; i<10; i++) {
        Thread.sleep(1000);
        System.out.println(getName() + i + "번째 수행");
      }
    } catch (InterruptException e) {
      System.out.println("스레드 강제 종료");
      return;
    }
  }
}

public class ThreadEx {
  public static void main(String args[]) {
    ThreadInterrupt th = new ThreadInterrupt("스레드");
    th.start();
    
    try {
      Thread.sleep(3000);
    } catch (InterruptException e) { }
    th.interrupt();
  }
}
```



### Multi Thread
* 여러 개의 스레드가 동시에 수행되면서 공유할 수 있을 때 공유되는 부분은 상호 배타적으로 사용되어야 함.
* Dead Lock : 프로그램의 수행이 이루어지지 않고 무한 수행.
* 임계 영역 (critical section) : 공유 자원을 사용하는 코드영역. <br/>
  공유 자원을 동시에 수정할 수 없도록 상호 배타적으로 실행될 수 있도록 작성되어야 함.
* 상호배제 문제 해결
  + 자바는 한 순간에 하나의 스레드만 실행할 수 있는 synchronized method 제공.
  + 한 스레드가 synchronized method를 수행 중이면 다른 스레드는 대기.
* 처리 방법
  + 공유 자원에 접근하는 메서드의 앞에 synchronized 메서드로 지정.
  + 공유 자원을 사용하는 영역을 synchronized(객체명)의 블록으로 지정.



### 타이머
> public class Timer extends Object


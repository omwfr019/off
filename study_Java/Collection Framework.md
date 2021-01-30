Collections Framework
=======================


<br/>

### Collection Framework
: 데이터 군을 저장하는 클래스들을 표준화한 설계
* Collection = 다수의 데이터 <br/>
  Framework = 표준화된 프로그래밍 방식

<br/>

### 핵심 인터페이스
* 컬렉션 프레임웍의 모든 컬렉션 클래스들은 List, Set, Map 중의 하나를 구현함
* 구현한 인터페이스 이름이 클래스의 이름에 포함되어있어 이름만으로 클래스의 특징을 쉽게 알 수 있음
* Vector, Stack, Hashtable, Properties와 같은 클래스들은 컬렉션 프레임웍이 만들어지기 이전부터 존재 ~> 컬렉션 프레임웍 명명법을 따르지 않음 <br/>
  기존 컬렉션 클래스들은 호환을 위해, 설계를 변경해서 남겨두었지만 사용을 권장하지 않음
* +) JDK1.5부터 Iterable인터페이스가 추가되고 이를 Collection인터페이스가 상속받도록 변경되었으나 이것은 단지 인터페이스들의 공통적인 메서드인 Iterator()를 뽑아서 중복을 제거하기 위한 것에 불과하므로 상속계층도에서 별 의미 없음


#### Collection 인터페이스
* List와 Set의 상위 인터페이스
* 컬렉션 클래스에 저장된 데이터를 읽고, 추가하고, 삭제하는 등 컬렉션을 다루는데 가장 기본적인 메서드들을 정의
+ 람다Lambda, 스트림Stream에 관련된 메서드 정의

#### List 인터페이스
* 중복 허용 / 저장순서 유지
* 구현 클래스 : ArrayList, LinkedList, Stack, Vector 등
* 상속계층도 <br/>
  Stack -> Vector -> List <br/>
  ArrayList -> List <br/>
  LinkedList -> List

#### Set 인터페이스
* 중복 허용 X / 저장순서 유지 X
* 구현 클래스 : HashSet, TreeSet 등
* 상속계층도 <br/>
  HashSet -> List <br/>
  TreeSet -> SortedSet -> List

#### Map 인터페이스
* 키key와 값value의 쌍으로 이루어진 데이터의 집합
* 순서 유지 X / key는 중복 허용 X, value는 중복 허용
* 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등
* 상속계층도
  Hashtable -> Map <br/>
  LinkedHashMap -> HastMap -> Map <br/>
  TreeMap -> SortedMap
* values()에서는 반환타입이 Collection. keySet()에서는 반환타입이 Set => 값value은 중복을 허용하므로 Collection 타입으로 반환, 키key는 중복을 허용하지 않으므로 Set타입으로 반환

#### Map.Entry 인터페이스
* Map 인터페이스의 내부 인터페이스
* 인터페이스 안에 인터페이스를 정의하는 내부 인터페이스inner interface 정의 가능
* Map에 저장되는 key-value 쌍을 다루기 위해 내부적으로 Entry인터페이스를 정의해 놓음
* Map인터페이스를 구현하는 클래스에서는 Map.Entry인터페이스도 함께 구현해야 함

<br/><br/>

### List
* 구조가 간단하며 사용하기 쉬움
* 데이터를 읽어오는데 걸리는 시간(접근시간, access time)이 가장 빠름
* 배열의 단점
  + 크기를 변경할 수 없음
    - 새로운 배열을 생성해서 데이터를 복사해야 함 ~> 기존의 배열로부터 새로 생성된 배열로 데이터를 복사해야하기 때문에 효율이 떨어짐
    - 실행속도를 향상시키기 위해 충분히 큰 크기의 배열을 생성해야 하므로 메모리가 낭비됨
  + 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸림
    - 차례대로 데이터를 추가하고 마지막에서부터 데이터를 삭제하는 것은 빠르지만
    - 배열의 중간에 데이터를 추가하려면, 빈자리를 만들기 위해 다른 데이터들을 복사해서 이동해야 함 <br/>
  + => 이러한 배열의 단점을 보완 => Linked List

#### ArrayList
* List를 구현하므로 저장순서 유지 및 중복 허용
* 기존의 Vector를 개선한 것 ~ Vector의 구현원리와 기능적 측면에서 동일
* Object 배열을 이용해서 데이터를 순차적으로 저장
  + 배열에 순서대로 저장되며, 배열에 더 이상 저장할 공간이 없으면 기존 배열에 저장한 내용을 새로운 배열로 복사한 다음에 저장
* elementData라는 이름의 Object 배열을 멤버변수로 선언함
* 선언된 배열의 타입이 모든 객체의 최고조상인 Object이기 때문에 모든 종류의 객체를 담을 수 있음

#### Vector 클래스
* Vector 클래스의 재구성
  ``` java
  public class MyVector implements List {
	
    Object[] data = null;	// 객체를 담기 위한 객체배열 선언
    int capacity = 0;	// 용량
    int size = 0;	// 크기

    public MyVector(int capacity) {
      if (capacity < 0)
        throw new IllegalArgumentException("유효하지 않은 값입니다. : "+ capacity);

      this.capacity = capacity;
      data = new Object[capacity];
    }

    public MyVector() {
      this(10);	// 크기를 지정하지 않으면 크기를 10으로 한다
    }

    // 최소한의 저장공간을(capacity)을 확보하는 메서드
    public void ensureCapacity(int minCapacity) {
      if (minCapacity - data.length > 0)
        setCapacity(minCapacity);
    }

    public boolean add(Object obj) {
      // 새로운 객체를 저장하기 전에 저장할 공간을 확보
      ensureCapacity(size+1);
      data[size++] = obj;
      return true;
    }

    public Object get(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
      return data[index];
    }

    public Object remove(int index) {
      Object oldObj = null;

      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
      oldObj = data[index];

      // 삭제하고자 하는 객체가 마지막 객체가 아니라면, 배열복사를 통해 빈자리를 채워줘야 한다
      if (index != size-1)
        System.arraycopy(data, index+1, data, index, size-index-1);

      // 마지막 데이터를 null로 한다. 배열은 0부터 시작하므로 마지막 요소는 index가 size-1
      data[size-1] = null;
      size--;
      return oldObj;
    }

    public boolean remove(Object obj) {
      for (int i=0; i<size; i++) {
        if (obj.equals(data[i])) {
          remove(i);
          return true;
        }
      }
      return false;
    }

    public void trimToSize() {
      setCapacity(size);
    }

    private void setCapacity(int capacity) {
      if (this.capacity == capacity) return;	// 크기가 같으면 변경하지 않음

      Object[] tmp = new Object[capacity];
      System.arraycopy(data, 0, tmp, 0, size);
      data = tmp;
      this.capacity = capacity;
    }

    public void clear() {
      for (int i=0; i<size; i++)
        data[i] = null;
      size = 0;
    }

    public Object[] toArray() {
      Object[] result = new Object[size];
      System.arraycopy(data, 0, result, 0, size);

      return result;
    }

    public boolean isEmpty() { return size==0; }
    public int capacity() { return capacity; }
    public int size() { return size; }


    /*******************************************/
    /* List 인터페이스로부터 상속받은 메서드들  */
    /*                (생략)                   */
    /*******************************************/
	
  }
  ```

#### LinkedList
* 배열의 단점을 보완하기 위함 => 배열은 모든 데이터가 연속적으로 존재하지만 링크드 리스트는 불연속적으로 존재하는 데이터를 서로 연결link한 형태
* 각 요소node : 자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성됨
  > class Node { <br/>
  >   Node next;  // 다음 요소의 주소를 저장 <br/>
  >   Object obj; // 데이터를 저장 <br/>
  > }
* 새로운 데이터를 추가할 때는 새로운 요소를 생성한 다음 추가하고자 하는 위치의 이전 요소의 참조를 새로운 요소에 대한 참조로 변경해주고, 새로운 요소가 그 다음 요소를 참조하도록 변경하기만 하면 되므로 처리속도가 매우 빠름
* 이동방향이 단방향이기 때문에 다음 요소에 대한 접근은 쉽지만 이전요소에 대한 접근은 어려움 ~> 이 점을 보완한 것이 '이중 연결리스트(doubly linked list)' <br/>
  linked list에 참조변수를 하나 더 추가하여 다음 요소에 대한 참조뿐 아니라 이전 요소에 대한 참조가 가능하도록 함 <br/>
  > class Node { <br/>
  >   Node next;  // 다음 요소의 주소 저장 <br/>
  >   Node previous;  // 이전 요소의 주소 저장 <br/>
  >   Object obj; // 데이터를 저장 <br/>
  > }  
* 이중 원형 연결리스트doubly circular linked list
  + 더블 링크드 리스트의 접근성을 향상
  + 더블 링크드 리스트의 첫 번째 요소와 마지막 요소를 서로 연결
* 실제 LinkedList 클래스는 이름과 달리 더블 링크드 리스트로 구현되어 있음

#### ArrayList와 LinkedList의 성능차이 비교
* 순차적으로 추가/삭제하는 경우 : ArrayList > LinkedList
  + 단, ArrayList를 생성할 때 충분한 초기용량을 확보해야 함. 크기가 충분하지 않으면, 새로운 크기의 ArrayList를 생성하고 데이터를 복사하면 LinkedList가 더 빠를 수 있음
  + 순차적으로 삭제 = 마지막 데이터부터 역순으로 삭제. ArrayList는 마지막 데이터부터 삭제할 경우 각 요소의 재배치가 필요하지 않기 때문에 매우 빠름. 마지막 요소를 null로 바꾸면 되기 때문
* 중간 데이터를 추가/삭제하는 경우 : LinkedList > ArrayList
  + LinkedList는 각 요소간의 연결만 변경해주면 되므로 처리속도가 매우 빠름
  + ArrayList는 각 요소들을 재배치하여 추가할 공간을 확보하거나 빈 공간을 채워야하므로 처리속도가 늦음
* 데이터 검색
  + ArrayList : 인덱스가 n인 데이터의 주소 = 배열의 주소 + n * 데이터 타입의 크기
    - 각 요소들이 연속적으로 메모리상에 존재
    - 순차적인 추가삭제는 더 빠름
    - 비효율적인 메모리사용 <br/>
    => 접근시간 빠름. 추가/삭제 속도 느림
  + LinkedList
    - 불연속으로 위치한 각 요소들이 서로 연결된 것이라 처음부터 n번째 데이터까지 차례차례 따라가야 함
    - 데이터가 많을수록 접근성이 떨어짐 <br/>
    => 저장해야하는 데이터의 개수가 많아질수록 데이터를 읽어오는 시간, 즉 접근시간access time이 길어짐 <br/>
    => 접근시간 느림. 추가/삭제 속도 빠름
* 다루고자 하는 데이터의 개수가 변하지 않는 경우 => ArrayList <br/>
  데이터 개수의 변경이 잦음 => LinkedList
* 컬렉션 프레임웍에 속한 대부분의 컬렉션 클래스들을 서로 변환이 가능한 생성자를 제공하므로 간단히 다른 컬렉션 클래스로 데이터를 옮길 수 있음 <br/>
  : 작업하기 전 데이터를 저장할 때는 ArrayList를 사용하고, 작업할 때 LinkedList로 데이터를 옮겨 작업하면 좋은 효율
  
#### Stack
* 마지막에 저장한 데이터를 가장 먼저 꺼내는 LIFO(Last In First Out)
* 순차적으로 데이터를 추가하고 삭제하는 스택에는 ArrayList와 같은 배열기반의 컬렉션 클래스가 적합
* 컬렉션 프레임웍 이전부터 존재했기 때문에 ArrayList가 아닌 Vector로부터 상속받아 구현

#### Queue
* 처음에 저장한 데이터를 가장 먼저 꺼내는 FIFO(First In First Out)
* 데이터의 추가/삭제가 쉬운 LinkedList로 구현하는 것이 적합
* 데이터를 꺼낼 때 항상 첫 번째 저장된 데이터를 삭제하므로, ArrayList와 같은 배열기반의 컬렉션 클래스를 사용한다면 데이터를 꺼낼 때마다 빈 공간을 채우기 위해 데이터의 복사가 발생하므로 비효율적
* 자바에서 큐는 Queue인터페이스로만 정의해 놓았을 뿐 별도의 클래스를 제공하지 않음. 대신 Queue 인터페이스를 구현한 클래스들이 있어서 이중에 하나를 선택해서 사용 <br/>
  Java API문서의 'All Known Implementing Classes'의 클래스들이 Queue인터페이스를 구현한 클래스

``` java
Stack st = new Stack();
Queue q = new LinkedList();	// Queue인터페이스의 구현체인 LinkedList를 사용

st.push("0");
st.push("1");
st.push("2");

q.offer("0");
q.offer("1");
q.offer("2");

while(!st.empty()) { System.out.print(st.pop()); }	// 210
while(!q.isEmpty()) { System.out.print(q.poll()); }	// 012
```

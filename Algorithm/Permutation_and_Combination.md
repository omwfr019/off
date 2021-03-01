Permutation and Conbination (순열과 조합)
=========================================

<br/>

## Permutation (순열)

: 순서가 있는 묶음. <br/>
한 번에 하나씩/일부/모든 요소를 가져와 정렬될 수 있음.

> nPr = factorial(n) / factorial(n-r) = n! / (n-r)!;

* 순서 고려
* 중복을 허용하지 않음
* Set 요소의 다른 배열
* ex) X, Y, Z라는 요소가 있는 집합의 2개 순열 값은 6 => {X,Y,Z}, {X,Z,Y}, {Y,X,Z}, {Y,Z,X}, {Z,X,Y}, {Z,Y,X}

#### Permutation with repetition (중복 순열)

> n^r

* 순서 고려
* 중복을 허용

<br/>

``` java
public static void main(String[] args) {
  List<Integer> numbers = new ArrayList<Integer>();

  numbers.add(2);
  numbers.add(1);
  numbers.add(4);
  numbers.add(3);

  int n = numbers.size();
  int r = 2;
  int result;

  result = factorial(n) / factorial(n-r);
  System.out.println("The permutation value for the numbers list is : " + result);
}

static int factorial(int number) {
  int f = 1;
  int j = 1;
  while(j <= number) {
    f = f * j;
    j++;
  }
  return f;
}
```

<br/>

### 방법 1
: 배열의 순서대로 하나씩 데이터를 swap하는 방법

#### 설계
1. 먼저, 위치가 마지막 요소를 가리키는지 확인. 위치가 마지막이라면 변경할 항목이 없으므로 배열을 print하고 리턴.
2. 위치가 배열의 마지막이 아니면, 배열의 첫 값부터 모든 값을 한 번씩 swap.
3. swap 후 하위 배열 array[position+1...end] 재귀를 수행.
4. position을 기준 인덱스로 하여 position보다 작은 값은 고정하고, 큰 값은 다시 swap을 진행하며 배열의 모든 순열을 얻음.  

#### 구현
``` java
static void permutation(int[] ary, int pos, int n, int r) {
  if(pos == r) {
    print(ary, r);
    return;
  }

  for(int depth = pos; depth < n; depth++) {
    swap(ary, pos, depth);
    permutation(ary, pos+1, n, r);
    swap(ary, pos, depth);	// 배열 원래대로
  }
}

static void swap(int[] ary, int pos, int depth) {
  int temp = ary[pos];
  ary[pos] = ary[depth];
  ary[depth] = temp;
}

static void print(int[] ary, int r) {
  for(int i = 0; i < r; i++) {
    System.out.print(ary[i] + " ");
  }
  System.out.println();
}

public static void main(String[] args) {
  int n = 3;
  int r = 3;
  int[] ary = {1,2,3};
  permutation(ary, 0, n, r);	// nPr
}
```

### 방법 2
: 순열에 사용되지 않은 데이터들을 따로 저장하여 하나씩 대입하는 방법
* 사전식 순열 구현 가능

#### 설계
1. 먼저, result에 들어간 숫자의 길이가 r과 같은지 확인. 같다면 배열을 print하고 리턴.
2. 1번에 해당하지 않으면, 배열의 첫 값부터 모든 값을 한 번씩 방문.
3. 배열 요소를 이미 방문했는지 체크(중복 체크)하고, 아니면 result에 값을 저장.

#### 구현
``` java
static boolean[] used;  // 중복 체크
static int[] result;

static void permutation(int[] ary, int pos, int n, int r) {
  // pos(result에 들어간 숫자의 길이)가 r과 같아지면 종료
  if(pos == r) {
    print(result, r);
    return;
  }
  
  for(int depth; depth < n; depth++) {
    if(!used[i]) {
      used[i] = true;
      result[pos] = ary[depth];
      permutation(ary, pos+1, n, r);
      used[i] = false;
    }
  }
}

static void print(int[] ary, int r) {
  for(int i = 0; i < r; i++) {
    System.out.print(ary[i] + " ");
  }
  System.out.println();
}

public static void main(String[] args) {
  int n = 3;
  int r = 3;
  int[] ary = {1,2,3};
  permutation(ary, 0, n, r);	// nPr
}
```

<br/><br/>


## Combination (조합)

: 순서가 없는 묶음. <br/>
한 번에 하나씩/일부/모두 선택하는 요소 집합의 다른 선택.

> nCr = factorial(n) / (factorial(r) * factorial(n-r)) = n! / (n-r)! * r!;

* 중복을 허용하지 않음
* 순서 고려 안함
* ex) X, Y, Z라는 요소가 있는 집합의 2개 조합 값은 3 => {X,Y}, {Y,Z}, {X,Z}

#### Combination with repetition (중복 조합)

> nHr = (n+r-1)C(r) = (n+r-1)! / r!

* 중복을 허용 (순서와 상관 없음)

<br/>

``` java
public static void main(String[] args) {
  List<Integer> numbers = new ArrayList<Integer>();

  numbers.add(2);
  numbers.add(4);
  numbers.add(1);
  numbers.add(5);
  numbers.add(3);

  int n = numbers.size();
  int r = 2;
  int result;

  result = factorial(n) / (factorial(r) * factorial(n-r));
  System.out.println("The permutation value for the numbers list is : " + result);
}
	
static int factorial(int number) {
  int f = 1;
  int j = 1;
  while(j <= number) {
    f = f * j;
    j++;
  }
  return f;
}
```

<br/>

#### 설계


#### 구현
``` java

```


<br/><br/>

---

### 순열과 조합

|순서 구분|종류|다음 원소 검색|
|---------|----|--------------|
|O|순열, 중복순열|0 부터 검색|
|X|조합, 중복조합|직전(기준)원소+1 부터 검색|

|중복 허용|종류|다음 원소 검색|
|---------|----|--------------|
|O|중복순열, 중복조합|원소 사용 여부 확인|
|X|순열, 조합|원소 사용 여부 확인 안함|


Collection Framework - Map
============================

<br/>

## Map 인터페이스
* 키key와 값value의 쌍으로 이루어진 데이터의 집합
* 순서 유지 X
* key는 중복 허용 X, value는 중복 허용 O <br/>
  기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게 됨
* 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등
* 상속계층도 <br/>
  + Hashtable -> Map <br/>
  + LinkedHashMap -> HastMap -> Map <br/>
  + TreeMap -> SortedMap
* 해싱hashing을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어 뛰어난 성능을 보임 <br/>
  => 하나의 키로 검색했을 때 결과가 단 하나. key = unique
* 시간복잡도 : O(1)
* Hashtable보다 새로운 버전인 HashMap 사용을 권장


#### 특징
* keySet()에서는 반환타입이 Set => 중복을 허용하지 않기 때문
* values()에서는 반환타입이 Collection => 중복을 허용하기 때문
* map.put(null, null);이나 map.get(null);을 허용

#### 메소드
|메소드|설명|
|--------|--------------------|
|HashMap()|HashMap 객체를 생성|
|HashMap(int initialCapacity)|지정된 값을 초기용량으로 하는 HashMap 객체를 생성|
|HashMap(int initialCapacity, float loadFactor)|지정된 초기용량과 load factor의 HashMap객체를 생성|
|HashMap(Map m)|지정된 Map의 모든 요소를 포함하는 HashMap을 생성|
|void clear()|Map의 모든 객체를 삭제|
|Object clone()|현재 HashMap을 복제해서 반환|
|boolean containsKey(Object key)|지정된 key객체와 일치하는 Map의 key객체가 있는지 확인|
|boolean containsValue(Object value)|지정된 value객체와 일치하는 Map의 value객체가 있는지 확인|
|Set entrySet()|Map에 저장되어 있는 key-value쌍을 Map.entry타입의 객체로 저장한 Set으로 반환|
|boolean equals(Object o)|동일한 Map인지 비교|
|Object get(Object key)|지정한 key객체에 대응하는 value객체를 찾아서 반환. 못찾으면 null 반환|
|Object getOrDefault(Object key, Object defaultValue)|지정된 키key의 값(객체)을 반환. 키를 못찾으면, 기본값defaultValue로 지정된 객체를 반환|
|int hashCode()|해시코드를 반환|
|boolean isEmpty()|Map이 비어있는지 확인|
|Set keySet()|Map에 저장된 모든 key객체를 반환|
|Object put(Object key, Object value)|Map에 value객체를 key객체에 연결(mapping)하여 저장|
|void putAll(Map m)|Map에 지정된 모든 요소를 HashMap에 저장|
|Object remove(Object key)|지정된 key객체와 일치하는 key-value객체를 삭제|
|Object replace(Object key, Object value)|지정된 키의 값을 지정된 객체(value)로 대체|
|boolean replace(Object key, Object oldValue, Object newValue)|지정된 키와 객체oldValue가 모두 일치하는 경우에만 새로운 객체로newValue로 대체
|int size()|Map에 저장된 key-value쌍의 개수를 반환|
|Collection values()|Map에 저장된 모든 value객체를 반환|


<br/>

### Map.Entry 인터페이스
* Map인터페이스는 키key와 값value을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는 데 사용 <br/>
  ~> Map에 저장되는 key-value 쌍을 묶어서 하나의 데이터entry로 저장하기 위해 내부적으로 Entry인터페이스를 정의해 놓음
  ``` java
  // 비객체지향적인 코드
  Object[] key;
  Object[] value;
  
  // 객체지향적인 코드
  Entry[] table;
  class Entry {
    Object key;
    Object value;
  }
  ```
* 인터페이스 안에 인터페이스를 정의하는 내부 인터페이스inner interface 정의 가능
* Map인터페이스를 구현하는 클래스에서는 Map.Entry인터페이스도 함께 구현해야 함

#### 메소드
|메소드|설명|
|------------|--------------------|
|boolean equals(Object o)|동일한 Entry인지 비교|
|Object getKey()|Entry의 key객체를 반환|
|Object getValue()|Entry의 value객체를 반환|
|int hashCode()|Entry의 해시코드를 반환|
|Object setValue(Object value)|Entry의 value객체를 지정된 객체로 바꿈|

<br/>

#### HashMap과 HashTable
* HashMap 사용을 권장
* HashTable은 HashMap보다 일찍 출시 ~> 하위 호환성을 위해 남아있는 것
* HashMap은 보조 해시 함수Additional Hash Function를 사용하기 때문에 해시 충돌hash collision이 덜 발생

<br/>

---

### TreeMap
: 이진검색트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장.
* 검색과 정렬에 적합
* 검색 성능은 HashMap이 더 뛰어나지만, 범위검색이나 정렬이 필요한 경우 TreeMap을 사용

#### 메소드
|메소드|설명|
|------------|--------------------|
|TreeMap()|TreeMap 객체를 생성|
|TreeMap(Comparator c)|지정된 Comparator를 기준으로 정렬하는 TreeMap 객체를 생성|
|TreeMap(Map m)|주어진 Map에 저장된 모든 요소를 포함하는 TreeMap을 생성|
|TreeMap(SortedMap m)|주어진 SortedMap에 저장된 모든 요소를 포함하는 TreeMap을 생성|
|Map.Entry ceilingEntry(Object key)|지정된 key와 일치하거나 큰 것중 제일 작은 것의 키와 값의 쌍(Map.Entry)을 반환. 없으면 null을 반환|
|Object ceilingKey(Object key)|지정된 key와 일치하거나 큰 것중 제일 작은 것의 키를 반환. 없으면 null을 반환|
|void clear()|TreeMap에 저장된 모든 객체를 제거|
|Object clone()|현재 TreeMap을 복제해서 반환|
|Comparator comparator()|TreeMap의 정렬기준이 되는 Comparator를 반환. Comparator가 지정되지 않았다면 null을 반환|
|boolean containsKey(Object key)|TreeMap에 지정된 키key가 포함되어있는지 알려줌 (포함되어 있으면 true)|
|boolean containsValue(Object value)|TreeMap에 지정된 값value이 포함되어있는지 알려줌 (포함되어 있으면 true)|
|NavigableSet descendingKeySet()|TreeMap에 지정된 키를 역순으로 정렬해서 NavigableSet에 담아서 반환|
|Set entrySet()|TreeMap에 저장된 키와 값을 entry의 형태로 Set에 저장해서 반환|
|Map.Entry firstEntry()|TreeMap에 저장된 첫번째(가장 작은) 키와 값의 쌍(Map.Entry)을 반환|
|Object firstKey()|TreeMap에 저장된 첫번째(가장 작은) 키를 반환|
|Map.Entry floorEntry(Object key)|지정된 key와 일치하거나 작은 것 중에서 제일 큰 키의 쌍(Map.Entry)을 반환. 없으면 null을 반환|
|Object floorKey(Object key)|지정된 key와 일치하거나 작은 것 중에서 제일 큰 키를 반환. 없으면 null을 반환|
|Object get(Object key)|지정된 키key의 값(객체)을 반환|
|SortedMap headMap(Object toKey)|TreeMap에 저장된 첫번째 요소부터 지정된 범위에 속한 모든 요소가 담긴 SortedMap을 반환(toKey는 미포함)|



<br/><br/>

---

## 해싱과 해시함수

### 해싱
: 해시함수hash function를 이용해서 데이터를 해시테이블hash table에 저장하고 검색하는 기법
* 데이터가 저장되어 있는 곳을 알려 주기 때문에 원하는 데이터를 빠르게 찾을 수 있음
* 해싱을 구현한 컬렉션 클래스 : HashSet, HashMap, Hashtable

배열과 링크드 리스트의 조합으로 이루어짐. <br/>
저장할 데이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 다시 그 곳에 연결 되어 있는 링크드 리스트에 저장.

### 해시 함수
* 데이터를 고정 길이의 데이터로 매핑하는 함수
* 하시 함수에 의해 얻어지는 값 : 해시값, 해시코드, 해시체크섬, 해시

#### 과정
1. 검색하고자 하는 값의 키로 해시함수를 호출
2. 해시함수의 계산결과(해시코드)로 해당 값이 저장되어 있는 링크드 리스트를 찾음
3. 링크드 리스트에서 검색한 키와 일치하는 데이터를 찾음

링크드 리스트는 검색에 불리. 배열은 원하는 요소가 몇 번째에 있는 지만 알면 원하는 값을 빠르게 찾을 수 있음.
> 배열의 인덱스가 n인 요소의 주소 = 배열의 시작주소 + type의 size * n

하나의 배열 요소에 많은 데이터가 저장된 형태보다는 많은 서랍에 하나의 데이터만 저장되어 있는 형태가 더 빠른 검색결과를 얻을 수 있음. <br/>
따라서 하나의 링크드 리스트에 최소한의 데이터만 저장되려면, 저장될 데이터의 크기를 고려해서 HashMap의 크기를 적절하게 지정해야 함. <br/>

해싱을 구현한 컬렉션 클래스에서는 Object 클래스에 정의된 hashCode()를 해시함수로 사용. <br/>
서로 다른 두 객체에 대해 equals()로 비교한 결과가 true인 동시에 hashCode()의 반환값이 같아야 같은 객체로 인식 ~> HashMap도 같은 방법으로 객체를 구별. <br/>
따라서 equals()를 재정의오버라이딩 하면 hashCode()도 함께 재정의 필요 (equals = true인 두 객체의 hashCode 결과 값이 같도록)

<br/>

### 해시 충돌
* 해시 함수가 서로 다른 입력값(key)에 대해 동일한 출력값을 반환. <br/>
  어떤 key가 해시 함수에 의해 변환된 인덱스로 배열에 접근했을 때 이미 값이 들어있는 것.
* 해시 성능 저하

* 충돌 공격collision attack : 해시 충돌을 일으키는 임의의 두 값을 찾는 과정
* 역상 공격 : 주어진 값에 대해 그 값과 해시 충돌을 일으키는 값을 찾는 것
* 충돌 저항성
  + 약한 충돌 저항성 : 주어진 x에 대해서 H(x) = H(y)인 x, y를 찾는 것이 어려운 경우
  + 강한 충돌 저항성 : H(x) = H(y)와 같이 같은 해시값을 갖는 x와 y를 찾는 것이 함수의 출력 값 범위 내에서 무시 가능할 때

<br/>

#### Collision Resolution 
1. Chaining (체이닝)
    : 배열-연결리스트 형식. 충돌이 일어난 자리에서 값들을 연결리스트로 연결.
    + Close Addressing 방법 (충돌이 일어나도 해당 위치에 저장)
2. Open Addressing (개방주소 방법)
    : 해시 함수에서 계산된 주소에 원소가 있는지 체크 -> 원소가 없으면 넣고, 있으면 정해진 규칙에 따라 다음 자리를 찾음
    + 추가 공간을 허용하지 않고 주어진 해시 테이블 공간 내에서 해결

3. 버켓
    : 2차원 배열 형식. 충돌이 일어난 자리에 배열로 쌓음
    + Close Addressing 방법 (충돌이 일어나도 해당 위치에 저장)
4. Linear Probing (선형 조사)
    : 충돌이 일어난 바로 뒷자리를 봄
    + i번째 해시 함수는 h(x)로 부터 i만큼 떨어짐
5. Quadratic Probing (이차원 조사)
    : 충돌이 일어나면 보폭을 이차함수에 의해 넓혀가면서 봄
6. Double Hashing (더블 해싱)
    : 두 개의 해시 함수를 사용. 충돌이 생겨 다음 주소를 계산할 때 두 번째 해시 함수값만큼 점프

Collection Framework - Map
============================

<br/>

### Map 인터페이스
* 키key와 값value의 쌍으로 이루어진 데이터의 집합
* 순서 유지 X
* key는 중복 허용 X, value는 중복 허용 O <br/>
  기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게 됨
* 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등
* 상속계층도 <br/>
  + Hashtable -> Map <br/>
  + LinkedHashMap -> HastMap -> Map <br/>
  + TreeMap -> SortedMap
* values()에서는 반환타입이 Collection. keySet()에서는 반환타입이 Set => 값value은 중복을 허용하므로 Collection 타입으로 반환, 키key는 중복을 허용하지 않으므로 Set타입으로 반환
* 해싱hashing을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어 뛰어난 성능을 보임 <br/>
  => 하나의 키로 검색했을 때 결과가 단 하나. key = unique
* Hashtable보다 새로운 버전인 HashMap 사용을 권장

#### 메서드
|메서드|설명|
|------------|--------------------|
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

#### 특징
* keySet()에서는 반환타입이 Set => 중복을 허용하지 않기 때문
* values()에서는 반환타입이 Collection => 중복을 허용하기 때문
* map.put(null, null);이나 map.get(null);을 허용

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

#### 메서드
|메서드|설명|
|------------|--------------------|
|boolean equals(Object o)|동일한 Entry인지 비교|
|Object getKey()|Entry의 key객체를 반환|
|Object getValue()|Entry의 value객체를 반환|
|int hashCode()|Entry의 해시코드를 반환|
|Object setValue(Object value)|Entry의 value객체를 지정된 객체로 바꿈|

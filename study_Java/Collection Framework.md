Collections Framework
=======================


<br/>

### Collection Framework
: 데이터 군을 저장하는 클래스들을 표준화한 설계
* Collection = 다수의 데이터 <br/>
  Framework = 표준화된 프로그래밍 방식

<br/>

### 핵심 인터페이스
* Collection - List
  + 순서가 있는 데이터의 집합
  + 데이터의 중복 허용
  + 구현 클래스 : ArrayList, LinkedList, Stack, Vector 등
* Collection - Set
  + 순서를 유지하지 않는 데이터의 집합
  + 데이터의 중복을 허용하지 않음
  + 구현 클래스 : HashSet, TreeSet 등
* Map
  + 키key와 값value의 쌍으로 이루어진 데이터의 집합
  + 순서를 유지하지 않음
  + 키는 중복을 허용하지 않고, 값은 중복을 허용함
  + 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등

* 컬렉션 프레임웍의 모든 컬렉션 클래스들은 List, Set, Map 중의 하나를 구현함
* 구현한 인터페이스 이름이 클래스의 이름에 포함되어있어 이름만으로 클래스의 특징을 쉽게 알 수 있음
* Vector, Stack, Hashtable, Properties와 같은 클래스들은 컬렉션 프레임웍이 만들어지기 이전부터 존재 ~> 컬렉션 프레임웍 명명법을 따르지 않음 <br/>
  기존 컬렉션 클래스들은 호환을 위해, 설계를 변경해서 남겨두었지만 사용을 권장하지 않음
* +) JDK1.5부터 Iterable인터페이스가 추가되고 이를 Collection인터페이스가 상속받도록 변경되었으나 이것은 단지 인터페이스들의 공통적인 메서드인 Iterator()를 뽑아서 중복을 제거하기 위한 것에 불과하므로 상속계층도에서 별 의미 없음


#### List 인터페이스
* 상속계층도
  Stack -> Vector -> List <br/>
  ArrayList -> List <br/>
  LinkedList -> List

#### Map 인터페이스
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

<br/>

### ArrayList
* List를 구현하므로 저장순서 유지 및 중복 허용
* 기존의 Vector를 개선한 것 ~ Vector의 구현원리와 기능적 측면에서 동일
* Object 배열을 이용해서 데이터를 순차적으로 저장
  + 배열에 순서대로 저장되며, 배열에 더 이상 저장할 공간이 없으면 기존 배열에 저장한 내용을 새로운 배열로 복사한 다음에 저장
* elementData라는 이름의 Object 배열을 멤버변수로 선언함

* 

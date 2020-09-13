Oracle
========


<br/><br/><br/>
### SQL 문 수행 과정
1. 같은 실행 계획이 공유 풀에 있는지 확인
2. SQL 문법 검사, DATA DICTIONARY를 검사를 수행하여 해당 사용자 소유의 테이블인지 확인
3. 실행 권한이 있는지 확인
4. 이상이 없으면 실행 계획을 작성, 적용


<br/><br/>
### 메모리 영역
* 사용자의 프로세스 요청 -> 오라클 서버 프로세스가 데이터베이스 버퍼 캐시를 읽음 -> 읽어온 데이터베이스 버퍼를 MRU End에 이동시킴 -> 다른 버퍼들을 aging하여 LRU End쪽으로 계속해서 이동.
* LRU(Least Recently Used) 알고리즘 : Oracle 데이터 베이스 서버는 자주 사용하는 데이터를 메모리에 오래 저장하여 I/O 효율을 높이고, 자주 사용하지 않는 데이터는 aging되어 데이터 파일에 물리적으로 저장하여 SGA 영역을 효율적으로 관리.

* 데이터베이스 버퍼 캐시 (Database Buffer Cache) : 메모리 영역의 집합. 사용자가 데이터를 요청하면 서버 프로세스는 데이터베이스 버퍼 캐시를 확인 -> 요청한 데이터가 있으면 버퍼 캐시로부터 데이터를 읽어 반환, 없으면 데이터 파일로부터 해당 데이터를 읽어 버퍼 캐시에 저장한 후 요청한 데이터를 돌려줌. 이때, 디스크 I/O가 발생하면서 데이터베이스 성능이 저하되므로 항상 적당한 크기의 데이터베이스 버퍼 캐시가 필요하게 됨.
  - dirty buffer로 구성된 write list와 LRU list로 구성.
  - LRU list는 free buffer, pinned buffer, dirty buffer로 구성.
  - 버퍼 캐시 메모리 영역에 저장되거나 변경된 데이터들은 LRU 알고리즘에 의해 버퍼 캐시에 보존되며, dirty buffer가 되어 write list로 이동. 한정된 버퍼 캐시 내에서 서버 프로세스가 더이상 free buffer를 찾을 수 없을 때 DBWO 프로세스에 신호를 보내고, DBWR 프로세스가 write list의 dirty buffer들을 데이터 파일에 저장.
* 공유 풀 영역 (SHARED POOL AREA) : 사용자가 작성한 SQL문이 저장되어 관리됨. 동일한 SQL문이 다시 실행될 때 확인된 SQL 문을 재사용하기 위해 SQL문을 Library Cache 내에 저장.
* 리두 로그 버퍼 (REDO LOG BUFFER) : 데이터베이스 장애 발생 시 복구를 위해 모든 변경된 정보와 원래의 원본 정보들을 저장하는 버퍼. 지정된 크기만큼의 데이터를 메모리에 저장하고 있다 이를 온라인 리두 로그 파일에 저장.


<br/><br/>
### 관련 용어
* MRU(Most Recently Used) End : 가장 최근에 접근한 버퍼 영역. 주로 데이터를 검색
* LRU End : 가장 이전에 접근한 버퍼 영역. 주로 Free Buffer를 검색할 때 이용
* Free Buffer : 아무 것도 저장되어 있지 않은 버퍼 영역
* Dirty Buffer : 변경된 데이터를 저장하고 있는 버퍼 영역. Write List로 옮겨짐
* Aging : 하나의 시스템 내에서의 모든 프로세스는 균등하게 자원을 할당 받아 사용할 수 있어야 함. 같은 시간에 한정된 시스템 자원을 요청한 프로세스들은 요청한 시간 별로 우선순위를 두어 그 자원의 사용권을 할당해 주는 시스템 내부 알고리즘



<br/><br/><br/>
## View
: 물리적으로 존재하지 않는 가상 테이블. 데이터의 논리적 부분집합.
* 다수의 테이블에서 필요한 값들만 가져옴 (저장된 SELECT 문)
* 사용자에게 필요한 정보만 제공 가능 ~> 보안
* 데이터 독립성
* 수정(ALTER)이 불가능하므로 생성시 OR REPLACE 옵션을 추가하여 새로 변경해 생성하는 것을 권장 (DROP 할 경우 권한까지 삭제되지만, OR REPLACE는 SELECT 문만 변경됨)
* VIEW에 INSERT 할 경우 원본 TABLE에 입력됨
* VIEW에 UPDATE 할 경우 VIEW는 데이터가 없어지고, 원본 TABLE 내용이 갱신됨
* 제한 조건
  - 테이블에 데이터가 NULL인 컬럼은 뷰에 포함 불가
  - WITH READ ONLY 옵션을 준 뷰는 갱신이 불가능
  - 가상컬럼(ROWID, ROWNUM, NEXTVAL, CURRVAL)에 대한 참조를 포함한 뷰는 INSERT 불가능


<br/><br/>
## DBLink
: 클라이언트 또는 현재의 데이터베이스에서 네트워크상의 다른 데이터베이스에 접속하기 위한 접속 설정을 정의하는 오라클 객체
* 원격지의 서버와 로컬 서버 간 조인에 사용
* 연동되는 데이터베이스 서버 조건
  - 상호 논리적 관계 : 개별 서버에 분산된 데이터들이 서로 상관 관계를 가지고 있음 (연산에 필요한 권한 등이 부여) ~> 개별 서버에 분산된 데이터 원본들을 한 곳으로 모았을 경우 데이터가 중앙집중적으로 구성
  - 컴퓨터 통신망에 연결 : 하드웨어와 소프트웨어를 통하여 서로 접근이 가능 (인트라넷, 동일 서브넷, WAN, 인터넷 등)
  - 지역적인 분리 : 데이터 서비스를 제공하는 주체(서버)가 서로 독립적으로 동작이 가능하도록 구축되어 있음


<br/><br/>
## 시노님Synonym
: 데이터베이스 객체에 동의어를 만드는 것. object에 대한 직접적인 참조.
* Alias와 유사 (alias는 일회성, synonym은 영구적)
* 다른 유저의 객체(테이블, 뷰, 프로시저, 함수, 패키지, 시퀀스 등)를 참조할 때 주로 사용
* 다른 유저의 이름, 객체의 실제이름을 보안
* PUBLIC 타입으로 구현할 경우 모든 사용자가 접근 가능, PRIVATE 타입은 특정 사용자만 참조 가능
* ex) DUAL은 SYS 계정이 소유하고 있는 테이블. public 권한으로 동의어를 생성하여 DUAL 이라는 Synonym을 사용하는 것 => SYS.DUAL


<br/><br/>
## 프로시저Procedure
: Transact-SQL 문장의 집합. 데이터베이스에 대한 어떤 일련의 작업을 정리한 절차를 관계형 데이터베이스 관리 시스템에 저장한 것 = 영구저장모듈Persistent Storage Module
* 어떠한 동작을 절차적 일괄처리 (특정 목적을 가지고 모인 순서대로 실행하는 명령어의 집합)
* 특정 구문을 반복해서 사용할 때 사용하는 PL/SQL BLOCK
* return 값을 반드시 반환하지 않아도 됨
* 일반적인 SQL문(select, insert, update, delete, merge 등)에서 사용 불가
* 사용 방법
    - SQL PLUS : EXEC 프로시저명;
    - PL/SQL : BEGIN 프로시저명 END;


<br/><br/>
## 함수Function
: RETURN문을 이용해 원하는 값을 반환하기 위해 만듦.
* 식의 일부로 사용됨
* return 값, 데이터 타입 필수


<br/><br/>
## 트리거Trigger



<br/><br/><br/><br/>
## MEMO


### DUAL 테이블
: 가상의 테이블. 값이 들어있지 않지만 테이블 역할을 할 수 있음. 주로 함수를 테스트하기 위해 select문을 사용할 때 사용.


<br/><br/>
### 오라클 버전 확인
> SELECT * FROM product_component_version;
> SELECT * FROM v$version;


<br/><br/>
### ESCAPE
: LIKE 연산으로 '%', '_'가 포함된 문자를 검색할 때 사용
 '%','_' 앞에 ESCAPE로 특수 문자를 지정
> SELECT * FROM 테이블이름 WHERE row이름 LIKE %A_% ESCAPE 'a';


<br/><br/>
### MERGE INTO 구문
: 테이블의 데이터(값)를 체크하고 이미 있으면 UPDATE, 없으면 INSERT
> MERGE INTO [TABLE/VIEW] &nbsp;&nbsp;-- update 또는 insert할 테이블 혹은 뷰 <br/>
> &nbsp;&nbsp; USING [TABLE/VIEW/DUAL] &nbsp;&nbsp;-- 비교할 대상 테이블 혹은 뷰  <br/>
> &nbsp;&nbsp; ON [조건] &nbsp;&nbsp;-- (조건이 일치하면 update, 불일치하면 insert)  <br/>
> &nbsp;&nbsp; WHEN MATCHED THEN  <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; UPDATE SET  <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; [COLUMN1] = [VALUE1], <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; [COLUMN2] = [VALUE2], <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; ... <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; (DELETE [TABLE] WHERE [COLUMN1] = [VALUE1] AND ...)  -- delete 구문도 사용 가능  <br/>
> &nbsp;&nbsp; WHEN NOT MATCHED THEN <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; INSERT (COLUMN1, COLUMN2, ...)  <br/>
> &nbsp;&nbsp;&nbsp;&nbsp; VALUES (VALUE1, VALUE2, ...)  <br/>
* Oracle 9i 버전 이상부터 지원 (10 ver 권장)
* ON 조건절의 컬럼은 update 불가능


<br/><br/>
### 중복 데이터 찾기
=> HAVING절로 COUNT가 1개 이상인 것을 가져오기
> SELECT column이름, COUNT(*) <br/>
> FROM 테이블이름  <br/>
> GROUP BY column이름 <br/>
> HAVING COUNT(*)>1 <br/>

> SELECT a  <br/>
> FROM (  <br/>
> &nbsp;&nbsp; SELECT column이름1, column이름2, COUNT(*) OVER(PARTITION BY column이름2) AS cnt  <br/>
> &nbsp;&nbsp; FROM 테이블이름 <br/>
> ) a <br/>
> WHERE a.cnt>1 <br/>

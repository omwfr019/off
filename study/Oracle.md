Oracle
========


<br/><br/><br/>
## View
: 가상 테이블
* 다수의 테이블에서 필요한 값들만 가져옴 (SELECT)
* 


<br/><br/>
## 시노님Synonym
: 데이터베이스 객체에 동의어를 만드는 것
* Alias와 유사
* 다른 유저의 객체(테이블, 뷰, 프로시저, 함수, 패키지, 시퀀스 등)를 참조할 때 주로 사용
* 다른 유저의 이름, 객체의 실제이름을 보안


<br/><br/>
## 프로시저Procedure


<br/><br/>
## 함수Function


<br/><br/>
## 트리거Trigger



<br/><br/><br/><br/>
## MEMO


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

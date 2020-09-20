SQL
====

<br/><br/><br/>
## 실행순서
: FROM  ->  ON  ->  JOIN  ->  WHERE ->  GROUP BY  ->  CUBE + ROLLUP -> HAVING ->  SELECT  ->  DISTINCT  ->  ORDER BY  -> TOP
(FROM ->  WHERE ->  GROUP BY  ->  HAVING  ->  SELECT  ->  ORDER BY)



<br/><br/><br/>
### 도메인Domain


<br/><br/>
### Bind Variable 바인드 변수
```
: 변수
```
: 최초 수행할 때 최적화를 거친 실행계획을 캐시에 적재 -> 실행시점에 그대로 가져와 값을 다르게 바인딩하며 반복 재사용.
SQL문 실행 => 문장에 대한 구문검사 및 문법적 오류 검사 -> 실행 계획을 세움
동일한 SQL문 반복 실행 => 이전 실행 문장인지 메모리에서 탐색 -> 찾을 경우 검사 과정 생략
하지만, 조건절 등을 상수로 처리할 시 같은 SQL 문장으로 인식 불가 ~> '바인드 변수'를 이용해 동일한 문장으로 인식.
* 처리 속도가 빠름
* 호스트 환경에서 생성
* PL/SQL 변수가 아님
* 컬럼 히스토그램 정보를 사용하지 못함

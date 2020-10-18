JDBC (Java DataBase Connectivity)
============


<br/><br/>
### JDBC [인터페이스]
: JVM의 시스템과 DB 시스템을 연결하고 통신하기 위한 JAVA의 표준 SQL 인터페이스 API.
자바 언어로 DB 프로그래밍을 하기 위해 사용되는 API.
SE에서 제공하는 java.sql 패키지.
> 자바 애플리케이션 ㅡ JDBC API ㅡ JDBC 드라이버 ㅡ 데이터베이스

<br/>

### JDBC 드라이버
: DBMS와 통신을 담당하는 자바 클래스.
java.sql의 인터페이스들을 상속하여 메소드의 몸체를 구현한 클래스 파일들.
  + DBMS 별 알맞은 JDBC 드라이버 필요 (jar).
> Class.forName("JDBC드라이버 이름"); <br/>
> ex) com.mysql.jdbc.Driver <br/>
> ex) oracle.jdbc.driver.OracleDriver <br/>
> ex) com.microsoft.sqlserver.jdbc.SQLServerDriver
<br/>

### JDBC URL
: DBMS와의 연결을 위한 식별 값
  + JDBC 드라이버에 따라 형식이 다름
> jdbc:[DBMS]:[데이터베이스식별자] <br/>
> ex) jdbc:mysql://HOST[:POST]/DBNAME[?param=value&param1=value2&...] <br/>
> ex) jdbc.oracle:thin@HOST:PORT:SID <br/>
> ex) jdbc:sqlserver://HOST[:PORT];databaseName=DB
<br/>

### JDBC-JAVA 연동

1. 웹 애플리케이션에 인식
  * 자바가 설치된 경로에 직접 JDBC 드라이버를 넣음 => WAS가 설치된 HOME의 lib 폴더
    + JDBC 드라이버 경로 : C:\app\오라클 설치한 사용자 계정\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar
    + 자바 경로 : C:\Program Files\Java\jre1.8.0_181\lib\ext
    + WAS에서 실행되는 모든 웹 애플리케이션에서 사용 가능
  
  * 이클립스와 JDBC 드라이버를 연결 => 각 웹 애플리케이션/WEB-INF-lib 폴더
    + 프로젝트 - Properties - Java Build Path - Libraries - Add External JARs
    + 해당 웹 애플리케이션에서만 사용 가능
<br/>

2. JDBC 드라이버 로딩
  > Class.forName("oracle.jdbc.driver.OracleDriver")
  + 메모리에 동적 로딩 ~> Class.forName(패키지이름+JDBC 드라이버 인터페이스를 상속하고 있는 클래스이름)
  + JDBC 드라이버 파일의 드라이버 인터페이스를 상속한 클래스가 동적으로 로딩될 때 자동으로 JDBC 드라이버 인스턴스가 생성되어
<br/>

3. DBMS 서버 접속
  > Connection conn = Driver Manager.getConnection("jdbc:oracle:thin(드라이버):@서버IP(도메인주소):포트번호:SID(식별자)","사용자명","비밀번호"); <br/>
  > // String url : 접속할 서버의 URL. 프로토콜+서버주소+서버포트+DB이름 <br/>
  > // String user : DB 서버에 로그인할 계정 <br/>
  > // String password : DB 서버에 로그인할 비밀번호 <br/>
  + java.sql 패키지의 DriverManager 클래스의 getConnection() 메서드 이용
  + 자바 프로그램과 데이터베이스를 네트워크 상에서 연결
  + 연결에 성공하면 DB와 연결된 상태를 Connection 객체로 표현하여 반환
<br/>

4. PreparedStatement & Statement
  > Statement stmt = conn.createStatement();
  + SQL문 및 결과를 전송하는 역할
  
  * 정적쿼리 : Statement
  * 동적쿼리 : PreparedStatement
<br/>
  
5. SQL문 실행
  > ResultSet rs = stmt.executeQuery("select * from table");
  + ResultSet executeQuery(String sql)
    - executeQuery() 메서드가 반환하는 ResultSet은 select한 결과값을 가지고 있음
    - SELECT
    - Cursor 메서드 : 내부적으로 외부를 나타냄. select의 결과값 추출.
      - void afterLast() : 커서를 끝 (빈 행)으로 이동
      - void beforeFirst() : 커서를 시작 (빈 행)으로 이동
      - boolean next() : 커서 다음 레코드가 있는지 판단하여 없으면 false, 있으면 true를 반환하고 커서를 다음 레코드로 이동
    - Getter 메서드 : 이동한 커서의 값을 데이터 타입에 따라 추출. ex) getString(int columnIndex), getInt(columnLabel)
  + int executeUpdate(String sql)
    - executeUpdate() 메서드가 반환하는 숫자값 = SQL문이 실행된 후 영향받은 레코드의 개수
    - UPDATE, DELETE, INSERT
<br/>

6. 자원해제
  > rs.close();
  > stmt.close(); & pstmt.close();
  > conn.close();
<br/>

#### Code

```java
// DBConnection.java

Connection conn = null;
try {
  String jdbcURL = "jdbc:oracle:thin:@localhost:1521:orcl";
  String dbUser = "scott";
  String dbPass = "tiger";
  System.out.println("DB 정상 연결");
  
  try {
    conn = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
    System.out.println("DB 계정 일치")
    
  } catch (SQLException e) {
    System.out.println("DB 계정 불일치");
    e.printStackTrace();
  }

} catch (ClassNotFoundException cnfe) {
  System.out.println("DB 드라이버 로딩 실패 : "+ cnfe.toString());
} catch (SQLException sqle) {
  System.out.println("DB 연결 실패");
} catch (Exception e) {
  System.out.println("Unkown error");
  e.printStackTrace();
} finally {
  if (conn != null) try { conn.close(); } catch(SQLException ex) {}
}

return conn;
```

<br/>
```java
PreparedStatement pstmt = null;
ResultSet rs = null;

try {  
  String sql = "insert into emp(num, name) values(?,?)";
  pstmt = conn.prepareStatement(sql);
  pstmt.setInt(1, 1234);
  pstmt.setString(2, "test");
  
  int resultCnt = pstmt.executeUpdate();
  System.out.println("성공 : " +  resultCnt);
  
  sql = "select * from emp where num<?";
  
  pstmt = con.prepareStatement(sql);
  pstmt.setInt(1, 5);
  rs = pstmt.executeQuery();
  
  while(rs.next()) {
    int a = rs.getInt("num");
    String b = rs.getString("name");
    System.out.println("num: "+num+", name: "+name);
  }
  
} catch (SQLException ex) {
  e.printStackTrace();
  System.out.println("실패");
} finally {
  if(rs != null) try { rs.close(); } catch (SQLException ex) {}
  if(stmt != null) try { pstmt.close(); } catch (SQLException ex) {}
}
```
  

***

<br/>
### 

TNS (Transparent Network Substrate)
====================================

오라클에서 사용하는 네트워크 기술. SQL * Net이 기반으로 하는 프로토콜.
Client-Server 또는 Server-Server 간 Data 전송을 도움.
DB 클라이언트는 먼저 리스너와 연결을 설정한 다음, DB 자체 경로가 재지정 될 때까지 대기.

오라클 독점 TNS 프로토콜은 Client-Server 통신에 이용. 기본적으로 포트 1521에서 수신 대기. 
일반적인 논리적 프로토콜이므로 TCP/IP와 같은 대부분의 물리적 네트워크 프로토콜 지원.

<br/>

### Client 설정 파일
- 접속하고자 하는 오라클 서버에 관한 설정 (프로토콜, 포트 번호, 서버 주소, 인스턴스 등)
- IP 주소 또는 컴퓨터 이름, DB 이름
- 들어오는 DB 요청을 정의. 프로세서에서 실행중인 모든 데이터베이스 이름(SID)을 포함.
  (새 DB가 추가되면 tnsnames.ora를 업데이트해야 함)
- ORACLE_HOME/network/admin/tnsnames.ora <br/>
  SID명=
    (DESCRIPTION=
      (ADDRESS=
        (PROTOCOL=TCP)
        (HOST=오라클서버IP주소)
        (PORT=포트번호)
      )
      (CONNECT_DATA=
        (SERVER=dedicated)
        (SERVICE_NAME=서비스명)
      )
    )

### Server 설정 파일
- 리스너의 이름과 주소 + Control Parameter 관련 정보 저장.
- protocol, IP adress(Hostname), port 번호, service_names(SID)
- ORACLE_HOME/network/admin/listener.ora <br/>
  LISTENER=
    (DESCRIPTION_LIST=
      (DESCRIPTION=
        (ADDRESS_LIST=
          (ADDRESS=
            (PROTOCOL=TCP)
            (HOST=호스트IP)
            (PORT=포트번호)
          )
        )
      )
      SID_LIST_LISTENER=
        (SID_LIST=
          (SID_NAME=SID_name)
          (ORACLE_HOME=오라클위치)
        )
      )

##### TNS Listener
- TNS 기술을 이용하는 SOL *NET이 사용하는 Listener.
- 클라이언트의 요청을 듣고, 통신 환경을 설정.
오라클 서버에 위치.

##### 패킷 헤더 구조
물리적 패킷에 투명하게 매핑 된 개별 논리적 패킷을 기반으로 함.


<br/>

### SQL *NET
 로컬 Oracle 데이터베이스 클라이언트와 관련 Oracle 데이터베이스 인스턴스 간 또는 두 개의 다른 Oracle 데이터베이스 인스턴스 간에 데이터베이스 링크가 있는 경우 Oracle이 제공하는 소프트웨어.


<br/><br/>

( 참고 : https://queryadvisor.com/queryadvisor.com/technology/tnsSubstrate.html )

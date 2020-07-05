아파치 메이븐 (Apache Maven)
=============================

자바용 프로젝트 관리 도구.
프로젝트의 전체적인 라이프 사이클 관리. <br/>



> 개발자 : 아파치 소프트웨어 재단 <br/>
저장소 : https://gitbox.apache.org/repos/asf?p=maven.git <br/>
프로그래밍 언어 : 자바 <br/>
운영 체제 : 크로스 플랫폼 <br/>
종류 : 빌드 도구 <br/>
라이선스 : 아파치 라이선스 2.0 (오픈 소스 소프트웨어) <br/>
웹사이트 : 	maven.apache.org <br/>

<br/>

### 특징
* 필요 라이브러리를 pom.xml에 정의 ~> 해당 라이브러리와 관련된 다른 라이브러리까지 자동 다운로드.

<br/>

### 빌드
소스코드 파일을 컴퓨터에서 실행할 수 있는 독립 소프트웨어 가공물로 변환하는 과정 또는 그에 대한 결과물.
=> 작성한 소스코드(java), 프로젝트에서 쓰인 각각의 파일 및 자원 등(.xml, .jpg, .jar, .properties)을 JVM이나 톰캣같은 WAS가 인식할 수 있는 구조로 패키징 하는 과정 및 결과물.

#### 빌드 도구build tool
프로젝트 생성, 테스트 빌드, 배포 등의 작업을 위한 전용 프로그램.
라이브러리 추가, 버전 동기화 등의 어려움을 해결.

#### Maven LifeCycle
미리 정해진 빌드 순서.
=> 메이븐은 프레임워크이기 때문에 동작 방식(빌드 순서)가 정해져 있음.
1. Default(Build) : 일반적인 빌드 프로세스를 위한 모델
2. Clean : 빌드 시 생성되었던 파일들을 삭제
3. Validate : 프로젝트가 올바른지 확인, 필요한 모든 정보를 사용할 수 있는지 체크
4. Compile : 프로젝트의 소스코드 컴파일
5. Test : 유닛(단위) 테스트를 수행하는 단계 (실패할 경우 빌드 실패 처리) (스킵 가능)
6. Package : 실제 컴파인된 소스 코드와 리소스들을 jar, war 등등의 파일 등의 배포를 위한 패키지로 만듦
6. Verify : 통합 테스트 결과에 대한 검사 실행, 품질 기준 충족 확인
7. Install : 패키지를 로컬 저장소에 설치
8. Site : 프로젝트 문서, 사이트 작성 및 생성
9. Deploy : 만들어진 Package를 원격 저장소에 release

최종 빌드 순서
1. compile : src/main/java 디렉토리 하위의 모든 소스 코드 컴파일
2. test : src/test/java, src/test/resources 테스트 자원 복사 및 테스트 소스 코드 컴파일
    ※junit : 단위 테스트 프레임워크. 테스트 단계를 거치기 위해 의존 설정을 해줌.
3. packaging : 컴파일과 테스트가 완료된 후 jar, war과 같은 형태로 압축.

Build Lifecycle 각각의 단계 = **Phase**
=> 의존 관계. 순차적으로 실행되어야 함 => 모든 빌드 단계는 이전 단계가 성공적으로 실행되었을 때 실행됨(=Dependency)

**Goal**
: 특정 작업, 최소한의 실행 단위(task) ~> 플러그인에서 실행할 수 있는 각각의 기능.
> 플러그인의 goal을 실행하는 방법
mvn groupId:artifacId:version:goal
mvn plugin:goal

<br/>

### 설정파일


참고 : https://goddaehee.tistory.com/199

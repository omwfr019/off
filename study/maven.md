Apache Maven (아파치 메이븐)
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

### Maven LifeCycle
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

#### 최종 빌드 순서
1. compile : src/main/java 디렉토리 하위의 모든 소스 코드 컴파일
2. test : src/test/java, src/test/resources 테스트 자원 복사 및 테스트 소스 코드 컴파일
    ※junit : 단위 테스트 프레임워크. 테스트 단계를 거치기 위해 의존 설정을 해줌.
3. packaging : 컴파일과 테스트가 완료된 후 jar, war과 같은 형태로 압축.

#### Phase
: Build Lifecycle 각각의 단계
=> 의존 관계. 순차적으로 실행되어야 함 => 모든 빌드 단계는 이전 단계가 성공적으로 실행되었을 때 실행됨(=Dependency)

#### Goal
: 특정 작업, 최소한의 실행 단위(task) ~> 플러그인에서 실행할 수 있는 각각의 기능.
> 플러그인의 goal을 실행하는 방법
mvn groupId:artifacId:version:goal
mvn plugin:goal

<br/>

### 설정파일
* settings.xml
 - 메이븐 빌드 툴과 관련된 설정 파일
 - MAVEN_HOME/conf 디렉토리에 위치
 - 메이븐을 빌드할 때 의존 관계에 있는 라이브러리, 플러그인을 중앙 저장소에서 개발자 PC로 다운로드 하는 위치(로컬저장소)의 기본 설정 : USER_HOME/.m2/repository  ~> settings.xml에서 변경 가능

* POM (Project Object Model, 프로젝트 객체 모델)
 - pom.xml 파일 => 메이븐을 이용하는 프로젝트의 root에 존재하는 xml 파일
   (하나의 자바 프로젝트에 빌드 툴을 maven으로 설정하면, 프로젝트 최상위 디렉토리에 'pom.xml' 파일 생성)
 - 프로젝트당 1개 존재 ~> 프로젝트의 설정, 의존성 등을 알 수 있음
 - ex)
    ``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion> <!--POM model의 버전-->
    <parent> <!--프로젝트의 계층 정보-->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.4.RELEASE</version>
        <relativePath/> <!--Lookup parent from repository-->
    </parent>
    <groupId>com.bis</groupId> <!--프로젝트를 생성하는 조직의 고유 아이디를 결정. 일반적으로 도메인 이름을 거꾸로 적음 -->
    <artifactId>r2r</artifactId> <!--프로젝트 빌드시 파일 대표이름. groupID 내에서 유일해야 함.
                                    Maven을 이용하여 빌드 시 다음과 같은 규칙으로 파일이 생성. artifactid-version.packaging.
                                    ex) r2r-0.0.1-SNAPSHOT.war 파일 생성-->
    <version>0.0.1-SNAPSHOT</version> <!--프로젝트의 현재 버전. 프로젝트 개발 중일 때는 SNAPSHOT을 접미사로 사용-->
    <packaging>war</packaging> <!--패키징 유형(jar, war, ear 등)-->
    <name>r2r</name> <!--프로젝트 이름 -->
    <description>Demo project for Spring Boot</description> <!--프로젝트에 대한 설명-->
    <url>http://~.com</url> <!--프로젝트에 대한 참고 Reference 사이트-->
        
    <properties>
        <java.version>1.8</java.version>
    </properties>
        
    <dependencies> <!--dependencies 태그 안에는 프로젝트와 의존 관계에 있는 라이브러리를 관리-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifacId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifacId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifacId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
    
    <build> <!--빌드에 사용할 플러그인 목록-->
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
        
</project>
```




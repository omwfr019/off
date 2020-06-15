이클립스(eclipse) 정리
=======================

<br/><br/>

### 단위
* 애플리케이션 개발 => project
* 프로젝트 및 설정 => workspace
(프로그램은 하나 이상의 프로젝트로 구성됨)
* 클래스 모음 => Package
  (명명규칙 : 프로젝트명.중간관리명.세부관리명)

<br/><br/>

### workspace
새로 만들기
  1. Eclipse Launcher에서 새로운 Workspace 이름(경로) 입력
  2. File > Switch Workspace > Other
Copy Settings
  - Workbench Layout : 현재 이클립스 화면 레이아웃 구성을 그대로 사용할 것인지
  - Working Sets : 이클립스 working set을 복사
  - Preferences : 기타 설정을 복사
Eclipse Launcher
  - Use this as the default and do not ask again : 이클립스 실행시 Eclipse Launcer를 띄우지 않음 (바로 실행)
  - Eclipse Launcher 다시 실행 : Windows > Preferences > General > Startup and Shutdown > Workspaces -> Prompt for workspace on startup 체크
Workspaces 전환
  - Window > Preferences > Startup and Shutdown > Workspaces

<br/><br/>

### Project Import/Export
##### export
: 프로젝트 마우스 우클릭 > Export > General > Archive File > 프로젝트 체크 > export 할 '경로\파일이름.zip' 지정 > Finish
##### import
: Package Explorer 마우스 우클릭 > Import > General > Existing Projects into Workspace > Select archive file > Finish

<br/><br/>

### New Java Project
* project name : 프로젝트 이름
* Use default location 체크 : 기존 workspace에 프로젝트를 만듦
* JRE
  - Use an execution environment JRE : 해당 이클립스의 JRE가 지원하는 버전 중 선택
  - Use a project specific JRE : 설치된 JRE나 JDK가 여러 개일 경우 특정 항목 선택
  - Use default JRE : Configure JREs... (Preferences > Java > Installed JREs에서 기본값으로 설정된 항목 선택
* Project layout : 루트 폴더 지정

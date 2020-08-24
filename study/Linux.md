Linux
=======
<br/>

### 규칙
* 대소문자 구분
* 파일이나 디렉터리 이름에 공백이 있을 경우 따옴표로 묶기
* 파일 이름 앞에 .을 붙이면 숨김 파일
<br/>


### 명령어
* 시스템 종료
  - poweroff, halt -p, init 0
  - shutdown -P now (-P, -p opion : 시스템 종료)  <br/>    
      + shutdown -P +10 => 10분 뒤에 종료 (P: poweroff)  <br/>
      + shutdown -r 22:00 => 오후 10시에 재부팅 (r: reboot)  <br/>
      + shutdown -c => 예약된 shutdown을 취소 (c: cancel) <br/>
      + shutdown -k +15 => 현재 접속한 사용자에게 15분 후에 종료된다는 메시지를 보내지만 실제로 종료는 안 됨  <br/>
* 시스템 재부팅
  - reboot, shutdown -r now, init 6
* 로그아웃
  : 현재 사용자의 시스템 접속을 끝냄. 시스템 종료 X
  - logout, exit
* 이전 명령 확인
  - ↑, ↓, history (삭제하려면 history -c)
* 자동완성
  - tab
* 현재 작업 중인 디렉터리 전체 경로 (Print Working Directory)
  - pwd
* 디렉터리 이동 (Change Directory)
  - cd => 현재 사용자의 홈 디렉터리로 이동. ex) /root
  - cd ~ubuntu => ubuntu 사용자의 홈 디렉터리로 이동
  - cd .. => 바로 상위의 디렉터리로 이동
  - cd /etc/systemd => /etc/systemd 디렉터리로 이동(절대 경로)
  - cd ../etc/systemd => 상대 경로로 이동. 현재 디렉터리의 상위로 이동한 후, 다시 /etc/systemd로 이동
* 파일 확인 (List)
  - ls => 현재 디렉터리의 파일 목록
  - ls /etc/systemd => 디렉터리의 목록
  - ls -a => 현재 디렉터리의 목록(숨김 파일 포함)
  - ls -l => 현재 디렉터리의 목록을 자세히 보여줌
  - ls * .conf => 확장자가 conf인 목록을 보여줌
  - ls -l /etc/systemd/b* => /etc/systemd 디렉터리에 있는 목록 중 앞 글자가 'b'인 것의 목록을 자세히 보여줌
* 파일 내용 출력 (conCATenate) ~> 파일의 내용을 화면에 보여줌. 여러 개 파일을 나열하면 파일을 연결하여 보여줌.
  - cat a.txt b.txt =>  a.txt와 b.txt를 연결해서 파일의 내용을 화면에 보여줌
* 디렉터리 복사 (CoPy) ~> 읽기 권한 필요. 복사한 파일은 복사한 사용자의 소유가 됨
  - cp abc.txt cba.txt => abc.txt를 cba.txt라는 이름으로 바꿔서 복사
  - cp -r abc cba => 디렉터리 복사. abc 디렉터리를 cba 디렉터리로 복사
* 디렉터리 생성 (MaKe DIRectory) ~> 해당 디렉터리는 명령을 실행한 사용자의 소유가 됨
  - mkdir abc => 현재 디렉터리 아래에/abc라는 디렉터리 생성
  - mkdir -p /def/fgh => /def/fgh 디렉터리를 생성하는데, 만약 부모 디렉터리 /def가 없다면 자동 생성 (p=Parents)
* 파일 생성
  - touch => 크기가 0인 새 파일을 생성하거나, 이미 파일이 존재한다면 파일의 최종 수정 시간 변경
* 파일/디렉터리 삭제 (ReMove) ~> 권한 필요
  - rm abc.txt => 해당 파일을 삭제 (내부적으로 'rm -f'로 연결됨)
  - rm -i abc.txt => 삭제 시 정말 삭제할 지 확인하는 메시지가 나옴
  - rm -f abc.txt => 삭제 시 확인하지 않고 바로 삭제함 (f는 Force의 약자)
  - rm -r abc => abc 디렉터리와 그 아래에 있는 하위 디렉터리를 강제로 전부 삭제. (r=Recursive)
* 디렉터리 삭제 (ReMove DIRectory) ~> 삭제 권한 필요, 디렉터리가 비어 있어야 함
  - rmdir abc => /abc 디렉터리를 삭제
  - 파일이 들어 있는 디렉터리 삭제는 'rm -r'
* 파일/디렉터리 이름 변경, 파일 이동 (MoVe)
  - mv abc.txt /etc/systemd/ => abc.txt를 /etc/systemd/ 디렉터리로 이동
  - mv aaa bbb ccc ddd => aaa, bbb, ccc 파일을 /ddd 디렉터리로 이동
  - mv abc.txt www.txt => abc.txt의 이름을 www.txt로 변경해서 이동
* 명령어 도움말 => man <명령어>
  - 위, 아래 이동 => ↑, ↓ 또는 J, K
  - 페이지 이동 => Page Up, Page Down 또는 Space Bar, B
  - 단어 검색 => /단어 (N을 누르면 다음 단어로, Q를 누르면 종료)
  - man [섹션 번호] 명령어 => man 명령어는 섹션을 1~9로 나눔. ex) man 1 ls

#### 마운트mount
: 물리적인 장치를 특정한 위치(대개 폴더)에 연결시켜 주는 과정.
하드디스크의 파티션, CD/DVD, USB 메모리 등을 사용하기 위해 지정한 위치에 연결
* mount => 현재 마운트된 장치들을 확인
* unmount /dev/cdrom => 기존 마운트 해제 (/dev/cdrom은 /dev/sr0에 링크된 파일)
<br/>


### vi editor
* 시작
  - vi [파일이름.형식] => __명령 모드__ 진입 (글자 입력 불가)
  
* __입력 모드__ 진입 (글자 입력 가능)
  - i => 현재 커서의 위치부터 입력
  - I => 현재 커서 줄의 맨 앞에서부터 입력
  - a => 현재 커서의 다음 칸부터 입력
  - A => 현재 커서 줄의 맨 마지막부터 입력
  - o => 현재 커서의 다음 줄에 입력
  - O => 현재 커서의 이전 줄에 입력
  - s => 현재 커서 위치의 한 글자를 지우고 입력
  - S => 현재 커서의 한 줄을 지우고 입력
* 입력 모드 탈출
  - Esc(button) => 명령 모드 진입
  
* 명령 모드에서 커서 이동
  - h => 커서를 왼쪽으로 한 칸 이동
  - l => 커서를 오른쪽으로 한 칸 이동
  - i => 커서를 아래로 한 칸 이동
  - k => 커서를 위로 한 칸 이동
  - Ctrl + f => 다음 화면으로 이동 (Page Down)
  - Ctrl + b => 이전 화면으로 이동 (Page Up)
  - ^ => 현재 행의 처음으로 이동 (Home)
  - $ => 현재 행의 마지막으로 이동 (End)
  - gg => 제일 첫 행으로 이동
  - G => 제일 끝 행으로 이동
  - 숫자G => 해당 숫자의 행으로 이동
  - :숫자Enter => 해당 숫자의 행으로 이동
  
* 명령 모드에서 삭제, 복사, 붙여넣기
  - x => 현재 커서가 위치한 글자 삭제 (Del)
  - X => 현재 커서가 위치한 앞 글자 삭제 (BackSpace)
  - dd => 현재 커서의 행 삭제
  - 숫자dd => 형태 커서부터 숫자만큼의 행 삭제
  - yy => 현재 커서가 있는 행을 복사
  - 숫자yy => 현재 커서부터 숫자만큼의 행을 복사
  - p => 복사한 내용을 현재 행 이후에 붙여넣기
  - P => 복사한 내용을 현재 행 이전에 붙여넣기
  
* 명령 모드에서 문자열 찾기
  - /문자열Enter => 해당 문자열을 찾음 (현재 커서 이후로)
  - n => 찾은 문자 중에서 다음 문자로 이동

* ex 모드(라인 명령 모드) 기능
  - :%s/기존문자열/새문자열 => 문자열 치환
  - :set number => vi 에디터 앞에 행 번호 표시

* 종료
  - : => __ex 모드 (라인 명령 모드)__ 진입
  - :q => 종료 
  - :w => 저장
  - :q! => 저장하지 않고 강제 종료

```


com.douzone.jblog.controller
-MainController [o]	[]
-UserController	[o]	[]
-BlogController	[o]	[]


---

jsp 공통부분 include 처리 (import) []

---

ex) 
@Pathvariable
BlogController는 무조권 id가 붙어야함. 무조권.  []

/{id}	 - 내 블로그 눌렀을 때 
/dooly
/kickscar..

/{id}/{categoryNo} - 카테고리 항목을 눌렀을 때, 
/kickscar/1

/{id}/{categoryNo}/{postNo} - 글 하단의 내용을 눌렀을 때, 
/kickscar/1/10

위의 3개의 화면이 다 똑같게 만들자 일단.  []

---

내블로그에서 블로그 관리, 누르면 /{id}/admin/basic으로 들어가야함.  []
auth 유저 사용. 
블로그 제목, 로고이미지 변경 []
카테고리에서는 삭제 리스트 만들고, 삭제(추가) 후 리다이렉트 시키기 []

글작성 부분에서, 카테고리를 꼭 넣어야함. (select-option) []
포스트에 성공하면, 글이 보이는 곳으로 이동 []





````
jblog db 사용자 생성

create database jblog

create user 'jblog'@'localhost' identified by 'jblog';
create user 'jblog'@'127.0.0.1' identified by 'jblog';
create user 'jblog'@'192.168.%' identified by 'jblog';

grant all privileges on jblog.* to 'jblog'@'localhost';
grant all privileges on jblog.* to 'jblog'@'127.0.0.1';
grant all privileges on jblog.* to 'jblog'@'192.168.%';


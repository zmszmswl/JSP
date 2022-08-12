drop table reply;
create table reply (
	no number primary key,
	writer varchar2(20),
	wdate date,
	content varchar2(200),
	notice_id number
)

insert into reply values(1, 'park', sysdate, '댓글1', 1);
insert into reply values(2, 'kim', sysdate, '댓글2', 2);
insert into reply values(3, 'lee', sysdate, '댓글3', 3);
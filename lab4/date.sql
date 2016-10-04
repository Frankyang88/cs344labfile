
drop table calender;

create table calender(when date);

insert into calender values (TO_DATE('01-05-1989, 11:30:35 A.M.','dd-mm-yyyy, HH:MI:SS  A.M.'));
insert into calender values (TO_DATE('02-06-1999, 1:20:35 A.M.','dd-mm-yyyy, HH:MI:SS  A.M.'));
insert into calender values (TO_DATE('23-9-1929, 11:50:35 P.M.','dd-mm-yyyy, HH:MI:SS  P.M.'));
insert into calender values (TO_DATE('14-12-1969, 1:40:35 P.M.','dd-mm-yyyy, HH:MI:SS  P.M.'));

select TO_CHAR(when, 'dd-mm-yyyy HH24:MI:SS') from calender;

select TO_CHAR(when, 'dd-Mon-yyyy, HH24:MI') from calender;
select TO_CHAR(when, 'dd/mm/yyyy, HH24:MI') from calender;
select TO_CHAR(when, 'mm/dd/yyyy HH24:MI:SS') from calender;


describe employee;

select * from employee where rownum <=5;

select dependent_name from dependent where relationship='Spouse' OR relationship= 'Daughter';

select * from project where plocation is null;

select fname,lname,dname from employee, department where employee.ssn=department.mgrssn;

select DISTINCT fname from employee,works_on where employee.ssn=works_on.essn and hours<18;

select fname,lname from employee where lname like 'W%';

select sname from salespeople where city in ('Barcelona','San Jose');

select cname,amt from customers,orders where customers.cnum=orders.cnum and amt between 1500 and 5000;

select count(*) from orders;

select AVG(AMT) from orders;

select orders.snum,MAX(amt) from orders,salespeople where orders.snum=salespeople.snum group by orders.snum,sname order by orders.snum asc;

select orders.snum,MAX(amt) from orders,salespeople where orders.snum=salespeople.snum and amt>3000 group by orders.snum,sname order by orders.snum asc;

select fname,lname,salary from employee order by salary asc;

select onum,cname,orders.cnum,salespeople.snum from orders,salespeople,customers where orders.cnum=customers.cnum and customers.city <> salespeople.city and orders.snum=salespeople.snum order by onum asc;

select cname,amt from orders,customers where amt=(select MAX(AMT) FROM orders) and customers.cnum=orders.cnum;

select * from orders where amt> (select AVG(amt) from orders where odate=to_date('03-10-1990','dd-mm-yy'));

select * from orders where orders.snum in (select salespeople.snum from salespeople where salespeople.city='London');

SELECT DISTINCT outer.snum,sname FROM customers outer,salespeople WHERE EXISTS (SELECT *  FROM customers inner  WHERE inner.snum = outer.snum AND inner.cnum != outer.cnum) AND outer.snum=salespeople.snum;

SELECT * FROM orders outer WHERE outer.amt > (SELECT AVG(inner.amt)  FROM orders inner where outer.cnum=inner.cnum group by inner.snum);

select distinct fname,lname from employee e where not exists ( select * from dependent d where d.essn=e.ssn);

select distinct p.pnumber from project p,employee e,works_on w where p.pnumber=w.pno and w.essn=e.ssn and e.lname='Smith' union (select distinct p.pnumber from project p,employee e,department d where d.mgrssn=e.ssn and d.dnumber=p.dnum  and e.lname='Smith');


update employee set salary=salary*1.1 where lname <>'Borg';

create table hou_emp as (select * from employee where address like '%Houston%');

create table emp_dep as (select e.fname,e.lname,d.dependent_name,d.sex,d.bdate from employee e,dependent d where d.essn=e.ssn);



select * from quest where qname like '%Dragon%';

select * from hero where bdate=TO_DATE('04-09-13','DD-MM-YY');

SELECT difficulty,COUNT(qname)
FROM quest 
GROUP BY difficulty Having COUNT(qname) > 1;

SELECT DISTINCT * FROM area outer where  outer.asize > (select AVG(inner.asize) from area inner); 

SELECT DISTINCT pid,hname FROM hero e WHERE EXISTS
      (SELECT * 
            FROM backpack b 
            WHERE b.hpid=e.pid);

select COUNT(pid) from hero;


delete from consumable where hpid=1;

select hero.hname,embarks_on.qname,weapon.wname from weapon,hero,embarks_on  where hero.pid=embarks_on.hpid and weapon.qname=embarks_on.qname;


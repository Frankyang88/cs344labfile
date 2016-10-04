DROP TABLE lab2a;
CREATE TABLE lab2a
(i int,
 r number(6,2),
 txt varchar2(20),
 when date);
INSERT INTO lab2a VALUES
   (1, 1.1, 'String 1',
    TO_DATE('01-May-2001', 'dd-mon-yyyy') );
INSERT INTO lab2a VALUES
   (2, 1.1, 'String 2',
    TO_DATE('02-jun-2001', 'dd-mon-yyyy') );
INSERT INTO lab2a VALUES
   (5, 5.5, 'Long String 5',
    TO_DATE('25-Dec-2001', 'dd-mon-yyyy') );
INSERT INTO lab2a VALUES
   (4, 4.4, 'Short String 4',
    TO_DATE('11-Nov-2005', 'dd-mon-yyyy') );
INSERT INTO lab2a VALUES
   (3, 3.3, 'Nothing',
    TO_DATE('01-jan-2000', 'dd-mon-yyyy') );
COMMIT;

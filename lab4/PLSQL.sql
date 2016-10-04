set SERVEROUTPUT on;
CREATE OR REPLACE PROCEDURE GETINFO 
AS
  CURSOR ec IS
	SELECT * FROM dependent;
   emp ec%ROWTYPE;
BEGIN
FOR emp IN ec LOOP
DBMS_OUTPUT.PUT_LINE (emp.dependent_name || '   ' || emp.relationship);
END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE GETE (aa IN NUMBER)
AS
  CURSOR ec IS
	SELECT * FROM works_on where pno = aa;
   emp ec%ROWTYPE;
   ex EXCEPTION;
BEGIN
FOR emp IN ec LOOP
DBMS_OUTPUT.PUT_LINE(emp.essn || '   ' || emp.hours);
IF emp.hours < 8 THEN
	RAISE ex;
END IF;
END LOOP;
EXCEPTION 
	WHEN NO_DATA_FOUND THEN NULL;
	WHEN ex THEN 
	DBMS_OUTPUT.PUT_LINE( 'lazy worker found!');

END;
/


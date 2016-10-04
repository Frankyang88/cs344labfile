CREATE OR REPLACE TRIGGER modify_salary
AFTER INSERT OR UPDATE OR DELETE OF salary ON e1
FOR EACH ROW
BEGIN
    IF INSERTING THEN
      UPDATE d1
      SET tot_sal = tot_sal + :NEW.salary
      WHERE dnumber = :NEW.dno;
    ELSIF UPDATING THEN
      UPDATE d1
      SET tot_sal = tot_sal + :NEW.salary - :OLD.salary
      WHERE dnumber = :OLD.dno;
    ELSIF deleting then
      UPDATE d1
      SET tot_sal = tot_sal - :OLD.salary
      WHERE dnumber = :OLD.dno;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER depart_change
AFTER UPDATE OF dno ON e1
FOR EACH ROW
BEGIN  
      UPDATE d1
      SET tot_sal = tot_sal -:OLD.salary
      WHERE dnumber = :OLD.dno;

      UPDATE D1
      SET tot_sal= tot_sal+:OLD.salary
      where dnumber=:NEW.dno;
END;
/






CREATE OR REPLACE TRIGGER CHECK_HERO_EXP
BEFORE INSERT OR UPDATE OF exp ON hero
FOR EACH ROW
DECLARE 
	neg_exp EXCEPTION;
BEGIN

	IF :NEW.exp < 0 THEN
		RAISE neg_exp;
	END IF;

EXCEPTION 
	WHEN neg_exp THEN
	RAISE_APPLICATION_ERROR(-20001,'Cannot set hero experience negative numnber');
END;
/

CREATE OR REPLACE TRIGGER CHECK_MON_LEVEL
BEFORE INSERT OR UPDATE OF mlevel ON monster
FOR EACH ROW
DECLARE 
	neg_level EXCEPTION;
BEGIN
	IF :NEW.mlevel < 0 THEN
		RAISE neg_level;
	END IF;
EXCEPTION 
	WHEN neg_level THEN
	RAISE_APPLICATION_ERROR(-20001,'Cannot set monster level negative numnber');
END;
/


CREATE OR REPLACE TRIGGER modify_herolevel
AFTER INSERT OR UPDATE OR DELETE OF exp ON hero
FOR EACH ROW
BEGIN
    IF INSERTING THEN     	 

	INSERT INTO HLEVEL VALUES(:NEW.exp,  CAST (log(2,:NEW.exp) AS INT));
	 

    ELSIF UPDATING THEN
      DELETE FROM HLEVEL WHERE hexp = :OLD.exp; 
      INSERT INTO HLEVEL VALUES(:NEW.exp, CAST (log(2,:NEW.exp) AS INT));
    ELSIF deleting then
      DELETE FROM HLEVEL WHERE hexp = :OLD.exp;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER modify_monsterhp
AFTER INSERT OR UPDATE OR DELETE OF mlevel ON monster
FOR EACH ROW
BEGIN
    IF INSERTING THEN
      INSERT INTO MHP VALUES(:NEW.mlevel,  CAST( (100+1.2*:NEW.mlevel) AS INT));
    ELSIF UPDATING THEN
      DELETE FROM MHP WHERE mlevel = :OLD.mlevel; 
      INSERT INTO MHP VALUES(:NEW.mlevel, CAST ( (100+1.2*:NEW.mlevel) AS INT));
    ELSIF deleting then
      DELETE FROM MHP   WHERE mlevel = :OLD.mlevel;
    END IF;
END;
/


CREATE OR REPLACE TRIGGER modify_weaponvalue
AFTER INSERT OR UPDATE OR DELETE OF  rarity, pdamage, mdamage ON weapon
FOR EACH ROW
BEGIN
    IF INSERTING THEN
      if :NEW.rarity='Unique' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+100) AS INT));
      elsif :NEW.rarity = 'Common'then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+10) AS INT));
      elsif :NEW.rarity = 'Rare' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+50) AS INT));
      elsif :NEW.rarity = 'Magic' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+20) AS INT));
      end if;	
    ELSIF UPDATING THEN	
      DELETE FROM WVALUE WHERE  rarity=:OLD.rarity and damageM=:OLD.mdamage and damageP=:OLD.pdamage;
      if :NEW.rarity='Unique' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+100) AS INT));
      elsif :NEW.rarity = 'Common'then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+10) AS INT));
      elsif :NEW.rarity = 'Rare' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+50) AS INT));
      elsif :NEW.rarity = 'Magic' then
      INSERT INTO WVALUE VALUES(:NEW.rarity,:NEW.mdamage,:NEW.pdamage,CAST( (:NEW.mdamage * 1.1+:NEW.pdamage*1.2+20) AS INT));
      end if;	
    ELSIF deleting then
      DELETE FROM WVALUE WHERE  rarity=:OLD.rarity and damageM=:OLD.mdamage and damageP=:OLD.pdamage;
    END IF;
END;
/






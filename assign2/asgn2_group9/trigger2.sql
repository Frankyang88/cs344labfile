
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





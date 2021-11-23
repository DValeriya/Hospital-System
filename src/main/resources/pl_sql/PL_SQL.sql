CREATE OR REPLACE NONEDITIONABLE PROCEDURE Create_User(
	USER_NAME VARCHAR2,
	PASSWORD VARCHAR2,
	PHONENUMBER VARCHAR2,
	BIRTHDATE DATE,
	EMAIL VARCHAR2,
	ROLE NUMBER) AS
	USER_ID NUMBER;
	USER_LOGIN NUMBER;
	BEGIN			
		SELECT OBJECT_ID_SEQ.NEXTVAL INTO USER_ID FROM DUAL;
		SELECT USER_LOGIN_SEQ.NEXTVAL INTO USER_LOGIN FROM DUAL;
		INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME)
			VALUES(USER_ID, NULL, 1, USER_NAME);
		INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
			VALUES(1, USER_ID, USER_LOGIN);
		INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
			VALUES(2, USER_ID, PASSWORD);
		INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
			VALUES(4, USER_ID, PHONENUMBER);
		INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
			VALUES(7, USER_ID, ROLE);
		IF BIRTHDATE IS NOT NULL
			THEN
				INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
					VALUES(5, USER_ID, BIRTHDATE);
		END IF;
		IF EMAIL IS NOT NULL AND LENGTH(EMAIL) > 0
			THEN
				INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
					VALUES(6, USER_ID, EMAIL);
		END IF;
		COMMIT;
	END;

CREATE OR REPLACE NONEDITIONABLE PROCEDURE Edit_User(
    USER_ID NUMBER,
    USER_NAME VARCHAR2,
    PASSWORD VARCHAR2,
    PHONENUMBER VARCHAR2,
    BIRTHDATE DATE,
    EMAIL VARCHAR2) AS
BEGIN
    UPDATE OBJECTS O SET O.NAME = USER_NAME WHERE OBJECT_ID = USER_ID;
    UPDATE ATTRIBUTES A SET A.VALUE = PASSWORD
        WHERE OBJECT_ID = USER_ID AND A.ATTR_ID = 2;
    UPDATE ATTRIBUTES A SET A.VALUE = PHONENUMBER
        WHERE OBJECT_ID = USER_ID AND A.ATTR_ID = 4;
    IF BIRTHDATE IS NOT NULL
       THEN
            UPDATE ATTRIBUTES A SET A.DATE_VALUE = BIRTHDATE
                WHERE A.OBJECT_ID = USER_ID AND A.ATTR_ID = 5;
    END IF;
    IF EMAIL IS NOT NULL
       THEN
            UPDATE ATTRIBUTES A SET A.VALUE = EMAIL
                WHERE A.OBJECT_ID = USER_ID AND A.ATTR_ID = 6;
    END IF;
    COMMIT;
END;
	
CREATE OR REPLACE NONEDITIONABLE PROCEDURE Create_DoctorSpec(
	TITLE VARCHAR2) AS
	SPEC_ID NUMBER;
	BEGIN			
		SELECT OBJECT_ID_SEQ.NEXTVAL INTO SPEC_ID FROM DUAL;
		INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME)
			VALUES(SPEC_ID, NULL, 6, TITLE);
		COMMIT;
	END;

CREATE OR REPLACE PROCEDURE EDIT_EMPLOYER_DATA (
    STATUS NUMBER,
    START_WORKING_TIME VARCHAR2,
    END_WORKING_TIME VARCHAR2,
    EMPLOYER_ID NUMBER) AS BEGIN
    UPDATE ATTRIBUTES STATUS SET STATUS.LIST_VALUE_ID = STATUS
        WHERE STATUS.ATTR_ID = 10 AND STATUS.OBJECT_ID = EMPLOYER_ID;
    UPDATE ATTRIBUTES START_WORKING_TIME SET START_WORKING_TIME.VALUE = START_WORKING_TIME
        WHERE START_WORKING_TIME.ATTR_ID = 11 AND START_WORKING_TIME.OBJECT_ID = EMPLOYER_ID;
    UPDATE ATTRIBUTES END_WORKING_TIME SET END_WORKING_TIME.VALUE = END_WORKING_TIME
        WHERE END_WORKING_TIME.ATTR_ID = 12 AND END_WORKING_TIME.OBJECT_ID = EMPLOYER_ID;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE CREATE_EMPLOYER_DATA(
    HIRING_DATE VARCHAR2,
    STATUS NUMBER,
    START_WORKING_TIME VARCHAR2,
    END_WORKING_TIME VARCHAR2,
    EMP_PARENT_ID NUMBER) AS
    EMP_DATA_ID NUMBER;
    BEGIN
        SELECT OBJECT_ID_SEQUENCE.NEXTVAL INTO EMP_DATA_ID FROM DUAL;
        INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES(EMP_DATA_ID, EMP_PARENT_ID, 2, EMP_DATA_ID, NULL);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, DATE_VALUE)
            VALUES (9, EMP_DATA_ID, HIRING_DATE);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
            VALUES (10, EMP_DATA_ID, STATUS);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (11, EMP_DATA_ID, START_WORKING_TIME);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (12, EMP_DATA_ID, END_WORKING_TIME);
    COMMIT;
END;

CREATE OR REPLACE NONEDITIONABLE PROCEDURE Create_Work_Day(
 EMPLOYER_ID NUMBER,
 WORK_DATE DATE) AS WORK_DAY_ID NUMBER;
 BEGIN
  SELECT OBJECT_ID_SEQ.NEXTVAL INTO WORK_DAY_ID FROM DUAL;
  INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME)
   VALUES(WORK_DAY_ID, NULL, 3, WORK_DAY_ID);
  INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
   VALUES(14, WORK_DAY_ID, EMPLOYER_ID);
  INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
   VALUES(15, WORK_DAY_ID, WORK_DATE);
  COMMIT;
 END;

CREATE OR REPLACE PROCEDURE DELETE_WORK_DAY(WORK_DAY_ID NUMBER) AS 
 BEGIN
    DELETE FROM ATTRIBUTES WHERE OBJECT_ID = WORK_DAY_ID;
    DELETE FROM OBJECTS WHERE OBJECT_ID = WORK_DAY_ID;
    COMMIT;
 END;

CREATE OR REPLACE PROCEDURE UPDATE_GENINFO(GEN_OBJECT_ID NUMBER, GEN_ADDRESS VARCHAR2, GEN_PHONE VARCHAR2, GEN_WORKING_START VARCHAR2, GEN_WORKING_END VARCHAR2)
    IS
    BEGIN
        UPDATE ATTRIBUTES ADDRESS SET ADDRESS.VALUE = GEN_ADDRESS
            WHERE ADDRESS.ATTR_ID = 30
            AND ADDRESS.OBJECT_ID = GEN_OBJECT_ID;
        UPDATE ATTRIBUTES PHONE SET PHONE.VALUE = GEN_PHONE
            WHERE PHONE.ATTR_ID = 31
            AND PHONE.OBJECT_ID = GEN_OBJECT_ID;
        UPDATE ATTRIBUTES WORKING_START SET WORKING_START.VALUE = GEN_WORKING_START
            WHERE WORKING_START.ATTR_ID = 32
            AND WORKING_START.OBJECT_ID = GEN_OBJECT_ID;
        UPDATE ATTRIBUTES WORKING_END SET WORKING_END.VALUE = GEN_WORKING_END
            WHERE WORKING_END.ATTR_ID = 33
            AND WORKING_END.OBJECT_ID = GEN_OBJECT_ID;
        COMMIT;
    END;

CREATE OR REPLACE PROCEDURE CREATE_GENINFO (GEN_ADDRESS VARCHAR2, GEN_PHONE VARCHAR2, GEN_WORKING_START VARCHAR2, GEN_WORKING_END VARCHAR2)
    IS
    GEN_OBJECT_ID NUMBER;
    BEGIN
        SELECT OBJECT_ID_SEQ.NEXTVAL INTO GEN_OBJECT_ID FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (GEN_OBJECT_ID, NULL, 7, GEN_OBJECT_ID, NULL);

        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (30, GEN_OBJECT_ID, GEN_ADDRESS);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (31, GEN_OBJECT_ID, GEN_PHONE);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (32, GEN_OBJECT_ID, GEN_WORKING_START);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (33, GEN_OBJECT_ID, GEN_WORKING_END);
        COMMIT;
    END;

CREATE OR REPLACE PROCEDURE CREATE_DOCTORDATA (APPOINTMENT_DURATION VARCHAR2, PARENT NUMBER, SPECIALIZATION NUMBER)
    IS
    DOCTORDATA_OBJECT_ID NUMBER;
    BEGIN
        SELECT OBJECT_ID_SEQ.NEXTVAL INTO DOCTORDATA_OBJECT_ID FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, DOCTORDATA_OBJECT_ID, DESCRIPTION)
            VALUES (DOCTORDATA_OBJECT_ID, PARENT, 4, DOCTORDATA_OBJECT_ID, NULL);
        INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE)
            VALUES (18, DOCTORDATA_OBJECT_ID, APPOINTMENT_DURATION);
        INSERT INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID)
            VALUES (17, SPECIALIZATION, DOCTORDATA_OBJECT_ID);
        COMMIT;
    END;

create or replace PROCEDURE UPDATE_DOCTORDATA(DOCTORDATA_OBJECT_ID NUMBER, DOCTORDATA_APPOINTMENTDURATION VARCHAR2, SPECIALIZATION NUMBER)
    IS
    BEGIN
        UPDATE ATTRIBUTES SET VALUE = DOCTORDATA_APPOINTMENTDURATION
            WHERE ATTR_ID = 18 AND OBJECT_ID = DOCTORDATA_OBJECT_ID;
        UPDATE OBJREFERENCE SET REFERENCE = SPECIALIZATION
            WHERE ATTR_ID = 17 AND OBJECT_ID = DOCTORDATA_OBJECT_ID;
        COMMIT;
    END;

CREATE OR REPLACE NONEDITIONABLE PROCEDURE Create_Appointment(
    EXPSTART VARCHAR2,
    EXPEND VARCHAR2,
    ACTSTART VARCHAR2,
    ACTEND Varchar2,
    DOCTORID NUMBER,
    PATIENTID NUMBER := null,
    DIAGNOSIS VARCHAR2,
    REFFERAL VARCHAR2,
    NXTAPP Number,
    STATUS VARCHAR2
) AS
    APP_ID NUMBER;
BEGIN
    SELECT OBJECT_ID_SEQ.nextval INTO APP_ID FROM DUAL;
    INSERT INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME)
    VALUES(APP_ID, NULL, 5, CONCAT('appointment',  TO_CHAR(APP_ID)));
    INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
    VALUES(19, APP_ID, null, TO_DATE(EXPSTART, 'DD/MM/YYYY HH24:Mi"'), null);
    INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
    VALUES(20, APP_ID, null, TO_DATE(EXPEND, 'DD/MM/YYYY HH24:Mi"'), null);
    IF ACTSTART IS NOT NULL THEN
        INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(21, APP_ID, null, ACTSTART, null);
    end if;
    IF ACTEND IS NOT NULL THEN
        INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(22, APP_ID, null, ACTEND, null);
    end if;
    INSERT INTO OBJREFERENCE(ATTR_ID, REFERENCE, OBJECT_ID)
    VALUES(23, APP_ID, DOCTORID);
    IF PATIENTID IS NOT NULL THEN
        INSERT INTO OBJREFERENCE(ATTR_ID, REFERENCE, OBJECT_ID)
        VALUES(24, APP_ID, PATIENTID);
    END IF;
    IF DIAGNOSIS IS NOT NULL THEN
        INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(25, APP_ID, DIAGNOSIS, null, null);
    end if;
    IF REFFERAL IS NOT NULL THEN
        INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(26, APP_ID, REFFERAL, null, null);
    end if;
    IF NXTAPP IS NOT NULL THEN
        INSERT INTO OBJREFERENCE(ATTR_ID, REFERENCE, OBJECT_ID)
        VALUES(27, APP_ID, NXTAPP);
    end if;
    INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
    VALUES(28, APP_ID, null, null, (Select LISTS.LIST_VALUE_ID FROM LISTS WHERE LISTS.LIST_VALUE_ID = STATUS));
    COMMIT;
END;


CREATE OR REPLACE NONEDITIONABLE PROCEDURE Update_Appointment(
    ID VARCHAR2,
    EXPSTART VARCHAR2 := null,
    EXPEND VARCHAR2 := null,
    ACTSTART VARCHAR2 := null,
    ACTEND VARCHAR2 := null,
    DOCTORID NUMBER := null,
    PATIENTID NUMBER := null,
    DIAGNOSIS VARCHAR2 := null,
    REFFERAL VARCHAR2 := null,
    NXTAPP Number := null,
    STATUS VARCHAR2 := null
) AS BEGIN
    IF EXPSTART IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 19 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = EXPSTART
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, date_value) VALUES ( 19, ID, EXPSTART );
    end if;
    IF EXPEND IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 20 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = EXPEND
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, date_value) VALUES ( 20, ID, EXPEND );
    end if;
    IF ACTSTART IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 21 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = ACTSTART
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, date_value) VALUES ( 21, ID, ACTSTART );
    end if;
    IF ACTEND IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 22 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = ACTEND
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, date_value) VALUES ( 22, ID, ACTEND );
    end if;
    IF DOCTORID IS NOT NULL THEN
        MERGE INTO OBJREFERENCE
        USING DUAL ON (REFERENCE = ID AND ATTR_ID = 23)
        WHEN NOT MATCHED THEN INSERT (attr_id, reference, object_id) VALUES ( 24, ID, DOCTORID )
        WHEN MATCHED THEN UPDATE SET OBJECT_ID = DOCTORID;
    END IF;
    IF PATIENTID IS NOT NULL THEN
        MERGE INTO OBJREFERENCE
        USING DUAL ON (REFERENCE = ID AND ATTR_ID = 24)
        WHEN NOT MATCHED THEN INSERT (attr_id, reference, object_id) VALUES ( 24, ID, PATIENTID )
        WHEN MATCHED THEN UPDATE SET OBJECT_ID = PATIENTID;
    END IF;
    IF DIAGNOSIS IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 25 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = DIAGNOSIS
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, value) VALUES ( 25, ID, DIAGNOSIS );
    end if;
    IF REFFERAL IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTRIBUTES.ATTR_ID = 26 AND ATTRIBUTES.OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET ATTRIBUTES.VALUE = REFFERAL
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, value) VALUES ( 26, ID, REFFERAL );
    end if;
    IF NXTAPP IS NOT NULL THEN
        MERGE INTO OBJREFERENCE
        USING DUAL ON (REFERENCE = ID AND ATTR_ID = 27)
        WHEN NOT MATCHED THEN INSERT (attr_id, reference, object_id) VALUES ( 27, ID, NXTAPP )
        WHEN MATCHED THEN UPDATE SET OBJECT_ID = NXTAPP;
    END IF;
    IF STATUS IS NOT NULL THEN
        MERGE INTO ATTRIBUTES
        USING DUAL ON (ATTR_ID = 28 and OBJECT_ID = ID)
        WHEN MATCHED THEN UPDATE SET LIST_VALUE_ID = STATUS
        WHEN NOT MATCHED THEN INSERT (attr_id, object_id, list_value_id) VALUES ( 28, ID, STATUS );
    end if;
    commit ;
END;
drop user c##product;
--계정생성
CREATE USER c##springboot IDENTIFIED BY springboot1234
    DEFAULT TABLESPACE users
    TEMPORARY TABLESPACE temp
    PROFILE DEFAULT;
--권한부여
GRANT CONNECT, RESOURCE TO c##product;
GRANT CREATE VIEW, CREATE SYNONYM TO c##product;
GRANT UNLIMITED TABLESPACE TO c##product;
--락 풀기
ALTER USER c##product ACCOUNT UNLOCK;
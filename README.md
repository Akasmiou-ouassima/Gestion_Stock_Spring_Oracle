### To Add a new user to Oracle Data base

```oraclesqlplus
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER user IDENTIFIED BY password

GRANT ALL PRIVILEGES TO user;
```

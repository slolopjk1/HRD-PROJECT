package com.example.usertop.misproject;

public class ContactDBCtrct {
    private ContactDBCtrct(){};

    public static final String TBL_CONTACT = "CONTACT_T";
    public static final String enumber = "enumber";
    public static final String password = "password";
    public static final String ssn = "ssn";
    public static final String name = "name";
    public static final String authority = "authority";
    public static final String status = "status";
    public static final String approval = "approval";
    public static final String position = "position";

    // CREATE TABLE IF NOT EXISTS CONTACT_T (NO INTEGER NOT NULL, NAME TEXT, PHONE TEXT, OVER20 INTEGER)
    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " +
            TBL_CONTACT + " " + "(" + enumber + " INTEGER " + ", " +
            password + " TEXT" + ", " + ssn + " INTEGER" + ", " +
            name + " TEXT" + ", " + authority + " INTEGER" + ", " + status + " INTEGER" +
            ", " + approval + " INTEGER"  + ", " +  position + " TEXT " + ")" ;

    // DROP TABLE IF EXISTS CONTACT_T
    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TBL_CONTACT ;

    // SELECT * FROM CONTACT_T
    public static final String SQL_SELECT = "SELECT * FROM " + TBL_CONTACT ;

    // INSERT OR REPLACE INTO CONTACT_T (NO, NAME, PHONE, OVER20) VALUES (x, x, x, x)
    public static final String SQL_INSERT = "INSERT INTO " +
            TBL_CONTACT + " " + "(" + enumber + ", " + password + ", " +
            ssn + ", " + name + ", " +  authority + ", " + status + ", "
            + approval +  ", " + position + ") VALUES";

    // DELETE FROM CONTACT_T
    public static final String SQL_DELETE = "DELETE FROM " + TBL_CONTACT ;
}

package com.example.usertop.misproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.sql.Date;


public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
/*
 private String Item_num ;
    private String Item_name ;
    private String SN ;
    private String Manufacture ;
    private String Model_name ;
    private String Standard ;
    private String Get_cost ;
    private String Dep_code ;
    private String Use_where ;
    private byte[] Image ;
 */
    public void insertData(String Item_num, String Item_name, String SN, String Manufacture, String Model_name, String Standard, String Dep_code, String Use_where, byte[] Image,String Get_get_date,String Get_pro_date, String Get_cost){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, Item_num);
        statement.bindString(2, Item_name);
        statement.bindString(3, SN);
        statement.bindString(4, Manufacture);
        statement.bindString(5, Model_name);
        statement.bindString(6, Standard);
        statement.bindString(7, Dep_code);
        statement.bindString(8, Use_where);
        statement.bindBlob(9, Image);
        statement.bindString(10, Get_get_date);
        statement.bindString(11, Get_pro_date);
        statement.bindString(12, Get_cost);

        statement.executeInsert();
    }

    public void insertData2(String Item_num, String Item_name, String SN, String Manufacture, String Model_name, String Standard, String Dep_code, String Use_where, String Image,String Get_get_date,String Get_pro_date, String Get_cost){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, Item_num);
        statement.bindString(2, Item_name);
        statement.bindString(3, SN);
        statement.bindString(4, Manufacture);
        statement.bindString(5, Model_name);
        statement.bindString(6, Standard);
        statement.bindString(7, Dep_code);
        statement.bindString(8, Use_where);
        statement.bindString(9, Image);
        statement.bindString(10, Get_get_date);
        statement.bindString(11, Get_pro_date);
        statement.bindString(12, Get_cost);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void updateData(String Item_name, String SN, String Manufacture, String Model_name, String Standard, String Dep_code, String Use_where, byte[] Image, String Get_cost,String Item_num) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE ITEM SET Item_name = ?, SN = ?, Manufacture = ?, Model_name = ?, Standard = ?, Dep_code = ?, Use_where = ?,Image = ?, Get_cost = ? WHERE Item_num = ?";
        SQLiteStatement statement = database.compileStatement(sql);


        statement.bindString(1, Item_name);
        statement.bindString(2, SN);
        statement.bindString(3, Manufacture);
        statement.bindString(4, Model_name);
        statement.bindString(5, Standard);
        statement.bindString(9, Get_cost);
        statement.bindString(6, Dep_code);
        statement.bindString(7, Use_where);
        statement.bindBlob(8, Image);
        statement.bindString(10, Item_num);

        statement.execute();
        database.close();
    }
    public void updateData2(String Get_date,String Pro_date,String Item_num) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE ITEM SET Get_date =?, Pro_date = ?  WHERE Item_num = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, Get_date);
        statement.bindString(2, Pro_date);
        statement.bindString(3, Item_num);

        statement.execute();
        database.close();
    }

    public  void deleteData(String Item_num) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ITEM WHERE Item_num = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, Item_num);
        statement.execute();
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.usertop.misproject;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class Item {
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
    private String Get_date ;
    private String Pro_date ;
    private String Count ;
    private boolean isChecked = false;


    public Item(String Item_num, String Item_name, String SN, String Manufacture, String Model_name,String Standard,String Dep_code, String Use_where, byte[] Image, String Get_date, String Pro_date, String Get_cost) {

        this.Item_name = Item_name;
        this.SN = SN;
        this.Manufacture = Manufacture;
        this.Model_name = Model_name;
        this.Standard = Standard;
        this.Dep_code = Dep_code;
        this.Use_where = Use_where;
        this.Image = Image;
        this.Item_num = Item_num;
        this.Get_date = Get_date;
        this.Pro_date = Pro_date;
        this.Get_cost = Get_cost;
    }
    public Item(String Item_num, String Item_name, String SN, String Manufacture, String Model_name,String Standard,String Dep_code, String Use_where,byte[] Image, String Get_date, String Pro_date, String Get_cost, String Count) {

        this.Item_name = Item_name;
        this.SN = SN;
        this.Manufacture = Manufacture;
        this.Model_name = Model_name;
        this.Standard = Standard;
        this.Get_cost = Get_cost;
        this.Dep_code = Dep_code;
        this.Use_where = Use_where;
        this.Image = Image;
        this.Item_num = Item_num;
        this.Get_date = Get_date;
        this.Pro_date = Pro_date;
        this.Count = Count;
    }

    public Item(boolean isChecked, String Item_num, String Item_name, String SN, String Manufacture, String Model_name,String Standard,String Dep_code, String Use_where,byte[] Image, String Get_date, String Pro_date, String Get_cost, String Count) {

        this.isChecked = isChecked;
        this.Item_name = Item_name;
        this.SN = SN;
        this.Manufacture = Manufacture;
        this.Model_name = Model_name;
        this.Standard = Standard;
        this.Get_cost = Get_cost;
        this.Dep_code = Dep_code;
        this.Use_where = Use_where;
        this.Image = Image;
        this.Item_num = Item_num;
        this.Get_date = Get_date;
        this.Pro_date = Pro_date;
        this.Count = Count;
    }

    public Item( String Item_num,String Get_date, String Pro_date) {
        this.Item_num = Item_num;
        this.Get_date = Get_date;
        this.Pro_date = Pro_date;

    }

    public Boolean getisChecked() {
        return isChecked;
    }

    public void setisChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getItem_num() {
        return Item_num;
    }

    public void setItem_num(String Item_num) {
        this.Item_num = Item_num;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String Item_name) {
        this.Item_name = Item_name;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getManufacture() {
        return Manufacture;
    }

    public void setManufacture(String Manufacture) {
        this.Manufacture = Manufacture;
    }

    public String getModel_name() {
        return Model_name;
    }

    public void setModel_name(String Model_name) {
        this.Model_name = Model_name;
    }

    public String getGet_cost() {
        return Get_cost;
    }

    public void setGet_cost(String Get_cost) {
        this.Get_cost = Get_cost;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String Standard) {
        this.Standard = Standard;
    }

    public String getDep_code() {
        return Dep_code;
    }

    public void setDep_code(String Dep_code) {
        this.Dep_code = Dep_code;
    }

    public String getUse_where() {
        return Use_where;
    }

    public void setUse_where(String Use_where) {
        this.Use_where = Use_where;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public String getGet_date() {
        return Get_date;
    }

    public void setGet_date(String Get_date) {
        this.Get_date = Get_date;
    }

    public String getPro_date() {
        return Pro_date;
    }

    public void setPro_date(String Pro_date) {
        this.Pro_date = Pro_date;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String Count) {
        this.Count = Count;
    }

}

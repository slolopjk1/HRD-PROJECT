package com.example.usertop.misproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.content.Context;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ContactDBHelper dbHelper = null;

    Button permission;
    ImageButton loginbtn;
    EditText E_id, E_password;

    String sId, sPw;

    int enumber, ssn, authority, status, approval;
    String password, name, position;

    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        E_id = (EditText)findViewById(R.id.E_id);
        E_password = (EditText)findViewById(R.id.E_password);

        init_tables(); // db 및 테이블 생성
    }

    private void init_tables(){ // db 및 테이블 생성하는 함수
        dbHelper = new ContactDBHelper(this);
    }

    private void save_values(){ //insert
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.execSQL(ContactDBCtrct.SQL_DELETE);

        System.out.println(enumber);

        String sqlInsert = ContactDBCtrct.SQL_INSERT + " (" + "'" + enumber + "', " +
                "'" + password + "', " + "'" + ssn + "', " + "'" + name + "', " +
                "'" + authority + "', " + "'" + status + "', " + "'" + approval + "', " +
                "'" + position + "') ";
        db.execSQL(sqlInsert);

    }

/*
    private void selectDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(ContactDBCtrct.SQL_SELECT, null);

        for(;cursor!=null;){
            if(cursor.moveToNext()){
                cursor.getInt(0);
            }
        }
        String sqlselect = ContactDBCtrct.SQL_SELECT;
        db.execSQL(sqlselect);
        System.out.println(sqlselect);
    }
*/

    private String[] load_values(){
        sId = E_id.getText().toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(ContactDBCtrct.SQL_SELECT, null);
        String[] loginData = new String[2];

        while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String id_s = Integer.toString(id);
                System.out.println("id:" + id);
                String password = cursor.getString(1);
                System.out.println("password: " + password);

                loginData[0] = id_s;
                loginData[1] = password;

                if(sId.equals(loginData[0])){
                    return loginData;
                }
            }
            loginData[0]="fail";
        return loginData;
    }

    public void Loginbtn(View view){
        try {
            sId = E_id.getText().toString();
            sPw = E_password.getText().toString();


            int join_count = 0;

            //selectDB();
            String[] loginData = load_values(); // sqlite member Table의 id, pwd 값을 받아옴

            System.out.println("logindata: " + loginData[0]);

            //sqlite DB와 EditText의 값을 비교
            if(sId.equals(loginData[0]) && sPw.equals(loginData[1])){
                System.out.println("sqlite db 값 저장 및 불러오기 및 비교 다 성공");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Intent intent2 = new Intent(getApplicationContext(), DownloadActivity.class);
                Intent intent3 = new Intent(getApplicationContext(), Item_InsertActivity.class);
                intent.putExtra("eenum", sId);
                intent2.putExtra("eenum", sId);
                intent3.putExtra("eenum", sId);
                startActivity(intent);
            }else if(loginData[0].equals("fail") && join_count==0){
                join_count = 1;
                registDB db = new registDB();
                String result = db.execute("authority",sId,sPw).get();
                String[] arrMember = getData(result);
                enumber = Integer.parseInt(arrMember[0]);
                password = arrMember[1];
                ssn = Integer.parseInt(arrMember[2]);
                name = arrMember[3];
                authority = Integer.parseInt(arrMember[4]);
                status = Integer.parseInt(arrMember[5]);
                approval = Integer.parseInt(arrMember[6]);
                position = arrMember[7]; // 여기까지가 서버에서 받아온 회원 정보

                System.out.println(enumber + password + ssn + name + authority + status + approval + position);
                System.out.println(enumber + ":" + sId);
                System.out.println(password + ":" + sPw);

                save_values();

                if(sId.equals(arrMember[0]) && sPw.equals(arrMember[1]) && approval == 1){
                    System.out.println("RDB 객체 생성 및 받아온 후 비교 완료");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Intent intent2 = new Intent(getApplicationContext(), DownloadActivity.class);
                    Intent intent3 = new Intent(getApplicationContext(), Item_InsertActivity.class);
                    intent.putExtra("eenum", sId);
                    intent2.putExtra("eenum", sId);
                    intent3.putExtra("eenum", sId);
                    startActivity(intent);
                }else if(approval != 1) {
                    showToast(this, "권한을 받지 못했습니다.");
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                }else{
                    showToast(this, "사원번호 또는 비밀번호를 잘못 입력했습니다. 123");
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                }
            }else{
                showToast(this, "사원번호 또는 비밀번호를 잘못 입력했습니다. 111");
                toast.setGravity(Gravity.TOP, 0, 330);
                toast.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Permission(View view){
        Intent intent = new Intent(getApplicationContext(),PermissionActivity.class);
        startActivity(intent);
    }

    public String[] getData(String result){
        String[] memberData = result.split(",");
        return memberData;
    }

    public static void showToast(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else{
            toast.setText(message);
        }
        toast.show();
    }
}
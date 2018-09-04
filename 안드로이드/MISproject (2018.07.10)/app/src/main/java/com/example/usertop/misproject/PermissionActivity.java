package com.example.usertop.misproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PermissionActivity extends AppCompatActivity {
    EditText enumber, password, E_repassword;
    String sId, sPw, sPw_chk;
    Button idcheck;
    Button per_end;
    String[] memberData = new String[5];

    private static Toast toast;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        enumber = (EditText) findViewById(R.id.enumber);
        password = (EditText) findViewById(R.id.password);
        E_repassword = (EditText)findViewById(R.id.E_repassword);
    }

    //ID 확인버튼
    public void idCheck(View view){
        sId = enumber.getText().toString();
        String result;

        try {
            registDB rdb = new registDB();
            result = rdb.execute("idcheck",sId).get();
            if(result.equals("1")) {
                showToast(this,"사용 가능한 사원번호입니다.");
                toast.setGravity(Gravity.TOP ,0,330);
                toast.show();
                count = 1;
            }
            else if(result.equals("2")){
                showToast(this,"사용 불가능한 사원번호입니다.");
                toast.setGravity(Gravity.TOP ,0,330);
                toast.show();
                count = 0;
            }else{
                showToast(this,"사원번호를 입력해주세요");
                toast.setGravity(Gravity.TOP ,0,330);
                toast.show();
                count = 0;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //권한 요청버튼
    public void bt_Join(View view){
        String result;
        sId = enumber.getText().toString();
        sPw = password.getText().toString();
        sPw_chk = E_repassword.getText().toString();
        if(count==1) {
            if(sPw.equals("")){
                showToast(this,"비밀번호를 입력하세요.");
                toast.setGravity(Gravity.TOP, 0, 330);
                toast.show();
            }
            else if (sPw.equals(sPw_chk)) {
                try {
                    registDB rdb = new registDB();
                    result = rdb.execute("pwdset",sId, sPw).get(); //receiveMsg
                    System.out.println("result: " + 123);

//                    memberData = result.split(",");
//                    for(int i=0;i<memberData.length;i++) {
//                        System.out.println(memberData[i]);
//                    }
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    intent.putExtra("member",result);
                    startActivityForResult(intent,0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showToast(this,"비밀번호를 잘못 입력했습니다.");
                toast.setGravity(Gravity.TOP, 0, 330);
                toast.show();
            }
        }else if(count!=1){
            showToast(this,"사원을 확인하세요.");
            toast.setGravity(Gravity.TOP, 0, 330);
            toast.show();
            count = 0;
        }
    }
    //취소 버튼
    public void per_End(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
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
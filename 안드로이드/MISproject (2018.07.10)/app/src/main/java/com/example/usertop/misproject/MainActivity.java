package com.example.usertop.misproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

//인텐트 끝 6/5
public class MainActivity extends AppCompatActivity {
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String Enum = intent.getStringExtra("eenum");
        ImageButton search = findViewById(R.id.search);
        ImageButton insert = findViewById(R.id.insert);
        ImageButton modi = findViewById(R.id.modi);
        ImageButton delete = findViewById(R.id.delete);
        ImageButton down = findViewById(R.id.down);
        ImageButton upload = findViewById(R.id.upload);
        ImageButton help = findViewById(R.id.Helpbtn);
        ImageButton user = findViewById(R.id.user);
        ImageButton print = findViewById(R.id.print);
        ImageButton mainbtn =findViewById(R.id.mainbtn);
        // 권한 설정
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA

            };
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 0);
            }
            else if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 1);
            }
            else if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 2);
            }
        }
        //sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/"+"test"+Enum+".db",null,1);
        sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/test.db",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ITEM (Item_num VARCHAR PRIMARY KEY, Item_name VARCHAR , SN VARCHAR , Manufacture VARCHAR , Model_name VARCHAR, Standard VARCHAR , Dep_code VARCHAR, Use_where VARCHAR, Image VARCHAR,  Get_date VARCHAR, Pro_date VARCHAR, Get_cost VARCHAR" +
                "" +
                "FOREIGN KEY depcode REFERENCES department(depcode))");
        //sqLiteHelper.queryData("CONSTRAINT FK_DEPCODE FOREIGN KEY depcode REFERENCES department(depcode)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS department (depcode INTEGER PRIMARY KEY AUTOINCREMENT, depname VARCHAR, depphone VARCHAR, depaddress VARCHAR) ");
        // 화면 전환
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),Item_SearchActivity.class);
            startActivity(intent);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Item_InsertActivity.class);
                startActivity(intent);
            }
        });

        modi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ModifiedActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Item_DeleteActivity.class);
                startActivity(intent);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UploadActivity.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HelpActivity.class);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReidrepwActivity.class);
                startActivity(intent);
            }
        });

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프린트를 클릭하면 캡쳐 후 인쇄
            }
        });

        mainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (Build.VERSION.SDK_INT >= 23) {
            switch (requestCode) {
                case 0:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    }else if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    }
                    break;
            }
        }
    }
}


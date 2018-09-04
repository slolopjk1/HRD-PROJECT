package com.example.usertop.misproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class DownloadActivity extends AppCompatActivity {
    TextView depname_text;
    String[] Dataset = new String[12];
    String[] Dataset2 = new String[12];
    String[] Arrnum = new String[2];
    int count =0;
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Intent intent = getIntent();
        String Enum = intent.getStringExtra("eenum");

        //sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/"+"test"+Enum+".db",null,1);
        sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/test.db",null,1);

        Button downloadbtn = findViewById(R.id.downloadbtn);
        depname_text = findViewById(R.id.depname_text);
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   registDB RDB = new registDB();
                   String text = depname_text.getText().toString();
                   String result = RDB.execute("depcode", text).get(); //receiveMsg
                   Arrnum = result.split("~");
                   int endnum = Integer.parseInt(Arrnum[0]);
                   for(int i=0; i<endnum; i++) {
                       Dataset = Arrnum[1].split("!");

                        Dataset2 = Dataset[i].split(",");

                        sqLiteHelper.insertData2(
                                Dataset2[0].trim(),
                                Dataset2[1].trim(),
                                Dataset2[2].trim(),
                                Dataset2[3].trim(),
                                Dataset2[4].trim(),
                                Dataset2[5].trim(),
                                Dataset2[6].trim(),
                                Dataset2[7].trim(),
                                Dataset2[8].trim(),
                                Dataset2[9].trim(),
                                Dataset2[10].trim(),
                                Dataset2[11].trim()
                        );
                    }
                   // }
                   Toast.makeText(getApplicationContext(), "Update successfully!!!", Toast.LENGTH_SHORT).show();

               }catch (Exception e){
                   Toast.makeText(getApplicationContext(), "Update error1!!!", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }
}


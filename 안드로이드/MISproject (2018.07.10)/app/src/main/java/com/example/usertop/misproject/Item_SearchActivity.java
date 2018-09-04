package com.example.usertop.misproject;

//체크 박스 리스트뷰로 연동하는중 끝남
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.sql.DriverManager.println;


public class Item_SearchActivity extends AppCompatActivity {
    ArrayList<Item> arItem;
    ListView View;
    ItemfomatAdapter adapter = null;
    ImageButton help, user, mainbtn, print, search_btn;
    EditText search_win;
    Button leam_more;
    TextView totalnum;
    ImageView 사진R;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_main);
        View = findViewById(R.id.list);
        arItem = new ArrayList<>();
        adapter = new ItemfomatAdapter(this, R.layout.activity_item_search, arItem);
        View.setAdapter(adapter);
        init();

        //전체 데이터 조회
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * , count(SN) FROM (SELECT * FROM ITEM ORDER BY Item_num DESC) GROUP BY SN");
        arItem.clear();
        totalnum.setText("총" + cursor.getCount() + "건");
        while (cursor.moveToNext()) {
            String Item_num = cursor.getString(0);
            String Item_name = cursor.getString(1);
            String SN = cursor.getString(2);
            String Manufacture = cursor.getString(3);
            String Model_name = cursor.getString(4);
            String Standard = cursor.getString(5);
            String Dep_code = cursor.getString(6);
            String Use_where = cursor.getString(7);
            byte[] Image = cursor.getBlob(8);
            String Get_date = cursor.getString(9);
            String Pro_date = cursor.getString(10);
            String Get_cost = cursor.getString(11);
            String 수량 = cursor.getString(12);

            arItem.add(new Item(Item_num, Item_name, SN, Manufacture, Model_name, Standard, Dep_code, Use_where, Image, Get_date, Pro_date, Get_cost, 수량));
        }
        adapter.notifyDataSetChanged();

        help.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReidrepwActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //검색 기능 검색어가 포함되어있으면 모두 출력
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ITEM WHERE (Item_name||Manufacture||Model_name) LIKE '%" + search_win.getText() + "%' " );
                arItem.clear();
                totalnum.setText("총" + cursor.getCount() + "건");
                while (cursor.moveToNext()) {
                    String Item_num = cursor.getString(0);
                    String Item_name = cursor.getString(1);
                    String SN = cursor.getString(2);
                    String Manufacture = cursor.getString(3);
                    String Model_name = cursor.getString(4);
                    String Standard = cursor.getString(5);
                    String Dep_code = cursor.getString(6);
                    String Use_where = cursor.getString(7);
                    byte[] Image = cursor.getBlob(8);
                    String Get_date = cursor.getString(9);
                    String Pro_date = cursor.getString(10);
                    String Get_cost = cursor.getString(11);


                    arItem.add(new Item(Item_num,Item_name, SN, Manufacture, Model_name, Standard, Dep_code, Use_where, Image, Get_date, Pro_date, Get_cost));
                }
                adapter.notifyDataSetChanged();
            }
        });

        View.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(Item_SearchActivity.this ,arItem.get(position).getItem_num()+"!!",Toast.LENGTH_LONG).show();
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Item_SearchActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT Item_num FROM ITEM");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            String SNS = arItem.get(position).getSN();
                            String Item_NS = arItem.get(position).getItem_num();
                            //byte[] byteArray  = arItem.get(position).getImage();
                            int posi = position;
                            // show dialog update at h  ere
                            showDialogUpdate(Item_SearchActivity.this, SNS , Item_NS, posi );
                        }else {
                            // delete
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT Item_num FROM ITEM");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            String Item_NS = arItem.get(position).getItem_num();
                            showDialogDelete(Item_NS);
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
        leam_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lean_MoreActivity.class);
                startActivity(intent);
            }
        });
    }




    private void showDialogUpdate(Activity activity,String SNS, String Item_NS, int posi){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_lean_moretest);
        dialog.setTitle("Update");

        Intent intent = new Intent(this, Modified_Lean_MoreActivity.class);
        intent.putExtra("SNS", SNS);
        intent.putExtra("Item_NS", Item_NS);
        intent.putExtra("posi", posi);
        //intent.putExtra("byteArray", byteArray );
        startActivity(intent);
        }

    private void showDialogDelete(final String Item_NS){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.sqLiteHelper.deleteData(Item_NS);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateItemList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
    private void updateItemList(){
        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ITEM");
        arItem.clear();
        while (cursor.moveToNext()) {
            String Item_num = cursor.getString(0);
            String Item_name = cursor.getString(1);
            String SN = cursor.getString(2);
            String Manufacture = cursor.getString(3);
            String Model_name = cursor.getString(4);
            String Standard = cursor.getString(5);
            String Dep_code = cursor.getString(6);
            String Use_where = cursor.getString(7);
            byte[] Image = cursor.getBlob(8);
            String Get_date = cursor.getString(9);
            String Pro_date = cursor.getString(10);
            String Get_cost = cursor.getString(11);
            String 수량 = cursor.getString(12);

            arItem.add(new Item(Item_num, Item_name, SN, Manufacture, Model_name, Standard, Dep_code, Use_where, Image, Get_date, Pro_date, Get_cost, 수량));
        }
        adapter.notifyDataSetChanged();
    }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){

            if (requestCode == 888) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 888);
                } else {
                    Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){

            if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    사진R.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

    public void init(){
        help = findViewById(R.id.Helpbtn);
        user = findViewById(R.id.user);
        print = findViewById(R.id.print);
        mainbtn =findViewById(R.id.mainbtn);
        search_win = findViewById(R.id.search_win);
        search_btn = findViewById(R.id.search_btn);
        leam_more = findViewById(R.id.leam_more);
        totalnum = findViewById(R.id.totalnum);
    }
}


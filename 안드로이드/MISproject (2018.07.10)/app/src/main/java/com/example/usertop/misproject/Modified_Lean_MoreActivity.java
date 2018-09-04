package com.example.usertop.misproject;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Modified_Lean_MoreActivity extends AppCompatActivity {
    private View header;
    private Uri photoUri;
    private final int CAMERA_CODE = 1111;
    private final int GALLERY_CODE=1112;
    String mImageCaptureName;//이미지 이름
    private String currentPhotoPath;
    ListView View;
    ImageView 사진R;
    ItemfomatAdapter2 adapter2 = null;
    ArrayList<Item> arItem2;
    ItemfomatAdapter3 adapter3 = null;
    ArrayList<Item> arItem;
    ArrayList<Item> arItemlist;
    EditText 모델명R, 제조업체명R, 품명R, 규격R, 부서명R, 위치R, 수량R, 취득단가R, 물품목록번호R, item_num, get_date, pro_date;
    String Model_name;
    String Manufacture;
    String Item_name;
    String Standard;
    String SN;
    String Get_cost;
    String Dep_code;
    String Use_where;
    String Item_num;
    String Get_date;
    String Pro_date;
    String 수량;
    Button update ,g2b,update2;
    byte[] Image;
    String Item_N;
    int Position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_moretest);
        init();
        arItem2 = new ArrayList<>();
        adapter2 = new ItemfomatAdapter2(this, R.layout.activity_lean_moretest, arItem2);
        arItem = new ArrayList<>();
        adapter3 = new ItemfomatAdapter3(this, R.layout.activity_lean_more_list, arItem);
        View.setAdapter(adapter3);

        final Intent intent = getIntent();
        final String SNS = intent.getStringExtra("SNS");
        final String Item_NS = intent.getStringExtra("Item_NS");
        //final int posi = intent.getIntExtra("posi", -1);
        final byte[] byteArray =  intent.getByteArrayExtra("byteArray");

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * , count(SN) FROM ITEM WHERE Item_num = '" + Item_NS + "'ORDER BY Item_num DESC" );
        arItem2.clear();

        while (cursor.moveToNext()) {
            Item_num = cursor.getString(0);
            Item_name = cursor.getString(1);
            SN = cursor.getString(2);
            Manufacture = cursor.getString(3);
            Model_name = cursor.getString(4);
            Standard = cursor.getString(5);
            Dep_code = cursor.getString(6);
            Use_where = cursor.getString(7);
            Image = cursor.getBlob(8);
            Get_date = cursor.getString(9);
            Pro_date = cursor.getString(10);
            Get_cost = cursor.getString(11);
            //수량 = cursor.getString(12);

            arItem2.add(new Item(Item_num, Item_name, SN, Manufacture, Model_name, Standard, Dep_code, Use_where, Image, Get_date, Pro_date, Get_cost));
        }
        adapter2.notifyDataSetChanged();

        모델명R.setText(Model_name);
        제조업체명R.setText(Manufacture);
        품명R.setText(Item_name);
        규격R.setText(Standard);
        //byte[] imageitem = arItem.get(posi).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        사진R.setImageBitmap(bitmap);
        부서명R.setText(Dep_code);
        위치R.setText(Use_where);
        물품목록번호R.setText(SN);
        취득단가R.setText(Get_cost);

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

        사진R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
               show();
            }
        });

        Cursor cursor3 = MainActivity.sqLiteHelper.getData("SELECT *  FROM ITEM WHERE Item_num LIKE '%" + SNS + "%'");
        arItem.clear();

        while (cursor3.moveToNext()) {
            String Item_num = cursor3.getString(0);
            String Item_name = cursor3.getString(1);
            String SN = cursor3.getString(2);
            String Manufacture = cursor3.getString(3);
            String Model_name = cursor3.getString(4);
            String Standard = cursor3.getString(5);
            String Dep_code = cursor3.getString(6);
            String Use_where = cursor3.getString(7);
            byte[] Image = cursor3.getBlob(8);
            String Get_date = cursor3.getString(9);
            String Pro_date = cursor3.getString(10);
            String Get_cost = cursor3.getString(11);
            //수량 = cursor2.getString(12);

            arItem.add(new Item(Item_num, Item_name, SN, Manufacture, Model_name, Standard, Dep_code, Use_where, Image, Get_date, Pro_date, Get_cost));
        }
        adapter3.notifyDataSetChanged();

        View.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        View.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter3.checkedConfirm(position);
                Toast.makeText(Modified_Lean_MoreActivity.this ,arItem.get(position).getItem_num()+"!!",Toast.LENGTH_LONG).show();
                Item_N = arItem.get(position).getItem_num();
                Position = position;
                adapter3.notifyDataSetChanged();
                return false;
            }
        });
        g2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.g2b.go.kr:8100/index.jsp");
                Intent intent  = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    MainActivity.sqLiteHelper.updateData(
                            품명R.getText().toString().trim(),
                            물품목록번호R.getText().toString().trim(),
                            제조업체명R.getText().toString().trim(),
                            모델명R.getText().toString().trim(),
                            규격R.getText().toString().trim(),
                            부서명R.getText().toString().trim(),
                            위치R.getText().toString().trim(),
                            Item_InsertActivity.imageViewToByte(사진R),
                            //Get_date.getText().toString().trim(),
                            //Pro_date.getText().toString().trim(),
                            취득단가R.getText().toString().trim(),
                            Item_NS.toString().trim()
                    );
                    //dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception error) {
                    Toast.makeText(getApplicationContext(), "Update error!!!", Toast.LENGTH_SHORT).show();
                    Log.e("Update error", error.getMessage());
                }
                updateItemList();
            }
        });


        Cursor cursor4 = MainActivity.sqLiteHelper.getData("SELECT *  FROM ITEM WHERE Item_num = '" + Item_N + "'");


        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                        MainActivity.sqLiteHelper.updateData2(
                                arItem.get(Position).getGet_date().toString().trim(),
                                arItem.get(Position).getPro_date().toString().trim(),
                                Item_N.toString().trim()

                        );



                    //dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception error) {
                    Toast.makeText(getApplicationContext(), "Update error!!!", Toast.LENGTH_SHORT).show();
                    Log.e("Update error", error.getMessage());
                }
                updateItemList2();
            }
        });
    }


    private void updateItemList() {
        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ITEM");
        arItem2.clear();
        while (cursor.moveToNext()) {
            String Item_num = cursor.getString(0);
            String 품명R = cursor.getString(1);
            String 물품목록번호R = cursor.getString(2);
            String 제조업체명R = cursor.getString(3);
            String 모델명R = cursor.getString(4);
            String 규격R = cursor.getString(5);
            String 부서명R = cursor.getString(6);
            String 위치R = cursor.getString(7);
            byte[] 사진R = cursor.getBlob(8);
            //String Get_date = cursor.getString(9);
            //String Pro_date = cursor.getString(10);
            String 취득단가R = cursor.getString(11);
//            String 수량 = cursor.getString(12);
            arItem2.add(new Item(Item_num, 품명R, 물품목록번호R, 제조업체명R, 모델명R, 규격R, 부서명R, 위치R, 사진R, Get_date, Pro_date, 취득단가R));

        }
        adapter2.notifyDataSetChanged();
    }

    private void updateItemList2() {
        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ITEM WHERE Item_num= '"+Item_N+"'");
        arItem.clear();
        while (cursor.moveToNext()) {
            String Item_num = cursor.getString(0);
            String Get_date = cursor.getString(9);
            String Pro_date = cursor.getString(10);

            arItem.add(new Item(Item_num,Get_date, Pro_date));


        }
        adapter3.notifyDataSetChanged();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("업로드할 이미지 선택");
        builder.setMessage("사진을 선택해 주세요");
        builder.setPositiveButton("카메라",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectPhoto();
                    }
                });
        builder.setNegativeButton("갤러리",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectGallery();
                    }
                });
        builder.setNeutralButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"취소를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
    //이미지 파일 생성
    private File createImageFile() throws IOException {
        File dir = new File(Environment.getExternalStorageDirectory() + "/path/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mImageCaptureName = timeStamp + ".png";

        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/path/"
                + mImageCaptureName);
        currentPhotoPath = storageDir.getAbsolutePath();

        return storageDir;

    }
    //사진찍은 파일을 가져온다
    private void getPictureForPhoto() {
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(currentPhotoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation;
        int exifDegree;

        if (exif != null) {
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        } else {
            exifDegree = 0;
        }

        사진R.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기
    }
    //사진(카메라)
    private void selectPhoto() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {

                }
                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, CAMERA_CODE);
                }
            }

        }
    }

    //사진(갤러리)
    public void selectGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    //카메라 코드//갤러리 코드 구분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    sendPicture(data.getData()); //갤러리에서 가져오기
                    break;
                case CAMERA_CODE:
                    getPictureForPhoto(); //카메라에서 가져오기
                    break;

                default:
                    break;
            }

        }
    }
    //이미지 뷰로 사진 입력
    private void sendPicture(Uri imgUri) {

        String imagePath = getRealPathFromURI(imgUri); // path 경로
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//경로를 통해 비트맵으로 전환

        사진R.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기

    }
    //사진 각도별로 구분해서 저장
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
    // 사진 각도별로 비트맵 생성해서 조절하기
    private Bitmap rotate(Bitmap src, float degree) {
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }
    //사진 경로
    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }
    public void init(){
        사진R = findViewById(R.id.사진R);
        update = findViewById(R.id.update);
        update2 = findViewById(R.id.update2);
        모델명R = (EditText)findViewById(R.id.모델명R);
        제조업체명R = (EditText) findViewById(R.id.제조업체명R);
        품명R = (EditText) findViewById(R.id.품명R);
        규격R = (EditText) findViewById(R.id.규격R);
        부서명R = (EditText) findViewById(R.id.부서명R);
        위치R = (EditText) findViewById(R.id.위치R);
        //수량R = (EditText) findViewById(R.id.수량R);
        취득단가R = (EditText) findViewById(R.id.취득단가R);
        물품목록번호R = findViewById(R.id.물품목록번호R);
        View = findViewById(R.id.list3);
        g2b = findViewById(R.id.g2b);
        item_num = findViewById(R.id.item_num);
        get_date = findViewById(R.id.get_date);
        pro_date = findViewById(R.id.pro_date);
    }

}

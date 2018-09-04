package com.example.usertop.misproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public  class Item_InsertActivity extends AppCompatActivity {
    private final int CAMERA_CODE = 1111;
    private final int GALLERY_CODE=1112;
    String nullvalue="";
    private Uri photoUri;
    private String currentPhotoPath;//실제 사진 파일 경로
    ImageView imageinsert;
    String mImageCaptureName;//이미지 이름
    EditText 모델명,제조업체명,품명,규격,수량,취득단가,SN;
    Spinner 부서명,위치;
    String pk;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String Enum = intent.getStringExtra("eenum");
        setContentView(R.layout.activity_item_insert);
        imageinsert = findViewById(R.id.imageinsert);
        Button g2b = findViewById(R.id.g2b);
        Button mainbttn = findViewById(R.id.mainbttn);
        ImageButton help = findViewById(R.id.Helpbtn);
        ImageButton user = findViewById(R.id.user);
        ImageButton print = findViewById(R.id.print);
        ImageButton mainbtn = findViewById(R.id.mainbtn);
        Button insert = findViewById(R.id.insert);

        init();

        //sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/"+"test"+Enum+".db",null,1);
        sqLiteHelper = new SQLiteHelper(this,"/mnt/sdcard/test.db",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ITEM (Item_num VARCHAR PRIMARY KEY, Item_name VARCHAR , SN VARCHAR , Manufacture VARCHAR , Model_name VARCHAR, Standard VARCHAR , Dep_code VARCHAR, Use_where VARCHAR,Image BLOB,  Get_date VARCHAR, Pro_date VARCHAR, Get_cost VARCHAR)");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                            //sqLiteHelper.queryData("SELECT SN FROM ITEM WHERE SN'" +SN.getText().toString().trim()+"'");
                    Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT SN FROM ITEM WHERE SN ='" +SN.getText().toString()+"'");
                    int result =50000001+ Integer.parseInt(수량.getText().toString().trim());
                    if(cursor.getCount()==0) {

                        for (int i = 50000001; i <result; i++) {
                            pk = "KKR-P-" + SN.getText().toString() + i;
                            sqLiteHelper.insertData(
                                    pk.trim(),
                                    품명.getText().toString().trim(),
                                    SN.getText().toString().trim(),
                                    제조업체명.getText().toString().trim(),
                                    모델명.getText().toString().trim(),
                                    규격.getText().toString().trim(),
                                    부서명.getSelectedItem().toString().trim(),
                                    위치.getSelectedItem().toString().trim(),
                                    imageViewToByte(imageinsert),
                                    "2018".trim(),
                                    "2018".trim(),
                                    취득단가.getText().toString().trim()
                            );
                        }
                    }
                    else {
                        for (int i = 50000001+cursor.getCount(); i < result + cursor.getCount(); i++) {
                            pk = "KKR-P-" + SN.getText().toString() + i;
                            sqLiteHelper.insertData(
                                    pk.trim(),
                                    품명.getText().toString().trim(),
                                    SN.getText().toString().trim(),
                                    제조업체명.getText().toString().trim(),
                                    모델명.getText().toString().trim(),
                                    규격.getText().toString().trim(),
                                    부서명.getSelectedItem().toString().trim(),
                                    위치.getSelectedItem().toString().trim(),
                                    imageViewToByte(imageinsert),
                                    "2018".trim(),
                                    "2018".trim(),
                                    취득단가.getText().toString().trim()
                            );
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    모델명.setText("");
                    SN.setText("");
                    제조업체명.setText("");
                    품명.setText("");
                    규격.setText("");
                    수량.setText("");
                    취득단가.setText("");
                    imageinsert.setImageResource(R.drawable.init);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

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

        imageinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
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

        mainbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
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
    }


    // 다이얼로그 인터페이스 생성
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

                imageinsert.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기
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

        imageinsert.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기

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

    private  void init(){
        SN = findViewById(R.id.물품목록번호);
        모델명 = findViewById(R.id.모델명);
        제조업체명 = findViewById(R.id.제조업체명);
        품명 = findViewById(R.id.품명);
        규격 =findViewById(R.id.규격);
        수량 = findViewById(R.id.수량);
        취득단가 = findViewById(R.id.취득단가);
        부서명 =findViewById(R.id.sp_btn1);
        위치 = findViewById(R.id.sp_btn2);

    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    //저장소 권한//쓰기 권한//카메라 권한
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


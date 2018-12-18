package com.hlub.dev.testcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imgHinh;
    private static final int REQUEST_CAMERA= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgHinh = (ImageView) findViewById(R.id.imgHinh);

    }

    public void openCamera(View view) {
        //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//mo màn hình camera
        //startActivityForResult(intent,REQUEST_CAMERA);

        //xin quyền vs android 6.0 hay API 23 trở nên
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // nếu ng dùng cho phép
        if(requestCode==REQUEST_CAMERA &&permissions.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//mo màn hình camera
            startActivityForResult(intent,REQUEST_CAMERA);
        }else{
            Toast.makeText(this, "Không cho phép mở camera !", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAMERA &&resultCode==RESULT_OK &&data!=null){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data"); //key:data la mac dinh
            //set image
            imgHinh.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

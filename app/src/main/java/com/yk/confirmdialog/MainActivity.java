package com.yk.confirmdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.yk.confirmalert.ConfirmDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        new ConfirmDialog(this) {

            @Override
            public void onConfirm(ConfirmDialog dialog) {
                Toast.makeText(MainActivity.this, "确认弹窗", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }
                .setDialogTitle("确认弹窗")
                .setDialogContent("弹窗内容")
                .setDialogTitleColor(0xFF999999)
                .setDialogTitleSize(16)
                .setDialogContentColor(0xFFFF0000)
                .setDialogContentSize(TypedValue.COMPLEX_UNIT_DIP, 14)
                .setCanceledOutside(true)
                .show();
    }

}
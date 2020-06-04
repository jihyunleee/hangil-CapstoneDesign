package com.hausung.hangil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hausung.hangil.Chatting.ChattingActivity;
import com.hausung.hangil.Map.MapActivity;
import com.hausung.hangil.MyPage.MyPageActivity;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView button1 = findViewById(R.id.map);
        ImageView  button2 = findViewById(R.id.chatting);
        ImageView button3 = findViewById(R.id.mypage);

        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MapActivity.class);
                        startActivity(intent);
                    }
                }
        );

        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), ChattingActivity.class);
                        startActivity(intent);
                    }
                }
        );

        button3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MyPageActivity.class);
                        startActivity(intent);
                    }
                }
        );


    }
}
package com.hausung.hangil.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hausung.hangil.R;
import com.hausung.hangil.Reservation.MoreActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MyPageActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //스와이프 코드
        slidr = Slidr.attach(this);

        //로그아웃, 버전정보, 회원탈퇴 페이지로 이동
        Button toMyPage2 = (Button) findViewById(R.id.toMyPageSecond);
        toMyPage2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //MyPageSecondActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MyPageSecondActivity.class);
                        startActivity(intent);
                    }
                }
        );

        //각 예약현황 세부항목 보기
        Button toMoreInfo = (Button) findViewById(R.id.moreInfo);
        toMoreInfo.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //MoreActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MoreActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}

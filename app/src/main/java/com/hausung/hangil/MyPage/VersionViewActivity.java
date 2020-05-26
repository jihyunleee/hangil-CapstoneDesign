package com.hausung.hangil.MyPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hausung.hangil.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class VersionViewActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_view);
        //스와이프 코드
        slidr = Slidr.attach(this);
    }


}

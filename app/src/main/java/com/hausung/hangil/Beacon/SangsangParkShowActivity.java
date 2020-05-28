package com.hausung.hangil.Beacon;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hausung.hangil.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class SangsangParkShowActivity extends AppCompatActivity {
    TextView textShow;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangsangpark_show);
        //스와이프 코드
        slidr = Slidr.attach(this);
        textShow = findViewById(R.id.textShow);

    }
}

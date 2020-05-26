package com.hausung.hangil.Reservation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.hausung.hangil.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MoreActivity extends AppCompatActivity {
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //스와이프 코드
        slidr = Slidr.attach(this);
    }
}

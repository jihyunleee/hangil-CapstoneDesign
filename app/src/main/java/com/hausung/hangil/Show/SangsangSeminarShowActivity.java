package com.hausung.hangil.Show;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hausung.hangil.R;
import com.hausung.hangil.Reservation.SangsangReservationActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class SangsangSeminarShowActivity extends AppCompatActivity {
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangsang_seminar_show);
        //스와이프 코드
        slidr = Slidr.attach(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("AllSangsangSeminarRoom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(BATTERY_SERVICE, document.getId() + " => " + document.getData());
                                TextView storedDate = findViewById(R.id.storedData);
                                storedDate.setText(document.getData().toString());
                            }
                        } else {
                            Log.w(BATTERY_SERVICE, "Error getting documents.", task.getException());
                        }
                    }
                });
        //상상세미나실 예약 페이지로 이동
        Button toReservationPage = (Button) findViewById(R.id.toReservation);
        toReservationPage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //ReservationActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), SangsangReservationActivity.class);

                        startActivity(intent);
                    }
                }
        );
    }
}

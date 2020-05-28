package com.hausung.hangil.Show;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hausung.hangil.R;
import com.hausung.hangil.Reservation.LibraryReservationActivity;

import java.util.ArrayList;

public class LibraryStudyRoomShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarystudyroom_show);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        final RecyclerView recyclerView = findViewById(R.id.recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("AllLibraryStudyRoom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<String> list = new ArrayList<>();
                        RecyclerShowActivity adapter = null;
                        String id;
                        String mStrDate;
                        String mStrTime;
                        String mStrFinishTime;
                        String name;
                        String number;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(BATTERY_SERVICE, document.getId() + " => " + document.getData());
                                id=document.get("id").toString();
                                mStrDate=document.get("mStrDate").toString();
                                mStrTime=document.get("mStrTime").toString();
                                mStrFinishTime=document.get("mStrFinishTime").toString();
                                name=document.get("name").toString();
                                number=document.get("number").toString();
                                list.add(id);
                                list.add(mStrDate);
                                list.add(mStrTime);
                                list.add(mStrFinishTime);
                                list.add(name);
                                list.add(number);
                                System.out.println(list);
                            }
                            adapter = new RecyclerShowActivity(list);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.w(BATTERY_SERVICE, "Error getting documents.", task.getException());
                        }
                    }
                });

        //스터디룸 예약 페이지로 이동
        Button toReservationPage = (Button) findViewById(R.id.toReservation);
        toReservationPage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //ReservationActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), LibraryReservationActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}

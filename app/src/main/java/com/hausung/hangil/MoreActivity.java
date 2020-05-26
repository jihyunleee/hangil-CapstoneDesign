/*
package com.hausung.hangil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hausung.hangil.Map.MapActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
public class MoreActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    TextView cIn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        //스와이프 코드
        slidr = Slidr.attach(this);
        //cIn = findViewById(R.id.checkInDate);

        ///////////////////////dbRef 주의/////////////////////////
        dbRef = FirebaseDatabase.getInstance().getReference().child("Room").child("1");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String in = dataSnapshot.child("checkinDate").getValue().toString();
                cIn.setText(in);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void backHome(View view){
        Intent intent=new Intent(MoreActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
*/

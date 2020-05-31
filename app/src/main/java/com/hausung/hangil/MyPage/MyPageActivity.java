package com.hausung.hangil.MyPage;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hausung.hangil.R;
import com.hausung.hangil.Show.RecyclerShowActivity;
import com.hausung.hangil.StartActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //스와이프 코드
        slidr = Slidr.attach(this);

        final RecyclerView recyclerView = findViewById(R.id.recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        //현재 로그인 아이디의 예약정보 리사이클러뷰로 보여주기
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("AllReservation").whereEqualTo("mId",userId.getEmail())
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
                        String building;
                        String room;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(BATTERY_SERVICE, document.getId() + " => " + document.getData());
                                id=document.get("id").toString();
                                mStrDate=document.get("mStrDate").toString();
                                mStrTime=document.get("mStrTime").toString();
                                mStrFinishTime=document.get("mStrFinishTime").toString();
                                name=document.get("name").toString();
                                building=document.get("building").toString();
                                room=document.get("room").toString();
                                list.add(id);
                                list.add(mStrDate);
                                list.add(mStrTime);
                                list.add(mStrFinishTime);
                                list.add(name);
                                list.add(building);
                                list.add(room);
                                System.out.println(list);
                            }
                            adapter = new RecyclerShowActivity(list);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.w(BATTERY_SERVICE, "Error getting documents.", task.getException());
                        }
                    }
                });

        //로그아웃, 버전정보, 회원탈퇴 페이지로 이동하는 버튼
        Button toLogout=findViewById(R.id.toLogout);
        Button toVersion=findViewById(R.id.toVersion);
        Button toSecession=findViewById(R.id.toSecession);

        toLogout.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //로그아웃 코드
                        FirebaseAuth.getInstance().signOut();
                        //StartActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), StartActivity.class);
                        startActivity(intent);
                    }
                }
        );

        toVersion.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), VersionViewActivity.class);
                        startActivity(intent);
                    }
                }
        );

        toSecession.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // Get auth credentials from the user for re-authentication. The example below shows
                        // email and password credentials but there are multiple possible providers,
                        // such as GoogleAuthProvider or FacebookAuthProvider.
                        AuthCredential credential = EmailAuthProvider
                                .getCredential("user@example.com", "password1234");

                        // Prompt the user to re-provide their sign-in credentials
                        firebaseUser.reauthenticate(credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        // Log.d(TAG, "User re-authenticated.");
                                    }
                                });

                        //회원탈퇴 코드 구현
                        firebaseUser.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //Log.d(TAG, "User account deleted.");
                                        }
                                    }
                                });
                        //firebaseUser=null;
                        //StartActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), StartActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}

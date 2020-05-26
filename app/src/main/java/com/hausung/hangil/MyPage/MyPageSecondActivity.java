package com.hausung.hangil.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.hausung.hangil.R;
import com.hausung.hangil.StartActivity;
import com.google.firebase.auth.FirebaseUser;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MyPageSecondActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_second);

        //스와이프 코드
        slidr = Slidr.attach(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //로그아웃 및 로그인 페이지로 이동
        Button logoutAndToStartPage = (Button) findViewById(R.id.logoutAndToStart);
        logoutAndToStartPage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //로그아웃 코드 구현
                        FirebaseAuth.getInstance().signOut();
                        //StartActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), StartActivity.class);
                        startActivity(intent);
                    }
                }
        );

        //버전 정보 페이지로 이동
        Button toVersionView = (Button) findViewById(R.id.toVersionView);
        toVersionView.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), VersionViewActivity.class);
                        startActivity(intent);
                    }
                }
        );

        //회원탈퇴 및 로그인 페이지로 이동
        Button deleteAndToStart = (Button) findViewById(R.id.deleteAndToStart);
        deleteAndToStart.setOnClickListener(
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

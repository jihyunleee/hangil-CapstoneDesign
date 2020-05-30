package com.hausung.hangil.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hausung.hangil.R;
import com.hausung.hangil.StartActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MyPageActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //스와이프 코드
        slidr = Slidr.attach(this);

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

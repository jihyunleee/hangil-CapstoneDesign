package com.hausung.hangil.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.hausung.hangil.MyPage.MyPageActivity;
import com.hausung.hangil.R;
import com.hausung.hangil.Show.LibraryStudyRoomShowActivity;
import com.hausung.hangil.Show.SangsangSeminarShowActivity;
import com.hausung.hangil.Show.SangsangVillageSeminarShowActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Map 이미지
        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.map);

        //AlertDialog
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

        //상상빌리지 세미나실 현황 페이지로 이동
        Button toSangsangVillageSeminarShowPage = (Button) findViewById(R.id.toSangsangVillageSeminaroom);
        toSangsangVillageSeminarShowPage.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        builder1.setTitle("세미나실 현황");
                        builder1.setIcon(R.mipmap.ic_launcher);
                        builder1.setMessage("상상빌리지 세미나실을 예약하시겠습니까?");
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //MyPageActivity로 가는 인텐트 생성
                                Intent intent1 = new Intent(getApplication(), SangsangVillageSeminarShowActivity.class);
                                startActivity(intent1);
                            }
                        });
                        builder1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder1.create().show();
                    }
                }
        );

        //상상관 세미나실 현황 이동
        Button toSangsagnSeminarRoom = (Button) findViewById(R.id.toSansangSeminaroom);
        toSangsagnSeminarRoom.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        builder2.setTitle("상상관 세미나실 현황");
                        builder2.setIcon(R.mipmap.ic_launcher);
                        builder2.setMessage("상상관 세미나실을 예약하시겠습니까?");
                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //MyPageActivity로 가는 인텐트 생성
                                Intent intent2 = new Intent(getApplication(), SangsangSeminarShowActivity.class);
                                startActivity(intent2);
                            }
                        });
                        builder2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder2.create().show();
                    }
                }
        );

        //학교 도서관 현황 페이지 이동
        Button toSchoolLibraryStudyRoom = (Button) findViewById(R.id.toSchoolLibraryStudyRoom);
        toSchoolLibraryStudyRoom.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        builder3.setTitle("학교 도서관 스터디룸 현황");
                        builder3.setIcon(R.mipmap.ic_launcher);
                        builder3.setMessage("도서관 스터디 룸을 예약하시겠습니까?");
                        builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //MyPageActivity로 가는 인텐트 생성
                                Intent intent3 = new Intent(getApplication(), LibraryStudyRoomShowActivity.class);
                                startActivity(intent3);
                            }
                        });
                        builder3.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder3.create().show();
                    }
                }
        );

        //마이페이지로 이동
        Button toMyPage = (Button) findViewById(R.id.toMyPage);
        toMyPage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MyPageActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}

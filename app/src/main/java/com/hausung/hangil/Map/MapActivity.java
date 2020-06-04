package com.hausung.hangil.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.hausung.hangil.Beacon.SangsangParkShowActivity;
import com.hausung.hangil.Chatting.ChattingActivity;
import com.hausung.hangil.MyPage.MyPageActivity;
import com.hausung.hangil.R;
import com.hausung.hangil.Show.LibraryStudyRoomShowActivity;
import com.hausung.hangil.Show.SangsangSeminarShowActivity;
import com.hausung.hangil.Show.SangsangVillageSeminarShowActivity;

public class MapActivity extends AppCompatActivity {

    Button chattingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //chattingbtn = findViewById(R.id.chatting);
        //Map 이미지
        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.map);

        //AlertDialog
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder5 = new AlertDialog.Builder(this);


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

        //상상파크 현황 이동
        Button toSansangPark = (Button) findViewById(R.id.toSansangPark);
        toSansangPark.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        builder4.setTitle("상상파크 현황 파악");
                        builder4.setIcon(R.mipmap.ic_launcher);
                        builder4.setMessage("상상파크 이용상황 보시겠습니까?");
                        builder4.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //MyPageActivity로 가는 인텐트 생성
                                Intent intent4 = new Intent(getApplication(), SangsangParkShowActivity.class);
                                startActivity(intent4);
                            }
                        });
                        builder4.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder4.create().show();
                    }
                }
        );

        //마이페이지로 이동
        /*Button toMyPage = (Button) findViewById(R.id.toMyPage);
        toMyPage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //SeminarShowActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MyPageActivity.class);
                        startActivity(intent);
                    }
                }
        );*/

        Button question = (Button) findViewById(R.id.question);
        question.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        builder5.setTitle("How to use this map");
                        builder5.setIcon(R.mipmap.ic_launcher);
                        builder5.setMessage("연구관을 클릭하면\n"+"상상파크(그라지에)의 현황을 알 수 있습니다.\n" +
                                "상상빌리지/미래관/상상관을 클릭하면\n" +"공유시설물을 예약할수 있습니다.");
                        builder5.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder5.create().show();
                    }
                }
        );

      /*  chattingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ChattingActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
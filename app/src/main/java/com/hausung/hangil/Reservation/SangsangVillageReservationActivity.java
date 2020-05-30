package com.hausung.hangil.Reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hausung.hangil.Map.MapActivity;
import com.hausung.hangil.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SangsangVillageReservationActivity extends AppCompatActivity {
    private SlidrInterface slidr;

    //예약 정보
    private EditText mStrName;
    private EditText mStrStudentid;
    private EditText mStrNumber;
    private  EditText mTime;

    private String mStrTime = "???";
    private String mStrFinishTime = "???";
    private String mStrDate = "???";

    String name;
    String id;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangsangvillage_reservation);
        //스와이프 코드
        slidr = Slidr.attach(this);

        mStrName=  (EditText) findViewById(R.id.name);
        mStrStudentid = (EditText) findViewById(R.id.studentid);
        mStrNumber = (EditText) findViewById(R.id.number);
        mTime = (EditText) findViewById(R.id.time);

        //상상빌리지 세미나실 현황 페이지로 이동
        Button ConfirmSubmit = (Button) findViewById(R.id.Submit);
        ConfirmSubmit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //예약 정보 업데이트
                        name = mStrName.getText().toString();
                        id = mStrStudentid.getText().toString();
                        number = mStrNumber.getText().toString();
                        //유저 아이디 정보를 받아오기 위해 FriebaseUser 필드 생성
                        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
                        //MapActivity로 가는 인텐트 생성
                        Intent intent = new Intent(getApplication(), MapActivity.class);
                        //파이어베이스에 정보 저장하기
                        FirebaseFirestore db= FirebaseFirestore.getInstance();
                        Map<String, Object> user = new HashMap<>();
                        user.put("name", name);
                        user.put("id", id);
                        user.put("number",number);
                        user.put("mStrTime",mStrTime);
                        user.put("mStrFinishTime",mStrFinishTime);
                        user.put("mStrDate",mStrDate);
                        user.put("mId", userId.getEmail());
                        db.collection("AllSangsangVillageSeminarRoom")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(id, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(id, "Error adding document", e);
                                    }
                                });

                        db.collection("AllReservation")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(id, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(id, "Error adding document", e);
                                    }
                                });
                        startActivity(intent);
                    }
                }
        );
    }

    public void mOnClick(View v){
        Calendar c = Calendar.getInstance();
        switch (v.getId()){
            case  R.id.SelectDate:
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(this,mDateSetListener, year, month, day).show();
                break;
            case R.id.SelectTime:
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(this,mTimeSetListener, hour,minute,true).show();
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mStrDate = String.format("%d년 %d월 %d일", year,month+1,dayOfMonth);
            //updateResult();
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()  {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay , int  minute) {
            int time = Integer.parseInt(mTime.getText().toString());
            mStrTime = String.format("%d시 %d분", hourOfDay, minute);
            mStrFinishTime =  String.format("%d시 %d분", hourOfDay+time, minute);
            //updateResult();
        }
    };
}
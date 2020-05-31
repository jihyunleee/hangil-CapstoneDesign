package com.hausung.hangil.Beacon;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hausung.hangil.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SangsangParkShowActivity extends AppCompatActivity implements BeaconConsumer {
    TextView textShow;
    private SlidrInterface slidr;
    private ScrollView scrollView;
    private ImageView imageView;

    private static final String TAG = "Beacontest";
    private BeaconManager beaconManager;

    private List<Beacon> beaconList = new ArrayList<>();
    TextView textView;

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangsangpark_show);

        textShow = findViewById(R.id.textShow);

        // 뷰 객체 생성
        //scrollView = (ScrollView)findViewById(R.id.scoll);
        imageView = (ImageView)findViewById(R.id.image);

        // 수평 스크롤바 사용 기능 설정
        scrollView.setHorizontalScrollBarEnabled(true);


        // 리소스 이미지 참조
        Resources res = getResources(); // getResources() 메소드를 사용해 BitmapDrawable 객체를 만들 수 있습니다.
        BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.sangsangpark_design);
        int bitmapWidth = bitmap.getIntrinsicWidth(); // 원본 이미지의 가로 길이
        int bitmapHeight = bitmap.getIntrinsicHeight(); // 원본 이미지의 세로 길이


        // 이미지 리소스와 이미지 크기 결정
        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;


        //비콘 매니저 생성, BeaconManager의 인스턴스 획득
        beaconManager = BeaconManager.getInstanceForApplication(this);
        textView = (TextView) findViewById(R.id.textShow);//비콘검색후 검색내용 뿌려주기위한 textview

        //비콘 매니저에서 layout 설정 'm:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25'
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        //beaconManager 설정 bind -> 서비스에 바인드를 호출
        beaconManager.bind(this);

        //beacon 을 활용하려면 블루투스 권한획득(Andoird M버전 이상)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access" );
                builder.setMessage("Please grant location access so this app can detect beacons.");
                builder.setPositiveButton(android.R.string.ok,null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                builder.show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    @Override
    public void onBeaconServiceConnect() {    //Beacon 서비스에 연결되면 호출된다
        //Notifier를 설정한다
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            // 비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
            // region에는 비콘들에 대응하는 Region 객체가 들어온다.
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    beaconList.clear();
                    for (Beacon beacon : beacons) {
                        beaconList.add(beacon);
                    }
                }
            }

        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }


    // 버튼이 클릭되면 textView 에 비콘들의 정보를 뿌린다.
    public void OnButtonClicked(View view){
        // 아래에 있는 handleMessage를 부르는 함수. 맨 처음에는 0초간격이지만 한번 호출되고 나면
        // 1초마다 불러온다.
        handler.sendEmptyMessage(0);
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            textView.setText("");


            // 비콘의 아이디와 거리를 측정하여 textView에 넣는다.
            for(Beacon beacon : beaconList){
                String uuid=beacon.getId1().toString(); //beacon uuid
                int major = beacon.getId2().toInt(); //beacon major
                int minor = beacon.getId3().toInt();// beacon minor
                String address = beacon.getBluetoothAddress();
                if(major==40001){
                    //beacon 의 식별을 위하여 major값으로 확인
                    //이곳에 필요한 기능 구현
                    //textView.append("ID 1 : " + beacon.getId2() + " / " + "Distance : " + Double.parseDouble(String.format("%.3f", beacon.getDistance())) + "m\n");
                    textView.append("여기는 상상파크 입니다\n");
                    textView.append("Beacon Bluetooth Id : "+address+"\n");
                    textView.append("Beacon UUID : "+uuid+"\n");
                }else{
                    //나머지 비콘검색
                    textView.append("ID 2: " + beacon.getId2() + " / " + "Distance : " + Double.parseDouble(String.format("%.3f", beacon.getDistance())) + "m\n");
                }

            }

            // 자기 자신을 1초마다 호출
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }
}
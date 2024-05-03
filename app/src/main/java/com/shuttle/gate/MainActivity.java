package com.shuttle.gate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.installations.Utils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shuttle.gate.Utils.Util;
import com.shuttle.gate.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> barcodeLauncher;
    int siteCount = 0;
    int maxSite = 4;
    String tag = "diorTAG";
    ActivityMainBinding binding;
    Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        startQRCodeScan();

        binding.btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GpsMonitor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면

                util.debugLog.log(tag, 0, result.getContents().substring(1, 7));
                siteCount++;
                util.debugLog.log(tag, 0, String.valueOf(siteCount));
                if(!(siteCount > maxSite)){
                    binding.textview2.setText(""+(siteCount - maxSite));
                    Toast.makeText(this, "스캔완료\n남은 좌석: " + (siteCount - maxSite), Toast.LENGTH_SHORT).show();
                    util.debugLog.log(tag,0, "스캔완료\n남은 좌석: "+(siteCount - maxSite));
                    startQRCodeScan();
                } else {
                    Toast.makeText(this, "남은 좌석이 없습니다.", Toast.LENGTH_SHORT).show();
                    binding.textview.setVisibility(View.VISIBLE);
                    binding.btn.setEnabled(true);
                    binding.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.textview.setVisibility(View.GONE);
                            siteCount = 0;
                            startQRCodeScan();
                        }
                    });
                }
            }
        }
    }

    /**/
    private void startQRCodeScan() {
        IntentIntegrator qrScan = new IntentIntegrator(this);
        qrScan.setCameraId(0);
        qrScan.setOrientationLocked(true);

        qrScan.initiateScan();
    }

    private void requestCoordinates() {

    }
}

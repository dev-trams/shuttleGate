package com.shuttle.gate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shuttle.gate.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> barcodeLauncher;
    int siteCount = 0;
    int maxSite = 4;
    String tag = "diorTAG";
    ActivityMainBinding binding;

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
                Log.d(tag, result.getContents().substring(1, 7));
                siteCount++;
                Log.d(tag, String.valueOf(siteCount));
                if(!(siteCount > maxSite)){
                    Toast.makeText(this, "스캔완료\n남은 좌석: " + (siteCount - 29), Toast.LENGTH_SHORT).show();
                    Log.i(tag, "스캔완료\n남은 좌석: "+(siteCount - 29));
                    startQRCodeScan();
                } else {
                    Toast.makeText(this, "남은 좌석이 없습니다.", Toast.LENGTH_SHORT).show();
                    binding.textview.setText("남은 좌석이 없습니다.");
                    binding.btn.setEnabled(true);
                    binding.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
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

package com.shuttle.gate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.shuttle.gate.databinding.ActivityLicensesBinding;

public class LicensesActivity extends AppCompatActivity {
    ActivityLicensesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLicensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.licensesPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                if(str.toString().equals("GU001")) {
                    Intent intent = new Intent(LicensesActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LicensesActivity.this, "버스 고유 ID가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
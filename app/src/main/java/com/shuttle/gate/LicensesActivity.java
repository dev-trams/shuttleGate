package com.shuttle.gate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.shuttle.gate.databinding.ActivityLicensesBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicensesActivity extends AppCompatActivity {
    ActivityLicensesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLicensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HashMap<Integer, String> licensesKeys = new HashMap<>();
        licensesKeys.put(1, "GU001");
        licensesKeys.put(2, "GI001");
        licensesKeys.put(3, "NA001");
        licensesKeys.put(4, "PC001");

        binding.btnLicenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean state = false;
                for (int i = 1; i < licensesKeys.size(); i++) {
                    state = binding.licensesPinEntry.getText().equals(licensesKeys.get(i));
                    if (state) {
                        Intent intent = new Intent(LicensesActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                if (!state)
                    Toast.makeText(LicensesActivity.this, "버스 고유 ID가 다릅니다.", Toast.LENGTH_SHORT).show();
            }
        });
        binding.licensesPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                boolean state = false;
                for (int i = 1; i < licensesKeys.size(); i++) {
                    state = str.toString().equals(licensesKeys.get(i));
                    if (state) {
                        Toast.makeText(LicensesActivity.this, "버스 탑승을 시작합니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LicensesActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LicensesActivity.this, "버스 고유 ID가 다릅니다.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
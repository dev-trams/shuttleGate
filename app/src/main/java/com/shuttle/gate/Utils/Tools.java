package com.shuttle.gate.Utils;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
    public Tools(){}
    public String inRow() {return "\n";}

    public String getText(TextView textView) {
        return textView.getText().toString();
    }

    public String getCode() {
        //노래고유번호 제작 (회사명+yyyyMMdd+랜덤한4자리문자)
        //등록일 기준 날짜 가져오는 변수 구현
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());

        //랜덤한 4자리 숫자+문자를 생성하여 등록일 기준 날짜와 이어붙인다.
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        String c;
        do {
            c = "" + (char) (random.nextInt(26) + 'a')
                    + (String) (String.valueOf(random.nextInt(9)))
                    + (char) (random.nextInt(26) + 'A')
                    + (String) (String.valueOf(random.nextInt(9)));
        } while (currentDate.indexOf(c) != -1);
        sb.append(c);
        String sid = currentDate + sb.toString();;
        return sid;
    }
}

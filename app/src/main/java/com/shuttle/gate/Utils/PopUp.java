package com.shuttle.gate.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;


public class PopUp {
    private Context context = null;
    private String uid = "";
    private String title = "";
    private String singer = "";
    private String number ="";
    private String aniTitle = "";
    private String stage = "";
    private String category = "";

    Tools tools = new Tools();

    public void setContext(Context context) {
        this.context = context;
    }

   /* public void setMusic(String uid, String title, String singer, String number) {
        this.uid = uid;
        this.title = title;
        this.singer = singer;
        this.number = number;
    }*/
    public void setMusic(String uid, String title, String singer, String number,
                         String aniTitle, String stage, String category) {
        this.uid = uid;
        this.title = title;
        this.singer = singer;
        this.number = number;
        this.aniTitle = aniTitle;
        this.stage = stage;
        this.category = category;
    }

    @NonNull
    private String getMusic() {
        return uid + tools.inRow() +
                title + tools.inRow() +
                singer + tools.inRow();
    }
    @NonNull
    private String getAni() {
        return aniTitle +
                tools.inRow() +
                stage +
                category;
    }

    public void showPopup(String getMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("노래방 번호 - " + number)
                .setMessage(getMessage + tools.inRow() + getMusic() + getAni());
        builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {/*팝업닫기*/}
        });
        builder.create().show();
    }
}

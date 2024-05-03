package com.shuttle.gate.Utils;
import androidx.appcompat.app.AppCompatActivity;


public class Util {
    AppCompatActivity appCompatActivity;
    public Util() {}
    public Util(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }
    public PopUp onPopup = new PopUp();
    public Tools tools = new Tools();
    public DebugLog debugLog = new DebugLog();
}

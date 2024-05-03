package com.shuttle.gate.Utils;

import android.util.Log;

import javax.crypto.MacSpi;

public class DebugLog {
    /**level code
     * 0-info, 1-debug, 2-warn, 3-error, 4-high-error
     * 201-googleAuth 202-UserData 203-E:UserData
     * 301-HTTP 302-E:HTTP
     */
    public void log(String title, int level, String msg) {
        if(title.equals("")) title = "log";
        String a;
        switch (level) {
            case 1: a = title+"/단계/관심";
                Log.d(a, getLogInfo()+msg);
                break;
            case 2: a= title+"/단계/주의";
                Log.w(a,getLogInfo()+msg);
                break;
            case 3: a= title+"/단계/위험";
                Log.e(a,getLogInfo()+msg);
                break;
            case 4: a= title+"/단계/치명";
                Log.e(a,getLogInfo()+msg);
                break;
            case 101: a=title+"/Adapter/State";
                Log.i(a, getLogInfo()+msg);
                break;
            case 201: a= title+"/Google/Auth";
                Log.d(a, getLogInfo()+msg);
                break;
            case 202: a=title+"/Google/UserData";
                Log.d(a,getLogInfo()+msg);
                break;
            case 203: a=title+"/Google/UserData";
                Log.e(a, getLogInfo()+msg);
                break;
            case 301: a=title+"/HTTP/request";
                Log.d(a, getLogInfo()+msg);
                break;
            case 302: a=title+"/HTTP/request";
                Log.e(a,getLogInfo()+msg);
                break;
            case 401: a=title+"/Array/size";
                Log.i(a, getLogInfo()+msg);
                break;
            case 501: a=title+"/BookMark/등록";
                Log.i(a, getLogInfo()+msg);
                break;
            case 502: a = title+"/BookMark/해제";
                Log.i(a, getLogInfo()+msg);
                break;
            case 601: a = title+"/Search/검색url";
                Log.i(a, getLogInfo()+msg);
                break;
            default: a=title+"/단계/평시";
                Log.i(a,getLogInfo()+msg);
                break;
        }
    }
    private String getLogInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 4) {
            StackTraceElement traceElement = stackTrace[4];
            String className = traceElement.getClassName();
            int lastDotIndex = className.lastIndexOf(".");
            if (lastDotIndex > 0 && lastDotIndex < className.length() - 1) {
                className = className.substring(lastDotIndex + 1);
            }
            return "[" + className + ":" + traceElement.getLineNumber() + "]";
        }
        return "";
    }

}

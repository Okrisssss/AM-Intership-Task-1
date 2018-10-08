package apple.example.com.amintershiptask1.receivers;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;


public class PhoneCallReceiver extends android.content.BroadcastReceiver {

    public static int lastState = TelephonyManager.CALL_STATE_IDLE;
    public static final String LOG = "PhoneCallReceiver";
    public static boolean isIncoming;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            Log.d(LOG, "Outgoing call");
        } else {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            int newState = 0;
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                newState = TelephonyManager.CALL_STATE_IDLE;
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                newState = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                newState = TelephonyManager.CALL_STATE_RINGING;
            }
            onCallStateChanged(newState);
        }
    }
    public void onCallStateChanged(int state) {
        if (lastState == state) {
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                Log.d(LOG, "Incoming call ringing");
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                    isIncoming = false;
                    Log.d(LOG, "Outgoing call started");
                } else {
                    Log.d(LOG, "Incoming call started");
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                    Log.d(LOG, "Missed call");
                } else if (isIncoming) {
                    Log.d(LOG, "Incoming call ended");
                } else {
                    Log.d(LOG, "Outgoing call ended");
                }
                break;
        }
        lastState = state;
    }
}
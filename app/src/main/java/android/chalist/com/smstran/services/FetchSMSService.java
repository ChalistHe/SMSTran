package android.chalist.com.smstran.services;

import android.app.Service;
import android.chalist.com.smstran.receiver.SMSReceiveBroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class FetchSMSService extends Service {
    private final static String TAG = "FetchSMSService";


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate in");
        SMSReceiveBroadcastReceiver smsReceiver = new SMSReceiveBroadcastReceiver(new SMSReceiveBroadcastReceiver.IMessageListener() {
            @Override
            public void onReceived(String msg) {
                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), "message: " + msg, duration);
                    toast.show();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

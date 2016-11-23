package android.chalist.com.smstran.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiveBroadcastReceiver extends BroadcastReceiver {
    private final static String TAG = "SMSReceiveBroadcastReceiver";

    private static IMessageListener mMessageListener;

    public SMSReceiveBroadcastReceiver() {
        super();
    }

    public SMSReceiveBroadcastReceiver(IMessageListener listener) {
        super();
        mMessageListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i(TAG, "senderNum: "+ senderNum + "; message: " + message);


                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                    mMessageListener.onReceived(message);
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e(TAG, "Exception smsReceiver" +e);
        }
    }

    public interface IMessageListener {
        public void onReceived(String msg);
    }
}
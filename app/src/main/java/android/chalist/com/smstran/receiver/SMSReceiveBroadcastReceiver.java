package android.chalist.com.smstran.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;

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
        Bundle extras = intent.getExtras();

        if (extras == null) {
            return;
        }

        Object[] smsExtras = (Object[]) extras.get(SmsConstant.PDUS);

        ContentResolver contentResolver = context.getContentResolver();
        Uri smsUri = Uri.parse(SmsConstant.SMS_URI);

        for (Object smsExtra : smsExtras) {
            // SMSを取り出す
            byte[] smsBytes = (byte[]) smsExtra;
            SmsMessage smsMessage = SmsMessage.createFromPdu(smsBytes);

            String body = smsMessage.getMessageBody();
            String address = smsMessage.getOriginatingAddress();

            // SMSのContentProviderに保存する
            ContentValues values = new ContentValues();
            values.put("address", address
                    values.put("body", body);

            Uri uri = contentResolver.insert(Uri.parse("content://sms/"), values);
        }
    }

    public interface IMessageListener {
        public void onReceived(String msg);
    }
}
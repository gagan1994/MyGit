package com.example.gagan.myexampleproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.regex.Matcher;

/**
 * Created by Gagan on 4/24/2018.
 */

public class SmsReceiver extends BroadcastReceiver {
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            String phoneNumber = smsMessage.getDisplayOriginatingAddress();
            String senderNum = phoneNumber;
            String messageBody = smsMessage.getMessageBody();
            Toast.makeText(context, messageBody, Toast.LENGTH_SHORT).show();
//            try {
//                if (messageBody != null) {
//                    Matcher m = p.matcher(messageBody);
//                    if (m.find()) {
//                        mListener.messageReceived(m.group(0));
//                    } else {
//                    }
//                }
//            } catch (Exception e) {
//            }
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}


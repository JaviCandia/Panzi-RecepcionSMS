package com.example.recepcindemensaje;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver {
    public void onReceive(Context context, Intent intent){
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = null;
        if (bundle != null){
            String informacion = "Sms Recibido desde: ";
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsMessages = new SmsMessage[pdus.length];
            for (int i=0; i<smsMessages.length; i++){
                smsMessages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                informacion += smsMessages[i].getOriginatingAddress()+"\n";
                informacion += "**** Mensaje ****\n";
                informacion += smsMessages[i].getMessageBody().toString();
            }
            Toast.makeText(context, informacion, Toast.LENGTH_SHORT).show();
        }
    }
}

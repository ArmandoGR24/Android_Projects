package com.example.smsrecive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String sender = smsMessage.getDisplayOriginatingAddress();
                String messageBody = smsMessage.getMessageBody();

                // Verifica si el número está en la lista
                if (isNumberInList(sender)) {
                    Toast.makeText(context, "Nuevo mensaje de: " + sender, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private boolean isNumberInList(String sender) {
        // Implementa la lógica para verificar si el número está en la lista
        List<String> phoneNumbers = Arrays.asList("8119894299", "8182600540", "27272"); // Ejemplo de números
        return phoneNumbers.contains(sender);
    }
}
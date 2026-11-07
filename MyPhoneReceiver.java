package ao.co.isptec.aplm.phonereceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG_MyPhoneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 1. Log imediato para provar que o receiver foi chamado
        Log.i(TAG, "O Broadcast Receiver foi acionado!");

        // 2. Verifica se a ação é a mudança de estado do telefone
        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {

            // 3. Obtém o estado da chamada (RINGING, OFFHOOK, IDLE)
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            // 4. Tenta obter o número, mas protege com um try-catch para não crashar
            String number = "";
            try {
                number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            } catch (Exception e) {
                number = "Desconhecido (bloqueado pelo sistema)";
            }

            // 5. Regista a informação no Logcat
            Log.i(TAG, "State: " + state + ", Number: " + number);
        }
    }
}
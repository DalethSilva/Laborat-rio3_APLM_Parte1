package ao.co.isptec.aplm.phonereceiverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // TAG é uma etiqueta usada para filtrar as mensagens no Logcat
    private static final String TAG = "TAG_MainActivity";

    // Código de identificação para sabermos que a resposta é do pedido de permissões
    private static final int PHONE_STATUS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Liga esta classe ao layout XML
        setContentView(R.layout.activity_main);
    }

    // O onStart é executado sempre que a aplicação fica visível
    @Override
    protected void onStart() {
        super.onStart();
        // Chama a função que vai verificar e pedir as permissões
        askPhonePermission();
    }

    // Função que verifica se temos as permissões e, se não tivermos, pede-as
    private void askPhonePermission() {
        // Verifica se a permissão READ_PHONE_STATE já foi dada
        int hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        // Se for diferente de GRANTED (concedida), significa que não temos permissão
        if (hasPhonePermission != PackageManager.PERMISSION_GRANTED) {
            // Pede a permissão ao utilizador (abre a janela de aviso)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_STATUS_REQUEST_CODE);
        }

        // Verifica se a permissão READ_CALL_LOG já foi dada
        hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if (hasPhonePermission != PackageManager.PERMISSION_GRANTED) {
            // Pede a permissão ao utilizador
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, PHONE_STATUS_REQUEST_CODE);
        }
    }

    // Este método é executado automaticamente quando o utilizador responde à janela de permissões
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Verifica se a resposta é do nosso pedido de permissões
        switch (requestCode) {
            case PHONE_STATUS_REQUEST_CODE: {
                // O array grantResults contém a resposta (PERMISSION_GRANTED ou PERMISSION_DENIED)
                // Se tiver tamanho maior que 0 e o primeiro for GRANTED, o utilizador aceitou
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "permissão de estado do telefone concedida");
                } else {
                    // Se não, o utilizador recusou
                    Log.i(TAG, "permissão de estado do telefone não concedida");
                }
                return;
            }
        }
    }
}
package ao.co.isptec.aplm.notepad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        // 1. Encontrar os componentes do layout
        EditText editTitle = findViewById(R.id.editTitle);
        EditText editContent = findViewById(R.id.editContent);
        Button btnOK = findViewById(R.id.btnOK);
        Button btnCancel = findViewById(R.id.btnCancel);

        // 2. Configurar o botão OK
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lê o texto escrito pelo utilizador
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();

                // Se o título não estiver vazio, guarda a nota
                if (!title.isEmpty()) {
                    // Adiciona diretamente à lista estática da ListNotesActivity
                    ListNotesActivity.titles.add(title);
                    ListNotesActivity.contents.add(content);

                    // Define o resultado como RESULT_OK e fecha a atividade
                    setResult(RESULT_OK);
                    finish();
                } else {
                    // Se o título estiver vazio, avisa o utilizador
                    editTitle.setError("O título não pode estar vazio!");
                }
            }
        });

        // 3. Configurar o botão Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha a atividade sem guardar nada
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
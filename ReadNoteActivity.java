package ao.co.isptec.aplm.notepad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        // 1. Encontrar os componentes do layout
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtContent = findViewById(R.id.txtContent);

        // 2. Receber a posição da nota que foi clicada na lista
        int position = getIntent().getIntExtra("NOTE_INDEX", -1);

        // 3. Se a posição for válida, mostra os dados da nota
        if (position != -1) {
            txtTitle.setText(ListNotesActivity.titles.get(position));
            txtContent.setText(ListNotesActivity.contents.get(position));
        }
    }
}
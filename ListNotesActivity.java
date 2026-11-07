package ao.co.isptec.aplm.notepad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNotesActivity extends AppCompatActivity {

    // Listas estáticas que guardam os títulos e os conteúdos das notas em memória.
    // O 'static' faz com que estas listas sejam partilhadas entre as várias telas.
    public static ArrayList<String> titles = new ArrayList<>();
    public static ArrayList<String> contents = new ArrayList<>();

    // Adaptador que liga a lista de títulos ao componente visual (ListView)
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        // 1. Encontrar os componentes do layout pelo seu ID
        Button btnNewNote = findViewById(R.id.btnNewNote);
        ListView listView = findViewById(R.id.listViewNotes);

        // 2. Criar o adaptador. O 'simple_list_item_1' é um layout padrão do Android para itens de lista.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);

        // 3. Configurar o clique do botão "New Note"
        btnNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma Intent para abrir a tela de criar nota
                Intent intent = new Intent(ListNotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });

        // 4. Configurar o clique num item da lista (para ler a nota)
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListNotesActivity.this, ReadNoteActivity.class);
            // Envia a posição da nota clicada para a próxima tela saber qual mostrar
            intent.putExtra("NOTE_INDEX", position);
            startActivity(intent);
        });
    }

    // 5. O onResume é chamado sempre que esta tela volta a ficar visível (ex: ao fechar a tela de criação)
    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza a lista visual caso tenha sido adicionada uma nova nota
        adapter.notifyDataSetChanged();
    }
}

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    Button buttonAddNote;
    ListView listViewNotes;
    ArrayList<String> notesList;
    ArrayAdapter<String> notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        listViewNotes = findViewById(R.id.listViewNotes);

        notesList = new ArrayList<>();
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(notesAdapter);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editTextNote.getText().toString().trim();
                if (!noteText.isEmpty()) {
                    addNoteToList(noteText);
                    editTextNote.getText().clear();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = notesList.get(position);
                editTextNote.setText(selectedNote);
                deleteNoteFromList(position);
            }
        });
    }

    private void addNoteToList(String note) {
        notesList.add(note);
        notesAdapter.notifyDataSetChanged();
    }

    private void deleteNoteFromList(int position) {
        notesList.remove(position);
        notesAdapter.notifyDataSetChanged();
    }
}

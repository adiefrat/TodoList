package huji.ac.il.todolistmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;

public class TodoListManagerActivity extends AppCompatActivity{

    private ListView todoLsv;
    private ArrayList<String> notes;
    private ArrayList<Date> dueDate;
    private huji.ac.il.todolistmanager.NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_todo_list_manager);
        notes = new ArrayList<String>();
        dueDate = new ArrayList<Date>();
        adapter = new NoteAdapter(this, R.layout.activity_note, notes, dueDate);
        todoLsv = (ListView) findViewById(R.id.lstTodoItems);
        todoLsv.setAdapter(adapter);
        super.onCreate(savedInstanceState);
        final Context context = this;
        todoLsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //todo: put option menu with delete and call.
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialogTitle = notes.get(position);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle(dialogTitle);
                if(dialogTitle.startsWith("call") || dialogTitle.startsWith("Call")){
                    final String numberToCall = dialogTitle.substring(4);
                    builder.setPositiveButton(R.string.call, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intentCall = new Intent(Intent.ACTION_CALL);
                            intentCall.setData(Uri.parse("tel:" + numberToCall));
                            startActivity(intentCall);
                        }
                    });
                }

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        notes.remove(position);
                        dueDate.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(TodoListManagerActivity.this, AddNewTodoActivity.class);
                startActivityForResult(intent,1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra("title");
                Date date = (Date)data.getSerializableExtra("date");
                notes.add(title);
                dueDate.add(date);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

package huji.ac.il.todolistmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewTodoActivity extends AppCompatActivity {
    private int year = 0, month = 0, day = 0;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);
        final Button btnOK = (Button) findViewById(R.id.btnOK);
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        intent = getIntent();
        final DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth() + 1;
                year = datePicker.getYear();
                String date =  day + "/" + month + "/" + year;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date utilDate = formatter.parse(date);
                    intent.putExtra("date", utilDate);
                } catch (ParseException e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                }
                String noteToAdd = ((EditText) findViewById(R.id.edtNewItem)).getText().toString();
                intent.putExtra("title", noteToAdd);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}

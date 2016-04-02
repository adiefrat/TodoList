package huji.ac.il.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> titles;
    private ArrayList<Date> dueDates;

    public NoteAdapter(Context context, int resource, ArrayList<String> titles, ArrayList<Date> dueDates) {
        super(context, resource, titles);
        this.context = context;
        this.titles = titles;
        this.dueDates = dueDates;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View note = inflater.inflate(R.layout.activity_note, parent, false);
        TextView tv =  (TextView) note.findViewById(R.id.txtTodoTitle);
        TextView dateDue =  (TextView) note.findViewById(R.id.txtTodoDueDate);
        String item  = this.titles.get(position);
        Date dueDate = this.dueDates.get(position);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dueDateStr = df.format(dueDate).toString();
        dateDue.setText(dueDateStr);
        tv.setText(item);
        Date currDate = new Date();
        if(currDate.after(dueDate) && (currDate.getDay() != dueDate.getDay() || currDate.getMonth() != dueDate.getMonth() || currDate.getYear() != dueDate.getYear())){
            tv.setTextColor(Color.RED);
            dateDue.setTextColor(Color.RED);
        }
        return note;
    }
}

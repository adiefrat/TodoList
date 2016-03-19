package huji.ac.il.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> objects;

    public NoteAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View note = inflater.inflate(R.layout.activity_note, parent, false);
        TextView tv =  (TextView) note.findViewById(R.id.notetv);
        String item  = this.objects.get(position);
        tv.setText(item);

        if(position % 2 == 0) {
            tv.setTextColor(Color.parseColor("#7E57C2"));
        } else {
            tv.setTextColor(Color.parseColor("#7986CB"));
        }
        return note;
    }
}

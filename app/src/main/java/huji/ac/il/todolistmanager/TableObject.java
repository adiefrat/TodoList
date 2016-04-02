package huji.ac.il.todolistmanager;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adi on 01/04/2016.
 */
public class TableObject
{
    ArrayList<String> titles;
    ArrayList<Date> dues;
    public TableObject(ArrayList<String> titles, ArrayList<Date> dues){
        this.titles = titles;
        this.dues = dues;
    }
}

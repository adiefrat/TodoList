package huji.ac.il.todolistmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adi on 31/03/2016.
 */

    public class DataHandler extends SQLiteOpenHelper {
    private static final String TITLE = "title", DUE = "due", ID = "_id",    //columns
            DB_NAME = "todo_db", TABLE_NAME = "todotable",
            TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID +
                    " integer primary key autoincrement," + TITLE + " TEXT," + DUE + " LONG )";
    private static final int DB_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;

    public DataHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void close() {
        db.close();
    }
    public void insert(String title, Date due) {
        ContentValues content = new ContentValues();
        content.put(TITLE, title);
        content.put(DUE, due.getTime());
        db.insertOrThrow(TABLE_NAME, null, content);
    }
    public void deleteTodo(String item, Date due) {
        db.delete(TABLE_NAME, TITLE + " = '" + item + "'", null);
    }

    public TableObject returnItems() {
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Date> dues = new ArrayList<Date>();
        TableObject tableObj = new TableObject(titles, dues);
        String selectAllTodos = "SELECT * FROM " + TABLE_NAME;
        Cursor crs = db.rawQuery(selectAllTodos, null);
        if (crs.getCount() == 0) {
            crs.close();
            return tableObj;
        }
        crs.moveToFirst();
        for (int i = 0; i < crs.getCount(); i++) {
            titles.add(crs.getString(1));
            String datestr = crs.getString(2);
            Date d = new Date(Long.parseLong(datestr));
            dues.add(d);
            crs.moveToNext();
        }
        crs.close();
        tableObj = new TableObject(titles, dues);
        return tableObj;
    }
}


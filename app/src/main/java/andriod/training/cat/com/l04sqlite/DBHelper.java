package andriod.training.cat.com.l04sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by LqLconf on 11/9/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    final private static String DATABASE_NAME = "messages.db";
    final private static String TABLE_MESSAGES = "messages";
    final private static String MESSAGE_ID = "msg_id";
    final private static String MESSAGE_TITLE = "msg_title";
    final private static String MESSAGE_SUBTITLE = "msg_subtitle";
    final private static String MESSAGE_TEXT = "msg_text";
    final private static String MESSAGE_TIMESTAMP = "msg_timestamp";

    DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_MESSAGES + " (" +
                    MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // MD5(MESSAGE_TEXT)
                    MESSAGE_TITLE + " TEXT, " +
                    MESSAGE_SUBTITLE + " TEXT, " +
                    MESSAGE_TEXT + " TEXT, " +
                    MESSAGE_TIMESTAMP + " INTEGER)"
            );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
            onCreate(db);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void onReset() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            onUpgrade(db,1,2);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    String selectMessages () {
        StringBuilder rows = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MESSAGES + " ORDER BY " + MESSAGE_TIMESTAMP + " ASC" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    String row = "<p>"+cursor.getInt(0)+":"+cursor.getString(1)+" <b>"+cursor.getString(2)+"</b> :: ["+cursor.getString(4)+"]<br/>&nbsp;&nbsp;&nbsp;"+cursor.getString(3)+"</p>\r\n";
                    rows.append(row);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rows.toString();
    }

    void insertMessage (String title, String subtitle, String text, String timestamp) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MESSAGE_TITLE, title);
            contentValues.put(MESSAGE_SUBTITLE, subtitle);
            contentValues.put(MESSAGE_TEXT, text);
            contentValues.put(MESSAGE_TIMESTAMP, timestamp);
            db.insert(TABLE_MESSAGES, null, contentValues);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
    ArrayList<QueryObject> selectMessagesToList () {
        ArrayList<QueryObject> rows = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MESSAGES + " ORDER BY " + MESSAGE_TIMESTAMP + " ASC" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    QueryObject i = new QueryObject(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    rows.add(i);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rows;
    }


}

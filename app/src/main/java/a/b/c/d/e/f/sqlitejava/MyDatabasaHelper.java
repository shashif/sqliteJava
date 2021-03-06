package a.b.c.d.e.f.sqlitejava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabasaHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Student.db";
    private static final String TABLE_NAME="student_details";
    private static final String ID="_id";
    private static final String NAME="Name";
    private static final String AGE="Age";
    private static final String GENDER="Gender";
    private static final String SQL1="CREATE TABLE  "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) , "+AGE+" INTEGER,"+GENDER+" VARCHAR(10) );";
    private static final String SQL2="DROP TABLE IF EXISTS student_details";
    private static final String SQL3="SELECT * FROM "+TABLE_NAME;
    private static final  int VERSION_NUMBER=2;

    public MyDatabasaHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context,"onCreate Called",Toast.LENGTH_LONG).show();
            db.execSQL(SQL1);
        }catch (Exception e)
        {

            Toast.makeText(context, (CharSequence) e,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"onUpgrade Called",Toast.LENGTH_LONG).show();
            db.execSQL(SQL2);
            onCreate(db);
        }catch (Exception e)
        {
            Toast.makeText(context, (CharSequence) e,Toast.LENGTH_LONG).show();
        }


    }

    public long insertData(String name,String age, String gender)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        long rowID=db.insert(TABLE_NAME,null,contentValues);
        return rowID;
    }


    public Cursor displayAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(SQL3,null);
        return cursor;

    }

    public boolean updataDAta(String id,String name, String age,String gender)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);

        db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{id});
        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?",new String[]{id});
    }

}

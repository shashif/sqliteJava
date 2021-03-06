package a.b.c.d.e.f.sqlitejava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEdit,ageEdit,genderEdit,idEditID;
    Button addButton,showButton,updateBtn,deleteBtn;
    MyDatabasaHelper mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdb= new MyDatabasaHelper(this);
        SQLiteDatabase sqLiteDatabase=mdb.getWritableDatabase();

        nameEdit=(EditText) findViewById(R.id.nameEditID);
        ageEdit=(EditText) findViewById(R.id.ageEditID);
        genderEdit=(EditText) findViewById(R.id.genderEditID);
        idEditID=(EditText) findViewById(R.id.idEditID);
        addButton= (Button)findViewById(R.id.addBtn);
        showButton= (Button)findViewById(R.id.showBtn);
        updateBtn= (Button)findViewById(R.id.updateBtn);
        deleteBtn= (Button)findViewById(R.id.deleteBtn);

   addButton.setOnClickListener(this);
   showButton.setOnClickListener(this);
   updateBtn.setOnClickListener(this);
   deleteBtn.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        String name=nameEdit.getText().toString();
        String age=ageEdit.getText().toString();
        String gender=genderEdit.getText().toString();
        String id=idEditID.getText().toString();


        if (v.getId()==R.id.addBtn)
        {


            nameEdit.setText("");
            ageEdit.setText("");
            genderEdit.setText("");
//                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();

            long rowID= mdb.insertData(name,age,gender);
            if (rowID==-1)
            {
                Toast.makeText(this,"Not Inserted",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"Sucessfully Data Inserted. Row: "+rowID+"",Toast.LENGTH_LONG).show();
            }
        }

        if (v.getId()==R.id.showBtn){

//            Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
//            SQLiteDatabase sqLiteDatabase=mdb.getWritableDatabase();
            Cursor cursor=mdb.displayAllData();
            if (cursor.getCount()==0)
            {
                //no data
                showData("Error","No data");
                return;
            }

            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext())
            {
                stringBuffer.append("ID :"+cursor.getString(0)+"\n");
                stringBuffer.append("Name :"+cursor.getString(1)+"\n");
                stringBuffer.append("AGE :"+cursor.getString(2)+"\n");
                stringBuffer.append("GENDER :"+cursor.getString(3)+"\n\n");

            }
            showData("ResultSet",stringBuffer.toString());
        }

        else if (v.getId()==R.id.updateBtn)
        {
            Boolean isUpdated=mdb.updataDAta(id,name,age,gender);
            if (isUpdated==true)
            {
                Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(this,"No Data Updated",Toast.LENGTH_LONG).show();
            }
        }

        if (v.getId()==R.id.deleteBtn)
        {
            int value=mdb.deleteData(id);
            if (value>0){
                Toast.makeText(this,"Data Deleted",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Not Deleted",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void showData(String title, String data) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();


    }


}
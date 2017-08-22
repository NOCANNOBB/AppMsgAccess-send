package com.example.zhang.backgroundsoundplay;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BackGroundActivity extends AppCompatActivity {
    ContentResolver cr;
    SQLiteDatabase sld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_ground);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);


        cr = this.getContentResolver();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String stuname = "Android";
                Cursor cur = cr.query(Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},"stuname=?",new String[]{stuname},
                        "sage ASC");
                while (cur.moveToNext()){

                }*/
                createOrOpenDataBase();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String stuname = "Android";
                Cursor cur = cr.query(Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},"stuname=?",new String[]{stuname},
                        "sage ASC");
                while (cur.moveToNext()){

                }*/
                closeDatabase();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String stuname = "Android";
                Cursor cur = cr.query(Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},"stuname=?",new String[]{stuname},
                        "sage ASC");
                while (cur.moveToNext()){

                }*/
                insert();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String stuname = "Android";
                Cursor cur = cr.query(Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},"stuname=?",new String[]{stuname},
                        "sage ASC");
                while (cur.moveToNext()){

                }*/
                delete();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String stuname = "Android";
                Cursor cur = cr.query(Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},"stuname=?",new String[]{stuname},
                        "sage ASC");
                while (cur.moveToNext()){

                }*/
                query();
            }
        });
    }
    public void createOrOpenDataBase(){
        try{
            sld = SQLiteDatabase.openDatabase("/data/data/com.pp4/mydb",
                    null,
                    SQLiteDatabase.OPEN_READWRITE |
                    SQLiteDatabase.CREATE_IF_NECESSARY);
            String SQL = "create table if not exists student" + "(sno char(5),stuname varchar(20),"
                    + "sage integer,sclass char(5)";
            Toast.makeText(getBaseContext(),"成功创建数据库",Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeDatabase(){
        try{
            sld.close();
            Toast.makeText(getBaseContext(),"成功关闭数据库",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insert(){
        try {
            String SQL = "insert into student values" + "('001','Android','22','283')";
            sld.execSQL(SQL);
            Toast.makeText(getBaseContext(), "成功插入一条记录", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete(){
        try{
            String sql = "delete from student;";
            sld.execSQL(sql);
            Toast.makeText(getBaseContext(),"成功删除所有记录",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void query(){
        try{
            String sql = "select * from student where sage>?";
            Cursor cur = sld.rawQuery(sql,new String[]{"20"});
            while (cur.moveToNext()){
                String sno = cur.getString(0);
                String name = cur.getString(1);
                int age = cur.getInt(2);
                String sclass = cur.getString(3);
                Toast.makeText(getBaseContext(),"查询到的记录为：'" + sno + "'\t'" + name
                + "'\t\t'" + age + "'\t'" + sclass + "'",Toast.LENGTH_LONG).show();
            }
            cur.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

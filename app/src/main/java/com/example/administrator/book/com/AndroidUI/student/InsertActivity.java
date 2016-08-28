package com.example.administrator.book.com.AndroidUI.student;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.example.administrator.book.com.model.Student;
import com.example.administrator.book.com.control.StudentControl;
public class InsertActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText no;
    private EditText major;
    private EditText Class;
    private EditText mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        no=(EditText)findViewById(R.id.etupdatano);
        name=(EditText)findViewById(R.id.etupdataname);
        Class= (EditText)findViewById(R.id.etupdataclass);
        major= (EditText)findViewById(R.id.etupdatamajor);
        mobile= (EditText)findViewById(R.id.etupdatamobile);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.insert){
             String studentNo; //学号
             String studentName;
             String studentMajor;
             String studentClass;
             String studentMobile;
            studentName=name.getText().toString().trim();
            studentNo=no.getText().toString().trim();
            studentClass=Class.getText().toString().trim();
            studentMajor=major.getText().toString().trim();
            studentMobile=mobile.getText().toString().trim();

            Student s1=new Student(studentNo, studentName, studentMajor, studentClass, studentMobile);
            StudentControl stucontrol=new StudentControl (InsertActivity.this);

            if(studentName.equals("")||studentNo.equals("")||studentMajor.equals("")||studentClass.equals("")||studentMobile.equals("")){
                new AlertDialog.Builder(InsertActivity.this).setMessage("不能有空行").show();
            }
            else{
                if(stucontrol.QueryOnByNo(studentNo)!=null){
                    Toast.makeText(InsertActivity.this,"该学生已经存在",Toast.LENGTH_SHORT).show();
                }
                else{
                    stucontrol.addStudent(s1);
                    {
                        no.setText("");
                        name.setText("");
                        major.setText("");
                        Class.setText("");
                        mobile.setText("");
                    }
                    buildDialog();
                }
            }
        }
    }
    private void buildDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(InsertActivity.this, android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("插入成功，是否继续插入");
        builder.setNegativeButton("返回上一页",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int whichButton){
               finish();
            }
        });
        builder.setPositiveButton("继续插入",null);
        builder.show();
    }
}

package com.example.administrator.book.com.AndroidUI.student;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.example.administrator.book.com.control.StudentControl;
import com.example.administrator.book.com.model.Student;

public class UpdataActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText major;
    private EditText mobile;
    private EditText no;
    private EditText name;
    private EditText Class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        major= (EditText)findViewById(R.id.etupdatamajor);
        mobile= (EditText)findViewById(R.id.etupdatamobile);
        no=(EditText)findViewById(R.id.etupdatano);
        Class=(EditText)findViewById(R.id.etupdataclass);
        name=(EditText)findViewById(R.id.etupdataname);
    }
    @Override
    public void onClick(View v){
        String studentMajor;
        String studentMobile;
        String studentNo;
        String studentClass;
        String studentName;
        studentNo=no.getText().toString().trim();
        studentMajor=major.getText().toString().trim();
        studentMobile=mobile.getText().toString().trim();
        studentClass=Class.getText().toString().trim();
        studentName=name.getText().toString().trim();

        Student s=new Student(studentNo,studentName,studentMajor,studentClass,studentMobile);
        StudentControl stucontrol=new StudentControl (UpdataActivity.this);

        if(studentNo.equals("")||studentMajor.equals("")||studentMobile.equals("")||studentClass.equals("")||studentName.equals("")){
            new AlertDialog.Builder(UpdataActivity.this).setMessage("不能有空行").show();
        }
        else{
            if(stucontrol.QueryOnByNo(studentNo)!=null){
                stucontrol.updataByNo( s);
                {
                    no.setText("");
                    major.setText("");
                    mobile.setText("");
                    name.setText("");
                    Class.setText("");
                }
                buildDialog();
            }
            else{
                Toast.makeText(UpdataActivity.this,"该学生不存在",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void buildDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(UpdataActivity.this, android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("更新成功，是否继续更新");
        builder.setNegativeButton("返回上一页",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int whichButton){
                finish();
            }
        });
        builder.setPositiveButton("继续更新",null);
        builder.show();
    }
}

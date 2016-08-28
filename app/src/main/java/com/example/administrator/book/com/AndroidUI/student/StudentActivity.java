package com.example.administrator.book.com.AndroidUI.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.example.administrator.book.com.AndroidUI.MainActivity;
import com.example.administrator.book.com.control.StudentControl;
import com.example.administrator.book.com.model.Student;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit;
    StudentControl studentcontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentcontrol=new StudentControl (StudentActivity.this);
        setContentView(R.layout.activity_student);
    }
    @Override
    public void onClick(View v){

        //插入
        if(v.getId()==R.id.button6){
            Intent intent=new Intent();
            intent.setClass(StudentActivity.this, InsertActivity.class);
            StudentActivity.this.startActivity(intent);
        }
        //删除
        if(v.getId()==R.id.button5){
            edit=new EditText(StudentActivity.this);
            new AlertDialog.Builder(StudentActivity.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int whichButton){
                            String na=edit.getText().toString();
                           if( studentcontrol.deleteStudentByNo(na)==true)
                            Toast.makeText(StudentActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            else
                            Toast.makeText(StudentActivity.this,"无这个人",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .show();
        }
        //更新
        if(v.getId()==R.id.button9){
            Intent intent=new Intent();
            intent.setClass(StudentActivity.this,UpdataActivity.class);
            StudentActivity.this.startActivity(intent);
        }
        //查询
        if(v.getId()==R.id.button8){
            edit=new EditText(StudentActivity.this);
            new AlertDialog.Builder(StudentActivity.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int whichButton){
                            String na=edit.getText().toString();
                            Student s[]=studentcontrol.QueryOnByNo(na);
                            if(s!=null)
                                new AlertDialog.Builder(StudentActivity.this)
                                        .setTitle("结果为")
                                        .setMessage(s[0].getStudentName()+" "+s[0].getStudentClass()+" "+s[0].getStudentMajor()+" "+s[0].getStudentMobile())
                                        .show();
                            else Toast.makeText(StudentActivity.this,"没有符合的记录",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .show();
        }
       //全部保存
        if(v.getId()==R.id.buttonSaveAll){
            studentcontrol.saveAll();
            Toast.makeText(StudentActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
        }
        //全部删除
        if(v.getId()==R.id.buttonDeleteAll){
            studentcontrol.deleteAll();
            Toast.makeText(StudentActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
        }
        //输出全部信息
        if(v.getId()==R.id.button7){
            Intent intent=new Intent();
            intent.setClass(StudentActivity.this,showAllStudentActivity.class);
            StudentActivity.this.startActivity(intent);
        }
    }
}

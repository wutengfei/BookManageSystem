package com.AndroidUI.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.administrator.book.R;
import com.control.StudentControl;
import com.model.Student;
import com.model.StudentSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class showAllStudentActivity extends AppCompatActivity {
    public StudentSet studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_student);
        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        StudentControl stucontrol=new StudentControl (showAllStudentActivity.this);
        Student s[]=stucontrol.getAllStudent();

        if(s!=null)
        for (int i = 0; i < s.length; i++) {
             HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("no", s[i].getStudentNo());
            item.put("name",s[i].getStudentName());
            item.put("phone", s[i].getStudentMobile());
            item.put("class", s[i].getStudentClass());
            item.put("major", s[i].getStudentMajor());
            data.add(item);
        }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.activity_student_listview,
                new String[]{"no", "name", "class", "major", "phone"},
                new int[]{R.id.tv_No, R.id.tv_Name, R.id.tv_Class, R.id.tv_Major, R.id.tv_Mobile});
        //实现列表的显示
        lv.setAdapter(adapter);

    }
}

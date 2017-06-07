package com.AndroidUI.borrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.administrator.book.R;
import com.control.BorrowControl;
import com.control.StudentControl;
import com.model.Borrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BorrowStudentBorrowSelect extends AppCompatActivity {
    private TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_student_borrow_select);
        textView = (TextView) findViewById(R.id.textView8);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void select(View v) {
        int j = 0;
        String studentNo = editText.getText().toString().trim();
        BorrowControl borrowControl = new BorrowControl(BorrowStudentBorrowSelect.this);
        String KEY_NO = "no";
        Borrow borrow[] = borrowControl.getBorrowMessage(KEY_NO,studentNo);
        StudentControl studentControl=new StudentControl(BorrowStudentBorrowSelect.this);

        ListView lv = (ListView) findViewById(R.id.lv);


        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        if (borrow != null)
            for (int i = 0; i < borrow.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("bookno", borrow[i].getBookno());
                item.put("borrowDate", borrow[i].getBorrowDate());
                data.add(item);
                //查询到的借阅数组中有几次该学生的学号，就是借阅了几本
                if (borrow[i].getStudentNo().equals(studentNo))
                    j++;
            }

        if (studentControl.QueryOnByNo(studentNo) == null) {//检测图书是否存在
            Toast.makeText(BorrowStudentBorrowSelect.this, "没有此学生", Toast.LENGTH_SHORT).show();
        } else {

            textView.setText("学号为" + studentNo + "的学生共借阅书籍" + j + "本");
            //创建SimpleAdapter适配器将数据绑定到item显示控件上
            SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.borrow_listview2, new String[]{"bookno", "borrowDate"},
                    new int[]{R.id.tv_bookno, R.id.tv_borrowDate});
            //实现列表的显示
            lv.setAdapter(adapter);
        }
    }
}

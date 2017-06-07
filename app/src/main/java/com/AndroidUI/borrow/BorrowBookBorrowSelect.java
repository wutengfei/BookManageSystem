package com.AndroidUI.borrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.administrator.book.R;
import com.control.BookControl;
import com.control.BorrowControl;
import com.model.Borrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BorrowBookBorrowSelect extends AppCompatActivity {
    private TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book_borrow_select);
        textView = (TextView) findViewById(R.id.textView8);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void select(View v) {
        int j = 0;
        String bookno = editText.getText().toString().trim();
        BorrowControl borrowControl = new BorrowControl(BorrowBookBorrowSelect.this);
        BookControl bookControl = new BookControl(BorrowBookBorrowSelect.this);
        String KEY_BOOKNO = "bookno";
        Borrow borrow[] = borrowControl.getBorrowMessage(KEY_BOOKNO,bookno);
        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        if (borrow != null)

            for (int i = 0; i < borrow.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("no", borrow[i].getStudentNo());
                item.put("borrowDate", borrow[i].getBorrowDate());
                data.add(item);

                //查询到的借阅数组中有几次该学生的学号，就是借阅了几本
                if (borrow[i].getBookno().equals(bookno))
                    j++;
            }

        if (bookControl.QueryOnByNo(bookno) == null) {//检测图书是否存在
            Toast.makeText(BorrowBookBorrowSelect.this, "没有此图书，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            if (data != null) {
                textView.setText("编号为" + bookno + "的图书，共有" + j + "个学生借走此书");
                //创建SimpleAdapter适配器将数据绑定到item显示控件上
                SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.borrow_listview3, new String[]{"no", "borrowDate"},
                        new int[]{R.id.tv_no, R.id.tv_borrowDate});
                //实现列表的显示
                lv.setAdapter(adapter);
            } else {
                Toast.makeText(BorrowBookBorrowSelect.this, "该图书还没有被借阅", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

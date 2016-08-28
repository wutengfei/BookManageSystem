package com.example.administrator.book.com.AndroidUI.borrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.administrator.book.R;
import com.example.administrator.book.com.control.BorrowControl;
import com.example.administrator.book.com.model.Borrow;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BorrowShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_show);
        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        BorrowControl borrowControl = new BorrowControl(BorrowShow.this);
        Borrow borrow[] = borrowControl.getAllBorrow();

        if (borrow != null)
            for (int i = 0; i < borrow.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("studentNo", borrow[i].getStudentNo());
                item.put("bookno", borrow[i].getBookno());
                item.put("borrowDate", borrow[i].getBorrowDate());
                data.add(item);
            }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.borrow_listview,new String[]{"studentNo", "bookno", "borrowDate"},
                new int[]{R.id.tv_no, R.id.tv_bookno, R.id.tv_borrowDate});
        //实现列表的显示
        lv.setAdapter(adapter);

    }
    
}

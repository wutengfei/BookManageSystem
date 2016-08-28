package com.example.administrator.book.com.AndroidUI.borrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.administrator.book.R;
import com.example.administrator.book.com.model.Borrow;

public class BorrowMessageSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_message_select);
    }
    //查看所有借阅信息
    public void show(View v) {
        Intent intent = new Intent(this, BorrowShow.class);
        startActivity(intent);
    }
 //学生借阅情况查询
    public void student(View v) {
        Intent intent = new Intent(this, BorrowStudentBorrowSelect.class);
        startActivity(intent);
    }
 //图书借阅情况查询
    public void book(View v) {
        Intent intent = new Intent(this, BorrowBookBorrowSelect.class);
        startActivity(intent);
    }
 //超期借书查询
    public void over(View v) {
        Intent intent = new Intent(this, BorrowOverDateSelect.class);
        startActivity(intent);
    }

}

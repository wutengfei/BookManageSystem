package com.AndroidUI.borrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.administrator.book.R;

public class BorrowManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_manage);
    }
    //借书
    public void borrow(View v) {
        Intent intent = new Intent(this, BorrowBook.class);
        startActivity(intent);
    }
    //还书
    public void back(View v) {
        Intent intent = new Intent(this, BorrowReturn.class);
        startActivity(intent);
    }
    //借阅信息查询
    public void borrowMessageSelect(View v) {
        Intent intent = new Intent(this, BorrowMessageSelect.class);
        startActivity(intent);
    }
}

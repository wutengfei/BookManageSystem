package com.AndroidUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.book.R;
import android.content.Intent;
import com.AndroidUI.book.BookMessageManage;
import com.AndroidUI.borrow.BorrowManage;
import com.AndroidUI.student.StudentActivity;
import com.model.BookSet;
import com.model.StudentSet;


public class MainActivity extends AppCompatActivity {
    private static final int item1 = Menu.FIRST;
    private static final int item2 = Menu.FIRST + 1;
    private static final int item3 = Menu.FIRST + 2;
    private static final int item4 = Menu.FIRST + 3;
    private static StudentSet studentSet;
    private static BookSet bookSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, item1, 0, "学生信息管理");
        menu.add(0, item2, 0, "图书信息管理");
        menu.add(0, item3, 0, "图书借阅管理");
        menu.add(0, item4, 0, "退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case item1:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StudentActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case item2:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, BookMessageManage.class);
                MainActivity.this.startActivity(intent2);
                break;
            case item3:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, BorrowManage.class);
                MainActivity.this.startActivity(intent3);
                break;
            case item4:
                quit();
                break;
        }
        return true;
    }

    public void quit() {
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(this).create();
        // 设置对话框标题
        isExit.setTitle("系统提示");
        // 设置对话框消息
        isExit.setMessage("确定要退出吗");
        // 添加选择按钮并注册监听
        isExit.setButton("确定", listener);
        isExit.setButton2("取消", listener);
        // 显示对话框
        isExit.show();
    }

    //监听返回键退出事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }

    private DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };
}

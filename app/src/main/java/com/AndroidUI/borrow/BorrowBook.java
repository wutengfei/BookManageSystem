package com.AndroidUI.borrow;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import com.example.administrator.book.R;

import com.control.BookControl;
import com.control.BorrowControl;
import com.control.StudentControl;
import com.model.Book;
import com.model.Borrow;
import com.model.Student;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowBook extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

    }

    public void borrow(View v) {
        String studentNo = editText.getText().toString().trim();
        String bookno = editText2.getText().toString().trim();
        String KEY_studentNo = "no";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String borrowDate = formatter.format(curDate);


        BorrowControl borrowControl = new BorrowControl(BorrowBook.this);
        Borrow borrows[] = borrowControl.getBorrowMessage(KEY_studentNo, studentNo);

        StudentControl studentControl = new StudentControl(BorrowBook.this);
        Student student[] = studentControl.QueryOnByNo(studentNo);

        BookControl bookControl = new BookControl(BorrowBook.this);
        Book book[] = bookControl.QueryOnByNo(bookno);




        if (studentNo.equals("") || bookno.equals("")) {
            new android.app.AlertDialog.Builder(BorrowBook.this).setMessage("请填写完整").show();
        } else {
            if (studentControl.QueryOnByNo(studentNo) == null) {//检测学生是否已存在
                Toast.makeText(BorrowBook.this, "没有此学生", Toast.LENGTH_SHORT).show();
            } else {
                if (book == null) {//检测图书是否存在
                    Toast.makeText(BorrowBook.this, "没有此图书", Toast.LENGTH_SHORT).show();
                } else {
                    int surplus;//剩余量
                    int totalnum = Integer.parseInt(book[0].getTotalnum());
                    int borrownum = Integer.parseInt(book[0].getBorrownum());
                    surplus = totalnum - borrownum;//库存量=总库存-借出量

                    if (surplus == 0) {//存库量为零
                        Toast.makeText(BorrowBook.this, "该图书已被借完", Toast.LENGTH_LONG).show();
                    } else {
                        String studentMobile = student[0].getStudentMobile();
                        Borrow borrow = new Borrow(studentNo, bookno, borrowDate, studentMobile);

                        if (borrowControl.isBorrowed(studentNo, bookno) != 0)//是否借过此书
                            Toast.makeText(BorrowBook.this, "您已借过此图书", Toast.LENGTH_LONG).show();

                        else if (borrows != null && borrows.length >= 5) {//借书超过五本
                            Toast.makeText(BorrowBook.this, "您已借阅了5本书，不能再借阅 ", Toast.LENGTH_LONG).show();
                        } else if (borrowControl.addBorrow(borrow)) {//借书成功

                            borrownum = borrownum + 1;//更新借出量
                            book[0].setBorrownum(String.valueOf(borrownum));

                            borrowControl.updateBorrow(book[0]);
                            Toast.makeText(BorrowBook.this, "您已成功借阅编号为" + book[0].getBookno() +
                                    "《" + book[0].getBookname() + "》" + "请于一个月内归还", Toast.LENGTH_LONG).show();
                        }


                    }

                }


            }
        }

    }

}




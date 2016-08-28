package com.example.administrator.book.com.control;

import android.content.Context;
import com.example.administrator.book.com.model.DBAdapter;
import com.example.administrator.book.com.model.Student;
import com.example.administrator.book.com.model.StudentSet;

/**
 * Created by Administrator on 2016/7/17.
 */
public interface StudentControlInterfece {
    public void addStudent(Student s1);
    public void saveAll();
    public void deleteAll();
    public boolean deleteStudentByNo(String no);
    public void updataByNo(Student e);
    public Student [] QueryOnByNo(String no);
    public Student[] getAllStudent();
    }


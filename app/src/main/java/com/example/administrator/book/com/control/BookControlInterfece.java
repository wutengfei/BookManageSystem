package com.example.administrator.book.com.control;

import com.example.administrator.book.com.model.Student;

/**
 * Created by Fly Wu on 2016/7/19.
 */
public interface BookControlInterfece {
    public void addStudent(Student s1);
    public void saveAll();
    public void deleteAll();
    public boolean deleteStudentByNo(String no);
    public boolean updataByNo(Student e);
    public Student [] QueryOnByNo(String no);
    public Student[] getAllStudent();
}

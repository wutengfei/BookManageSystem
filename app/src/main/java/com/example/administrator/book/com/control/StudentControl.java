package com.example.administrator.book.com.control;

import android.content.Context;
import com.example.administrator.book.com.model.DBAdapter;
import com.example.administrator.book.com.model.StudentSet;
import com.example.administrator.book.com.model.Student;
/**
 * Created by Administrator on 2016/7/16.
 */
public class StudentControl implements StudentControlInterfece{
    private static DBAdapter stuDB;
    private static StudentSet set;
    Context context;
    public StudentControl(Context context){
        this.context=context;
        stuDB=new DBAdapter(context);
        stuDB.open();

    }
    public void addStudent(Student s1){
        stuDB.insert(s1);
        stuDB.close();
    }
    public void saveAll() {
        set=StudentSet.getStudentList();
        set.readFile(context);
        //使用事务可以极大地加快插入速度，不再是一条一条插入，而是暂存在缓存区最后一起插入数据库
        stuDB.db.beginTransaction();//开启事务
        for (int i = 0; i < set.size(); i++) {
            stuDB.insert(set.get(i));
        }
        stuDB.db.setTransactionSuccessful();// 设置事务标志为成功，当结束事务时就会提交事务
        stuDB.db.endTransaction();// 结束事务
        stuDB.close();
    }
    public void deleteAll(){
        stuDB.deleteAllData();
    }
    public boolean deleteStudentByNo(String no){
        Student s[]= stuDB.getOneByNo(no);
        if(s!=null) {
            stuDB.deleteOneDataByNo(no);
            stuDB.close();
            return true;
        }
        return false;
    }
    public void updataByNo(Student e)
    {
        String no=e.getStudentNo();
        Student s[]= stuDB.getOneByNo(no);
        if(s!=null) {
            stuDB.updateOneDataByNo(no, e);
            stuDB.close();
        }
    }
    public Student [] QueryOnByNo(String no){
        return stuDB.getOneByNo(no);
    }
    public StudentSet getAllStudentSet(){
        return set;
    }
    public Student[] getAllStudent(){
        return stuDB.getAllStu();
    }
}

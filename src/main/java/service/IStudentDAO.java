package service;

import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    public void add(Student student) throws SQLException;

    public Student findById(int id);

    public List<Student> findAll();

    public boolean delete(int id) throws SQLException;

    public boolean update(Student student) throws SQLException;


}

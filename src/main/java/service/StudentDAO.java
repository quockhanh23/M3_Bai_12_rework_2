package service;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

    public StudentDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/demo4?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("insert into students(id, name, age, address)" + "value (?, ?, ?,?)");
        ) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
        }
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("select * from students where id =?");
        ) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id1 = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String address = rs.getString("address");
                int age = Integer.parseInt(rs.getString("age"));
                student = new Student(id1, name, age, address);
            }
        } catch (SQLException e) {

        }
        return student;

    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("select * from students");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = Integer.parseInt(rs.getString("age"));
                String address = rs.getString("address");
                studentList.add(new Student(id, name, age, address));
            }

        } catch (SQLException e) {
        }
        return studentList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("delete from students where id = ?")) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (" update students set name = ?, age = ?, address = ? where id = ?;")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.setString(3, student.getAddress());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}

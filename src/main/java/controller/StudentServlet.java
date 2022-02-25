package controller;

import model.Student;
import service.IStudentDAO;
import service.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    IStudentDAO iStudentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListStudent(request, response);
                break;
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                showDelete(request, response);
                break;
        }
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = iStudentDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/delete.jsp");
        request.setAttribute("delete", student);
        dispatcher.forward(request, response);

    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = iStudentDAO.findById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/edit.jsp");
        request.setAttribute("edit", student);
        requestDispatcher.forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/list.jsp");
        List<Student> studentList = iStudentDAO.findAll();
        request.setAttribute("alo", studentList);
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    saveCreate(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    saveEdit(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    saveDelete(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void saveDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        iStudentDAO.delete(id);
        response.sendRedirect("/students");
    }

    private void saveEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Student student = new Student(id, name, age, address);
        iStudentDAO.update(student);
        response.sendRedirect("/students");
    }

    private void saveCreate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        iStudentDAO.add(new Student(id, name, age, address));
        response.sendRedirect("/students");
    }
}

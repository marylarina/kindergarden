package com.example.servlets;

import com.google.gson.Gson;
import com.example.controllers.ChildControllerImpl;
import com.example.entity.Child;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/child/*")
public class ChildServlet extends MyHttpServlet{

    private ChildControllerImpl childController;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        childController = ChildControllerImpl.getInstance();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/add")) {

            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String patronymic = request.getParameter("patronymic");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");
            String group_Id = request.getParameter("group_id");

            String jsonResponse = null;
            try {
                jsonResponse = childController.Add(surname, name, age, gender, group_Id, patronymic);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, jsonResponse);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/edit")) {

            String child_id = request.getParameter("child_id");
            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String patronymic = request.getParameter("patronymic");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");
            String group_Id = request.getParameter("group_id");

            String jsonResponse = null;
            try {
                jsonResponse = childController.Update(child_id, surname, name, age, gender, group_Id, patronymic);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, jsonResponse);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/get")) {

            List<Child> children = null;
            try {
                children = childController.All();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String jsonResponse = gson.toJson(children);
            sendJsonResponse(response, jsonResponse);
        } else if (pathInfo.equals("/bygroup")) {
            String group_id = request.getParameter("group_id");
            List<Child> children = null;
            try {
                children = childController.getByGroupId(group_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String jsonResponse = gson.toJson(children);
            sendJsonResponse(response, jsonResponse);

        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo(); // Получаем дополнительный пут
        if (pathInfo.equals("/delete")) {
            String child_id = request.getParameter("child_id");

            String jsonResponse = null;
            try {
                jsonResponse = childController.Delete(child_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, jsonResponse);

        }
    }
}

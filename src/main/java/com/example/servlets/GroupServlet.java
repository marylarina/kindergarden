package com.example.servlets;

import com.google.gson.Gson;
import com.example.controllers.GroupControllerImpl;
import com.example.entity.Group;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/group/*")
public class GroupServlet extends MyHttpServlet{
    private GroupControllerImpl groupController;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        groupController = GroupControllerImpl.getInstance();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/add")) {

            String group_name = request.getParameter("group_name");

            String jsonResponse = null;
            try {
                jsonResponse = groupController.Add(group_name);
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
            String group_id = request.getParameter("group_id");
            String group_name = request.getParameter("group_name");

            String jsonResponse = null;
            try {
                jsonResponse = groupController.Update(group_id, group_name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, jsonResponse);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/getAll")) {

            List<Group> groups = null;
            try {
                groups = groupController.All();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String jsonResponse = gson.toJson(groups);
            sendJsonResponse(response, jsonResponse);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo(); // Получаем дополнительный пут
        if (pathInfo.equals("/delete")) {
            String group_id = request.getParameter("group_id");

            String jsonResponse = null;
            try {
                jsonResponse = groupController.Delete(group_id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendJsonResponse(response, jsonResponse);
        }
    }
}

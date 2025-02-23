package com.example.todo.servlet;

import com.example.todo.dao.TodoDao;
import com.example.todo.model.Todo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/todos", "/todos/new"})
public class TodoServlet extends HttpServlet {

    private final TodoDao todoDao = new TodoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        switch (servletPath) {
            case "/todos/new":
                request.getRequestDispatcher("/WEB-INF/views/todoForm.jsp").forward(request, response);
                break;

            case "/todos":
                List<Todo> todos = todoDao.getAllTodos();
                request.setAttribute("todos", todos);
                request.getRequestDispatcher("WEB-INF/views/todoList.jsp").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.isEmpty()) {
            request.setAttribute("error", "Title is required!");
            request.getRequestDispatcher("WEB-INF/views/todoForm.jsp").forward(request, response);
            return;
        }
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todoDao.addTodo(todo);
        response.sendRedirect(request.getContextPath() + "/todos");
    }
}

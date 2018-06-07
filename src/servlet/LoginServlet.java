package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BungyBean;
import exception.NoTextException;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    public LoginServlet(){}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BungyBean bb = new BungyBean();

        try {
            String userId = request.getParameter("userId");

            if(userId.equals("")) {
                throw new NoTextException();
            }

            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/WindowTest.jsp");
            disp.forward(request, response);
        }
        catch(NoTextException e) {
            bb.setMessage("入力が不正です");
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/Login.jsp");
            disp.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
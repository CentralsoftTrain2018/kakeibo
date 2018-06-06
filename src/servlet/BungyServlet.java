package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BungyBean;




@WebServlet("/BungyServlet")
public class BungyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    public BungyServlet(){}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BungyBean bb = new BungyBean();

        bb.setMokuhyou(100000);
        bb.setSisyutu(80000);
        bb.setHanninIchi(60);
        bb.setDate(20);

        request.setAttribute("bean", bb);
        RequestDispatcher disp = request.getRequestDispatcher("/Bungy.jsp");
        disp.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}

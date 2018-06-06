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

        try {
            String mokuhyouStr = request.getParameter("mokuhyou");
            String shisyutuStr = request.getParameter("shisyutu");
            String hanninIchiStr = request.getParameter("hanninIchi");
            String dateStr = request.getParameter("date");
            int mokuhyou = Integer.parseInt(mokuhyouStr);
            int shisyutu = Integer.parseInt(shisyutuStr);
            int hanninIchi = Integer.parseInt(hanninIchiStr);
            int date = Integer.parseInt(dateStr);
            bb.setMokuhyou(mokuhyou);
            bb.setSisyutu(shisyutu);
            bb.setHanninIchi(hanninIchi);
            bb.setDate(date);

            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/Bungy.jsp");
            disp.forward(request, response);
        }
        catch(NumberFormatException e) {
            bb.setMessage("入力が不正です");
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/Bungy.jsp");
            disp.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}

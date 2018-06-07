package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BungyBean;
import exception.IllegalNumberException;
import service.ExpenseService;
import vo.BungyVo;




@WebServlet("/BungyServlet")
public class BungyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    public BungyServlet(){}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BungyBean bb = new BungyBean();

        try {
            /**
            String mokuhyouStr = request.getParameter("mokuhyou");
            String shisyutuStr = request.getParameter("shisyutu");
            String dateStr = request.getParameter("date");
            int mokuhyou = Integer.parseInt(mokuhyouStr);
            int shisyutu = Integer.parseInt(shisyutuStr);
            int date = Integer.parseInt(dateStr);
            **/

            BungyVo bv = ExpenseService.getMokuhyouAndExpenses("aaa", "201810");


            if(bv.getMokuhyou() < 0) {
                throw new IllegalNumberException();
            }
            if(bv.getTotalexpenses() < 0) {
                throw new IllegalNumberException();
            }
            /**
            if(date < 0 || 100 < date) {
                throw new IllegalNumberException();
            }
            **/

            bb.setMokuhyou(bv.getMokuhyou());
            bb.setSisyutu(bv.getTotalexpenses());
            bb.setMonth("201810");

            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/Bungy.jsp");
            disp.forward(request, response);
        }
        catch(NumberFormatException | IllegalNumberException e) {
            bb.setMessage("入力が不正です");
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/WindowTest.jsp");
            disp.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}

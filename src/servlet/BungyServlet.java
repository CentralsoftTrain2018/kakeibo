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
import exception.IllegalNumberException;
import service.ExpenseService;




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
            HttpSession session = request.getSession();
            String userId = (String)session.getAttribute("userId");
            String nengetu=new String(request.getParameter("nengetu").getBytes("iso-8859-1"),"UTF-8");
            //String nengetu=request.getParameter("nengetu");
            bb = ExpenseService.getMokuhyouAndExpenses(userId,nengetu);


            if(bb.getMokuhyou() < 0) {
                throw new IllegalNumberException();
            }
            if(bb.getSisyutu() < 0) {
                throw new IllegalNumberException();
            }
            /**
            if(date < 0 || 100 < date) {
                throw new IllegalNumberException();
            }
            **/
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

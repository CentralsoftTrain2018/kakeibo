package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

/**
 * Servlet implementation class IndexStartServlet
 */
@WebServlet("/KakeiboServlet")
public class KakeiboServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("KakeiboServletが実行されました。");


        String choice = request.getParameter("choice");

        if(choice.equals("touroku")) {
            int kingaku = Integer.parseInt(request.getParameter("kingaku"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String expenseName = request.getParameter("expenseName");
            String userId = request.getParameter("userId");
            Service.addExpense(kingaku, categoryId, expenseName, userId);
        }
        if(choice.equals("henkou")) {
            int expenseId = Integer.parseInt(request.getParameter("expenseId"));
            int kingaku = Integer.parseInt(request.getParameter("kingaku"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String expenseName = request.getParameter("expenseName");
            Service.updateExpense(expenseId, kingaku, categoryId, expenseName);
        }
        if(choice.equals("sakujo")) {

            int expenseId = Integer.parseInt(request.getParameter("expenseId"));
            Service.deleteExpense(expenseId);
        }
        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher("Kakeibo.jsp");
        disp.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
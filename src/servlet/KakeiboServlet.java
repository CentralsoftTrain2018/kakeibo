package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.KakeiboBean;
import exception.NoTextException;
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

        KakeiboBean kb = new KakeiboBean();
        String choice = request.getParameter("choice");

        if(choice.equals("touroku")) {
            try {
                int kingaku = Integer.parseInt(request.getParameter("kingaku"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String expenseName = request.getParameter("expenseName");
                String userId = request.getParameter("userId");

                if(expenseName.equals("") || userId.equals("")) {
                    throw new NoTextException();
                }

                Service.addExpense(kingaku, categoryId, expenseName, userId);
            }
            catch(NumberFormatException | NoTextException e) {
                kb.setMessage("入力が不正です");
            }
        }

        if(choice.equals("henkou")) {
            try {
                int expenseId = Integer.parseInt(request.getParameter("expenseId"));
                int kingaku = Integer.parseInt(request.getParameter("kingaku"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String expenseName = request.getParameter("expenseName");

                if(expenseName.equals("")) {
                    throw new NoTextException();
                }

                Service.updateExpense(expenseId, kingaku, categoryId, expenseName);
            }
            catch(NumberFormatException | NoTextException e){
                kb.setMessage("入力が不正です");
            }
        }

        if(choice.equals("sakujo")) {
            try {
                int expenseId = Integer.parseInt(request.getParameter("expenseId"));
                Service.deleteExpense(expenseId);
            }
            catch(NumberFormatException e) {
                kb.setMessage("入力が不正です");
            }
        }

        //JSPに遷移する
        request.setAttribute("bean", kb);
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
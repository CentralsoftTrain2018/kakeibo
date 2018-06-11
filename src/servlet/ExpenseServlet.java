package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ExpenseBean;
import exception.NoTextException;
import service.ExpenseService;

/**
 * Servlet implementation class IndexStartServlet
 */
@WebServlet("/KakeiboServlet")
public class ExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("KakeiboServletが実行されました。");

        ExpenseBean kb = new ExpenseBean();
        String choice = request.getParameter("choice");

        String expenseIdStr = request.getParameter("expenseId");
        String kingakuStr = request.getParameter("kingaku");
        String categoryIdStr = request.getParameter("categoryId");
        String expenseName = request.getParameter("expenseName");
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");

        if(choice.equals("touroku")) {
            try {
                int kingaku = Integer.parseInt(kingakuStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                if(expenseName.equals("")) {
                    throw new NoTextException();
                }
                ExpenseService.addExpense(kingaku, categoryId, expenseName, userId);
            }
            catch(NumberFormatException | NoTextException e) {
                kb.setMessage("入力が不正です");
            }
        }

        if(choice.equals("henkou")) {
            try {
                int expenseId = Integer.parseInt(expenseIdStr);
                int kingaku = Integer.parseInt(kingakuStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                if(expenseName.equals("")) {
                    throw new NoTextException();
                }
                ExpenseService.updateExpense(expenseId, kingaku, categoryId, expenseName);
            }
            catch(NumberFormatException | NoTextException e) {
                kb.setMessage("入力が不正です");
            }
        }

        if(choice.equals("sakujo")) {
            try {
                int expenseId = Integer.parseInt(expenseIdStr);
                ExpenseService.deleteExpense(expenseId);
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
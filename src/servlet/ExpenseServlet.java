package servlet;

import java.io.IOException;
import java.util.Calendar;

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
@WebServlet("/ExpenseServlet")
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
        System.out.println("ExpenseServletが実行されました。");

        ExpenseBean eb = new ExpenseBean();
        String choice = request.getParameter("choice");
        if(choice == null) {
            choice = "";
        }
        String expenseIdStr = request.getParameter("expenseId");
        String kingakuStr = request.getParameter("kingaku");
        String categoryIdStr = request.getParameter("categoryId");
        String expenseName = request.getParameter("expenseName");
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String year = request.getParameter("year");
        String month = request.getParameter("month");

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
                eb.setMessage("入力が不正です");
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
                eb.setMessage("入力が不正です");
            }
        }

        if(choice.equals("sakujo")) {
            try {
                int expenseId = Integer.parseInt(expenseIdStr);
                ExpenseService.deleteExpense(expenseId);
            }
            catch(NumberFormatException e) {
                eb.setMessage("入力が不正です");
            }
        }

        Calendar calendar = Calendar.getInstance();
        if(year != null && month != null) {
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
            calendar.set(Calendar.MONTH, Integer.parseInt(month));
        }
        calendar.set(Calendar.DATE, 1);
        eb = ExpenseService.getAllSumOfDay(calendar, userId);
        eb.setStartDayOfTheWeek(calendar.get(Calendar.DAY_OF_WEEK));
        eb.setDate(calendar);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        eb.setEndDay(calendar.get(Calendar.DATE));

        //JSPに遷移する
        request.setAttribute("bean", eb);
        RequestDispatcher disp = request.getRequestDispatcher("Expense.jsp");
        disp.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
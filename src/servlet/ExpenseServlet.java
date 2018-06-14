package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
        // String expenseName = request.getParameter("expenseName");
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("selectDay");
        String message = "";

        if(choice.equals("touroku")) {
            try {
                int kingaku = Integer.parseInt(kingakuStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                String expenseName = new String(request.getParameter("expenseName").getBytes("iso-8859-1"), "UTF-8");
                if(expenseName.equals("")) {
                    throw new NoTextException();
                }
                ExpenseService.addExpense(kingaku, categoryId, expenseName, userId);
            }
            catch(NumberFormatException | NoTextException e) {
                message = "入力が不正です";
            }
        }

        if(choice.equals("henkou")) {
            try {
                int expenseId = Integer.parseInt(expenseIdStr);
                int kingaku = Integer.parseInt(kingakuStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                String expenseName = new String(request.getParameter("expenseName").getBytes("iso-8859-1"), "UTF-8");
                if(expenseName.equals("")) {
                    throw new NoTextException();
                }
                ExpenseService.updateExpense(expenseId, kingaku, categoryId, expenseName);
            }
            catch(NumberFormatException | NoTextException e) {
                message = "入力が不正です";
            }
        }

        if(choice.equals("sakujo")) {
            try {
                int expenseId = Integer.parseInt(expenseIdStr);
                ExpenseService.deleteExpense(expenseId);
            }
            catch(NumberFormatException e) {
                message = "入力が不正です";
            }
        }

        Calendar calendar = Calendar.getInstance();
        if(year != null && month != null) {
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
            calendar.set(Calendar.MONTH, Integer.parseInt(month));
        }
        calendar.set(Calendar.DATE, 1);

        if(month != null && month.length() == 1)
        {
            int tuki = Integer.parseInt(month)+1;
            month = "0" + String.valueOf(tuki);
        }

        if(day != null && day.length() == 1)
        {
            day = "0" + day;
        }

        if(year == null | month == null | day == null)
        {
            LocalDate ld = LocalDate.now();
            String now[] = String.valueOf(ld).split("-",0);

            year = now[0];
            month = now[1];
            day = now[2];
        }

        String date = year + "-" + month + "-" + day;
        System.out.println(date);

        eb = ExpenseService.makeExpenseBean(calendar, Date.valueOf(date), userId);

        eb.setStartDayOfTheWeek(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        eb.setDate(calendar);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        eb.setEndDay(calendar.get(Calendar.DATE));


        eb.setMessage(message);
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
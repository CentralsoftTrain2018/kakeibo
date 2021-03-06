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
public class ExpenseServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

        ExpenseBean eb = new ExpenseBean();
        String choice = null;
        if ( request.getParameter( "choice" ) == null )
        {
            choice = "";
        }
        if ( request.getParameter( "choice" ) != null )
        {
            choice = new String( request.getParameter( "choice" ).getBytes( "iso-8859-1" ), "UTF-8" );
        }

        String expenseIdStr = request.getParameter( "expenseId" );
        String kingakuStr = request.getParameter( "kingaku" );
        String categoryIdStr = request.getParameter( "categoryId" );
        // String expenseName = request.getParameter("expenseName");
        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String year = request.getParameter( "year" );
        String month = request.getParameter( "month" );
        String day = request.getParameter( "selectDay" );
        String message = "";
        String isChangeStr = request.getParameter( "isChange" );

        boolean isChange = Boolean.valueOf( isChangeStr );

        if ( year == null && month == null && day == null )
        {
            LocalDate ld = LocalDate.now();
            String now[] = String.valueOf( ld ).split( "-", 0 );

            year = now[0];
            month = now[1];
            day = now[2];
        }

        if ( year != null && month != null && day == null )
        {
            month = String.valueOf( Integer.parseInt( month ) );
            day = "01";
        }

        String date = year + "-" + month + "-" + day;

        Calendar calendar = Calendar.getInstance();
        if ( year != null && month != null )
        {
            calendar.set( Calendar.YEAR, Integer.parseInt( year ) );
            calendar.set( Calendar.MONTH, Integer.parseInt( month ) - 1 );
        }
        calendar.set( Calendar.DATE, 1 );

        if ( choice.equals( "登録" ) )
        {
            int kingaku = Integer.parseInt( kingakuStr );
            int categoryId = Integer.parseInt( categoryIdStr );
            String expenseName = new String( request.getParameter( "expenseName" ).getBytes( "iso-8859-1" ), "UTF-8" );
            if ( expenseName.equals( "" ) )
            {
                throw new NoTextException();
            }
            ExpenseService.addExpense( kingaku, categoryId, expenseName, userId, Date.valueOf( date ) );
        }

        if ( choice.equals( "変更" ) )
        {
            String dataList = request.getParameter( "data" );

            String data[] = dataList.split( "/", 0 );

            for ( int i = 0; i < data.length; i++ )
            {
                int categoryId = Integer.parseInt( data[i] );
                int expenseId = Integer.parseInt( data[i + (data.length / 4)] );
                String expenseName = new String( data[i + (data.length / 4 * 2)].getBytes( "iso-8859-1" ), "UTF-8" );

                if ( expenseName.equals( "" ) )
                {
                    throw new NoTextException();
                }
                int kingaku = Integer.parseInt( data[i + (data.length / 4 * 3)] );

                ExpenseService.updateExpense( expenseId, kingaku, categoryId, expenseName );
            }
        }

        if ( choice.equals( "削除" ) )
        {
            int expenseId = Integer.parseInt( expenseIdStr );
            ExpenseService.deleteExpense( expenseId );

            isChange = false;
        }

        eb = ExpenseService.makeExpenseBean( calendar, Date.valueOf( date ), userId );

        if ( isChangeStr != null )
        {
            eb.setChange( isChange );
        }

        if ( isChangeStr == null )
        {
            eb.setChange( Boolean.valueOf( "false" ) );
        }

        eb.setStartDayOfTheWeek( calendar.get( Calendar.DAY_OF_WEEK ) - 1 );
        eb.setDate( calendar );
        calendar.set( Calendar.DATE, calendar.getActualMaximum( Calendar.DATE ) );
        eb.setEndDay( calendar.get( Calendar.DATE ) );

        eb.setSelectDay( Integer.parseInt( day ) );
        eb.setMessage( message );

        //JSPに遷移する
        request.setAttribute( "bean", eb );
        RequestDispatcher disp = request.getRequestDispatcher( "Expense.jsp" );
        disp.forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        doGet( request, response );
    }

}
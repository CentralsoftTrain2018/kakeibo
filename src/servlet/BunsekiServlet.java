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

import bean.BunsekiListBean;
import service.AdviceService;

/**
 * Servlet implementation class BunsekiServlet
 */
@WebServlet("/BunsekiServlet")
public class BunsekiServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public BunsekiServlet()
    {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String year = request.getParameter( "year" );
        String month = request.getParameter( "month" );

        System.out.println( "Servlet" + year + "-" + month );

        BunsekiListBean bean = chengeServiceMesod( year, month, userId );

        request.setAttribute( "bean", bean );

        RequestDispatcher disp = request.getRequestDispatcher( "/Bunseki.jsp" );
        disp.forward( request, response );
    }

    private BunsekiListBean chengeServiceMesod( String year, String month, String userId )
    {
        Calendar calendar = Calendar.getInstance();

        if ( year != null && month != null )
        {
            calendar.set( Calendar.YEAR, Integer.parseInt( year ) );
            calendar.set( Calendar.MONTH, Integer.parseInt( month ) - 1 );
            return AdviceService.selectBunseki( userId, calendar );
        }
        System.out.println( "Servlet" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) );

        return AdviceService.selectBunseki( userId );

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}

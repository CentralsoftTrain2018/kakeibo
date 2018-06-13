package servlet;

import java.io.IOException;

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

        BunsekiListBean bean=AdviceService.selectBunseki( userId );

        request.setAttribute( "bean", bean );

        RequestDispatcher disp = request.getRequestDispatcher( "/Bunseki.jsp" );
        disp.forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}

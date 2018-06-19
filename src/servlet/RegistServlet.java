package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BungyBean;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

        String userId = request.getParameter( "userId" );
        String password = request.getParameter( "password" );
        String passwordConfirm = request.getParameter( "passwordConfirm" );
        String mail = request.getParameter( "mail" );
        String income = request.getParameter( "income" );

        BungyBean bb = new BungyBean();

        RequestDispatcher disp = request.getRequestDispatcher( "/Login.jsp" );
        disp.forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}

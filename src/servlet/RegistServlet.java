package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginBean;
import bean.RegistBean;
import service.UserDataService;

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
        RegistBean rb = new RegistBean();
        LoginBean lb = new LoginBean();

        String userId = request.getParameter( "userId" );
        String password = request.getParameter( "password" );
        String mail = request.getParameter( "mail" );
        String incomeStr = request.getParameter( "income" );
        int income = Integer.parseInt( incomeStr );

        if ( UserDataService.isUnique( userId ) )
        {
            rb.setUserId( userId );
            rb.setPassword( password );
            rb.setMail( mail );
            rb.setIncome( income );
            UserDataService.passRegistDara( rb );

            lb.setMessage( "登録が完了しました。ログインしてください。" );
            request.setAttribute( "bean", lb );
            RequestDispatcher disp = request.getRequestDispatcher( "/Login.jsp" );
            disp.forward( request, response );

        } else
        {
            rb.setMessage( "そのユーザーIDは既に使用されています。" );
            request.setAttribute( "bean", rb );
            RequestDispatcher disp = request.getRequestDispatcher( "/Regist.jsp" );
            disp.forward( request, response );
        }

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

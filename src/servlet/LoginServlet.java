package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BungyBean;
import bean.LoginBean;
import service.ExpenseService;
import service.UserDataService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public LoginServlet()
    {
    }

    //ログイン画面のサーブレット
    //呼び出し元
    //Login.jsp
    //呼び出し先
    //UserDataService
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        BungyBean bb = new BungyBean();
        LoginBean lb = new LoginBean();

        String userId = request.getParameter( "userId" );
        String password = request.getParameter( "password" );

        lb.setUserId( userId );
        lb.setPassword( password );

        //ログイン可能
        if ( UserDataService.isLogin( lb ) )
        {
            //入っていない場合。今日が何年何月かをnengetsuに入れる
            Calendar calendar = Calendar.getInstance();
            String nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );

            //String nengetu=request.getParameter("nengetsu");
            bb = ExpenseService.getMokuhyouAndExpenses( userId, nengetsu );

            HttpSession session = request.getSession();
            session.setAttribute( "userId", userId );
            request.setAttribute( "bean", bb );
            RequestDispatcher disp = request.getRequestDispatcher( "/Bungy.jsp" );
            disp.forward( request, response );

        } else
            //ユーザーID・パスワードが間違っている
        {
            lb.setMessage( "ユーザーIDまたはパスワードが間違っています。" );
            request.setAttribute( "bean", lb );
            RequestDispatcher disp = request.getRequestDispatcher( "/Login.jsp" );
            disp.forward( request, response );

        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        doGet( request, response );
    }

}
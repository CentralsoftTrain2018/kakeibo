package servlet;

import java.io.IOException;
import java.time.LocalDate;

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
    //登録画面のサーブレット
    //呼び出し元
    //Regist.jsp
    //呼び出し先
    //UserDataService
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        RegistBean rb = new RegistBean();
        LoginBean lb = new LoginBean();

        //フォームの値を取得
        String userId = request.getParameter( "userId" );
        String password = request.getParameter( "password" );
        String mail = request.getParameter( "mail" );
        String incomeStr = request.getParameter( "income" );
        int income = Integer.parseInt( incomeStr );

        LocalDate ld  = LocalDate.now();

        String registMonth = String.format("%tY/%tm", ld, ld);

        //ユーザーIDが重複していない
        if ( UserDataService.isUnique( userId ))
        {
            UserDataService.passRegistDara( userId, password, mail, income, registMonth);

            lb.setMessage( "登録が完了しました。ログインしてください。" );
            request.setAttribute( "bean", lb );
            RequestDispatcher disp = request.getRequestDispatcher( "/Login.jsp" );
            disp.forward( request, response );

        } else //ユーザーIDが重複している
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

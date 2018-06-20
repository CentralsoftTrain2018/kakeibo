package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.JihakuListBean;
import service.AdviceService;

/**
 * Servlet implementation class IndexStartServlet
 */
@WebServlet("/JihakuServlet")
public class JihakuServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JihakuServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //自白画面のサーブレット
    //呼び出し元
    //Bungy.jsp
    //呼び出し先
    //AdviceService
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String nengetsu = request.getParameter("nengetsu");
        AdviceService conan = new AdviceService();

        JihakuListBean bean = conan.jihaku( userId , nengetsu);
        //beanをリクエストにセット キー名は「bean」とする
        request.setAttribute( "bean", bean );

        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher( "Jihaku.jsp" );
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

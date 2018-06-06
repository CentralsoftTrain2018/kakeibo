package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.JihakuBean;

/**
 * Servlet implementation class IndexStartServlet
 */
@WebServlet("/JihakuServlet")
public class JihakuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JihakuServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("JihakuServlet実行");

        JihakuBean bean = new JihakuBean();
        int sisyutu = 10000;
        int mokuhyou = 5000;
        String categoryname = ("雑費");
        //計算結果と表示するメッセージを入れ物（bean)にセットする
        bean.setCategoryname(categoryname);
        bean.setExcess(sisyutu,mokuhyou);
      //beanをリクエストにセット キー名は「bean」とする
        request.setAttribute("bean", bean);

        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher("Jihaku.jsp");
        disp.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}


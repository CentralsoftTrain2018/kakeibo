package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ConanBean;

@WebServlet("/ConanServlet")
public class ConanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConanBean bean = new ConanBean();

        //計算結果と表示するメッセージを入れ物（bean)にセットする
        bean.setThisMonth(5);
        bean.setNextMonth(6);
        bean.setTotal(20000);
        bean.setGoodCategory("食費");
        bean.setGoodDifference(500);
        bean.setBadCategory("交際費");
        bean.setBadDifference(3000);

        //beanをリクエストにセット キー名は「bean」とする
        request.setAttribute("bean", bean);

        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher("/Conan.jsp");
        disp.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

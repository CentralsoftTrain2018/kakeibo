package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ConanBean;
import bean.ConanListBean;

@WebServlet("/ConanServlet")
public class ConanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //カテゴリ数
        int categorySu = 3;

        List<ConanBean> list =new  ArrayList<ConanBean>();
        for( int i= 0; i < categorySu; i ++)
        {
            ConanBean cb = new ConanBean();
            cb.setCategoryName("食費");
            cb.setGoal(30000);
            cb.setSpending(25000);
            cb.setDifference(30000-25000);

            list.add(cb);
        }

        //次の画面で表示するための入れ物を準備する
        ConanListBean bean = new ConanListBean();
        bean.setList(list);
        bean.setThisMonth(5);
        bean.setTotalGoal(150000);
        bean.setTotalSpending(140000);
        bean.setTotalDifference(150000-140000);


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

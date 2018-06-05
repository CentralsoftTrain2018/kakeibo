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

<<<<<<< HEAD
        //カテゴリ数を取得（SQLでやる）
        int categorySu = 3;

        String categoryName;
        int goal;		 //目標
        int spending;	 //支出

        int sumg = 0;
        int sums = 0;

        //カテゴリの数だけ差額とかをリストにぶち込む
        List<ConanBean> list = new ArrayList<ConanBean>();
        for (int i = 0; i < categorySu; i++) {
            ConanBean cb = new ConanBean();
            categoryName = "食費";
            cb.setCategoryName(categoryName);

            goal = (int)(Math.random() * 10000 + 1);
            cb.setGoal(goal);
            sumg += goal;

            spending = 250;
            cb.setSpending(spending);
            sums += spending;

            cb.setDifference(goal - spending);

            list.add(cb);
        }

        //次の画面で表示するための入れ物を準備する
        ConanListBean bean = new ConanListBean();
        bean.setList(list);
        //何とかする
=======
        //計算結果と表示するメッセージを入れ物（bean)にセットする
>>>>>>> branch 'master' of https://github.com/CentralsoftTrain2018/kakeibo
        bean.setThisMonth(5);
<<<<<<< HEAD
        bean.setTotalGoal(sumg);
        bean.setTotalSpending(sums);
        bean.setTotalDifference(sumg - sums);

=======
        bean.setNextMonth(6);
        bean.setTotal(20000);
        bean.setGoodCategory("食費");
        bean.setGoodDifference(500);
        bean.setBadCategory("交際費");
        bean.setBadDifference(3000);

        //beanをリクエストにセット キー名は「bean」とする
>>>>>>> branch 'master' of https://github.com/CentralsoftTrain2018/kakeibo
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

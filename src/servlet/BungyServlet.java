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
import exception.IllegalNumberException;
import service.ExpenseService;

@WebServlet("/BungyServlet")
public class BungyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public BungyServlet()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BungyBean bb = new BungyBean();

        try {
            HttpSession session = request.getSession();
            String userId = (String)session.getAttribute("userId");
            String year = request.getParameter("year");

            System.out.println("サーブレッ");
            String month = request.getParameter("month");
            String nengetsu = "";

            System.out.println(month);

            if(year != null && month != null)
            {
                month = String.format("%02d",Integer.parseInt(month));
                nengetsu = year + "/" + month;
            }



            System.out.println("サーブレット："+nengetsu);

            /*
             * nengetuに文字が入っていない場合（他の画面からバンジーボタンが押された）
             * 文字が入っている場合（バンジー画面から数値が送られる）
             * の場合わけが行われる。
             */
            if(nengetsu!="")
            {
                nengetsu=new String(nengetsu.getBytes("iso-8859-1"),"UTF-8");
            }
            else
            {
                //入っていない場合。今日が何年何月かをnengetsuに入れる
                Calendar calendar = Calendar.getInstance();
                nengetsu = new SimpleDateFormat( "yyyy/MM" ).format( calendar.getTime() );
            }
            //String nengetu=request.getParameter("nengetsu");
            bb = ExpenseService.makeBungyBean(userId,nengetsu);


            if(bb.getMokuhyou() < 0) {
                throw new IllegalNumberException();
            }
            if(bb.getSisyutu() < 0) {
                throw new IllegalNumberException();
            }
            /*
            if(date < 0 || 100 < date) {
                throw new IllegalNumberException();
            }
            */
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/Bungy.jsp");
            disp.forward(request, response);
        }
        catch(NumberFormatException | IllegalNumberException e) {
            bb.setMessage("入力が不正です");
            request.setAttribute("bean", bb);
            RequestDispatcher disp = request.getRequestDispatcher("/WindowTest.jsp");
            disp.forward(request, response);
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        doGet( request, response );
    }

}

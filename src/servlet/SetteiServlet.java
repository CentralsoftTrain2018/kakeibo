package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SetteiBean;
import service.UserDataService;

/**
 * Servlet implementation class Settei
 */
@WebServlet("/SetteiServlet")
public class SetteiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetteiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //設定画面のサーブレット
    //呼び出し元
    //Settei.jsp
    //呼び出し先
    //UserDataService
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String choice = request.getParameter("choice");
        String oldCategoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("newCategoryName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newIncomeStr = request.getParameter("newIncome");
        String newMokuhyoukingakuStr = request.getParameter("newMokuhyoukingaku");

        int newIncome= -1;
        int newMokuhyoukingaku= -1;

        if (choice == null )
        {
            choice = "";
        }


        if(choice.equals("addCategory"))
        {
            UserDataService.addCategory(newCategoryName);
        }
        if(choice=="updateCategory")
        {
            UserDataService.updateCategory(newCategoryName, userId, oldCategoryName);
        }
        if(choice.equals("deleteCategory"))
        {
            UserDataService.deleteCategory(userId, oldCategoryName);
        }
        if(choice.equals("updatePassword"))
        {
            updatePassword(userId, oldPassword, newPassword);
        }
        if(choice.equals("updateSyunyuu"))
        {
            try {
                newIncome = Integer.parseInt(newIncomeStr);
                UserDataService.updateSyunyuu(userId, newIncome);
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if(choice.equals("updateMokuhyou"))
        {
            try {
                newMokuhyoukingaku = Integer.parseInt(newMokuhyoukingakuStr);
                UserDataService.updateMokuhyou(userId, newMokuhyoukingaku, oldCategoryName);
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }

            UserDataService.settei( userId );


        SetteiBean bean = UserDataService.settei( userId );
        request.setAttribute( "bean", bean );

        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher( "Settei.jsp" );
        disp.forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    //パスワードの変更
    //呼び出し元
    //SetteiServlet
    //呼び出し先
    //UserDataService
    private void updatePassword(String userId,String oldPassword, String newPassword) {
        String nowPassword = UserDataService.getPassword(userId);
        if(nowPassword.equals(oldPassword)) {
            UserDataService.updatePassword(userId, newPassword);
        }
    }

}

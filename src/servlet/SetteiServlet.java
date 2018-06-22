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
        String oldCategoryName = null;
        String newCategoryName = null;
        String newCategoryColor=request.getParameter("colorName");
        if(request.getParameter("oldCategoryName")!=null)
        {
               oldCategoryName =new String(request.getParameter("oldCategoryName").getBytes("iso-8859-1"), "UTF-8");
        }
        if(request.getParameter("newCategoryName")!=null)
        {
               newCategoryName =new String(request.getParameter("newCategoryName").getBytes("iso-8859-1"), "UTF-8");
        }
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newIncomeStr = request.getParameter("newIncome");
        String newMokuhyoukingakuStr = request.getParameter("newMokuhyoukingaku");
        String dispName=request.getParameter("dispName");;

        int newIncome= -1;
        int newMokuhyoukingaku= -1;

        if (choice == null )
        {
            choice = "";
        }
        if(dispName==null)
        {
            dispName="";
        }

        if(choice.equals("addCategory"))
        {
            newMokuhyoukingaku = Integer.parseInt(newMokuhyoukingakuStr);
            UserDataService.addCategory(newCategoryName,newCategoryColor,userId,newMokuhyoukingaku);
        }
        if(choice.equals("updateCategory"))
        {
            System.out.println("ikcjherfh");
            UserDataService.updateCategory(newCategoryName, userId, oldCategoryName);
        }
        if(choice.equals("deleteCategory"))
        {
            UserDataService.deleteCategory(userId, oldCategoryName);
        }
        if(choice.equals("updatePassword"))
        {
            UserDataService.updatePassword(userId, newPassword);
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


        SetteiBean bean = UserDataService.settei( userId );
        bean.setDispName(dispName);
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
}
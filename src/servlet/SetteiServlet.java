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
import service.UserDataSevice;

/**
 * Servlet implementation class Settei
 */
@WebServlet("/Settei")
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String choice = request.getParameter("choice");
        String categoryIdStr = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");

        int categoryId = -1;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
        }

        if(choice.equals("addCategory"))
        {
            UserDataSevice.addCategory(categoryName);
        }
        if(choice.equals("updateCategory"))
        {
            UserDataSevice.updateCategory(categoryId, categoryName);
        }
        if(choice.equals("deleteCategory"))
        {
            UserDataSevice.deleteCategory(categoryId);
        }

        SetteiBean bean = UserDataSevice.settei( userId);
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

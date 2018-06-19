package servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ConanListBean;
import service.AdviceService;

@WebServlet("/ConanServlet")
public class ConanServlet extends HttpServlet
{
    final static String account = "central.train2018";
    final static String passwd = "train-2018";
    private static final long serialVersionUID = 1L;

    public ConanServlet()
    {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String userId = ( String ) session.getAttribute( "userId" );
        String nengetsu = request.getParameter("nengetsu");
        String sendMail = request.getParameter("sendMail");

        //次の画面で表示するための入れ物を準備する
        ConanListBean bean = AdviceService.selectConanAdvice( userId, nengetsu );

        if(sendMail != null) {
            String mailAddress = AdviceService.getMailAddress(userId);
            sendMail(mailAddress, nengetsu);
        }

        request.setAttribute( "bean", bean );

        //JSPに遷移する
        RequestDispatcher disp = request.getRequestDispatcher( "/Conan.jsp" );
        disp.forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        doGet( request, response );
    }

    private void sendMail(String mailAddress, String nengetsu)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.post", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        final Message msg = new MimeMessage(Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account,passwd);
            }
        }));

        String mail = account + "@gmail.com";
        String to = mailAddress;          // カンマ区切りで複数メール
        try {
            msg.setFrom(new InternetAddress(mail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("バンジークーポン");
            String mailText =
                    "バンジー代10％オフ\n" +
                    nengetsu +
                    "末まで";
            msg.setText(mailText);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

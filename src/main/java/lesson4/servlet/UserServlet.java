package lesson4.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Log: POST, UserServlet");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String str = req.getParameter("name");
        out.printf("<html>" +
                "<head>" +
                "<title>Java EE</title>" +
                "<style type=\"text/css\">" +
                "table, th, td {" +
                "    border: 2px solid black;" +
                "    border-collapse: collapse;" +
                "}</style>" +
                "</head>" +
                "<body>"+
                "<table>" +
                "<tr><td>ФИО</td><td>%s</td></tr>" +
                "<tr><td>Дата рождения</td><td>%s</td></tr>" +
                "<tr><td>Город проживания</td><td>%s</td></tr>" +
                "</table>"+
                "</body>" +
                "</html>",
                new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                new String(req.getParameter("date").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                new String(req.getParameter("city").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        out.close();
    }
}

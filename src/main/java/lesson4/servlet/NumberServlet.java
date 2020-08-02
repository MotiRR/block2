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
import java.text.ParseException;

@WebServlet(name = "NumberServlet", urlPatterns = "/number_servlet")
public class NumberServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(NumberServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Log: GET, NumberServlet");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            long n = Long.parseLong(req.getParameter("n"));
            createResponse(n, out);
        } catch (Exception e) {
            out.printf("<html><body><h1>500</h1></body></html>");
        } finally {
            out.close();
        }
    }

    private void createResponse(long n, PrintWriter out) {
        out.printf("<html>" +
                "<title>Java EE</title>" +
                "<style type=\"text/css\">" +
                " table, th, td {" +
                "  border: 2px solid black;" +
                "  border-collapse: collapse;" +
                "}</style>" +
                "<body><table>");
        for(int i = 1; i <= n; i++) {
            out.printf("<tr>");
            for (int j = 1; j <= n; j++)
                out.printf("<td>%s</td>", new StringBuilder().append(i).append("-").append(j));
            out.printf("</tr>");
        }
        out.printf("</table></body></html>");
    }
}

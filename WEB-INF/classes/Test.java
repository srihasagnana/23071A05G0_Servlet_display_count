import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Test extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String user_name = req.getParameter("username");

        ServletContext context = req.getServletContext();
        Integer count = (Integer) context.getAttribute("userCount");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "siri@2005");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select name from student where name='user_name'");

            out.println("<h2>User logged in is: " + user_name + "</h2>");

        }catch(Exception e){
            out.println("<p>error is: " + e.getMessage() + "</h2>");
        }

        if (count == null) {
            count = 0;
        }

        if (user_name != null) {
            count = count + 1;
            context.setAttribute("userCount", count);
            out.println("<h2>User logged count: " + count + "</h2>");
        }


        out.close();
    }
}

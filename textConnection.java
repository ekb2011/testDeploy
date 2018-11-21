import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class textConnection extends HttpServlet
{
    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String loginUser = "irteam";
        String loginPasswd = "Goodboy12!";
//        String loginUrl = "jdbc:mysql://localhost:13306/test";
        String loginUrl="jdbc:mysql://133.186.132.184:13306/test";
        response.setContentType("text/html;charset=UTF-8");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>클라우드 운영팀 목록</TITLE></HEAD>");
        out.println("<BODY><H1>클라우드 운영팀 현황</H1>");
	out.println("<br/><br/><H1>New Version of Java Servlet</H1>");
        // Load the mm.MySQL driver
        try
           {
              Class.forName("org.gjt.mm.mysql.Driver");
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

              String query = "SELECT * FROM member;";

              // Perform the query
              ResultSet rs = statement.executeQuery(query);

              out.println("<TABLE border>");

              // Iterate through each row of rs
              while (rs.next())
              {
                  int m_num = rs.getInt("idx");
                  String m_name = rs.getString("name");
                  String m_content = rs.getString("team");
                  out.println("<tr>" +
                              "<td>" + m_num + "</td>" +
                              "<td>" + m_name + "</td>" +
                              "<td>" + m_content + "</td>" +
                              "</tr>");
              }

              out.println("</TABLE>");

              rs.close();
              statement.close();
              dbcon.close();
            }
        catch (SQLException ex) {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "Toast: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }  // end catch SQLException

        catch(java.lang.Exception ex)
            {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "Toast: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }
         out.close();
    }
}

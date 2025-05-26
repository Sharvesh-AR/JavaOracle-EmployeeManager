
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException,IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apollo","root","");
            Statement st=con.createStatement();
            ResultSet rs;
           int empno=Integer.parseInt(req.getParameter("eno"));
            String name=req.getParameter("ename");
            int sal=Integer.parseInt(req.getParameter("esal"));
            String s="insert into emp(eno,ename,esal) values("+ empno + " , '" + name + " ',   " + sal + " )";
            st.executeUpdate(s);
            rs=st.executeQuery("select * from emp");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EMP</title>");  
            out.println("</head>");
            out.println("<body<center>>");
            out.println("<table border=3>");
            out.println("<caption>JDBC CONNECTION</caption>");
            out.println("<tr><th>Emp no</th><th>EMP name</th><th>Emp salary</th></tr>");
            while(rs.next())
            {
            out.println("<tr><td>"+rs.getInt("eno")+"</td>");
            out.println("<td>"+rs.getString("ename")+"</td>");
            out.println("<td>"+rs.getInt("esal")+"</td></tr>");
            }
            out.println("</table></center>");
            out.println("</body>");
            out.println("</html>");
            con.close();
        }
        catch(ClassNotFoundException e){}
        catch(SQLException e){}
         finally { 
            out.close(); 
       }
   }	
 }

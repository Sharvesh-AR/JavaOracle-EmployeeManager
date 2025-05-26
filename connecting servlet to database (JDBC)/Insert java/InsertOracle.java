import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertOracle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        Connection con = null;
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle database
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"
            );

            // Retrieve parameters
            int empno = Integer.parseInt(req.getParameter("eno"));
            String name = req.getParameter("ename").trim();
            int sal = Integer.parseInt(req.getParameter("esal"));

            // Insert safely using PreparedStatement
            String insertSQL = "INSERT INTO emp (eno, ename, esal) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertSQL);
            pstmt.setInt(1, empno);
            pstmt.setString(2, name);
            pstmt.setInt(3, sal);
            pstmt.executeUpdate();

            // Retrieve and display all employees
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM emp");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee Table</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f6f9; padding: 40px; }");
            out.println("table { border-collapse: collapse; width: 80%; margin: auto; background: #fff; }");
            out.println("th, td { padding: 12px; border: 1px solid #ccc; text-align: center; }");
            out.println("th { background-color: #007bff; color: white; }");
            out.println("caption { margin-bottom: 10px; font-size: 24px; font-weight: bold; color: #333; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<table>");
            out.println("<caption>JDBC Employee Table</caption>");
            out.println("<tr><th>Emp No</th><th>Emp Name</th><th>Emp Salary</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("eno") + "</td>");
                out.println("<td>" + rs.getString("ename") + "</td>");
                out.println("<td>" + rs.getInt("esal") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");

            // Close resources
            rs.close();
            pstmt.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            out.println("<p>Oracle JDBC Driver not found.</p>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<p>Database error occurred.</p>");
            e.printStackTrace(out);
        } catch (NumberFormatException e) {
            out.println("<p>Please enter valid numeric values for Employee ID and Salary.</p>");
            e.printStackTrace(out);
        } finally {
            try {
                if (con != null && !con.isClosed()) con.close();
            } catch (SQLException e) {
                e.printStackTrace(out);
            }
            out.close();
        }
    }
}

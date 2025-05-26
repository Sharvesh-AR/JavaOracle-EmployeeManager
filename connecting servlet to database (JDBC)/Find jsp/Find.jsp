<html>
<head><title>JSP with JDBC</title></head>
<body>
<%@page import="java.sql.*" %>
<%
   try
     {
       Class.forName("oracle.jdbc.driver.OracleDriver");
       Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
       Statement stmt = con.createStatement();
          int empno=Integer.parseInt(request.getParameter("eno"));
       ResultSet rs = stmt.executeQuery("select * from emp where eno="+empno+"");
       %>
     <table border=2>
     <tr><th>EmpNo</th><th>EmpName</th><th>Salary</th></tr>
     <%
         while(rs.next())
         {
         %>
         <tr><td><%=rs.getInt(1) %></td>
             <td><%=rs.getString(2) %></td>
             <td><%=rs.getInt(3) %></td>
         </tr>
     <%
      }
      %>
      </table>
      <% }catch(Exception e){}; %>

</body>
</html>

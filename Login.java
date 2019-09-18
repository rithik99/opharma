import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Login extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html");
		int i;
		PrintWriter out=response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/opharma","root","rithik99");

			String uname = request.getParameter("uname");
			String psw = request.getParameter("psw");
			HttpSession session=request.getSession(true);
			
			PreparedStatement stmt = con.prepareStatement("select name,uname,password from login where uname = ? and password = ?");
			stmt.setString(1,uname);
			stmt.setString(2,psw);
			ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					String j = rs.getString(1);
					String order = " ";
					int bill = 0;
					int cost;
					/*Orders*/
					PreparedStatement stmt2 = con.prepareStatement("select orders from orders where uname = ?");
					stmt2.setString(1,uname);
					ResultSet rs1 = stmt2.executeQuery();
					ResultSetMetaData rsmd = rs1.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					while(rs1.next())
					{
						for(i=1;i<=numberOfColumns;i++)
						{
							order = order + " | " + rs1.getString(i);
						}
					}
					/*Cart Cost*/
					PreparedStatement stmt3 = con.prepareStatement("select ordercost from orders where uname = ?");
					stmt3.setString(1,uname);
					ResultSet rs2 = stmt3.executeQuery();
					ResultSetMetaData rsmd1 = rs2.getMetaData();
					int numberOfColumns1 = rsmd1.getColumnCount();
					while(rs2.next())
					{
						for(i=1;i<=numberOfColumns1;i++)
						{
							cost = Integer.parseInt(rs2.getString(i));
							bill = bill + cost;
						}
					}
					String tbill = Integer.toString(bill);
					session.setAttribute("name",uname);
					session.setAttribute("order",order);
					session.setAttribute("user",j);
					session.setAttribute("bill",tbill);
					RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
					rd.forward(request,response);
					return;
				}
				else{
					out.print("No user found");}
			
}catch(Exception e){out.print(e);}}}
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class cartadd extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart","root","rithik99");
			String order = request.getParameter("order");
			int bill = 0;
					int cost,i;
			String ordercost = request.getParameter("ordercost");
			PreparedStatement stmt = con.prepareStatement("insert into cart values (?,?)");
			stmt.setString(1,order);
			stmt.setString(2,ordercost);
			stmt.execute();
			PreparedStatement stmt3 = con.prepareStatement("select cost from cart");
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
			String bill1 = Integer.toString(bill);
			out.print("<html>");
			out.print("<body>");
			out.print("Updated");
			out.print("Order: "+order);
			out.print("OrderCost: "+ordercost);
			out.print("Updated: "+bill);
			out.print("</body>");
			out.print("</html>");
			
			
}catch(Exception e){out.print(e);}}}
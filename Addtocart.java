import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Addtocart extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/opharma","root","rithik99");
			HttpSession session=request.getSession(false);
			if(session==null){
				out.print("<script>");
				out.print("alert('Please Login First');");
				out.print("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request,response);
			}  
			else{ 
				String uname=(String)session.getAttribute("name");
				String order = request.getParameter("order");
				String ordercost = request.getParameter("ordercost");
					session.setAttribute("order",order);
					session.setAttribute("ordercost",ordercost);
				PreparedStatement stmt = con.prepareStatement("insert into orders values (?,?,?)");
				stmt.setString(1,uname);
				stmt.setString(2,order);
				stmt.setString(3,ordercost);
				stmt.execute();
				RequestDispatcher rd = request.getRequestDispatcher("added.jsp");
				rd.forward(request,response);
				return;
			}  
			out.close();  
			}catch(Exception e)
			{
				out.print(e);
			}
}}
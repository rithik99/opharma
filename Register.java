import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Register extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
	}
	/*javac -classpath "C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar;C:\Program Files\Java\jre1.8.0_221\lib\ext\mysql-connector-java-5.1.48.jar" */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/opharma","root","rithik99");

			String uname = request.getParameter("uname");
			String psw = request.getParameter("psw");
			String nam = request.getParameter("nam");
			String cit = request.getParameter("cit");
			String ema = request.getParameter("ema");
			String num = request.getParameter("num");
					PreparedStatement pst = con.prepareStatement("insert into login values(?,?,?,?,?,?)");
					pst.setString(1,uname);
					pst.setString(2,psw);
					pst.setString(3,nam);
					pst.setString(4,cit);
					pst.setString(5,ema);
					pst.setString(6,num);
					pst.executeUpdate();
				response.sendRedirect("login.html");
					
		}catch(Exception e){
			out.print(e);
		}
	}
}
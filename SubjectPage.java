import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubjectPageServlet
 */
@WebServlet("/SubjectPage")
public class SubjectPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String s="";
		try{
			Cookie c[]= request.getCookies();
			for(Cookie cook:c) {
				
				if(cook.getName().equalsIgnoreCase("offer")) {
				   s= cook.getValue();
				}
			}
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		String sql="SELECT distinct subject from books";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		out.println("<html>");
		out.println("<html><body>");
		out.println("<marquee><h4>Attractive Offers On " +s+ " Books</h4></marquee>");
		out.println("<h3>Select The Desired Subject</h3>");
		out.println("<hr>");
		while(rs.next()){
			String sub=rs.getString(1);
			out.println("<a href=BookList?subject="+sub+">");
			out.println(sub);
			out.println("</a><br>");
		}
		out.println("<hr>");
		out.println("<a href=buyer.jsp>Buyer-Page</a>");
		out.println("</body></html>");
		
		}catch(Exception e){
			out.println(e);
		}
	}

}

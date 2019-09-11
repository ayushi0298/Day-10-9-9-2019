
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
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookList")
public class BookList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject=request.getParameter("subject");
		Cookie[] ck = request.getCookies();
		for (Cookie c : ck) {
		if (c.getName().equals("offer")) {
		c.setValue(request.getParameter("subject"));
		response.addCookie(c);
		}
		}
		
		PrintWriter out=response.getWriter();
		try{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		String sql="SELECT BookId,BookName from books where Subject=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, subject);
		ResultSet rs=ps.executeQuery();
		out.println("<html>");
		out.println("<html><body>");
		out.println("<h3>Select The Desired Title</h3>");
		out.println("<hr>");
		while(rs.next()){
			String code=rs.getString(1);
			String title=rs.getString(2);
			
			out.println("<a href=BookDetails?code="+code+">");
			out.println(title);
			out.println("</a><br>");
		}
		out.println("<hr>");
		out.println("<a href=SubjectPage>Subject-Page</a>");
		out.println("<a href=buyer.jsp>Buyer-Page</a>");
		out.println("</body></html>");
	
		
		}catch(Exception e){
			out.println(e);
		
	}
		}
	}


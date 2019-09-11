import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookStore")
public class BookStore extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			String sql="insert into books values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String bookId=request.getParameter("bookId");
		String bookName=request.getParameter("bookName");
		String authorName=request.getParameter("authorName");
		String subject=request.getParameter("subject");
		String price=request.getParameter("price");
		
		try {
			ps.setString(1, bookId);
			ps.setString(2, bookName);
			ps.setString(3, authorName);
			ps.setString(4, subject);
			ps.setString(5, price);
			ps.executeUpdate();
			
			out.println("REGISTRATION COMPLETED");
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			ps.close();
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
}

package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.service.HotelServiceImpl;
import model.Hotel;
@WebServlet("/FindALLHotelServlet")
public class FindALLHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HotelServiceImpl ms = new HotelServiceImpl();
		List<Hotel> findAll = ms.findAll();
		request.setAttribute("Hotel", findAll);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user-list.jsp");
		requestDispatcher.forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("已幫你查詢完成");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

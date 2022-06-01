package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.dao.impl.HotelServiceDao_Hibernate;
import model.Hotel;


@WebServlet("/insertHotelServlet")
public class insertHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HotelServiceDao_Hibernate implDB = new HotelServiceDao_Hibernate();
		String hotel_name = request.getParameter("hotel_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String boss_name = request.getParameter("boss_name");
		String phone =  request.getParameter("phone") ;
		String status = request.getParameter("status");
		String roomtype = request.getParameter("roomtype");
		Hotel hotel = new Hotel(hotel_name, price, boss_name, phone, status, roomtype);
		implDB.save(hotel);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Hotel_Servlet?action");
		requestDispatcher.forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("新增成功"+hotel);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

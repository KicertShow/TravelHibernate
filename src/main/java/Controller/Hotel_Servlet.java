package Controller;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.swing.text.html.HTML;

import dao.dao.Fun_HotelDAO;
import model.Hotel;

@WebServlet("/Hotel_Servlet")
public class Hotel_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Fun_HotelDAO fun_HotelDAO = new Fun_HotelDAO();
		int i =0;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Fun_HotelDAO funDAO = new Fun_HotelDAO();
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");

		try {
			switch (action) {
			case "query":
				processQuery(req, res, funDAO);
				break;
			case "insert":
				Insert(req, res, funDAO);
				break;
			case "update":
				update(req, res, funDAO);
				break;
			case "edit":
				showEditForm(req, res, funDAO);
				break;
			case "new":
				showNewForm(req, res);
			case "delete":
				delete(req, res, funDAO);
			default:
				always(req,res,funDAO);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	private void processQuery(HttpServletRequest request, HttpServletResponse response, Fun_HotelDAO funDAO)
			throws SQLException, IOException, ServletException {
		List<Hotel> hotels = funDAO.selectAll();
		request.setAttribute("Hotel", hotels);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user-list.jsp");
		requestDispatcher.forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("已幫你查詢完成");
		}
		
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("/user-form.jsp");
		
		rd.forward(request, response);
		System.out.println("已成功跳轉");
	}

	private void Insert(HttpServletRequest request, HttpServletResponse response, Fun_HotelDAO funDAO)
			throws SQLException, IOException, ServletException {
		String hotel_name = request.getParameter("hotel_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String boss_name = request.getParameter("boss_name");
		String phone =  request.getParameter("phone") ;
		String status = request.getParameter("status");
		String roomtype = request.getParameter("roomtype");
		Hotel hotel = new Hotel(hotel_name, price, boss_name, phone, status, roomtype);
		funDAO.insert(hotel);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Hotel_Servlet?action");
		requestDispatcher.forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("新增成功"+hotel);
		}
		

	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response, Fun_HotelDAO funDAO)
			throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		funDAO.delete(id);
		request.getRequestDispatcher("Hotel_Servlet?action");
		
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("已經刪除");
		}
		
		
	}
	
	private void update (HttpServletRequest request, HttpServletResponse response, Fun_HotelDAO funDAO) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String hotel_name = request.getParameter("hotel_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String boss_name =request.getParameter("boss_name");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String roomtype = request.getParameter("roomtype");
		Hotel hotel = new Hotel(id, hotel_name, price, boss_name, phone, status, roomtype);
		funDAO.update(hotel);
		request.getRequestDispatcher("Hotel_Servlet?action").forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("更新囉");
		}
	
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response,Fun_HotelDAO funDAO)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		Hotel edit = funDAO.findOrderByid(id);
		RequestDispatcher rd = request.getRequestDispatcher("/user-form-edit.jsp");
		request.setAttribute("Hotel", edit);
		rd.forward(request, response);
		
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("幫你取得id值為="+edit.getId());
		}
		
	
		
	}

	
	public void always (HttpServletRequest req,HttpServletResponse res,Fun_HotelDAO funDAO)throws IOException,ServletException {
		List<Hotel> always = funDAO.selectAll();
		req.setAttribute("Hotel", always);
		RequestDispatcher transer = req.getRequestDispatcher("user-list.jsp");
		transer.forward(req, res);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("Auto自動載入");
		}
	}
	
}

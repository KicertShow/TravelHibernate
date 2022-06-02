package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import dao.dao.impl.Fun_HotelDAO;
import dao.service.HotelServiceImpl;
import model.Hotel;

@WebServlet("/Hotel_Servlet")
	

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 定義檔案暫存門檻
maxFileSize = 1024 * 1024 * 10, // 允許單個檔案最大大小；當上傳檔案大小超過定義會丟出 exception (IllegalStateException)
maxRequestSize = 1024 * 1024 * 50 // 允許整個 multipart/form-data 要求最大大小；當上傳檔案大小超過定義會丟出 exception (IllegalStateException)
)
public class Hotel_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Fun_HotelDAO funDAO = new Fun_HotelDAO();
		HotelServiceImpl daoimpl = new HotelServiceImpl();
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");

		try {
			switch (action) {
			case "query":
				processQuery(req, res, daoimpl);
				break;
			case "insert":
				Insert(req, res, daoimpl);
				break;
			case "update":
				update(req, res, daoimpl);
				break;
			case "edit":
				showEditForm(req, res, daoimpl);
				break;
			case "new":
				showNewForm(req, res);
			case "delete":
				delete(req, res, daoimpl);
			default:
				always(req,res,daoimpl);
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

	private void processQuery(HttpServletRequest request, HttpServletResponse response ,HotelServiceImpl daoimpl)
			throws SQLException, IOException, ServletException {
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		List<Hotel> hotels = daoimpl.findAll();
		
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

	private void Insert(HttpServletRequest request, HttpServletResponse response, HotelServiceImpl daoimpl)
			throws SQLException, IOException, ServletException {
		String hotel_name = request.getParameter("hotel_name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		String boss_name = request.getParameter("boss_name");
		String phone =  request.getParameter("phone") ;
		String status = request.getParameter("status");
		String roomtype = request.getParameter("roomtype");
		Part part = request.getPart("picture");
		
		BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
		byte[] pic = new byte[bis.available()];
		bis.read();
		Hotel hotel = new Hotel(hotel_name, price, boss_name, phone, status, roomtype, pic);
		daoimpl.save(hotel);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Hotel_Servlet?action");
		requestDispatcher.forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("新增成功"+hotel);
		}
		

	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response,HotelServiceImpl daoimpl)
			throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		daoimpl.delete(id);
		request.getRequestDispatcher("Hotel_Servlet?action");
		
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("已經刪除");
		}
		
		
	}
	
	private void update (HttpServletRequest request, HttpServletResponse response,HotelServiceImpl daoimpl) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String hotel_name = request.getParameter("hotel_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String boss_name =request.getParameter("boss_name");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String roomtype = request.getParameter("roomtype");
		Part part = request.getPart("picture");
		BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
		byte[] pic = new byte[bis.available()];
		
		Hotel hotel =  new Hotel(hotel_name, price, boss_name, phone, status, roomtype, pic);
		daoimpl.update(hotel);
		request.getRequestDispatcher("Hotel_Servlet?action").forward(request, response);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("更新囉");
		}
	
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response,HotelServiceImpl daoimpl)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		Hotel edit = daoimpl.findById(id);
		RequestDispatcher rd = request.getRequestDispatcher("/user-form-edit.jsp");
		request.setAttribute("Hotel", edit);
		rd.forward(request, response);
		
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("幫你取得id值為="+edit.getId());
		}
		
	
		
	}

	
	public void always (HttpServletRequest req,HttpServletResponse res,HotelServiceImpl daoimpl)throws IOException,ServletException {
		List<Hotel> finall = daoimpl.findAll();
		req.setAttribute("Hotel", finall);
		RequestDispatcher transer = req.getRequestDispatcher("user-list.jsp");
		transer.forward(req, res);
		int i =0;
		for(i=0; i<5; i++) {
			System.out.println("Auto自動載入");
		}
	}
	
}

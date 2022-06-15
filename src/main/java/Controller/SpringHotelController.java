package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import dao.service.HotelServiceImpl;
import model.Hotel;

@Controller
public class SpringHotelController {

	
	@RequestMapping(path = "/ShowNewForm",method = RequestMethod.POST)
		public String ShowNewForm(@RequestParam("showNewForm")String showNewForm,Model m ,SessionStatus status,HotelServiceImpl impl){
		
		System.out.println("This is success transfer to new form");
		return "/user-form.jsp";
	}
	
	
	
	
		@RequestMapping(path = "/Hotel.Query",method = RequestMethod.POST)
		public String query(@RequestParam("query")String query,Model m ,SessionStatus status,HotelServiceImpl daoimpl){
			List<Hotel> finall = daoimpl.findAll();
			m.addAttribute("Hotel", finall);
			System.out.println("Help you find out all,Thank God!^^");
			return "/user-list.jsp";
		}
		
		
		@RequestMapping(path = "/Hotel.Delete",method = RequestMethod.POST)
		public String Delete(@RequestParam("DeleteId")int DeleteId,Model m ,SessionStatus status,HotelServiceImpl daoimpl){
			daoimpl.delete(DeleteId);
			System.out.println("Delte you want it !");
			return "/user-list.jsp";
		}
		
		
		
		
		@RequestMapping(path = "/shoeEditForm",method = RequestMethod.POST)
		public String showEditForm(@RequestParam("UpdateId")int UpdateId,Model m ,SessionStatus status,HotelServiceImpl daoimpl){
			Hotel editID = daoimpl.findById(UpdateId);
			m.addAttribute("Hotel", editID);
		System.out.println("This is success transfer to Edit form");
		return "/user-form-edit.jsp";
		}
		
		@RequestMapping(path = "/Insert",method = RequestMethod.POST)
		public String Insert(
				@RequestParam("hotel_name")String hotel_name,
				@RequestParam("price")Integer price,
				@RequestParam("boss_name")String boss_name,
				@RequestParam("phone")String phone,
				@RequestParam("status")String status,
				@RequestParam("roomtype")String roomtype,
				Model m ,HotelServiceImpl daoimpl,Hotel hotel,HttpServletRequest request)throws ServletException,IOException,SQLException{
			Part part = request.getPart("picture");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
			byte[] pic = new byte[bis.available()];
			bis.read(pic);
			
			Hotel hotel2 = new Hotel(hotel_name, price, boss_name, phone, status, roomtype, pic);
			daoimpl.save(hotel2);
		System.out.println("Insert ");
		return "/user-list.jsp";
		}
		
		@RequestMapping(path = "/Update",method = RequestMethod.POST)
		public String Update(
				@RequestParam("hotel_name")String hotel_name,
				@RequestParam("price")Integer price,
				@RequestParam("boss_name")String boss_name,
				@RequestParam("phone")String phone,
				@RequestParam("status")String status,
				@RequestParam("roomtype")String roomtype,
				Model m ,HotelServiceImpl daoimpl,Hotel hotel,HttpServletRequest request)throws ServletException,IOException,SQLException{
			Part part = request.getPart("picture");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
			byte[] pic = new byte[bis.available()];
			bis.read(pic);
			
			Hotel hotel2 = new Hotel(hotel_name, price, boss_name, phone, status, roomtype, pic);
			daoimpl.update(hotel2);
		System.out.println("Update ");
		return "/user-list.jsp";
		}
		
//		Map<String, String> errors =new HashMap<String,String>();
//		m.addAttribute("errors", errors);  //=request.setAttribute("errors", errors);
//	
//		if (userName==null || userName.length()==0) {
//			errors.put("name", "name is required");
//		}
//	
//		if (errors !=null && !errors.isEmpty()) {
////		return new  ModelAndView("/form.jsp");
//			return "/form.jsp";
//		}
//		m.addAttribute("userName", userName);
//			return "/final.jsp";
//			
//		}
		
		
		
	}

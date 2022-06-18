package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import model.Hotel;
import model.HotelService;

@Controller
public class SpringHotelController {

	@GetMapping("/hotel")
		public  String Index() {
			return "user-list";
		}
	
	
	
	
	@RequestMapping(path = "/ShowNewForm",method = RequestMethod.POST)
		public String ShowNewForm(@RequestParam("showNewForm")String showNewForm,Model m ,SessionStatus status,HotelService impl){
		
		System.out.println("This is success transfer to new form");
		return "user-form";
	}
	
	
	

		@RequestMapping(path = "/Hotel.Query",method = RequestMethod.POST)
		public String query(@RequestParam("query")String query,Model m ,SessionStatus status,HotelService daoimpl){
			List<Hotel> finall = daoimpl.findAll();
			m.addAttribute("Hotel", finall);
			System.out.println("Help you find out all,Thank God!^^");
			return "user-list";
		}
		
		
		@RequestMapping(path = "/Hotel.Delete",method = RequestMethod.POST)
		public String Delete(@RequestParam("DeleteId")int DeleteId,Model m ,SessionStatus status,HotelService daoimpl){
			daoimpl.delete(DeleteId);
			System.out.println("Delte you want it !");
			return "user-list";
		}
		
		
		
		
		@RequestMapping(path = "/shoeEditForm",method = RequestMethod.POST)
		public String showEditForm(@RequestParam("UpdateId")int UpdateId,Model m ,SessionStatus status,HotelService daoimpl){
			Hotel editID = daoimpl.findById(UpdateId);
			m.addAttribute("Hotel", editID);
		System.out.println("This is success transfer to Edit form");
		return "user-form-edit";
		}
		
		
		
		@RequestMapping(path = "/insert",method = RequestMethod.POST)
		public String Insert(
				@RequestParam(name = "hotel_name" ) String hotel_name,
				@RequestParam(name = "price") Integer price,
				@RequestParam(name ="boss_name") String boss_name,
				@RequestParam(name ="phone") String phone,
				@RequestParam(name ="status") String status,
				@RequestParam(name ="roomtype") String roomtype,
				Model m ,HotelService daoimpl,Hotel hotel ,HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException{
			Hotel hh = new Hotel();
			m.addAttribute("Hotel", hh);
			Part part = request.getPart("picture");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
			byte[] pic = new byte[bis.available()];
			bis.read(pic);
			
			Hotel hotel2 = new Hotel(hotel_name, price, boss_name, phone, status, roomtype, pic);
			daoimpl.save(hotel2);
		System.out.println(hotel_name+price);
		return "user-list";
		}
		
		@RequestMapping(path = "/Update",method = RequestMethod.POST)
		public String Update(
				@RequestParam(name = "id")Integer id,
				@RequestParam(name = "hotel_name")String hotel_name,
				@RequestParam(name = "price")Integer price,
				@RequestParam(name ="boss_name")String boss_name,
				@RequestParam(name ="phone")String phone,
				@RequestParam(name ="status")String status,
				@RequestParam(name ="roomtype")String roomtype,
				Model m ,HotelService daoimpl,Hotel hotel,HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException{
			Part part = request.getPart("picture");
			BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
			byte[] pic = new byte[bis.available()];
			bis.read(pic);
			
			Hotel hotel2 =  new Hotel(id, hotel_name, price, boss_name, phone, status, roomtype, pic);
			daoimpl.update(hotel2);
		System.out.println("Update ");
		return "user-list";
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

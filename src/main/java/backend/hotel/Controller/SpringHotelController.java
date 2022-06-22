package backend.hotel.Controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import backend.hotel.model.hotel.Hotel;
import backend.hotel.model.hotel.HotelService;
import backend.hotel.model.hotel.InterFaceHotelService;

@Controller
public class SpringHotelController {
	
	
	InterFaceHotelService daoimpl;
	ServletContext context;
	@Autowired
	public SpringHotelController(InterFaceHotelService daoimpl, ServletContext context) {
		this.daoimpl = daoimpl;
		this.context = context;
	}




	@GetMapping("/hotel")
		public  String Index() {
			return "user-list";
		}
	
	
	
	@GetMapping("/ShowNewForm")
		public String ShowNewForm(@RequestParam("showNewForm")String showNewForm,Model m ,SessionStatus status){
		m.addAttribute("hotel", new Hotel());
		System.out.println("This is success transfer to new form");
		return "user-form";
	}
	
	@PostMapping(value = "/ShowNewForm")
	public String Insert(
		@ModelAttribute("hotel")Hotel hotels,BindingResult result) {
		System.out.println("準備新增接收資料了");
		MultipartFile picture = hotels.getProductImage();
		String originalFilename = picture.getOriginalFilename();
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			hotels.setFileName(originalFilename);
		}

		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (picture != null && !picture.isEmpty()) {
			try {
				byte[] b = picture.getBytes();
				Blob blob = new SerialBlob(b);
				hotels.setImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
//		

		Timestamp adminTime = new Timestamp(System.currentTimeMillis());
		hotels.setAdmissionTime(adminTime);
		
		try {
			daoimpl.save(hotels);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			result.rejectValue("account", "", "帳號已存在，請重新輸入");
			return "user-form";
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
			result.rejectValue("account", "", "請通知系統人員...");
			return "user-form";
		}
//		// 將上傳的檔案移到指定的資料夾, 目前註解此功能
//		try {
//			File imageFolder = new File(rootDirectory, "images");
//			if (!imageFolder.exists())
//				imageFolder.mkdirs();
//			File file = new File(imageFolder, "MemberImage_" + member.getId() + ext);
//			productImage.transferTo(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//		}
		return "user-list";
	}
	
	
	

		@RequestMapping(path = "/Hotel.Query",method = RequestMethod.POST)
		public String query(@RequestParam("query")String query,Model m ,SessionStatus status){
			m.addAttribute("Hotel", daoimpl.findAll());
			System.out.println("Help you find out all,Thank God!^^");
			return "user-list";
		}
		//取照片
		@GetMapping("/crm/picture/{id}")
		public ResponseEntity<byte[]> getPicture(@PathVariable("id") Integer id) {
			byte[] body = null;
			ResponseEntity<byte[]> re = null;
			MediaType mediaType = null;
			HttpHeaders headers = new HttpHeaders();
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());

			Hotel hotel = daoimpl.findById(id);
			if (hotel == null) {
				return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			}
			
			String filename = hotel.getFileName();
			if (filename != null) {
				if (filename.toLowerCase().endsWith("jfif")) {
					mediaType = MediaType.valueOf(context.getMimeType("dummy.jpeg"));
				} else {
					mediaType = MediaType.valueOf(context.getMimeType(filename));
					headers.setContentType(mediaType);
				}
			}
			Blob blob = hotel.getImage();
			if (blob != null) {
				body = blobToByteArray(blob);
//			} else {
				String path = null;
//				if (hotel.getGender() == null || hotel.getGender().length() == 0) {
//					path = noImageMale;
//				} else if (hotel.getGender().equals("M")) {
//					path = noImageMale;
//				} else {
//					path = noImageFemale;
//					;
//				}
				body = fileToByteArray(path);
			}
			re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

			return re;
		}
		
		private byte[] fileToByteArray(String path) {
			byte[] result = null;
			try (InputStream is = context.getResourceAsStream(path);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
				byte[] b = new byte[819200];
				int len = 0;
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
				result = baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public byte[] blobToByteArray(Blob blob) {
			byte[] result = null;
			try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
				byte[] b = new byte[819200];
				int len = 0;
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
				result = baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;

		}
		
		@RequestMapping(path = "/Hotel.Delete",method = RequestMethod.POST)
		public String Delete(@RequestParam("DeleteId")int DeleteId,Model m ,SessionStatus status ){
			daoimpl.delete(DeleteId);
			System.out.println("Delte you want it !");
			return "user-list";
		}
		
		
		
		
		@RequestMapping(path = "/shoeEditForm",method = RequestMethod.POST)
		public String showEditForm(@RequestParam("UpdateId")int UpdateId,Model m ,SessionStatus status){
			Hotel editID = daoimpl.findById(UpdateId);
			m.addAttribute("Hotel", editID);
		System.out.println("This is success transfer to Edit form");
		return "user-form-edit";
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
			
//			Hotel hotel2 =  new Hotel(id, hotel_name, price, boss_name, phone, status, roomtype, pic);
//			daoimpl.update(hotel2);
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

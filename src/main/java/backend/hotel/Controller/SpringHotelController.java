package backend.hotel.Controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
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
import backend.hotel.model.hotel.InterFaceHotelService;

@Controller
public class SpringHotelController {
	
	@Autowired
	InterFaceHotelService hotelService;
	@Autowired
	ServletContext context;
	




	@GetMapping("/hotel")
		public  String Index(Model m) {
		m.addAttribute("Hotel", hotelService.findAll());
		System.out.println("Help you find out all,Thank God!^^");
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
			hotelService.save(hotels);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			result.rejectValue("account", "", "帳號已存在，請重新輸入");
			return "user-form";
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
			result.rejectValue("account", "", "請通知系統人員...");
			return "user-form";
		}
		System.out.println("222此方法儲存");
		return "redirect:/hotel";
	}
	
	
	

		
		
			
		
		@RequestMapping(path = "/Hotel.Delete",method = RequestMethod.GET)
		public String Delete(@RequestParam("DeleteId")Integer DeleteId,Model m ,SessionStatus status ){
			hotelService.delete(DeleteId);
			for (int i = 0; i < 5; i++) {
				System.out.println("Delte you want it !"+DeleteId);				
			}
			return "redirect:/hotel";
		}
		
		
		
		@RequestMapping(path = "/showEditForm",method = RequestMethod.GET)
		public String showEditForm(@RequestParam("UpdateId")Integer UpdateId,Model m ){
			Hotel editID = hotelService.findById(UpdateId);
			m.addAttribute("hotel", editID);
			
		System.out.println("This is success transfer to Edit form");
		return "user-form-edit";
		}
		
		
	
		
		
		
		@RequestMapping(path = "/showEditForm",method = RequestMethod.POST)
		public String Update(
				@ModelAttribute("hotel")Hotel hotels,
				BindingResult result,Model model) {
			System.out.println("準備新增接收資料了");
			Timestamp adminTime = new Timestamp(System.currentTimeMillis());
			hotels.setAdmissionTime(adminTime);

			MultipartFile picture = hotels.getProductImage();

			if (picture.getSize() == 0) {
				// 表示使用者並未挑選圖片
			} else {
				String originalFilename = picture.getOriginalFilename();
				if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
					hotels.setFileName(originalFilename);
				}

				// 建立Blob物件
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
			}
			hotelService.update(hotels);
			return "redirect:/hotel";
		}
		
		//取照片
				@GetMapping("/crm/picture/{id}")
				public ResponseEntity<byte[]> getPicture(@PathVariable("id") Integer id) {
					byte[] body = null;
					ResponseEntity<byte[]> re = null;
					MediaType mediaType = null;
					HttpHeaders headers = new HttpHeaders();
					headers.setCacheControl(CacheControl.noCache().getHeaderValue());

					Hotel hotel = hotelService.findById(id);
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
					
					try {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						InputStream is = blob.getBinaryStream();
						byte[] b = new byte[81920];
						int len = 0;
						while ((len = is.read(b)) != -1) {
							baos.write(b, 0, len);
						}
						headers.setContentType(mediaType);
						headers.setCacheControl(CacheControl.noCache().getHeaderValue());
						re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return re;
				}
		
		
		
	}

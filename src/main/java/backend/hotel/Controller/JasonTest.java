package backend.hotel.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import backend.hotel.model.JasonBean;


@Controller
public class JasonTest {

	@GetMapping("/jsoncreate12.controller")
	@ResponseBody  //application/json 自動轉成jason
	public JasonBean processJsonAction3() throws JsonProcessingException {
		JasonBean hBean2 = new JasonBean();
		hBean2.setBossname("Kennyshow");
		hBean2.setHotelname("Great Hotel");
		
		return  hBean2;
	}
	
	
	
		

}

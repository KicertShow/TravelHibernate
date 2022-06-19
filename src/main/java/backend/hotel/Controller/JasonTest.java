package backend.hotel.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import backend.hotel.model.JasonBean;


@Controller
public class JasonTest {

	@GetMapping("/jsoncreate12.controller")
	@ResponseBody  //application/json 自動轉成jason
	public ArrayList<JasonBean> processJsonAction3() throws JsonProcessingException {
		JasonBean hBean2 = new JasonBean();
		hBean2.setBossname("Kenny");
		hBean2.setHotelname("Great Hotel");
		JasonBean hBean3 = new JasonBean();
		hBean3.setBossname("惠文");
		hBean3.setHotelname("Great Hotel");
		ArrayList<JasonBean> lists = new ArrayList<JasonBean>();
		lists.add(hBean2);
		lists.add(hBean3);
		
		return  lists;
	}
	
	
	
		

}

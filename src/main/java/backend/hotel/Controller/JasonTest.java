package backend.hotel.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import backend.hotel.model.hotel.JasonBean;


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
	
	@GetMapping("Jason")
	@ResponseBody
	public ArrayList<JasonBean> TestJason(){
		ArrayList<JasonBean> lists = new ArrayList<JasonBean>();
		JasonBean J1 = new JasonBean();
		J1.setBossname("KennyShow");
		J1.setHotelname("AmercanHotel");
		JasonBean J2 = 	new JasonBean();
		J2.setBossname("kennyshow");
		J2.setHotelname("hahha");
		lists.add(J2);
		lists.add(J1);
		return lists;
	}
	
	
	
		

}

package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringHotelController {

	
	@RequestMapping(path = "/SpringHotelController",method = RequestMethod.GET)
		public String processAction(@RequestParam("asd")String asd) {
			
			
			
			
			return "123";
		}
}

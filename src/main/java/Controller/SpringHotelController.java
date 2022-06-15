package Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class SpringHotelController {

	
	@RequestMapping(path = "/SpringHotelController",method = RequestMethod.GET)
		public String processAction(@RequestParam("userName")String userName,Model m ,SessionStatus status){
		Map<String, String> errors =new HashMap<String,String>();
		m.addAttribute("errors", errors);  //=request.setAttribute("errors", errors);
	
		if (userName==null || userName.length()==0) {
			errors.put("name", "name is required");
		}
	
		if (errors !=null && !errors.isEmpty()) {
//		return new  ModelAndView("/form.jsp");
			return "/form.jsp";
		}
		m.addAttribute("userName", userName);
			return "/final.jsp";
			
		}
}

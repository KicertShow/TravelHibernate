package Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller



public class test {
@RequestMapping(path = "/test" ,method = RequestMethod.GET)
	public String test123(@RequestParam("userName")String usernaString,@RequestParam("userpwd")String userpwd) {
		System.out.println(usernaString+userpwd);
		
		return "user-list.jsp";
	}
	
}

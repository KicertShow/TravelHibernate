package backend.hotel.Controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AccountController {
		@GetMapping("test")
		public String AccountController01() {
			return "account";
		}
		@PostMapping("accountAction")
	public String  AccountController(
			@RequestParam("username")String username,
			@RequestParam("userpwd")String userpwd,Model m) {
			HashMap<Object, Object> errors = new HashMap<>();
			m.addAttribute("errors", errors);
			
			if (username ==null && username.length()==0 ) {
				errors.put("msg", "必須輸入");
			}
			if (errors !=null && !errors.isEmpty()) {
				return "account";
			}
			m.addAttribute("username", username);
			m.addAttribute("userpwd", userpwd);
			return "accountSuccess";
	}

}

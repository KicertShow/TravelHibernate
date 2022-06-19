package backend.hotel.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.hotel.model.logging.Logging;
import backend.hotel.model.logging.LoggingInterFaceService;
@Controller
public class AccountController {
		@Autowired
	private LoggingInterFaceService logService;
	
	
		@GetMapping("loginsystemmain.controller")
		public String AccountController01() {
		return "logging";
		}
		
		
		@PostMapping("checklogin.controller")
		public String processAction(@RequestParam("userName")String user,@RequestParam("userPwd")String pwd,Model m) {
			HashMap<String, String> errors = new HashMap<String, String>();
			m.addAttribute("errors", errors);
			
			if (user ==null || user.length()==0) {
				errors.put("user", "username is required");
			}
			if (pwd ==null || pwd.length()==0) {
				errors.put("pwd", "pwd is required");
			}
			if (errors !=null && !errors.isEmpty()) {
				return "logging";
			}
			boolean result = logService.checklog(new Logging(user,pwd));
			if (result) {
				m.addAttribute("user", user);
				m.addAttribute("pwd", pwd);
				return "loginSuccess";
			}
			errors.put("msg", "please input correct username or password");
			return "logging";
		
		}

}

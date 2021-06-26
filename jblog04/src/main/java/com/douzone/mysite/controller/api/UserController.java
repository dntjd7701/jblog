package com.douzone.mysite.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 얘는 응답을 json으로.
 * @author kang-woosung
 *
 */
//@RestController >> ResponseBody 안써도 다 메시지로. 
//@GetMapping() -- >RequestMapping, get 방식.
@Controller("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
//	@Autowired
//	private UserService userService;
//	
//	@ResponseBody
//	@RequestMapping("/checkemail")
//	public JsonResult checkEmail(
//			@RequestParam(value="email", required=true, defaultValue="") String email) {
//		
//		UserVo userVo = userService.getUser(email);
//		return JsonResult.success(userVo != null);
//	}

}

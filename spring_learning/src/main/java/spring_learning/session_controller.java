package spring_learning;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

//Spring Session 사용방법
@Controller
/*
 @SessionAttributes : Controller에서 셋팅된 값이며, DTO가 있어야 정상적으로 핸들링됨
 DTO형태가 Session 형태의 DTO, @SessionAttributes => API Server
*/

//@SessionAttributes("mid")	
public class session_controller {

	//Session을 의존성 주입형태로 interface를 필드에 선언하여 모든 메소드에 세션을 적용가능
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) { //HttpSession(Spring) = HttpServletRequest를 이용하여 session 동일 	
		String userid = "kim";
		se.setAttribute("mid",userid);
		return null;
	}
	//HttpSession : Controller, DAO, DTO, VO
	//해당 세션을 생성 후 문자열 변수로 변환하여 Model 전달 => JSP에 출력
	@GetMapping("/session2.do")
	public String session2(HttpSession se,Model m) {
		String id = (String)se.getAttribute("mid");
		/*
		System.out.println(id);
		*/
		m.addAttribute("mid",id);
		return "session";
	}
	
	/*
	 @SessionAttribute = session.getAttribute 동일한 성능을 가진 어노테이션
	 해당 어노테이션 사용시 주의사항은 null일 경우 400 error가 발생할 수 있으므로
	 name 속성 그리고 required 속성을 핸들링하는 것이 중요한 포인트 입니다.
	 */
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name="mid", required = false)String mid) {
		System.out.println(mid);
		String test = (String)this.hs.getAttribute("mid");	//필드에 있는 세션
		System.out.println(test);
		return null;
	}
	
	
	@GetMapping("/session4.do")
	public String session4() {
		this.hs.invalidate();	//필드에 올려놓은 Session 을 로드하여 세션 초기화
		this.hs.removeAttribute("mid");	//특정 등록된 session키만 삭제
		System.out.println(this.hs.getAttribute("mid"));
		return null;
	}
	
}

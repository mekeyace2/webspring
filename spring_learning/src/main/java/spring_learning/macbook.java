package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {
	
	//@Autowird, @Inject : 의존성 주입 XML=>Java, Java=>XML
	
	//@Resource : new 클래스명 호출과 동일하게 작동을 하며, 
	//@Repository의 이름을 가져오는 역활
	@Resource(name="macbook_DAO")
	private macbook_DAO dao;
	
	@Resource(name="macbook_DTO")
	public macbook_DTO macbook_DTO;
	
	PrintWriter pw = null;
	
	/*
	  Model 과 HttpServletResponse 은 함께 사용하지 못합니다.
	  두개의 interface 역활이 같으므로 하나만 사용이 가능합니다. 
	 */
	//과정을 삭제처리하는 메소드
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		int result = this.dao.macbook_delete(Integer.parseInt(midx));
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('올바르게 해당 과정을 삭제 하였습니다.');"
					+ "location.href='./macbook_list.do';"
					+ "</script>");
		}
		this.pw.close();
		return null;
	}
	
	//과정 내용 수정을 처리하는 메소드
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		//insert, update, delete 무조건 결과를 int로 받음
		int result = this.dao.macbook_update(dto);	//DAO로 값을 전송
		System.out.println(result);
		String msg = "";
		if(result > 0) {
			msg = "alert('정상적으로 데이터가 수정 되었습니다.');"
					+ "location.href='./macbook_list.do';";
		}
		m.addAttribute("msg",msg);
		return "load";
	}
	

	//과정 수정 페이지(출력)
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m) {
		//System.out.println(midx);
		//DTO의 setter에 값을 이관 한 상황
		macbook_DTO onedata = this.dao.macbook_one(midx);
		//System.out.println(onedata.class_name);  //DTO의 getter 메소드를 호출
		m.addAttribute("onedata",onedata);		//JSTL로 값을 이관함
		return null;
	}
	
	
	
	
	
	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		//List<macbook_DTO> : DTO형태의 배열로 생성하여, JSP로 전달
		List<macbook_DTO> classList = this.dao.macbook_select();
		m.addAttribute("ea",classList.size());
		m.addAttribute("classList",classList);
		return null;
	}
	
	
	
	//개설된 과정을 삭제 하는 메소드
	
	
	
	//과정개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			int result = this.dao.macbook_insert(dto);
			String msg = "";
			if(result > 0) {
			   	msg = "alert('과정 개설이 올바르게 생성 되었습니다.');"
			   			+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg",msg);
		}catch (Exception e) {
			
		}finally {
			
		}
		
		
		return "load";
	}
	
	
}

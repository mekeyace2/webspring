package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	
	@Resource(name="banner_DTO")
	banner_DTO dto;	//new
	
	@Resource(name="banner_DAO")
	banner_DAO dao;
	
	@Resource(name="file_rename")
	file_rename fname;	//파일명을 개발자가 원하는 형태로 변경
	
	//Field에 있는 dto와 매개변수에 있는 dto 다른 형태를 가지고 있습니다.
	//this.dto => Field에 있는 dto 지칭,  dto => 매개변수에 있는 dto
	
	//@ModelAttribute : 1:1매칭 => name 과 DTO 자료형 변수가 같은것 있으면 무조건 값을 setter발동
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name = "dto") banner_DTO dto,
			MultipartFile bfile, HttpServletRequest req) throws Exception {
		
		String file_new = null;
	    if(bfile.getSize() > 0) {
	    	file_new = this.fname.rename(bfile.getOriginalFilename());
	    	//웹 디렉토리 개발자가 생성한 파일명으로 저장하는 코드
	    	String url = req.getServletContext().getRealPath("/upload/");
	    	FileCopyUtils.copy(bfile.getBytes(),new File(url + file_new));
	    	dto.setFile_url("/upload/" + file_new); //웹디렉토리 경로 및 파일명
	    	dto.setFile_new(file_new);		//개발자가 원하는 방식으로 파일명을 변경값
	    	dto.setFile_ori(bfile.getOriginalFilename());	//사용자가 적용한 파일명
	    }
		
		this.callback = this.dao.new_banner(dto);
	    System.out.println(this.callback);
		
		return null;
	}
	
	//search 검색에 관련사항은 필수조건은 아니며, 또한 null 처리가 되었을 경우 defaultValue 값이 공백처리
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m,
			@RequestParam(defaultValue = "", required = false)String search,
			@RequestParam(defaultValue = "1", required = false)Integer pageno
			) {
	    //데이터 총 갯수 확인 코드
		int total = this.dao.banner_total();
		
		int userpage = 0;	//사용자가 클릭한 페이지 번호에 맞는 순차번호 계산값
		//끝
		if(pageno == 1) {
			userpage = 0;
		}else {	//1외 페이지 번호를 클릭시
			userpage = (pageno - 1) * 5;
		}
		//해당 일련번호를 계산하여 jsp에 전달
		m.addAttribute("userpage",userpage);
		
		List<banner_DTO> all = null;
		if(search.intern() == "") {	//검색어가 없을 경우
			all = this.dao.all_banner(pageno);	//인자값 : 사용자가 페이지 번호를 클릭한 값
		}
		else {	//검색어가 있을 경우
			all = this.dao.search_banner(search);
		}
		m.addAttribute("total",total);		//데이터 전체 갯수
		m.addAttribute("search",search);	//검색어를 jsp 전달
		m.addAttribute("all",all);
		return null;
	}
	
}





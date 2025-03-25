package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
//@Mapper : mapper.xml과 연동하는 interface 입니다.
//** mapper.xml에서 사용하는 id 기준으로 메소드 이름을 설정하게 됩니다.
@Mapper
public interface macbook_mapper {
	public int macbook_update(macbook_DTO dto);	//데이터 수정 DAO
	public int macbook_insert(macbook_DTO dto);  //신규 데이터 입력
	List<macbook_DTO> macbook_select();	//전체 데이터
	macbook_DTO macbook_one(String midx);	//하나의 데이터만 가져옴
	public int macbook_delete(int midx);	//삭제처리 (숫자값)	
}

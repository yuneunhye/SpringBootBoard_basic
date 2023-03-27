package com.example.Myapp1.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Myapp1.domain.BoardDto;



@Repository("boardDao")  //객체 주입시 @Autowired - 동일한 인터페이스를 상속받은 클래스가 2개있을때 
                         //누굴 주입해줘야 할지 모른다. @Resource(name="boardDao")
public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		
		return sm.selectList("Board_getList", dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		// TODO Auto-generated method stub
		return sm.selectOne("Board_getView", dto);
	}

	@Override
	public void insert(BoardDto dto) {
		// TODO Auto-generated method stub
		sm.insert("Board_insert", dto);
	}

	@Override
	public void update(BoardDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BoardDto dto) {
		// TODO Auto-generated method stub
		
	}

}

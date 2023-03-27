package com.example.Myapp1.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.Myapp1.domain.BoardDto;
import com.example.Myapp1.repository.BoardDao;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="boardDao")
	BoardDao boardDao;
	
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return boardDao.getList(dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		// TODO Auto-generated method stub
		return boardDao.getView(dto);
	}

	@Override
	public void insert(BoardDto dto) {
		boardDao.insert(dto);
	}

	@Override
	public void update(BoardDto dto) {
		boardDao.update(dto);
	}

	@Override
	public void delete(BoardDto dto) {
		boardDao.delete(dto);
	}

}

package com.example.Myapp1.service;

import java.util.List;

import com.example.Myapp1.domain.BoardDto;

public interface BoardService {
	
	List<BoardDto> getList(BoardDto dto);
	BoardDto getView(BoardDto dto);
	void insert (BoardDto dto);
	void update (BoardDto dto);
	void delete (BoardDto dto);
	

}

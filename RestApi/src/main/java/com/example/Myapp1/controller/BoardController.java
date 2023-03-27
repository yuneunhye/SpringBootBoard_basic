package com.example.Myapp1.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Myapp1.common.FileDownload;
import com.example.Myapp1.domain.BoardDto;
import com.example.Myapp1.service.BoardService;


@Controller 
public class BoardController {
	
	@Resource(name = "boardService")
	BoardService service;
	
	@GetMapping("/board/list")
	String board_list(BoardDto dto, Model model)
	{
		
		for(BoardDto temp : service.getList(dto))
		{
			System.out.println(temp.getContents());
		}
		model.addAttribute("boardList", service.getList(dto));
		return "board/board_list";
	}
	
	//http://localhost:9000/board/view/1
	@GetMapping("/board/view/{id}")
	String board_view(@PathVariable("id")String id, BoardDto dto, Model model)
	{
		dto.setId(id);
		BoardDto resultDto = service.getView(dto);
		
		resultDto.setContents(resultDto.getContents().replaceAll("\n",  "<br/>"));
		model.addAttribute("boardDto", resultDto);
		return "board/board_view";
	}
	
	
	@GetMapping("/board/write")
	String board_write(BoardDto dto, Model model)
	{
		return "board/board_write";
	}
	
	//Ajax로 데이터 받아서 처리하자 
	@ResponseBody  //데이터를 JSON형식으로 출력하게 한다 
	@PostMapping("/board/save")
	HashMap<String, String>board_save(BoardDto dto){
		System.out.println(dto.getTitle());
		HashMap<String, String> map = new HashMap<String, String>();
		
		//정보가 저장이 잘 되었으면 성공메시지를 실패하면 실패메시지를 반환해줌 ajax형식으로
		try
		{
			service.insert(dto);
			map.put("result", "success");
		}
		catch(Exception e)
		{
			map.put("result", "fail");
		}
		return map;
	}
	
	@Value("${fileUploadPath}")
	String fileUploadPath;
	
	//http://127.0.0.1:8090/download/image/11.jpg
	@GetMapping("/download/image/{file}")
	public void download(@PathVariable("file")String file, HttpServletResponse response)
	{
		FileDownload.download(fileUploadPath+"/image", file, response);
	}
}







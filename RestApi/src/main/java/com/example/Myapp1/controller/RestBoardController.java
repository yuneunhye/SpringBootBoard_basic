package com.example.Myapp1.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Myapp1.common.FileUploadUtil;
import com.example.Myapp1.domain.BoardDto;
import com.example.Myapp1.service.BoardService;

@CrossOrigin("*") // vue와 통신하기위한 애노테이션 원래는 도메인이나 아이피주소가 와야함 
@RestController
public class RestBoardController {

	@Value("${fileUploadPath}")
	String fileUploadPath;
	
	@Value("${domain}")
	String domain;
	
	
	@Resource(name="boardService")
	BoardService service;
	
	@GetMapping("/api/board/list")
	List<BoardDto> getList(){
		return service.getList(null);
	}
	
	@PostMapping("/api/board/insert")
	Map<String, String>insert(MultipartFile file, BoardDto dto)
	{
		Map<String, String>map =new HashMap<String, String>();
		String uploadDir = fileUploadPath+ "/image" ;
   		
   		//http://localhost:8090/fileUpload/image/1582531436.jpeg
   		if(file!=null)
   		{
   			try {
   				String filename=FileUploadUtil.upload(uploadDir, file);
   				dto.setFilename(filename);
   				dto.setImage_url(domain+"/"+uploadDir+"/"+filename);
   				service.insert(dto);
   				
   			} catch (IOException e) {
   				e.printStackTrace();
   			}
   		}
   		
   		map.put("result", "success");
   		return map;
		
	}
	
}

package com.example.Myapp1.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	
	private String id="";
	private String title="";
	private String writer="";
	private String contents="";
	private String wdate="";
	private String filename="";
	private String image_url="";

}

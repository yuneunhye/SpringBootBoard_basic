package com.example.Myapp1.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	//자바 cos.jar - 파일이름이중복될때 처리, 스프링 부트에서는 파일업로드시 파일명 중복을 해결을 안하고 있다
	//파일이 존재하는지 물어봐서 파일이름과 확장자를 분리시켜서 1.jpg, 1(1).jpg, 1(2).jpg
	//아니면 파일생성날짜와 사긴으로 새로운 파일명을 만드는방법 
	
	public static String getFileName(String contextPath, String oriFileName)
	{
		String filePath = contextPath; //파일 업로드 경로임 
		int pos = oriFileName.lastIndexOf(".");
		//파일명을 파일명과 확장자로 분리한다 
		System.out.println("pos########"+pos);
		String ext = oriFileName.substring(pos+1); //확장자
		String oriFile = oriFileName.substring(0, pos); //파일명
		String filename = oriFileName;
		
		//현재 파일명을 디렉토리에서 검색하자. 
		File newFile = new File(filePath + "/" +filename );
		int i=1;
		while(newFile.exists())//존재하면 
		{
			//새로운 파일명을 생성한다 
			filename = oriFile + "("+i+")."+ext;
			i++;
			newFile = new File(filePath + "/" +filename );
		}
		
		return filename; //더이상중복되는 파일이 없을때 반환한다 
	}
	
	//파일업로드 - static으로 만들음 
	public static String upload(String uploadDir,  
			 MultipartFile multipartFile) throws IOException
	{
		//업로드할 파일명을 새로 가져온다 
		String fileName = getFileName(uploadDir, multipartFile.getOriginalFilename());
		//업로드 경로를 가져온다 
		Path uploadPath = Paths.get(uploadDir);
        
		//디렉토리가 존재하지 않으면 새로 작성한다 
        if (!Files.exists(uploadPath)) {
            try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        //파일을 업로드 한다 
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);//이미 파일 존재하면 바꿔치기해라
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }    
        
        return fileName;//새로작성된 파일명을 보낸다. 
	}
	
}

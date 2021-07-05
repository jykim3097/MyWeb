package com.team404.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team404.command.MultiUploadVO;
import com.team404.command.UploadVO;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {

	// 예제 화면 처리
	@RequestMapping("/upload")
	public void snsBoard() {

	}

	// 단일 파일 업로드 형식
	@RequestMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {

		try {

			String fileName = file.getOriginalFilename(); // 실제 파일명
//			long fileSize = file.getSize(); //파일 사이즈
			String fileExtention = fileName.substring(fileName.lastIndexOf(".")); // 확장자 이름

			System.out.println(fileExtention);

			File saveFile = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileName);
			file.transferTo(saveFile); // 실제 파일을 로컬환경으로 저장

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "snsBoard/upload_ok"; // 결과화면
	}

	// 다중 파일 업로드 1
	@RequestMapping("/upload_ok2")
	public String upload_ok2(MultipartHttpServletRequest files) {

		System.out.println(files);

		List<MultipartFile> file = files.getFiles("file");

		try {

			for (int i = 0; i < file.size(); i++) {

				String fileName = file.get(i).getOriginalFilename(); // 실제 파일명
				String fileExtention = fileName.substring(fileName.lastIndexOf(".")); // 확장자 이름

				System.out.println(fileExtention);

				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileName);
				file.get(i).transferTo(saveFile); // 실제 파일을 로컬환경으로 저장
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "snsBoard/upload_ok";
	}

	// 다중 파일 업로드 2
	@RequestMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") List<MultipartFile> file) {
		
		try {
			for(int i=0; i< file.size(); i++) {
				
				String fileName = file.get(i).getOriginalFilename(); //실제 파일명
				String fileExtention = fileName.substring(fileName.lastIndexOf(".")); //확장자 이름
				
				System.out.println(fileExtention);
				
				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileName);
				file.get(i).transferTo(saveFile); //실제 파일을 로컬환경으로 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "snsBoard/upload_ok";
	}
	
	// 가변적인 폼 형식의 업로드
	@RequestMapping("/upload_ok4")
	public String upload_ok4(MultiUploadVO vo) {
		
		ArrayList<UploadVO> list = vo.getList();

		try {
			for(int i=0; i<list.size(); i++) {
				String fileName = list.get(i).getFile().getOriginalFilename(); //실제 파일명
				String fileExtention = fileName.substring(fileName.lastIndexOf(".")); //확장자 이름
				
				String name = list.get(i).getName();
				
				System.out.println("파일확장자: " + fileExtention);
				System.out.println("폼에서 작성한 이름: " + name);
				
				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileName);
				list.get(i).getFile().transferTo(saveFile); //실제 파일을 로컬환경으로 저장
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "snsBoard/upload_ok";
	}
}

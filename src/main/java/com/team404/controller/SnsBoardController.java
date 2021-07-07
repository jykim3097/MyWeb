package com.team404.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.team404.command.MultiUploadVO;
import com.team404.command.SnsBoardVO;
import com.team404.command.UploadVO;
import com.team404.command.UserVO;
import com.team404.snsboard.service.SnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {
	
	@Autowired
	@Qualifier("snsBoardService")
	private SnsBoardService snsBoardService;

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
	
	/// 210706 
	@RequestMapping("/snsList")
	public void snsList() {
		
	}
	
	//
	@ResponseBody
	@RequestMapping(value="/snsUpload", method=RequestMethod.POST)
	public String upload(@RequestParam("content") String content,
						 @RequestParam("file") MultipartFile file,
						 HttpSession session) {
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");

		try {
			String writer = userVO.getUserId();
			
//			System.out.println(writer);
//			System.out.println(file);
//			System.out.println(content);
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			String fileLoca = sdf.format(date); //폴더 위치
			
			File folder = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca); //폴더를 만들위치
			
			if(!folder.exists()) {
				folder.mkdir(); // 폴더 생성
			}

			// 파일명
			String fileRealName = file.getOriginalFilename();
			
			// 사이즈
			Long size = file.getSize();
			
			// 저장된 전체 경로
			String uploadPath = folder.getPath(); // 폴더명을 포함한 경로
			
			// DB에서 다른 유저와 이름이 겹치지 않게 처리
			String fileExtention = fileRealName.substring(fileRealName.lastIndexOf(".")); // 확장자 이름
			
			// 가짜 이름 만들기
			UUID uuid = UUID.randomUUID(); //16진수 랜덤값
			String uuids = uuid.toString().replaceAll("-", ""); //가짜 파일명 
			String fileName = uuids + fileExtention;
			
//			System.out.println("폴더 위치 : " + fileLoca);
//			System.out.println("파일명 : " + fileRealName);
//			System.out.println("사이즈 : " + size);
//			System.out.println("업로드 경로 : " + uploadPath);
//			System.out.println("업로드 파일명 : " + fileName);
			
			File saveFile = new File(uploadPath + "\\" + fileName);
			file.transferTo(saveFile); //파일 쓰기
			
			//DB작업
			SnsBoardVO vo = new SnsBoardVO(0, writer, content, uploadPath, fileLoca, fileName, fileRealName, null);
			int result = snsBoardService.insert(vo);
//			System.out.println("result : " + result);
			
			if(result == 1) {
				return "success insert";
			} else {
				return "fail insert";
			}
			
			
		} catch (NullPointerException e) { // 에러시에 fail
			return "fail return id";
		} catch (Exception e) {
			return "fail";
		}

	}
	
	//210707
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public ArrayList<SnsBoardVO> getList() {
		
		ArrayList<SnsBoardVO> list = snsBoardService.getList();
		
		return list;
	}
	
	// 이미지를 절대 주소로 보내면 에러가 뜨니까
	// 컨트롤러에서 이미지데이터를 반환하는 요청을 처리해야한다
	// PathVariable은 특수문자를 잘라버려서 특수문자도 받게 처리해야한다 :.+
//	@ResponseBody
//	@RequestMapping(value="/view/{fileLoca}/{fileName:.+}")
//	public byte[] view(@PathVariable("fileLoca") String fileLoca,
//					   @PathVariable("fileName") String fileName) {
//		
//		byte[] result = null;
//		
//		try {
//			// 파일 데이터를 바이트데이터로 변환해서 반환
//			File file = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca + "\\" + fileName);
//			result = FileCopyUtils.copyToByteArray(file);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	@ResponseBody
	@RequestMapping(value="/view/{fileLoca}/{fileName:.+}")
	public ResponseEntity<byte[]> view(@PathVariable("fileLoca") String fileLoca,
					   @PathVariable("fileName") String fileName) {
		
		ResponseEntity<byte[]> result = null;
		
		try {
		
			// 반환은 위랑 같지만 보내는 타입에 대한 문서를 정확히 적어서 보낸 거다
			// 파일 데이터를 바이트데이터로 변환해서 반환
			File file = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca + "\\" + fileName);
			
			// 반환형 헤더 객체
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath())); //path 타입으로 반환
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/download/{fileLoca}/{fileName:.+}")
	public ResponseEntity<byte[]> download(@PathVariable("fileLoca") String fileLoca,
										   @PathVariable("fileName") String fileName) {
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			File file = new File(APP_CONSTANT.UPLOAD_PATH + "\\" + fileLoca + "\\" + fileName);
			
			//반환할 헤더 객체 (다운로드 형식으로 속성을 추가)
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=" + fileName);
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

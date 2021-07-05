package com.team404.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVO {

	// 폼 화면에서 넘어오는 변수를 지정
	private String name;
	private MultipartFile file;
}

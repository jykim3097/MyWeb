<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="margin-top : 100px;">

	<h3>commons-fileupload 라이브러리 추가 <br>
		스프링 설정 파일에 최소 설정
	</h3>
	
	<!-- 단일 파일 업로드 -->
	<div style="border: 1px solid black; display:inline-block;">
		<form action="upload_ok" method="post" enctype="multipart/form-data">
			파일선택:<input type="file" name="file">
			<input type="submit" class="btn btn-default" value="전송">
		</form><br>
	</div>
	<!-- 다중 파일 업로드 -->
	<div style="border: 1px solid black; display:inline-block;">
		<form action="upload_ok2" method="post" enctype="multipart/form-data">
			파일선택:<input type="file" name="file" multiple="multiple">
			<input type="submit" class="btn btn-default" value="전송">
		</form><br>
	</div>
	
	<!-- 다중 파일 업로드 2 -->
	<div style="border: 1px solid black; display:inline-block;">
		<form action="upload_ok3" method="post" enctype="multipart/form-data">
			파일선택:<input type="file" name="file"><br>
			파일선택:<input type="file" name="file"><br>
			파일선택:<input type="file" name="file"><br>
			<input type="submit" class="btn btn-default" value="전송">
		</form>
	</div>
	
	<!-- 가변적 폼 형식의 업로드 -->
	<div style="border: 1px solid black; display:inline-block;">
		<form action="upload_ok4" method="post" enctype="multipart/form-data">
			이름:<input type="text" name="list[0].name"><br>
			파일선택:<input type="file" name="list[0].file"><br>
			
			이름:<input type="text" name="list[1].name"><br>
			파일선택:<input type="file" name="list[1].file"><br>
			
			이름:<input type="text" name="list[2].name"><br>
			파일선택:<input type="file" name="list[2].file"><br>
			
			<input type="submit" class="btn btn-default" value="전송">
		</form>
	</div>

</div>
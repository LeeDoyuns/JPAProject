package com.barlink.api.test.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {
	
	private long id;
	
	private String name;
	
	private int age;
	
	private String teamName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd") //return 할때 yyyy-MM-dd 형태로 변환
	private LocalDateTime joinDate;
	
	/*
	 * String 문자열 형태의 날짜 데이터 변환.
	 */
	public void setJoinDate(String date) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.joinDate = LocalDate.parse(date,df).atStartOfDay();
	}
	
//	public String getJoinDate(LocalDateTime date) {
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		return sf.format(date);
//		
//	}
	
//	@QueryProjection
//	public MemberDTO (String name, String teamName) {
//		this.name = name;
//		this.teamName = teamName;	
//	}
	
	

}

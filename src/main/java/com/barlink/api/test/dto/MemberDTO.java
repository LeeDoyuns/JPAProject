package com.barlink.api.test.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {
	
	private long id;
	
	private String name;
	
	private int age;
	
	private String teamName;
	
	private LocalDateTime joinDate;

//	@QueryProjection
//	public MemberDTO (String name, String teamName) {
//		this.name = name;
//		this.teamName = teamName;	
//	}
	
	

}

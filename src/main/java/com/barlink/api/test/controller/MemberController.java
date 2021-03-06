package com.barlink.api.test.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barlink.api.test.domain.TeamView;
import com.barlink.api.test.dto.MemberDTO;
import com.barlink.api.test.dto.SubQueryDTO;
import com.barlink.api.test.dto.TeamDTO;
import com.barlink.api.test.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jpa")
@Api(tags="Admin : 주류 상세 정보 기능 조회, 추가 & (검수 요청 수정, 삭제)")
public class MemberController {
	
	@Autowired
	private MemberService service;

	
	
	@PostMapping("/insertMember")
	public ResponseEntity<Map<String,Object>> insertMember(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();
		
		

		try {
			service.insertMember(dto);
			result.put("result", true);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	@PostMapping("/insertTeam")
	public ResponseEntity<Map<String,Object>> insertTeam(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();
		
		

		try {
			service.insertTeam(dto);
			result.put("result", true);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	@PutMapping("/updateMemberTeam")
	public ResponseEntity<Map<String,Object>> updateMemberTeam(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();
		
		

		try {
			service.updateMemberTeam(dto);
			result.put("result", true);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping("/selectMemberList")
	public ResponseEntity<Map<String,Object>> selectMemberList(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();

		try {
			List<MemberDTO> list = service.findAllMember(); 
			result.put("result", true);
			result.put("list", list);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	
	@PostMapping("/selectMemberList2")
	public ResponseEntity<Map<String,Object>> selectMemberList2(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();

		try {
			List<MemberDTO> list = service.findAllMember2(dto); 
			result.put("result", true);
			result.put("list", list);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	@PostMapping("/subQuery")
	public ResponseEntity<Map<String,Object>> subQuery(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();

		try {
			List<SubQueryDTO> list = service.subQuery(dto); 
			
			result.put("result", true);
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	@PostMapping("/subQuery2")
	public ResponseEntity<Map<String,Object>> subQuery2(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();
		
		try {
			List<TeamDTO> list = service.subQuery2(dto); 
			result.put("result", true);
			result.put("list", list);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	
	@PostMapping("/subQuery3")
	public ResponseEntity<Map<String,Object>> subQuery3(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberDTO dto){
		Map<String,Object> result = new HashMap();
		
		try {
			List<TeamView> list = service.subQuery3(); 
			result.put("result", true);
			result.put("list", list);
		}catch(Exception e) {
			result.put("result", false);
			result.put("message", e.getMessage());
		}
		return new ResponseEntity<Map<String,Object>> (result,HttpStatus.OK);
	}
	
	
	
	
	
	
}

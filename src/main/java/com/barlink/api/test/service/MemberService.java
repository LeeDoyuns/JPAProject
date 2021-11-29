package com.barlink.api.test.service;

import java.util.List;

import com.barlink.api.test.dto.MemberDTO;

public interface MemberService {

	void insertMember(MemberDTO dto);

	void insertTeam(MemberDTO dto);

	void updateMemberTeam(MemberDTO dto);

	List<MemberDTO> findAllMember();

	List<MemberDTO> findAllMember2(MemberDTO dto);

}

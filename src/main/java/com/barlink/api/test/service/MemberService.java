package com.barlink.api.test.service;

import java.util.List;

import com.barlink.api.test.domain.TeamView;
import com.barlink.api.test.dto.MemberDTO;
import com.barlink.api.test.dto.SubQueryDTO;
import com.barlink.api.test.dto.TeamDTO;

public interface MemberService {

	void insertMember(MemberDTO dto);

	void insertTeam(MemberDTO dto);

	void updateMemberTeam(MemberDTO dto);

	List<MemberDTO> findAllMember();

	List<MemberDTO> findAllMember2(MemberDTO dto);

	List<SubQueryDTO> subQuery(MemberDTO dto);

	List<TeamDTO> subQuery2(MemberDTO dto);

	List<TeamView> subQuery3();

}

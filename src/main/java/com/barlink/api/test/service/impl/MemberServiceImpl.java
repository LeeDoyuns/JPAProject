package com.barlink.api.test.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barlink.api.test.domain.Member;
import com.barlink.api.test.domain.QMember;
import com.barlink.api.test.domain.QTeam;
import com.barlink.api.test.domain.Team;
import com.barlink.api.test.dto.MemberDTO;
import com.barlink.api.test.service.MemberService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository repo;
	@Autowired
	private TeamRepo tRepo;
	
	/*QueryDSL을 사용하기 위한 필드*/
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public void insertMember(MemberDTO dto) {
		Member m = new Member();
		m.setAge(dto.getAge());
		m.setName(dto.getName());
		repo.save(m);
	}

	@Override
	public void insertTeam(MemberDTO dto) {
		Team t = new Team();
		t.setTeamName(dto.getTeamName());
		tRepo.save(t);
		
	}

	@Override
	public void updateMemberTeam(MemberDTO dto) {
		Team t = new Team();
		t.setTeamName(dto.getTeamName());
		t = tRepo.findByTeamName(t.getTeamName());
		
		List<Member> mList = repo.findByNameContains(dto.getName());
		
		for(Member m : mList) {
			m.setTeamId(t);
			m = repo.save(m);
		}
		
		
		
	}
	
	
	

	@Override
	public List<MemberDTO> findAllMember() {
		QMember mem = QMember.member;
		QTeam tem = QTeam.team;
		
		JPAQueryFactory factory = new JPAQueryFactory(em);
		
		/**
		 * 필드 직접 조회. 멤버변수들의 명칭이 일치해야 한다. 
		 */
		QueryResults<MemberDTO> list = factory.select(Projections.fields(MemberDTO.class, 
				mem.id,
				mem.name,
				mem.age,
				tem.teamName,
				mem.joinDate
				))
				.from(mem.member)
				.innerJoin(mem.teamId(),tem)
				.fetchResults();
		
		
		
		/*
		 * 사용할 필드가 하나인 경우.
		 * */
//		List<String> list = factory.select(tem.teamName)
//				.from(mem.member)
//				.innerJoin(mem.teamId(),tem)
//				.fetch();
		
		/**
		 * Projection 대상을 생성자로 조회. DTO클래스에 해당 생성자가 존재해야함. constructor는 type만 맞으면 됨. 
		 */
//		List<MemberDTO> listResult = factory.select(Projections.constructor(MemberDTO.class	//변환할 DTO 클래스.
//				,mem.id,
//				mem.name,
//				mem.age,
//				tem.teamName,
//				mem.joinDate ))
//				.from(mem.member)
//				.innerJoin(mem.teamId(),tem)
//				.fetch();
		
		
		/**
		 * Projection 대상을 setter메서드로 조회, 생성자가 존재하면 에러 발생함. setter/getter 만 존재해야됨.
		 */
		/*
		List<MemberDTO> listResult = factory.select(Projections.bean(MemberDTO.class	
				,mem.id,
				mem.name,
				mem.age,
//				tem.teamName,
				mem.joinDate ))
				.from(mem.member)
				.innerJoin(mem.teamId(),tem)
				.fetch();
		
		*/
		
		/**
		 * Projection 대상에 @QueryProjection 어노테이션으로 QueryDSL에 의존성 주입. DTO도 Q파일이 생성 가능해진다.
		 */
//		List<MemberDTO> listResults = factory.select(new QMemberDTO(mem.name,tem.teamName))
//				.from(mem.member)
//				.innerJoin(mem.teamId(),tem)
//				.fetch();
				
		List<MemberDTO> result= list.getResults();
//		List<MemberDTO> result= listResult;
		return result;
	}
	
	
	/**
	 * 동적 조회. 파라미터는 빈값일수도, 아닐 수도 있음.
	 */
	@Override
	public List<MemberDTO> findAllMember2(MemberDTO dto){
		
		JPAQueryFactory factory = new JPAQueryFactory(em);
		
		QMember mem = QMember.member;
		QTeam tem = QTeam.team;
		
		List<MemberDTO> result = factory
				.select(Projections.fields(MemberDTO.class, 
					mem.id,
					mem.name,
					mem.age,
					tem.teamName,
					mem.joinDate
				))
				.from(mem.member)
				.innerJoin(mem.teamId(),tem)
				.where(	//동적쿼리는 where절 안에 필요한 만큼 넣을 수 있다.
						dynamicCowName(dto.getName(),mem),
						dynamicMemberId(dto.getId(),mem),
						dynamicMemberJoinDate(dto,mem),
						dynamicTeamName(dto.getTeamName(), tem)
						)
				.fetch();
		
		return result;
	}
	
	
	//동적쿼리 메서드
	/**********************************************************************/
	
	private BooleanExpression dynamicCowName(String name, QMember m) {
		if( !"".equals(name) && name != null ) {
			//name값이 null이거나 빈값이 아니라면 where절에서 name을 조회한다.
			return m.name.eq(name);
		}
		
		return null;
	}
	
	private BooleanExpression dynamicMemberId(long memberId, QMember m) {
		if(memberId != 0) {
			return m.id.eq(memberId);
		}
		
		return null;
	}
	
	private BooleanExpression dynamicMemberJoinDate(MemberDTO dto, QMember m) {
		
		if(dto.getJoinDate() != null) {
			LocalDate dt = dto.getJoinDate().toLocalDate();	//LocalDate로 변환.
			LocalDateTime after = dt.atStartOfDay();								//해당일 0시 0분
			LocalDateTime before = LocalDateTime.of(dt, LocalTime.of(23,59,59));	//해당일 23시 59분 59초
			
			if( dto.getJoinDate() != null ) { 
				//입력한 날짜에 회원가입한 회원 조회 
				return m.joinDate.between(after, before);
			}
		}
		
		return null;
	}
	
	private BooleanExpression dynamicTeamName(String teamName, QTeam t) {
		
		if(!"".equals(teamName) && teamName != null ) {
			return t.teamName.eq(teamName);
		}
		
		return null;
	}
	
	/**********************************************************************/
	
	
	
	
}

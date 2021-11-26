package com.barlink.api.test.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barlink.api.test.domain.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{

	Member findByName(String name);

}

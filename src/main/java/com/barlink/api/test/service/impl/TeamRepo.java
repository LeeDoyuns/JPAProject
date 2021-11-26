package com.barlink.api.test.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barlink.api.test.domain.Team;


@Repository
public interface TeamRepo extends JpaRepository<Team,Long>{

	Team findByTeamName(String teamName);

}

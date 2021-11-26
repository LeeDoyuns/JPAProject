package com.barlink.api.test.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Entity(name="member")
@DynamicInsert
@Getter
@Setter
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private int age;
	
	@ManyToOne(targetEntity = Team.class)
	@JoinColumn(name = "team_id")
	private Team teamId;
	
	private LocalDateTime joinDate;
}

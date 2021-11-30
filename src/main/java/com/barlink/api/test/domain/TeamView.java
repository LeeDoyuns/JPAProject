package com.barlink.api.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 원칙적으로는 from절에 서브쿼리를 사용할 수 없다.
 * 편법으로 view처럼 사용할 수 있는 entity를 만드는 것으로 맵핑할 쿼리를 직접 작성한다.
 * @author Doyun
 *
 */
@Entity
@Subselect(
		"select "
		+ "m.name,"
		+ "t.team_name "
		+ "from member m, team t"
		+ " where m.team_id = t.team_id"
		)
@Getter
@Setter
@Immutable
@Synchronize("member")
@NoArgsConstructor
public class TeamView {
	@Id
	@Column(name="name")
	private String name;
	
	
	@Column(name="team_name")
	private String teamName;
}

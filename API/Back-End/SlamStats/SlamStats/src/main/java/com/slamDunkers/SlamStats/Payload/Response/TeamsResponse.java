package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamsResponse {
	private int id;
	private String TeamName;
	private String City;
	private String Logo;
	private String Nickname;
	private boolean AllStar;
	private boolean nbaFranchise;
	private String ConferenceName;
	private String DivisionName;
	private String Code;

	public TeamsResponse(int id,
	                     String name,
	                     String city,
	                     String logo,
	                     String nickname,
	                     boolean allStar,
	                     boolean nbaFranchise,
	                     String conference,
	                     String code

	) {
	}
}

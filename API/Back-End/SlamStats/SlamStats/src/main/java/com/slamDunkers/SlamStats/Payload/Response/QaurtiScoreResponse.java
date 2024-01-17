package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QaurtiScoreResponse {
	public int q1Score;
	public int q2Score;
	public int q3Score;
	public int q4Score;
}

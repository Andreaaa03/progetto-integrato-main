package com.slamDunkers.SlamStats.Payload.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamArticoliPreferitiResponse {
    List<TeamsResponse> teams;
    List<BlogCompleto> articoli;

}

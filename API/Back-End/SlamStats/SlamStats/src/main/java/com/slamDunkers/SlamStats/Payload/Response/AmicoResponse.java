package com.slamDunkers.SlamStats.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmicoResponse {
    private Integer seguito;
    private String username;
    private boolean amico;
    private String role;
    private Long follower;
    private Long following;

}

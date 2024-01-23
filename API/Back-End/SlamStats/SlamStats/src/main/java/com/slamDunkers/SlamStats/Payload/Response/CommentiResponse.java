package com.slamDunkers.SlamStats.Payload.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentiResponse {
    private Integer id;
    private String username;
    private String commento;
    private String giorno;
    private String nGiorno;
    private String ora;

}

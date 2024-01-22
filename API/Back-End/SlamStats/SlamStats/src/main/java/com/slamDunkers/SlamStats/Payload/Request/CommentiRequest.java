package com.slamDunkers.SlamStats.Payload.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CommentiRequest {
    private String token;
    private String testo;
    private Optional<Integer> id_commento_padre;
    private Optional<Integer> id_games;
    private Optional<Integer> id_blog;
}


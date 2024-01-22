package com.slamDunkers.SlamStats.Payload.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtenteArticoloRequest {
    private String token;
    private int idArticolo;

}

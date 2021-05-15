package com.lubycon.ourney.domains.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class JwtPayload {
    @NotNull
    private Long id;
    @NotNull
    private String issuer;
}

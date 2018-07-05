package com.cercle.jds.player.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewPlayerDTO {
    private String identity;
    private String firstName;
    private String lastName;
    private String login;
}

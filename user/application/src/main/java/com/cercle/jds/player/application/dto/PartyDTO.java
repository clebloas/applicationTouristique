package com.cercle.jds.player.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyDTO {
    private String id;
    private String gameId;
    private Set<String> players;
    private Instant startedAt;
    private Instant finishedAt;
}

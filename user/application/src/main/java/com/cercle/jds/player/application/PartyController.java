package com.cercle.jds.player.application;

import com.cercle.jds.player.application.dto.PartyDTO;
import com.cercle.jds.player.application.dto.NewPartyDTO;
import com.cercle.jds.user.domain.GameId;
import com.cercle.jds.user.domain.Party;
import com.cercle.jds.user.domain.PartyId;
import com.cercle.jds.user.domain.PlayerId;
import com.cercle.jds.user.domain.common.Identity;
import com.cercle.jds.user.domain.common.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.Validate.notNull;

@Controller
@RequestMapping("/parties")
public class PartyController {

    private final Repository<Party> parties;
    private Clock clock;

    @Autowired
    public PartyController(Repository<Party> parties, Clock clock) {

        this.parties = notNull(parties);
        this.clock = notNull(clock);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PartyDTO get(String id) {
        return maptoDTO(parties.load(new PartyId(id)));
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void newParty(@RequestBody NewPartyDTO partyDTO) {
        parties.save(Party.create(
                new GameId(partyDTO.getGameId()),
                partyDTO.getPlayers()
                        .stream()
                        .map(PlayerId::new)
                        .collect(toSet())));
    }

    @GetMapping("/")
    @ResponseBody
    public Set<PartyDTO> listPlayers() {
        return parties.retrieveAll().stream()
                .map(this::maptoDTO)
                .collect(toSet());
    }

    @PatchMapping("/{id}/state/start")
    @ResponseBody
    public void start(String id) {
        Party party = parties.load(new PartyId(id));
        party.start(clock);
        parties.save(party);
    }

    @PatchMapping("/{id}/state/finish")
    @ResponseBody
    public void finish(String id) {
        Party party = parties.load(new PartyId(id));
        party.finish(clock);
        parties.save(party);
    }

    private PartyDTO maptoDTO(Party party) {
        return new PartyDTO(party.getId().identity(),
                party.gameId().identity(),
                party.listPlayers()
                        .stream()
                        .map(Identity::identity)
                        .collect(toSet()),
                party.getStartedAt().orElse(null),
                party.getFinishedAt().orElse(null)
        );
    }

}

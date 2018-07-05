package com.cercle.jds.player.application;

import com.cercle.jds.player.application.dto.PlayerDTO;
import com.cercle.jds.player.application.dto.NewPlayerDTO;
import com.cercle.jds.user.domain.*;
import com.cercle.jds.user.domain.common.Identity;
import com.cercle.jds.user.domain.common.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.Validate.notNull;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final Repository<Player> players;
    private final Repository<Game> games;
    private final Repository<Party> parties;

    @Autowired
    public PlayerController(Repository<Player> players, Repository<Game> games, Repository<Party> parties) {
        this.players = notNull(players);
        this.games = notNull(games);
        this.parties = notNull(parties);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PlayerDTO get(String id) {
        return maptoDTO(players.load(new PlayerId(id)));
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void newPlayer(@RequestBody NewPlayerDTO playerDTO) {
        players.save(Player.create(playerDTO.getFirstName(), playerDTO.getLastName()));
    }

    @GetMapping("/")
    @ResponseBody
    public Set<PlayerDTO> listPlayers() {
        return players.retrieveAll().stream()
                .map(this::maptoDTO)
                .collect(toSet());
    }

    @PostMapping("/{playerId}/games/{gameId}")
    @ResponseBody
    public void hold(String playerId, String gameId) {
        PlayerId playerIdentity = new PlayerId(playerId);
        GameId gameIdentity = new GameId(gameId);
        if (!games.exist(gameIdentity) || !players.exist(playerIdentity)) {
            throw new IllegalArgumentException(format("GameId(%s) or playerId(%s) do not exist", gameId, playerId));
        }
        Player player = players.load(playerIdentity);
        player.hold(gameIdentity);
        players.save(player);
    }

    @GetMapping("/{playerId}/games")
    @ResponseBody
    public Set<String> holdGames(String playerId) {
        PlayerId playerIdentity = new PlayerId(playerId);
        if (!players.exist(playerIdentity)) {
            throw new IllegalArgumentException(format("playerId(%s) does not exist", playerId));
        }
        Player player = players.load(playerIdentity);
        return player.listOwnGames()
                .stream()
                .map(Identity::identity)
                .collect(toSet());
    }

    @PostMapping("/{playerId}/parties/{partyId}")
    @ResponseBody
    public void participate(String playerId, String partyId) {
        PlayerId playerIdentity = new PlayerId(playerId);
        PartyId partyIdentity = new PartyId(partyId);
        if (!parties.exist(partyIdentity) || !players.exist(playerIdentity)) {
            throw new IllegalArgumentException(format("PartyId(%s) or playerId(%s) do not exist", partyId, playerId));
        }
        Player player = players.load(playerIdentity);
        player.participateTo(partyIdentity);
        players.save(player);
    }

    @GetMapping("/{playerId}/parties")
    @ResponseBody
    public Set<String> parties(String playerId) {
        PlayerId playerIdentity = new PlayerId(playerId);
        if (!players.exist(playerIdentity)) {
            throw new IllegalArgumentException(format("playerId(%s) does not exist", playerId));
        }
        Player player = players.load(playerIdentity);
        return player.parties()
                .stream()
                .map(Identity::identity)
                .collect(toSet());
    }

    private PlayerDTO maptoDTO(Player player) {
        return new PlayerDTO(player.getId().identity(), player.getFirstName(), player.getLastName());
    }
}

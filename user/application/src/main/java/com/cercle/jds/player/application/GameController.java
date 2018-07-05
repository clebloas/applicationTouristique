package com.cercle.jds.player.application;

import com.cercle.jds.player.application.dto.NewGameDTO;
import com.cercle.jds.player.application.dto.GameDTO;
import com.cercle.jds.user.domain.*;
import com.cercle.jds.user.domain.common.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.Validate.notNull;

@Controller
@RequestMapping("/games")
public class GameController {

    private Repository<Game> games;

    public GameController(Repository<Game> games){

        this.games = notNull(games);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public GameDTO get(String id) {
        return maptoDTO(games.load(new GameId(id)));
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void newGame(@RequestBody NewGameDTO GameDTO) {
        games.save(Game.create());
    }

    @GetMapping("/")
    @ResponseBody
    public Set<GameDTO> listGames() {
        return games.retrieveAll().stream()
                .map(this::maptoDTO)
                .collect(toSet());
    }

    private GameDTO maptoDTO(Game game) {
        return new GameDTO(game.getId().identity());
    }

}

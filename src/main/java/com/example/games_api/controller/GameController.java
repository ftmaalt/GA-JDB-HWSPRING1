package com.example.games_api.controller;


import com.example.games_api.model.Game;
import com.example.games_api.service.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService service;
    public GameController(GameService service){
        this.service=service;
    }

    @Value("${app.name}")
    private String appName;


    @Value("${app.dev.name}")
    private String devName;

    @Value("${app.dev.intro}")
    private String devIntro;


    @Value("${app.dev.theme}")
    private String appTheme;

    @Value("${app.dev.favGame}")
    private String favGame;


    @Value("${app.dev.learning}")
    private String learning;


    @GetMapping("/welcome")
    public Map<String, String> welcome(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("application", appName);
        map.put("developer", devName);
        map.put("description", devIntro);
        map.put("theme", appTheme);
        map.put("currentlyLearning", learning);
        return map;
    }

    @GetMapping("/games")
    public List<Game> getAll() {
        return service.getALL();
    }

    @GetMapping("/games/{id}")
    public Game getOne(@PathVariable Long id) {
        return service.getByID(id).orElseThrow(() -> notFound(id));
    }
    @GetMapping("/games/search")
    public List<Game> search(@RequestParam String name) {
        return service.getByName(name);
    }


    @GetMapping("/games/filter")
    public List<Game> filter(@RequestParam(required = false) String genre,
                             @RequestParam(required = false, defaultValue = "0") int year,
                             @RequestParam(required = false) Double minRating) {
        return service.filter(genre, year, minRating);
    }


    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        return service.create(game);
    }


    @PutMapping("/games/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game game) {
        return service.update(id, game).orElseThrow(() -> notFound(id));
    }


    @DeleteMapping("/games/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        if (!service.delete(id)) throw notFound(id);
        return Map.of("message", "Game " + id + " deleted");
    }


    @GetMapping("/games/summary")
    public Map<String, Object> summary() {
        return service.summary();
    }


    @GetMapping("/games/favourite")
    public Map<String, Object> favourite() {
        Game game = service.getFavGame(favGame)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Favourite game '" + favGame + "' not found in the list"));
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("favourite", game);
        result.put("note", devName + "'s all-time favourite game");
        return result;
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with id " + id + " not found");
    }

}




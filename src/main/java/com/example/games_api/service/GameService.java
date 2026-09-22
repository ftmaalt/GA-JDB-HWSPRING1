package com.example.games_api.service;

import com.example.games_api.model.Game;
import org.springframework.stereotype.Service;


import java.util.*;

@Service public class GameService{
    private final List<Game> games = new ArrayList<>(List.of(
            new Game(123L, "Minecraft", "Building, Creative, SMP", 2012,8.9, 2000000),
            new Game(1256L, "Minecraft", "Building, Creative, SMP", 2012,8.9, 2000000),
            new Game(1213L, "Stardew Valley", "Simulation", 2016,9.9, 405600),
            new Game(218L, "RDR2", "Adventure",2016 ,7.8, 465030),
            new Game(5654L, "FIFA", "Simulation", 2011,7.9, 124600),
            new Game(156782L, "The Legend of Zelda", "Adventure", 2017, 3.9, 97800),
            new Game(25645L, "The Witcher 3: Wild Hunt", "RPG", 2015, 9.2, 958998),
            new Game(33454L, "Hades", "Roguelike", 2020, 8.5, 9354672),
            new Game(467789L, "God of War", "Action", 2018, 6.7, 94656),
            new Game(7547467L, "Elden Ring", "RPG", 2022, 9.6, 345686)
    ));
    private long nextID=11;

    public List<Game> getALL(){
        return games;
    }
    public Optional<Game> getByID(Long id){
        Optional<Game> getGByID= games.stream().filter(game -> game.id().equals(id)).findFirst();
        return getGByID;
    }

    public List<Game> getByName(String gameName){
        List<Game> getGByName= games.stream().filter(game -> game.name().toLowerCase().contains(gameName.toLowerCase())).toList();
        return getGByName;
    }

    public List<Game> filter(String genre, int year, Double minRating) {
        List<Game> list = games.stream()
                .filter(game -> genre == null || game.genre().equalsIgnoreCase(genre))
                .filter(game -> year==0 || game.year() == year)
                .filter(game -> minRating == null || game.rating() >= minRating)
                .toList();
        return list;
    }
    public Game create(Game g) {
        Game created = new Game(nextID++, g.name(), g.genre(), g.year(), g.rating(), g.noOfDownloads());
        games.add(created);
        return created;
    }

    public Optional<Game> update(Long id, Game g) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).id().equals(id)) {
                Game updated = new Game(id, g.name(), g.genre(), g.year(), g.rating(), g.noOfDownloads());
                games.set(i, updated);
                return Optional.of(updated);
            }
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return games.removeIf(g -> g.id().equals(id));
    }

    public Map<String,Object> summary(){
        Map<String, Object> summary= new LinkedHashMap<>();
        summary.put("Average Game Rating:", Math.round(games.stream().mapToDouble(Game::rating).average().orElse(0) * 100.0) / 100.0);
        summary.put("Highest Rated Game:", games.stream().max(Comparator.comparingDouble(Game::rating)).orElse(null));
        summary.put("Average Games Download:", Math.round(games.stream().mapToDouble(Game::noOfDownloads).average().orElse(0) * 100.0) / 100.0);
        summary.put("Most Popular Game:",games.stream().max(Comparator.comparingDouble(Game::noOfDownloads)).orElse(null)) ;
        return summary;

    }
    public Optional<Game> getFavGame(String userfavGame){
        Optional<Game> favGame= games.stream().filter(game -> game.name().equalsIgnoreCase(userfavGame)).findFirst();
        return favGame;
    }

}
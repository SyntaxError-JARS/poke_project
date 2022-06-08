package com.revature.pokedex.util.web.servlets;

import com.revature.pokedex.pokemon.Pokemon;
import com.revature.pokedex.trainer.Trainer;
import com.revature.pokedex.util.web.dto.PokemonInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/test")
public class TestServlet {

    @GetMapping("/1")
    public @ResponseBody String test(){
        return "Welcome to the wonderful world of Spring";
    }

    @GetMapping("/2")
    public @ResponseBody String test2(){
        return "Hey, this is another get method. nice right.";
    }

    @PostMapping("/post-test")
    public @ResponseBody Trainer postTest(@RequestBody Trainer trainer){
        return trainer;
    }

    @PostMapping("/post-pokemon")
    public @ResponseBody PokemonInitializer postTestPokemon(@RequestBody PokemonInitializer pokemon){
        return pokemon;
    }
}

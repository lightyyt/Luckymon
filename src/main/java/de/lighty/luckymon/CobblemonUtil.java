package de.lighty.luckymon;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.world.World;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class CobblemonUtil {
    public static PokemonEntity Spawn(World world, String name){
        int lvl = new Random().nextInt(5, 30);
        String properties = name + " lvl=" + lvl;
        var pokemonProps = PokemonProperties.Companion.parse(properties); // your PokemonProperties instance

        // "World" is causing issues, since the function is expecting "Level"
        return PokemonProperties.Companion.parse(properties).createEntity(world);
    }
}

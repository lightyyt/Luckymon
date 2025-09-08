package de.lighty.luckymon;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

import static de.lighty.luckymon.Luckymon.LOGGER;

public class LuckyBlockActions {

    private static final Random rng = Random.create();
    private static final List<LuckyFunction> luckyFunctions = new ArrayList<>();
    private static final List<LuckyFunction> unluckyFunctions = new ArrayList<>();

    public static void Register(){
        luckyFunctions.add(
                new LuckyFunction(
                        (world, block)->{
                            //var pika = CobblemonUtil.Spawn(world, "pikachu");
                            //((IP)pika)
                            //world.spawnEntity(tnt); // Why Minecraft.. Just why...
                        },
                        1
                )
        );

        unluckyFunctions.add(
                new LuckyFunction(
                        (world, block)->{
                            var tnt = EntityType.TNT.create(world);
                            tnt.refreshPositionAndAngles(block.toCenterPos(), 0, 0);
                            tnt.setFuse(30);
                            world.spawnEntity(tnt); // Why Minecraft.. Just why...
                        },
                        1
                )
        );
    }

    private static boolean isLucky(int luck){
        int unluckyChance = 100/2-(luck/2); // 0:50%, 50:25%, 100:0%
        int luckRoll = rng.nextBetween(0, 100);
        if(luckRoll<unluckyChance) {
            return false;
        }
        return true;
    }

    private static LuckyFunction getRandomFromList(List<LuckyFunction> functions) {
        // Step 1: Calculate the total weight (inverse of rarity)
        int totalWeight = 0;
        for (LuckyFunction f : functions) {
            // Higher rarity number → smaller weight
            // Add 1 to avoid zero weight
            totalWeight += 1.0 / f.rarity();
        }

        // Step 2: Pick a random number between 0 and totalWeight
        double roll = rng.nextDouble() * totalWeight;

        // Step 3: Walk through the list and pick the function
        double sum = 0;
        for (LuckyFunction f : functions) {
            sum += 1.0 / f.rarity();
            if (roll <= sum) {
                return f;
            }
        }

        // Fallback (shouldn't happen)
        return functions.get(functions.size()-1);
    }

    public static void RunAction(World world, BlockPos pos, int luck) {
        // Determine if lucky or unlucky
        boolean lucky = isLucky(luck);

        LuckyFunction func = null;
        LOGGER.info(lucky ? "lucky" : "not lucky lol :3");
        if(lucky) func = getRandomFromList(luckyFunctions);
        else func = getRandomFromList(unluckyFunctions);

        func.function().run(world, pos);
    }
}

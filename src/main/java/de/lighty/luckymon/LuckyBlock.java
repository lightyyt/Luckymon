package de.lighty.luckymon;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import java.util.List;

public class LuckyBlock extends Block {
    public int luck;

    public LuckyBlock(Settings settings) {
        super(settings);
        this.luck = luck;
    }


    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {

        tooltip.add(Text.literal("Luck: " + luck + "%").formatted(Formatting.YELLOW, Formatting.ITALIC));
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if(!world.isClient()) // FLOOF YOU MINECRAFT WHY CANT YOU JUST WORK?!?!?
            LuckyBlockActions.RunAction((World)world, pos, luck);
    }


}

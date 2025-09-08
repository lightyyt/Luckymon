package de.lighty.luckymon;

import de.lighty.luckymon.interfaces.ILuckyWrapper;

/**
 * @param rarity 0 = Common, 100 = Insane
 */
public record LuckyFunction(ILuckyWrapper function, int rarity) { }

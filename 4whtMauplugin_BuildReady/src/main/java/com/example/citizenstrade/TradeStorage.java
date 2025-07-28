package com.example.citizenstrade;

import java.util.HashSet;
import java.util.Set;

public class TradeStorage {
    private static final Set<Integer> tradeEnabledNPCs = new HashSet<>();

    public static boolean hasTrade(int npcId) {
        return tradeEnabledNPCs.contains(npcId);
    }

    public static void setTradeEnabled(int npcId, boolean enabled) {
        if (enabled) tradeEnabledNPCs.add(npcId);
        else tradeEnabledNPCs.remove(npcId);
    }
}

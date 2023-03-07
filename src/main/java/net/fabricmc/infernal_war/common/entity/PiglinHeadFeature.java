package net.fabricmc.infernal_war.common.entity;

import java.util.function.IntFunction;
import net.minecraft.util.function.ValueLists;

public enum PiglinHeadFeature {
    NONE(0),
    RIGHT_EYEPATCH(1);

    private static final IntFunction<PiglinHeadFeature> BY_ID;
    private final int id;

    private PiglinHeadFeature(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PiglinHeadFeature byIndex(int index) {
        return BY_ID.apply(index);
    }

    static {
        BY_ID = ValueLists.createIdToValueFunction(PiglinHeadFeature::getId, PiglinHeadFeature.values(), ValueLists.OutOfBoundsHandling.WRAP);
    }
}

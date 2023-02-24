package net.fabricmc.infernal_war.common.entity;

import java.util.function.IntFunction;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import com.mojang.serialization.Codec;

public enum PiglinType implements StringIdentifiable {

    NORMAL(0,"normal"),
    REDSHAWL(1,"redshawl");

    public static final Codec<PiglinType> CODEC;
    private static final IntFunction<PiglinType> BY_ID;
    private final int id;
    private final String name;

    private PiglinType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static PiglinType byId(int id) {
        return BY_ID.apply(id);
    }

    static {
        CODEC = StringIdentifiable.createCodec(PiglinType::values);
        BY_ID = ValueLists.createIdToValueFunction(PiglinType::getId, PiglinType.values(), ValueLists.OutOfBoundsHandling.WRAP);
    }
}

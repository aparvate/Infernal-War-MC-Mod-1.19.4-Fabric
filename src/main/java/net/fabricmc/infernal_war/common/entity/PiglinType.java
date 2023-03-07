package net.fabricmc.infernal_war.common.entity;

import java.util.function.IntFunction;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import com.mojang.serialization.Codec;

public enum PiglinType implements StringIdentifiable {

    NORMAL_NORMAL(0,0,"normal_normal"),
    NORMAL_PALE(0,1,"normal_pale"),
    NORMAL_PINK(0,2,"normal_pink"),
    REDSHAWL_NORMAL(1,0,"redshawl_normal"),
    REDSHAWL_PALE(1,1,"redshawl_pale"),
    REDSHAWL_PINK(1,2,"redshawl_pink"),
    CLOAKED_NORMAL(2,0,"cloaked_normal"),
    CLOAKED_PALE(2,1,"cloaked_pale"),
    CLOAKED_PINK(2,2,"cloaked_pink"),
    SHIRTLESS_NORMAL(3,0,"shirtless_normal"),
    SHIRTLESS_PALE(3,1,"shirtless_pale"),
    SHIRTLESS_PINK(3,2,"shirtless_pink"),
    DRIFTER_NORMAL(4,0,"drifter_normal"),
    DRIFTER_PALE(4,1,"drifter_pale"),
    DRIFTER_PINK(4,2,"drifter_pink");

    public static final Codec<PiglinType> CODEC;
    //private static final IntFunction<PiglinType> BY_ID;
    private final int id;
    private final int skinColor;
    private final String name;

    private PiglinType(int id, int skinColor, String name){
        this.id = id;
        this.skinColor = skinColor;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getSkinColor() {
        return this.skinColor;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static PiglinType byId(int id, int skinColor) {
        for (PiglinType object : PiglinType.values()) {
            if (object.getId() == id & object.getSkinColor() == skinColor)
            return object;
        }
        return NORMAL_NORMAL;
    }

    static {
        CODEC = StringIdentifiable.createCodec(PiglinType::values);
        //BY_ID = ValueLists.createIdToValueFunction(PiglinType::getId, PiglinType.values(), ValueLists.OutOfBoundsHandling.WRAP);
    }
}

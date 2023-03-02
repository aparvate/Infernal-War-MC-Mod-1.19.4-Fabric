package net.fabricmc.infernal_war.common.access;

import net.fabricmc.infernal_war.common.entity.PiglinType;
import net.minecraft.entity.EntityData;

public class PiglinData
    implements EntityData {
        public final PiglinType type;

        public PiglinData(PiglinType type) {
            this.type = type;
        }
    }

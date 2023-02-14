package net.fabricmc.infernal_war.client;

import net.fabricmc.api.ClientModInitializer;

public class InfernalWarClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        InfernalWarPredicateProvider.registerModModels();
    }
    
}

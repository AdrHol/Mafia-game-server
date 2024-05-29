package com.adrhol.mafiaGame.model;

import com.adrhol.mafiaGame.rooms.model.Player;

public record GameStateObject(String playerId, String playerName, boolean gameStarted,
                              boolean isPlayerAlive, PlayerRole playerRole) {
}

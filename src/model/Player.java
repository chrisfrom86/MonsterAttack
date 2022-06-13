/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.BattleScreen;

/**
 *
 * @author chris
 */
public class Player extends Character {
    
    Dice dice = new Dice();
    public static int playerLvl = 1;
    public int playerExp = 0;
    public int playerMaxHP = 10 + (dice.d12(playerLvl));
    public int playerHP = playerMaxHP;
    public static int playerSpells;
    public static int playerMaxSpells;
    public static int playerAtk;
    public static int enemyKills;
    
    public Player(int level) {
        this.playerLvl = level;
        this.playerMaxHP = dice.d12(level) + level + 3;
        this.playerHP = this.playerMaxHP;
        this.playerMaxSpells = 5;
        this.playerSpells = this.playerMaxSpells;
        this.enemyKills = 0;
    }
    
    public void updatePlayerExp(int exp) {
        this.playerExp += exp;
    }
    
    public void playerLvlUp() {
        ++playerLvl;
        
        this.playerExp = 0;
        
        playerMaxHP += dice.d12(1);
        playerHP = playerMaxHP;
        
        UpdatePlayerHP(playerHP);
        UpdatePlayerSpells(3);
        ClearPlusLabels();
        
        AddHistory("Congratulations! You are now level " + playerLvl + "!");
        AddHistory("You have been fully healed.\n");
    }
    
    //getters
    public static int getPlayerLvl() {
        return playerLvl;
    }

    public int getPlayerExp() {
        return playerExp;
    }

    public int getPlayerMaxHP() {
        return playerMaxHP;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public static int getPlayerSpells() {
        return playerSpells;
    }

    public static int getPlayerMaxSpells() {
        return playerMaxSpells;
    }

    public static int getPlayerAtk() {
        return playerAtk;
    }

    public static int getEnemyKills() {
        return enemyKills;
    }
    
    //setters
    public static void setPlayerLvl(int playerLvl) {
        Player.playerLvl = playerLvl;
    }

    public void setPlayerExp(int playerExp) {
        this.playerExp = playerExp;
    }

    public void setPlayerMaxHP(int playerMaxHP) {
        this.playerMaxHP = playerMaxHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public static void setPlayerSpells(int playerSpells) {
        Player.playerSpells = playerSpells;
    }

    public static void setPlayerMaxSpells(int playerMaxSpells) {
        Player.playerMaxSpells = playerMaxSpells;
    }

    public static void setPlayerAtk(int playerAtk) {
        Player.playerAtk = playerAtk;
    }

    public static void setEnemyKills(int enemyKills) {
        Player.enemyKills = enemyKills;
    }
    
}

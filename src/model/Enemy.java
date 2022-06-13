/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author chris
 */
public class Enemy extends Character {
    
    Dice dice = new Dice();
    public static int enemyHP;
    public static int enemyMaxHP;
    public static int enemyAtk;
    
    public Enemy(int level) {
        this.enemyMaxHP = dice.d10(level) + level + 3;
        this.enemyHP = enemyMaxHP;
        this.enemyAtk = dice.d6(level) + level;
    }
        
    //getters
    public static int getEnemyHP() {
        return enemyHP;
    }
    
    public static int getEnemyMaxHP() {
        return enemyMaxHP;
    }
    
    public static int getEnemyAtk() {
        return enemyAtk;
    }

    //setters
    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
    }
    
    public void setEnemyMaxHP(int enemyMaxHP) {
        this.enemyMaxHP = enemyMaxHP;
    }
    
    public void setEnemyAtk(int enemyAtk) {
        this.enemyAtk = enemyAtk;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jdk.internal.vm.annotation.IntrinsicCandidate;

/**
 *
 * @author chris
 */
public class Character {
    
    @IntrinsicCandidate
    public Character() {
    }

    public int physicalAttack(int level) {
        Enemy.enemyAtk = dice.d6(level);
        return Enemy.enemyAtk;
    }

    public int reduceHP(int playerAtk) {
        return Enemy.enemyHP -= playerAtk;
    }

    public int addHP(int potion) {
        return Enemy.enemyHP += potion;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author chris
 */
public class HealPotion extends Potion {
        
    public int lesserHeal(int level) {
        return dice.d6(level) + level + 3;
    }
    
    public int moderateHeal(int level) {
        return dice.d8(level) + level + 5;
    }
    
    public int greaterHeal(int level) {
        return dice.d10(level) + level + 7;
    }
    
    public int greatestHeal(int level) {
        return dice.d12(level) + level + 9;
    }
}

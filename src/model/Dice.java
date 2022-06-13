/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author chris
 */
public class Dice {
    private Random random = new Random();
    
    private int sum;
    
    public int d4(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(4)+1);
        }
        return (sum);
    }
    
    public int d6(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(6)+1);
        }
        return (sum);
    }
    
    public int d8(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(8)+1);
        }
        return (sum);
    }
    
    public int d10(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(10)+1);
        }
        return (sum);
    }
    
    public int d12(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(12)+1);
        }
        return (sum);
    }
    
    public int d20(int rolls) {
        sum = 0;
        for(int i=0; i<rolls; ++i) {
            sum += (random.nextInt(20)+1);
        }
        return (sum);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Main;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class BattleScreen implements Initializable{

    private Random random = new Random();
    private int playerLvl = 1;
    private int playerExp = 0;
    private int playerHP = 10 + (playerLvl * 6);
    private int enemyHP = 10 + (playerLvl * 3);
    private int playerSpells = 3;
    private int playerAtk;
    private int enemyAtk;
    private int enemyKills = 0;
    private String loot;
    
    @FXML
    private Button attackButton;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Button fireballButton;
    @FXML
    private Button itemButton;
    @FXML
    private Label playerLvlLabel;
    @FXML
    private Label playerLvlPlusLabel;
    @FXML
    private Label playerExpLabel;
    @FXML
    private Label playerExpPlusLabel;
    @FXML
    private Label playerHPLabel;
    @FXML
    private Label playerHPPlusLabel;
    @FXML
    private Label playerSpellsLabel;
    @FXML
    private Label playerSpellsPlusLabel;
    @FXML
    private Label enemyHPLabel;
    @FXML
    private Label enemyHPPlusLabel;
    @FXML
    private Label enemyKillsLabel;
    @FXML
    private Label enemyKillsPlusLabel;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerLvlLabel.setText("Player Lvl: " + playerLvl);
        UpdatePlayerHP(playerHP);
        UpdateEnemyHP(enemyHP);
        UpdatePlayerExp(playerExp);
        enemyKillsLabel.setText("Kills: 0");
        UpdatePlayerSpells();
    }
    
    @FXML
    void attackButtonOnAction(ActionEvent event) throws InterruptedException, MalformedURLException {
        System.out.println("You attacked your enemy!");
        PlayerAttack();

        PlusLabelFlash(enemyHPPlusLabel, -playerAtk);
        EnemyAttack();
    }

    @FXML
    void fireballButtonOnAction(ActionEvent event) throws InterruptedException {
        System.out.println("You cast fireball!");
        playerAtk = playerLvl * 10;
        enemyHP -= playerAtk;

        UpdateEnemyHP(enemyHP);
        --playerSpells;
        UpdatePlayerSpells();
        PlusLabelFlash(enemyHPPlusLabel, -playerAtk);
        EnemyAttack();
    }

    @FXML
    void itemButtonOnAction(ActionEvent event) throws InterruptedException {
        System.out.println("You used a potion!");
        int healHP = random.nextInt((int) (playerHP*.5)) +1;
        playerHP += healHP;
        System.out.println("You healed " + healHP + " hit points!");
        UpdatePlayerHP(playerHP);

        PlusLabelFlash(playerHPPlusLabel, healHP);
        EnemyAttack();
    }
    
    public int PlayerAttack() throws MalformedURLException, InterruptedException {
        playerAtk = 0;
        playerAtk = random.nextInt(playerLvl*3) +1;
        enemyHP -= playerAtk;
        UpdateEnemyHP(enemyHP);
        
        if(enemyHP <= 0) {
            enemyImage.setOpacity(0.0);
            sleep(1000);

            ChangeEnemy();
            return playerAtk;
        }
        else
            return playerAtk;
    }
    
    public int EnemyAttack() {
        enemyAtk = 0;
        enemyAtk = random.nextInt(playerLvl*3) +1;
        
        playerHP -= enemyAtk;
        UpdatePlayerHP(playerHP);
        System.out.println("<Enemy> hit you for " + enemyAtk + " damage!");
        System.out.println("Player HP: " + playerHP + "\n");
        return enemyAtk;
    }
    
    public void ChangeEnemy() throws MalformedURLException, InterruptedException {
        UpdatePlayerExp(10);
        if(playerExp >= 20)
            PlayerLvlUp();
        File dir = new File("C:\\Users\\chris\\OneDrive\\Documents\\NetBeansProjects\\AttackMonsters\\src\\enemy");
        File[] enemies = dir.listFiles();
        int nextImage = random.nextInt(enemies.length);
        Image nextEnemy = new Image(enemies[nextImage].toURI().toURL().toString());
        enemyImage.setImage(nextEnemy);
        enemyImage.setOpacity(1.0);
        
        UpdateEnemyKills();
        enemyHP = 10 + playerLvl*3;
        UpdateEnemyHP(enemyHP);
        System.out.println(Arrays.toString(enemies));
    }
    
    private void UpdatePlayerHP(int playerHP) {
        playerHPLabel.setText("Player HP: " + playerHP);
        System.out.println("Player HP: " + playerHP + "\n");
    }
    
    private void UpdatePlayerExp(int playerExpUpdate) {
        playerExp += playerExpUpdate;
        playerExpLabel.setText("Player EXP: " + playerExp);
    }
    
    private void UpdateEnemyHP(int enemyHP) {
        System.out.println("You hit <Enemy> for " + playerAtk + " damage!");
        enemyHPLabel.setText("Enemy HP: " + enemyHP);
        System.out.println("Enemy HP: " + enemyHP + "\n");

    }
    
    private void PlayerLvlUp() {
        ++playerLvl;
        playerLvlLabel.setText("Player Lvl: " + playerLvl);
        playerExp = 0;
        UpdatePlayerExp(0);
        playerHP = 10 + (playerLvl * 6);
        UpdatePlayerHP(playerHP);
        playerSpells = 3;
        UpdatePlayerSpells();
    }
    
    private void UpdatePlayerSpells() {
        playerSpellsLabel.setText("Spells: " + playerSpells);
    }
    
    private void UpdateEnemyKills() {
        ++enemyKills;
        enemyKillsLabel.setText("Kills: " + enemyKills);
    }
    
    private void PlusLabelFlash(Label label, int number) throws InterruptedException {
        label.setText(String.valueOf(number));
        sleep(1000);
        label.setText("");
    }
    
    public void GetLoot() {
        
    }


}

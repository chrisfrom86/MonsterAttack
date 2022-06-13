/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import model.Dice;
import model.Enemy;
import Main.Main;
import java.io.File;
import java.util.Random;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class BattleScreen implements Initializable{
    
    private final String imageDirectory = "C:\\Users\\chris\\OneDrive\\Documents\\NetBeansProjects\\AttackMonsters\\src\\enemy";

    Dice dice = new Dice();
    Player player = new Player(1);
    private int textHistory = -1;
    Enemy enemy = new Enemy(Player.playerLvl);

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
    @FXML
    private TextArea gameHistoryTextArea;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerLvlLabel.setText("Player Lvl: " + playerLvl);
        
        UpdatePlayerHP(playerHP);
        UpdateEnemyHP(Enemy.getEnemyHP());
        UpdatePlayerExp(playerExp);
        
        enemyKillsLabel.setText("Kills: 0");
        
        UpdatePlayerSpells(3);
        ClearPlusLabels();
        
        gameHistoryTextArea.setText("While wandering in a forest, you are attacked by a scary monster!");
    }
    
    @FXML
    void attackButtonOnAction(ActionEvent event) throws InterruptedException, MalformedURLException, Exception {
        DisableButtons();
        AddHistory("You attacked your enemy!");
        PlayerAttack();

//        PlusLabelFlash(enemyHPPlusLabel, -playerAtk);
//        EnemyAttack();
    }

    @FXML
    void fireballButtonOnAction(ActionEvent event) throws InterruptedException, MalformedURLException, Exception {
        DisableButtons();
        AddHistory("You cast fireball!");
        if(dice.d20(1) == 20) {
            AddHistory("Your fireball critcally hit!");
            playerAtk = 2 * (dice.d6(4) + dice.d6(playerLvl));
        } else {
            playerAtk = dice.d6(4) + dice.d6(playerLvl);
        }
        enemy.reduceHP(playerAtk);

        UpdateEnemyHP(Enemy.getEnemyHP());
        
        UpdatePlayerSpells(--playerSpells);
        PlusLabelFlash(enemyHPPlusLabel, -playerAtk);
        if(Enemy.getEnemyHP() <= 0) {
            ChangeEnemy();
        } else {
            EnemyAttack();
        }
    }

    @FXML
    void itemButtonOnAction(ActionEvent event) throws InterruptedException, Exception {
        DisableButtons();
        AddHistory("You used a potion.");
        if(dice.d20(1) == 20) {
            AddHistory("You critically healed!");
            healHP = 2 * (dice.d8(playerLvl) + playerLvl);
        } else {
            healHP = dice.d8(playerLvl) + playerLvl;
        }
        
        playerHP += healHP;
        AddHistory("You healed " + healHP + " hit points.");
        UpdatePlayerHP(playerHP);

        PlusLabelFlash(playerHPPlusLabel, healHP);

        EnemyAttack();
    }
    
    public int PlayerAttack() throws MalformedURLException, InterruptedException, Exception {
        playerAtk = 0;
        if(dice.d20(1) == 20) {
            AddHistory("You got a critical hit!");
            playerAtk = 2 * dice.d6(playerLvl);
        } else {
            playerAtk = dice.d6(playerLvl);

        }
        enemy.reduceHP(playerAtk);
        UpdateEnemyHP(Enemy.getEnemyHP());
        
        if(Enemy.getEnemyHP() <= 0) {
            ChangeEnemy();
            return playerAtk;
        }
        else
            EnemyAttack();
            return playerAtk;
    }
    
    public void EnemyAttack() throws InterruptedException, Exception {
        if(dice.d20(1) == 20) {
            AddHistory("Enemy critical hit!");
            enemy.physicalAttack(2*playerLvl);
        } else {
            enemy.physicalAttack(playerLvl);
        }
        
        playerHP -= Enemy.enemyAtk;
        
        PlusLabelFlash(playerHPPlusLabel, -Enemy.enemyAtk);
        AddHistory("<Enemy> hit you for " + Enemy.enemyAtk + " damage.");
        UpdatePlayerHP(playerHP);
        EnableButtons();
        if(playerHP <= 0) {
            GameRestart();
        }
    }
    
    public void ChangeEnemy() throws MalformedURLException, InterruptedException {
        AddHistory("You defeated <Enemy>!" + "\n");
        
        FadeImage(enemyImage);

        updatePlayerExpLabels();
            if(playerExp >= 20){
                PlayerLvlUp();
            }
        
        UpdateEnemyKills();
        Enemy newEnemy = new Enemy(playerLvl);
        newEnemy.setEnemyMaxHP(dice.d10(playerLvl));
        newEnemy.setEnemyHP(Enemy.enemyMaxHP);
        enemyHPLabel.setText("Enemy HP: " + Enemy.enemyHP);
        
        AddHistory("A new enemy appears!");

        ClearPlusLabels();
        EnableButtons();
    }
    
    public void FadeImage(ImageView imageview) throws MalformedURLException {
        FadeTransition ftOut = new FadeTransition(Duration.millis(1500), imageview);
        ftOut.setFromValue(1.0);
        ftOut.setToValue(0.0);
        
        FadeTransition ftIn = new FadeTransition(Duration.millis(0), imageview);
        ftIn.setFromValue(0.0);
        ftIn.setToValue(1.0);
        
        SequentialTransition st = new SequentialTransition(imageview, ftOut, ftIn);
        st.play();
        
        File dir = new File(imageDirectory);
        File[] enemies = dir.listFiles();
        
        Random random = new Random();
        int nextImage = random.nextInt(enemies.length);
        Image nextEnemy = new Image(enemies[nextImage].toURI().toURL().toString());
        enemyImage.setImage(nextEnemy);
    }
    
    private void UpdatePlayerHP(int playerHP) {
        playerHPLabel.setText("Player HP: " + playerHP);
    }
    
    private void updatePlayerExpLabels() {
        playerExpLabel.setText("Player EXP: " + playerExp);
        playerExpPlusLabel.setText(String.valueOf(playerExp));
    }
    
    private void UpdateEnemyHP(int enemyHP) {
        AddHistory("You hit <Enemy> for " + playerAtk + " damage.");
        enemyHPLabel.setText("Enemy HP: " + enemyHP);
    }
    
    public void PlayerLvlUp() {
        player.playerLvlUP();
        playerLvlLabel.setText("Player Lvl: " + playerLvl);
        updatePlayerExpLabels();
        
        ClearPlusLabels();
        
        AddHistory("Congratulations! You are now level " + playerLvl + "!");
        AddHistory("You have been fully healed.\n");
    }
    
    private void UpdatePlayerSpells(int spells) {
        playerSpells = spells;
        playerSpellsLabel.setText("Spells: " + playerSpells);
    }
    
    private void UpdateEnemyKills() {
        ++enemyKills;
        enemyKillsLabel.setText("Kills: " + enemyKills);
    }
    
    private void PlusLabelFlash(Label label, int number) throws InterruptedException {
//        ClearPlusLabels();
        label.setText(String.valueOf(number));
        sleep(1000);
    }
    
    public void ClearPlusLabels() {
        playerLvlPlusLabel.setText("");
        playerExpPlusLabel.setText("");
        playerHPPlusLabel.setText("");
        playerSpellsPlusLabel.setText("");
        enemyHPPlusLabel.setText("");
        enemyKillsPlusLabel.setText("");
    }
    
    public void AddHistory(String text) {
        ++textHistory;
        gameHistoryTextArea.appendText("\n" + textHistory + ": " + text);
        gameHistoryTextArea.setScrollTop(Double.MAX_VALUE);
    }
    
    public void DisableButtons() {
        attackButton.setDisable(true);
        fireballButton.setDisable(true);
        itemButton.setDisable(true);
    }
    
    public void EnableButtons() {
        attackButton.setDisable(false);
        fireballButton.setDisable(false);
        itemButton.setDisable(false);
    }
    
    public void GameRestart() throws Exception {
        AddHistory("You died.");
        AddHistory("Would you like to try again in a parallel universe? y/n");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

            switch (input) {
                case "y":
                    Stage stage = (Stage) attackButton.getScene().getWindow();
                    Main mainStage = new Main();
                    mainStage.start(stage);
                    break;
                case "n":
                    System.exit(0);
                default:
                    AddHistory("Please enter y or n");
                    break;
            }

    }
    
}

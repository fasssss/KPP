package sample.main;

import javafx.animation.AnimationTimer;
import sample.model.Player;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Apple;
import java.lang.Thread;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TheGame {
    String path = new File("").getAbsolutePath();
    Apple ap = new Apple("/out/production/SnakeAreYouOK/sprites/Apple.png", Math.random() * 1000, Math.random() * 1000);
    Player pl = new Player("/out/production/SnakeAreYouOK/sprites/Head.jpg", 25, 25);
    Text scoreSpace = new Text("SCORE: " + String.valueOf(pl.score));
    FlowPane back = new FlowPane();
    Group rootNode = new Group(back, pl.playerSub, ap.appleSub, scoreSpace);
    Scene pixelart1Town = new Scene(rootNode);

    public void playerCollectApple() {
        double x = pl.posMem[0] + 25;
        double y = pl.posMem[1] + 25;
        if (x > ap.posApple[0] - 25 && x < ap.posApple[0] + 50 && y > ap.posApple[1] - 25 && y < ap.posApple[1] + 50) {
            pl.score += 100;
            scoreSpace.setText("SCORE: " + String.valueOf(pl.score));
            ap.refreshApple("/out/production/SnakeAreYouOK/sprites/Apple.png");
            pl.incTale();
        }
    }

    TheGame(Stage Play) {
        Play.setScene(pixelart1Town);
        Play.setTitle("The Game");
        Play.setMaximized(true);
        Play.setFullScreen(true);
        Play.show();
        scoreSpace.setX(10);
        scoreSpace.setY(40);
        scoreSpace.setFont(Font.font("Verdana", 35));
        pixelart1Town.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                pl.movementH(key);
            }
        });

        try {
            Image image = new Image(new FileInputStream(path + "/out/production/SnakeAreYouOK/sprites/GameBack.jpg"));
            ImageView backGround = new ImageView(image);
            back.getChildren().addAll(backGround);

        } catch (IOException e) {
            e.printStackTrace();
        }
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                playerCollectApple();
                if(pl.bite() == true || pl.borders() == true){
                    Play.close();
                    //TheGame res = new TheGame(new Stage());
                }
                //gameOver();
            }
        }.start();
    }
    Main reseted;
    public void gameOver(){
        if(pl.bite() == true){
        }
    }
}


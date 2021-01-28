package org.seariver.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.seariver.BaseActor;
import org.seariver.BaseGame;
import org.seariver.BaseScreen;
import org.seariver.TilemapActor;
import org.seariver.actor.Flag;
import org.seariver.actor.Koala;
import org.seariver.actor.Solid;

import static com.badlogic.gdx.Input.Keys;

public class LevelScreen extends BaseScreen {

    Koala jack;

    boolean gameOver;
    int coins;
    float time;

    Label coinLabel;
    Label timeLabel;
    Label messageLabel;
    Table keyTable;

    public void initialize() {

        TilemapActor tma = new TilemapActor("map.tmx", mainStage);

        for (MapObject obj : tma.getRectangleList("Solid")) {
            MapProperties props = obj.getProperties();
            new Solid((float) props.get("x"), (float) props.get("y"),
                    (float) props.get("width"), (float) props.get("height"),
                    mainStage);
        }

        for (MapObject obj : tma.getTileList("Flag")) {
            MapProperties props = obj.getProperties();
            new Flag((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        jack = new Koala((float) startProps.get("x"), (float) startProps.get("y"), mainStage);

        gameOver = false;
        coins = 0;
        time = 60;
        coinLabel = new Label("Coins: " + coins, BaseGame.labelStyle);
        coinLabel.setColor(Color.GOLD);
        timeLabel = new Label("Time: " + (int) time, BaseGame.labelStyle);
        timeLabel.setColor(Color.LIGHT_GRAY);
        messageLabel = new Label("Message", BaseGame.labelStyle);
        messageLabel.setVisible(false);
        keyTable = new Table();

        uiTable.pad(20);
        uiTable.add(coinLabel);
        uiTable.add(keyTable).expandX();
        uiTable.add(timeLabel);
        uiTable.row();
        uiTable.add(messageLabel).colspan(3).expandY();
    }

    public void update(float deltaTime) {

        if (gameOver) return;

        for (BaseActor flag : BaseActor.getList(mainStage, "org.seariver.actor.Flag")) {
            if (jack.overlaps(flag)) {
                messageLabel.setText("You Win!");
                messageLabel.setColor(Color.LIME);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor actor : BaseActor.getList(mainStage, "org.seariver.actor.Solid")) {
            Solid solid = (Solid) actor;

            if (jack.overlaps(solid) && solid.isEnabled()) {
                Vector2 offset = jack.preventOverlap(solid);
                if (offset != null) {
                    // collided in X direction
                    if (Math.abs(offset.x) > Math.abs(offset.y)) {
                        jack.velocityVec.x = 0;
                        // collided in Y direction
                    } else {
                        jack.velocityVec.y = 0;
                    }
                }
            }
        }
    }

    public boolean keyDown(int keyCode) {

        if (keyCode == Keys.SPACE && jack.isOnSolid()) {
            jack.jump();
        }

        return false;
    }
}

package org.seariver.screen;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import org.seariver.BaseActor;
import org.seariver.BaseScreen;
import org.seariver.TilemapActor;
import org.seariver.actor.Koala;
import org.seariver.actor.Solid;

import static com.badlogic.gdx.Input.Keys;

public class LevelScreen extends BaseScreen {

    Koala jack;

    public void initialize() {

        TilemapActor tma = new TilemapActor("map.tmx", mainStage);

        for (MapObject obj : tma.getRectangleList("Solid")) {
            MapProperties props = obj.getProperties();
            new Solid((float) props.get("x"), (float) props.get("y"),
                    (float) props.get("width"), (float) props.get("height"),
                    mainStage);
        }

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        jack = new Koala((float) startProps.get("x"), (float) startProps.get("y"), mainStage);
    }

    public void update(float deltaTime) {

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

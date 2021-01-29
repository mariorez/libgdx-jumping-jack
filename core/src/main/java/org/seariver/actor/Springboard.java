package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.seariver.BaseActor;

public class Springboard extends BaseActor {

    public Springboard(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("items/springboard.png", 1, 3, 0.2f, true);
    }
}

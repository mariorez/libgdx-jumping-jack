package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.seariver.BaseActor;

public class Flag extends BaseActor {

    public Flag(float x, float y, Stage stage) {
        super(x, y, stage);
        loadAnimationFromSheet("items/flag.png", 1, 2, 0.2f, true);
    }
}

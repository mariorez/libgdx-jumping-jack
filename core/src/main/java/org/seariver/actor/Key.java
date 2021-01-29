package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.seariver.BaseActor;

public class Key extends BaseActor {

    public Key(float x, float y, Stage stage) {
        super(x, y, stage);

        loadTexture("items/key.png");
        rotateBy(10);

        Action tilt = Actions.sequence(
                Actions.rotateBy(-20, 0.5f),
                Actions.rotateBy(20, 0.5f));
        addAction(Actions.forever(tilt));
    }
}

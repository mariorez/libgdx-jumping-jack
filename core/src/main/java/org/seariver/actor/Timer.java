package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.seariver.BaseActor;

public class Timer extends BaseActor {

    public Timer(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("items/timer.png");
        Action pulse = Actions.sequence(
                Actions.scaleTo(1.1f, 1.1f, 0.5f),
                Actions.scaleTo(1.0f, 1.0f, 0.5f));
        addAction(Actions.forever(pulse));
    }
}

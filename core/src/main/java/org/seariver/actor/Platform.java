package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends Solid {

    public Platform(float x, float y, Stage stage) {
        super(x, y, 32, 16, stage);
        loadTexture("items/platform.png");
    }
}

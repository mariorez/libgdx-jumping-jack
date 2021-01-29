package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.seariver.BaseActor;

public class Solid extends BaseActor {

    private boolean enabled;

    public Solid(float x, float y, float width, float height, Stage stage) {
        super(x, y, stage);
        setSize(width, height);
        setBoundaryRectangle();
        enabled = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}

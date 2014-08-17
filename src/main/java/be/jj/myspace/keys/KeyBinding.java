package be.jj.myspace.keys;

import java.awt.event.KeyEvent;

/**
* Created by Jon on 17/08/2014.
*/
public enum KeyBinding {
    UP(KeyEvent.VK_UP, true),
    DOWN(KeyEvent.VK_DOWN, true),
    LEFT(KeyEvent.VK_LEFT, true),
    RIGHT(KeyEvent.VK_RIGHT, true),
    SHOOT(KeyEvent.VK_SPACE)
    ;

    private int keyCode;
    private boolean movementKey;

    KeyBinding(int keyCode) {
        this(keyCode, false);
    }

    KeyBinding(int keyCode, boolean isMovementKey) {
        this.keyCode = keyCode;
        this.movementKey = isMovementKey;
    }

    public boolean isMovementKey() {
        return movementKey;
    }

    public static KeyBinding fromKeyCode(int keyCode) {
        for (KeyBinding key : KeyBinding.values()) {
            if (key.keyCode == keyCode)
                return key;
        }
        return null;
    }
}

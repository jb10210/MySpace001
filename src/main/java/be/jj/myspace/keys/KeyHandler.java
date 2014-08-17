package be.jj.myspace.keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jon on 17/08/2014.
 */
public class KeyHandler implements KeyListener {

    private Set<MySpaceKeyListener> mySpaceKeyListeners;

    public KeyHandler() {
        this.mySpaceKeyListeners = new HashSet<MySpaceKeyListener>();
    }


    public void addMySpaceKeyListener(MySpaceKeyListener listener) {
        this.mySpaceKeyListeners.add(listener);
    }

    public void removeMySpaceKeyListener(MySpaceKeyListener listener) {
        this.mySpaceKeyListeners.remove(listener);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBinding keyBinding = KeyBinding.fromKeyCode(e.getKeyCode());
        if (keyBinding != null) {
            fireKeyPressed(keyBinding);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyBinding keyBinding = KeyBinding.fromKeyCode(e.getKeyCode());
        if (keyBinding != null) {
            fireKeyReleased(keyBinding);
        }
    }

    private void fireKeyPressed(KeyBinding keyPressed) {
        for (MySpaceKeyListener mySpaceKeyListener : mySpaceKeyListeners) {
            mySpaceKeyListener.keyPressed(new KeyPressedEvent(keyPressed));
        }
    }

    private void fireKeyReleased(KeyBinding keyReleased) {
        for (MySpaceKeyListener mySpaceKeyListener : mySpaceKeyListeners) {
            mySpaceKeyListener.keyReleased(new KeyReleasedEvent(keyReleased));
        }
    }


    public interface MySpaceKeyListener {
        public void keyPressed(KeyPressedEvent event);

        public void keyReleased(KeyReleasedEvent event);
    }

    public class KeyPressedEvent extends EventObject {

        private KeyBinding keyPressed;

        /**
         * Constructs a prototypical Event.
         *
         * @throws IllegalArgumentException if source is null.
         */
        public KeyPressedEvent(KeyBinding keyPressed) {
            super(KeyHandler.this);
            this.keyPressed = keyPressed;
        }

        @Override
        public KeyHandler getSource() {
            return KeyHandler.this;
        }

        public KeyBinding getKeyPressed() {
            return keyPressed;
        }
    }

    public class KeyReleasedEvent extends EventObject {

        private KeyBinding keyReleased;

        /**
         * Constructs a prototypical Event.
         *
         * @throws IllegalArgumentException if source is null.
         */
        public KeyReleasedEvent(KeyBinding keyReleased) {
            super(KeyHandler.this);
            this.keyReleased = keyReleased;
        }

        @Override
        public KeyHandler getSource() {
            return KeyHandler.this;
        }

        public KeyBinding getKeyReleased() {
            return keyReleased;
        }
    }
}

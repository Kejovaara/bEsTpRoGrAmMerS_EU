package input;

import com.badlogic.gdx.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public Key esc = new Key("esc");
    public Key enter = new Key("enter");
    public Key space = new Key("space");
    public Key shift = new Key("shift");
    public Key up = new Key("up");
    public Key down = new Key("down");
    public Key left = new Key("left");
    public Key right = new Key("right");
    public Key w = new Key("w");
    public Key a = new Key("a");
    public Key s = new Key("s");
    public Key d = new Key("d");
    // public Key m = new Key("m"); // MAYBE FOR BIG MAP LATER ON

    public InputHandler(Game game){
        //TODO
    }

    public class Key{
        private String name;
        private int timesPressed = 0;
        private boolean pressed = false;
        private boolean released = false;

        public int getTimesPressed(){
            return timesPressed;
        }

        public boolean isPressed(){
            return pressed;
        }

        public boolean isReleased(){
            return released;
        }

        public Key(String name){
            this.name = name;
        }

        public void toggle(boolean isPressed){
            pressed = isPressed;
            released = !pressed;
            if(isPressed){
                timesPressed++;
            }
        }

        public void reset(){
            this.pressed = false;
            this.released = false;
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {
        // this.toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // this.toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // this.toggleKey(e.getKeyCode(), false);
    }
}

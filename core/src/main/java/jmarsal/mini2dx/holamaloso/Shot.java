package jmarsal.mini2dx.holamaloso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.graphics.Sprite;

/**
 * Created by Jordi Marsal on 24/01/2017.
 * https://github.com/jordimarsal/
 */

public class Shot extends Sprite{

    public static final int STATE_IN = 0;
    public static final int STATE_OUT = 1;

    private int state;
    private float x,y;
    private int direction;
    private float limX = Gdx.graphics.getWidth();
    private float limY = Gdx.graphics.getHeight();

    public Shot(Texture texture, int direction) {
        super(texture);
        this.direction=direction;
    }

    public void going(float inc){
        if (direction == Personaje.LEFT || direction == Personaje.RIGHT)x = x+getNewXInc(inc);
        if (direction == Personaje.UP || direction == Personaje.DOWN)y = y+getNewYInc(inc);
        this.setPosition(x, y);
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public int getState(){
        state = STATE_IN;
        if (x >= limX || y >= limY) {
            state = STATE_OUT;;
        }
        return state;
    }

    private float getNewXInc(float inc){
        if (direction == Personaje.LEFT) return -inc;
        else return inc;
    }

    private float getNewYInc(float inc){
        if (direction == Personaje.UP) return -inc;
        else return inc;
    }
}

package jmarsal.mini2dx.holamaloso;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Jordi Local on 06/09/2015.
 * https://github.com/jordimarsal/
 */
public class Player extends Personaje {

    public Player(Texture texture){
        super(texture);
        x = 100;
        y = 100;
        nombre = "play";
    }

    public void moveStep(float iX, float iY) {
        x -= iX;
        y -= iY;
        if (iX >0)direction = Personaje.LEFT;
        if (iX <0)direction = Personaje.RIGHT;
        if (iX==0 && iY>0)direction = Personaje.UP;
        if (iX==0 && iY<0)direction = Personaje.DOWN;
        boundsUpdate();
    }
}

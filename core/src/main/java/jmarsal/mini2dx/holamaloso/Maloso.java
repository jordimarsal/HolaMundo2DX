package jmarsal.mini2dx.holamaloso;


import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.engine.geom.CollisionBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordi Local on 04/09/2015.
 * https://github.com/jordimarsal/
 */
public class Maloso extends Personaje {


    public Maloso(Texture texture) {
        super(texture);
        x = 250;// Gdx.graphics.getWidth() - (int) (Math.random() * 120) - 32;
        y = 250;//Gdx.graphics.getHeight() + 32 - (int) (Math.random() * (Gdx.graphics.getHeight() - 64));
        nombre = "malo";
    }

    public void move() {
        x += xInc;
        y += yInc;
        boundsUpdate();
    }

    public void updateStep(float xPlayer, float yPlayer) {
        float xr = xPlayer - x;
        float yr = yPlayer - y;
        float xof = 0.2f;
        float yof = 0.2f;
        float ratio = xPlayer / x;
        // System.out.println("/**>");
        // System.out.println("  xr:" + xr + " yr:" + yr);

        if (xPlayer > x) {
            //   System.out.println("  hacia >");
            direction = Personaje.RIGHT;
        } else {
            xof = -xof;
            //  System.out.println("  hacia <");
            direction = Personaje.LEFT;
        }
        if (yPlayer > y) {
            //  System.out.println("  hacia V  : "+(xPlayer/x));
            if (ratio > 0.9f && ratio < 1.1f) direction = Personaje.DOWN;

        } else {
            yof = -yof;
            //  System.out.println("  hacia ^");
            if (ratio > 0.9f && ratio < 1.1f) direction = Personaje.UP;
        }
        xInc = xof + xr / minW;
        yInc = yof + yr / minW;
        //  System.out.println("  x:"+x+" xI:" + xInc + " || y:"+y+" yI:" + yInc);
        //  System.out.println("<**/");
    }

    private void getCollisions() {

        /* Representado por una coordenada
        Quad<CollisionPoint> collisions = new Quad<CollisionPoint>(numElementos, x, y, (float) HolaMini2DxGame.SPRITE_W, (float) HolaMini2DxGame.SPRITE_H);
        //Add a collision
        collisions.add(new CollisionPoint(4f, 4f));
        //Get all collisions in an area
        List<CollisionPoint> collisionsInArea = new ArrayList<CollisionPoint>();
        collisions.getElementsWithinRegion(collisionsInArea, new Rectangle(0f, 0f, 2f, 2f));
        //collisionsInArea now has a list of collisions in the rectangle's area
*/
        /*Re presentado por un area*/

        //////RegionQuad<CollisionBox> collisions = new RegionQuad<CollisionBox>();
//Add a collision
       ////// collisions.add(new CollisionBox(4f, 4f, 2f, 2f));
//Get all collisions in an area
        List<CollisionBox> collisionsInArea = new ArrayList<CollisionBox>();
       ////// collisions.getElementsWithinRegion(collisionsInArea, new Rectangle(0f, 0f, 2f, 2f));
//collisionsInArea now has a list of collisions intersecting or within the rectangle's area
    }




    public void colisionesrender() {

/*
        polygon.setPosition(car1.x, car1.y);
        polygon.setRotation(car1.rotation);
        polygon2.setPosition(car2.x, car2.y);
        polygon2.setRotation(car2.rotation);
*/



    }


}

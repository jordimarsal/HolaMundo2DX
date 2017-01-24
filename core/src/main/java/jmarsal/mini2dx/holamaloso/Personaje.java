package jmarsal.mini2dx.holamaloso;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

/**
 * Created by Jordi Local on 06/09/2015.
 * https://github.com/jordimarsal/
 */
public class Personaje {
    public static final int ANIMATION_FRAMES = 3;
    public static final int ANIMATION_NUMBER = 4;
    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;


    protected final int minW = 3 * Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Rectangle bounds;
    Polygon polygon;
    public int direction;
    public float x, y;
    public ArrayList<Animation> arrayAnimation;
    public Sprite spriteQuiet;
    protected String nombre;
    protected float xInc, yInc;

    public Personaje(Texture texture){
        arrayAnimation = new ArrayList<Animation>();
        SetArrayAnimation(arrayAnimation, texture);

        direction = (int) (Math.random() * 4);
        spriteQuiet = new Sprite(texture, HolaMini2DxGame.SPRITE_W, HolaMini2DxGame.SPRITE_H);
        boundsCreate();
    }




    protected void boundsCreate() {

        bounds = new Rectangle(x, y, HolaMini2DxGame.SPRITE_W, HolaMini2DxGame.SPRITE_H);
        polygon = new Polygon(new float[]{0, 0, bounds.width, 0, bounds.width, bounds.height, 0, bounds.height});
        polygon.setOrigin(bounds.width / 2, bounds.height / 2);

    }

    protected void boundsUpdate() {

        bounds = new Rectangle(x, y, HolaMini2DxGame.SPRITE_W, HolaMini2DxGame.SPRITE_H);
        //polygon = new Polygon(new float[]{0, 0, bounds.width, 0, bounds.width, bounds.height, 0, bounds.height});
        polygon.setOrigin(bounds.width / 2, bounds.height / 2);
        float [] v=polygon.getTransformedVertices();
        //System.out.println(nombre+" pol:("+v[0]+","+v[1]+")"+"("+v[2]+","+v[3]+")"+"("+v[4]+","+v[5]+")"+"("+v[6]+","+v[7]+")");

    }

    protected void SetArrayAnimation(ArrayList<Animation> array, Texture textureArray) {
        for (int row = 0; row < ANIMATION_NUMBER; row++) {
            Animation ani = new Animation();
            for (int col = 0; col < ANIMATION_FRAMES; col++) {
                Sprite sprite = new Sprite(textureArray, col * HolaMini2DxGame.SPRITE_W, row * HolaMini2DxGame.SPRITE_H, HolaMini2DxGame.SPRITE_W, HolaMini2DxGame.SPRITE_H);
                // System.out.println("row:" + row + " col:" + col);
                ani.addFrame(sprite, 0.15f);
            }
            ani.setFlipY(true);
            ani.setLooping(true);
            array.add(ani);
        }
    }

    public boolean hasColision(Polygon polygonPlayer){
        if (Intersector.overlapConvexPolygons(polygonPlayer, polygon)) {
            //COLLISION DON'T HAPPEN!!!
            return false;
        }else{
            return true;
        }
    }

}

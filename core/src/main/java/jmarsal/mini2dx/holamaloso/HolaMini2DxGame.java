package jmarsal.mini2dx.holamaloso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

public class HolaMini2DxGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "jmarsal.mini2dx.holamaloso";
    public static AssetManager MANAGER = new AssetManager();

    public static final int SPRITE_H = 32;
    public static final int SPRITE_W = 32;
    public static final int QUIET = 4;
    public static final int MULTI_INPUT = 11;
    public static final int MULTI_MOB1 = 20;

    private Maloso maloso;
    private Player player;
    private ArrayList<Shot> allShots;
    private ArrayList<Integer> deleteList;
    private Texture textuShot;
    private int previousDirection;
    boolean hasShots = false;
    float iDelta, mobDelta;

    @Override
    public void initialise() {

        LoadAssets loadAssets= new LoadAssets(MANAGER);
        MANAGER = loadAssets.getManager();
        while (!MANAGER.update()) {
        }
        allShots = new ArrayList<Shot>();
        deleteList = new ArrayList<Integer>();
        textuShot = MANAGER.get(loadAssets.getPath()+"disparo.png", Texture.class);

        Texture texturePlayer = MANAGER.get(loadAssets.getPath()+"image1.png", Texture.class);
        player = new Player(texturePlayer);

        Texture textuMalo = MANAGER.get(loadAssets.getPath()+"mal1.png", Texture.class);
        maloso = new Maloso(textuMalo);
    }



    @Override
    public void update(float delta) {
        for (Animation a : player.arrayAnimation) {
            a.update(delta);
        }
        for (Animation a : maloso.arrayAnimation) {
            a.update(delta);
        }
        processShots();
        processMobs(delta);
        processInput(delta);
    }

    private void processShots() {
        int i = 0;
        for (Shot s : allShots) {
            s.going(5);
            if (s.getState() == Shot.STATE_OUT) {
                deleteList.add(i);
            }
            i++;
        }
        if (deleteList.size() > 0) {
            for (Integer d : deleteList) {
                allShots.remove(d);
            }
        }
        if (allShots.size() == 0) hasShots = false;
        deleteList.clear();
    }

    private void processMobs(float delta) {
        mobDelta+= delta;
        if(mobDelta >= delta * MULTI_MOB1){
            maloso.updateStep(player.x, player.y);
            mobDelta=0;
        }
        maloso.move();
        if(maloso.hasColision(player.polygon)) System.out.println("PUM!");

        if(mobDelta > 10000)mobDelta=0;
    }


    private void processInput(float delta) {
        float step = 1.3f;
        boolean pulsed = false;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveStep(step,step);
            pulsed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveStep(-step,step);
            pulsed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveStep(step,-step);
            pulsed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveStep(-step,-step);
            pulsed = true;
        }
        step = 2f;
        if (!pulsed) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player.moveStep(0, step);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player.moveStep(0, -step);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.moveStep(step,0);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.moveStep(-step, 0);
            } else {
                if (player.direction != QUIET)previousDirection = player.direction;
                player.direction = QUIET;
            }
        }
        iDelta += delta;
        // delta es 0.01, limitamos los disparos por segundo
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && iDelta > (delta * MULTI_INPUT) ) {
            System.out.println("Fire:" + player.direction);
            allShots.add(getSpriteShot(player.x, player.y));
            hasShots = true;
            iDelta = 0;
        }
        if (iDelta > 1000) iDelta = 0;
        if (player.y < 0) player.y = 0;
        if (player.x < 0) player.x = 0;
        float limY = Gdx.graphics.getHeight() - SPRITE_H;
        float limX = Gdx.graphics.getWidth() - SPRITE_W;
        if (player.y > limY) player.y = limY;
        if (player.x > limX) player.x = limX;
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        g.setBackgroundColor(Color.DARK_GRAY);
        //g.drawTexture(sprite, 0f, 0f);


        if (player.direction < QUIET) {
            player.arrayAnimation.get(player.direction).draw(g, player.x, player.y);
        } else {
            g.drawSprite(player.spriteQuiet, player.x, player.y);
        }
        maloso.arrayAnimation.get(maloso.direction).draw(g, maloso.x , maloso.y );
        if (hasShots) {
            for (Shot s : allShots) {
                g.drawSprite(s, s.getX(), s.getY());
            }
        }
    }

    private Shot getSpriteShot(float x, float y) {
        int dir = (player.direction == QUIET) ? previousDirection : player.direction;
        Shot s = new Shot(textuShot, dir);
        s.setX(x + 20);
        s.setY(y + 12);
        return s;
    }
}

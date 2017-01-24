package jmarsal.mini2dx.holamaloso;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Jordi Marsal on 24/01/2017.
 * https://github.com/jordimarsal/
 */
public class LoadAssets {

    private static final String path ="core/assets/";

    private AssetManager MANAGER ;
    LoadAssets(AssetManager manager){
        MANAGER = manager;
        initManager();
    }

    private void initManager(){
        String path = getPath();

        // TEXTURES
        MANAGER.load(path +"disparo.png", Texture.class);
        MANAGER.load(path +"image1.png", Texture.class);
        MANAGER.load(path +"mal1.png", Texture.class);

        // MUSICA
        //MANAGER.load(path +VICTORY, Music.class);
    }
    AssetManager getManager(){
        return MANAGER;
    }

    public static String getPath(){
        return path;
    }
}

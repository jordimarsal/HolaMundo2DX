package jmarsal.mini2dx.holamaloso.android;

import org.mini2Dx.android.AndroidMini2DxConfig;

import com.badlogic.gdx.backends.android.AndroidMini2DxGame;

import android.os.Bundle;

import jmarsal.mini2dx.holamaloso.HolaMini2DxGame;

public class AndroidLauncher extends AndroidMini2DxGame {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidMini2DxConfig config = new AndroidMini2DxConfig(HolaMini2DxGame.GAME_IDENTIFIER);
        initialize(new HolaMini2DxGame(), config);
    }
}

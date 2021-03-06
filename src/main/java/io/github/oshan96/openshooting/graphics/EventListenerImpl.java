package io.github.oshan96.openshooting.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.texture.Texture;
import io.github.oshan96.openshooting.resources.ImageResource;
import io.github.oshan96.openshooting.world.World;
import io.github.oshan96.openshooting.world.sprites.fighters.impl.FighterBee;
import io.github.oshan96.openshooting.world.sprites.fighters.impl.FighterKree;

/**
 * @author oshan
 */
public class EventListenerImpl implements GLEventListener {

    public static GL2 gl;
    public static Texture background = null;



    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0,0,0,1);       //use black when the screen is cleared
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        background = new ImageResource("/images/background/street.png").getTexture();
        Graphics.createObjectTexture(background,0,0,32,32);

        FighterKree playerOne = new FighterKree(0,0,64,64, 0, 0);
        FighterBee playerTwo = new FighterBee(-1,0,64,64, 0, 0);
        World.addGameObject(playerOne);
        World.addGameObject(playerTwo);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
//        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);        //clear the color buffer

        World.render();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);     //2-Dimensional
        gl.glLoadIdentity();

//        gl.glOrtho(-400,400,-300,300,-1,1);    //-x,x,-y,y,-z,z (screen is mapped to pixel value)

        float unitsTall = Renderer.getWindowHeight() / (Renderer.getWindowWidth() / Renderer.tileSize);

        gl.glOrtho(-Renderer.tileSize / 2, Renderer.tileSize / 2, -unitsTall / 2, unitsTall / 2, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}

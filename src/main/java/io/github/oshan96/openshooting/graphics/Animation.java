package io.github.oshan96.openshooting.graphics;

import com.jogamp.opengl.util.texture.Texture;

import java.util.List;

/**
 * @author oshan
 */
public class Animation {
    private List<Texture> sprites;
    private int currentFrame = 0;
    private int fps = 20;
    private long lastFrameTime = 0;

    public Animation() {
    }

    public Animation(List<Texture> sprites) {
        this.sprites = sprites;
    }

    public void play() {
        //iterate through sprites array
        long currentTime = System.nanoTime();

        if(currentTime > lastFrameTime + 1000000000 / fps) {
            currentFrame++;

            if(currentFrame >= sprites.size()) {
                currentFrame = 0;
            }

            lastFrameTime = currentTime;
        }
    }

    public List<Texture> getSprites() {
        return sprites;
    }

    public void setSprites(List<Texture> sprites) {
        this.sprites = sprites;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public Texture getCurrentImage() {
        return sprites.get(currentFrame);
    }
}

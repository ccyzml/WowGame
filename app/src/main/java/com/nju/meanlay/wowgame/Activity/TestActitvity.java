package com.nju.meanlay.wowgame.Activity;

import android.annotation.SuppressLint;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nju.meanlay.wowgame.R;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import androidx.appcompat.app.AppCompatActivity;

public class TestActitvity extends AppCompatActivity {
    private static TestActitvity master = null;

    private GLSurfaceView mGLView;
    private MyRenderer renderer = null;
    private FrameBuffer fb = null;
    private World world = null;

    private int fps = 0;

    private Light sun = null;

    private GL10 lastGl = null;

    private RGBColor back = new RGBColor(50, 50, 10);

    private static final int SWAP_UI = 1;
    private boolean swapUI = false;

    private Random rand = new Random();

    // Handle the message for turning on and off the immersive GUI
    public static Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            // Handle the message on the thread associated to the given looper.
            if (message.what == TestActitvity.SWAP_UI) {
                swapSystemUIdo();
            }
        }
    };

    public static int countSquares = 0;
    public Object3D obj;
    public Texture texture;

    protected void onCreate(Bundle savedInstanceState) {

        Logger.log("onCreate");

        if (master != null) {
            copy(master);
        }

        super.onCreate(savedInstanceState);

        mGLView = new GLSurfaceView(getApplication());
        mGLView.setEGLContextClientVersion(2);
        mGLView.setPreserveEGLContextOnPause(true);

        renderer = new MyRenderer();
        mGLView.setRenderer(renderer);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    // Swap System UI Send message to the main Activity thread.
    public static void swapSystemUI() {
        Message message = new Message();
        message.what = TestActitvity.SWAP_UI;
        TestActitvity.handler.sendMessage(message);
    }

    // Swap System UI - do the work (this is the right thread which can do this)
    @SuppressLint("InlinedApi")
    protected static void swapSystemUIdo() {
        Log.i("MainActivity", "swapUI");
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        int uiSetting = TestActitvity.getApp().getWindow().getDecorView().getSystemUiVisibility();
        int ver = Build.VERSION.SDK_INT;
        if (ver >= 14) {
            uiSetting ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (ver >= 16) {
            uiSetting ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (ver >= 19) {
            uiSetting ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        TestActitvity.getApp().getWindow().getDecorView().setSystemUiVisibility(uiSetting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.exit(0);
    }

    private void copy(Object src) {
        try {
            Logger.log("Copying data from master Activity!");
            Field[] fs = src.getClass().getDeclaredFields();
            for (Field f : fs) {
                f.setAccessible(true);
                f.set(this, f.get(src));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean onTouchEvent(MotionEvent me) {

        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_UP) {
            swapUI = true;
            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }

        try {
            Thread.sleep(15);
        } catch (Exception e) {
            // No need for this...
        }

        return super.onTouchEvent(me);
    }

    protected boolean isFullscreenOpaque() {
        return true;
    }

    class MyRenderer implements GLSurfaceView.Renderer {

        private long time = System.currentTimeMillis();

        public MyRenderer() {
        }

        @Override
        public void onSurfaceCreated(GL10 gl10, javax.microedition.khronos.egl.EGLConfig eglConfig) {

        }

        public void onSurfaceChanged(GL10 gl, int w, int h) {

            // Renew the frame buffer
            if (lastGl != gl) {
                Log.i("HelloWorld", "Init buffer");
                if (fb != null) {
                    fb.dispose();
                }
                fb = new FrameBuffer(w, h);
                fb.setVirtualDimensions(fb.getWidth(), fb.getHeight());
                lastGl = gl;
            } else {
                fb.resize(w, h);
                fb.setVirtualDimensions(w, h);
            }

            // Create the world if not yet created
            if (master == null) {
                world = new World();
                world.setAmbientLight(20, 20, 20);

                sun = new Light(world);
                sun.setIntensity(250, 250, 250);

                // Create the texture we will use in the blitting
                texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.ic_launcher_background)), 256, 256));
                TextureManager.getInstance().addTexture("texture", texture);

                // Create the object
//                obj = Primitives.getPlane(1, 20f);
//                world.addObject(obj);
//                obj.translate(0, 0, 0);
//                obj.setTexture("texture");
//                obj.build();

                File objFile = new File("/models/Ragnaros/Ragnaros.obj");
                File mtlFile = new File("models/Ragnaros/Ragnaros.mtl");
                InputStream objInputStream = null;
                InputStream mtlInputStream = null;
                try {
                    objInputStream = new FileInputStream(objFile);
                    mtlInputStream = new FileInputStream(mtlFile);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                obj = Loader.loadOBJ(objInputStream,mtlInputStream,0.05f)[0];

                Camera cam = world.getCamera();
                cam.moveCamera(Camera.CAMERA_MOVEOUT, 15);
                cam.lookAt(SimpleVector.ORIGIN);

                SimpleVector sv = new SimpleVector();
                sv.set(SimpleVector.ORIGIN);
                sv.y -= 100;
                sv.z -= 100;
                sun.setPosition(sv);
                MemoryHelper.compact();

                if (master == null) {
                    Logger.log("Saving master Activity!");
                    master = TestActitvity.this;
                }
            }
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        }

        public void onDrawFrame(GL10 gl) {

            // If the switch is on, call the UI swap in the correct thread
            if (swapUI) {
                TestActitvity.swapSystemUI();
                swapUI = false;
            }

            // Draw the main screen
            fb.clear(back);
            world.renderScene(fb);
            world.draw(fb);
            fb.display();

            if (System.currentTimeMillis() - time >= 1000) {
                Logger.log(fps + "fps");
                fps = 0;
                time = System.currentTimeMillis();
            }
            fps++;
        }
    }

    public static TestActitvity getApp() {
        return master;
    }
}

package mx.itesm.soul;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by User on 17/02/2017.
 */

public class PantallaCreditos extends Pantalla {
    private final mx.itesm.soul.ColourlessSoul menu;
    private final AssetManager manager;

    //sonidos
    Music windMusic = Gdx.audio.newMusic(Gdx.files.internal("musicSounds/wind.mp3"));

    private Preferences prefs = Gdx.app.getPreferences("Settings");

    //texturas
    private Texture texturaFondo;
    private Texture texturaCreditos;
    private Image imgCredits;
    private boolean vanish = false;

    //Escena
    private Stage escena;
    private SpriteBatch batch;

    public PantallaCreditos(mx.itesm.soul.ColourlessSoul menu) {
        this.menu = menu;
        manager=menu.getAssetManager();
    }

    @Override
    public void show() {
        // Cuando cargan la pantalla
        cargarTexturas();
        crearObjetos();
        Gdx.input.setInputProcessor(new Procesador());
    }

    private void crearObjetos() {
        batch = new SpriteBatch();
        escena = new Stage(vista, batch);
        Image imgFondo = new Image(texturaFondo);
        escena.addActor(imgFondo);

        //Boton
        imgCredits = new Image(texturaCreditos);
        imgCredits.setPosition(0,-ALTO+140);
        escena.addActor(imgCredits);

        if(prefs.getBoolean("Music",true))
            windMusic.play();

        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(true);
    }

    private void cargarTexturas() {
        texturaFondo = manager.get("fondoPrincipal.jpg");
        texturaCreditos = manager.get("creditos.png");
    }


    @Override
    public void render(float delta) {
        // 60 x seg
        actualizarObjetos(delta);
        if (vanish){
            imgCredits.setColor(1,1,1,imgCredits.getColor().a-0.01f);
            windMusic.setVolume(windMusic.getVolume()-0.009f);
        }
        if(imgCredits.getColor().a<=0){
            windMusic.stop();
            menu.setScreen(new mx.itesm.soul.PantallaExtras(menu));
        }
        borrarPantalla();
        escena.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
            vanish = true;
    }

    private void actualizarObjetos(float delta) {
        if(imgCredits.getY()!=0) imgCredits.setY(imgCredits.getY()+2);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        escena.dispose();
        manager.unload("fondoPrincipal.jpg");
        manager.unload("creditos.png");

        windMusic.dispose();
    }

    private class Procesador implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            vanish = true;
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
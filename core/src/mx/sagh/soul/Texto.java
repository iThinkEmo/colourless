package mx.sagh.soul;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by User on 24/03/2017.
 */

public class Texto extends Actor {
    private BitmapFont font;

    public Texto(){
        font = new BitmapFont(Gdx.files.internal("WorldOfWater.fnt"));
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        GlyphLayout glyp = new GlyphLayout();
        glyp.setText(font, mensaje);
        float anchoTexto = glyp.width;
        font.draw(batch,glyp,x-anchoTexto/2,y);
    }

}
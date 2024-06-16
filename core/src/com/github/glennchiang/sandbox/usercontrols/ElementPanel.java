package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.glennchiang.sandbox.elements.ElementType;

// Handles rendering of element buttons for ElementPainter
public class ElementPanel {
    private final ElementPainter elementPainter;
    private final ElementType[] elementTypes;
    public final Table table = new Table(); // Layout group containing all the buttons

    public ElementPanel(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
        this.elementTypes = elementPainter.getElementTypes();

//        table.setDebug((true));

        for (int i = 0; i < elementTypes.length; i++) {
            ElementType elementType = elementTypes[i];
            Color color = elementType.color;

            // Create pixmap for button background
            Pixmap pixmap = new Pixmap(64, 32, Pixmap.Format.RGBA8888);
            pixmap.setColor(color);
            if (elementType == ElementType.EMPTY) {
                pixmap.drawRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());
            } else {
                pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());
            }
            Texture texture = new Texture(pixmap);
            Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
            pixmap.dispose();

            Button button = new ImageButton(drawable);

            // Create style for button label
            Label.LabelStyle style = new Label.LabelStyle();
            style.font = new BitmapFont();
            style.fontColor = color;
            Label label = new Label(elementType.name(), style);

            // When button is clicked, set the active element of elementPainter
            // to the corresponding element
            button.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    elementPainter.setActiveElement(elementType);
                }
            });

            table.add(button).expandX().fill().pad(2);
            table.add(label).expandX().fill().pad(2);
            table.row();
        }
    }

}

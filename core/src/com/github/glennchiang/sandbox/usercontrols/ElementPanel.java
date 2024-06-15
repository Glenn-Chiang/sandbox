package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.glennchiang.sandbox.elements.ElementType;

// Handles rendering of element buttons for ElementPainter
public class ElementPanel {
    private final ElementPainter elementPainter;
    private final ElementType[] elementTypes;
    private final Button[] buttons;
    public final Table table = new Table(); // Layout group containing all the buttons

    public ElementPanel(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
        this.elementTypes = elementPainter.getElementTypes();
        this.buttons = new Button[elementTypes.length];

        table.setDebug((true));

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);

        for (int i = 0; i < elementTypes.length; i++) {
            ElementType elementType = elementTypes[i];

            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
            style.font = new BitmapFont();
            style.fontColor = elementType.color;

            pixmap.setColor(elementType.color);
            pixmap.fillRectangle(0,0, 32, 16);
            Texture texture = new Texture(pixmap);

            Button button = new TextButton(elementType.name(), style);
            button.setBackground(new TextureRegionDrawable(new TextureRegion(texture)));

            // When button is clicked, set the active element of elementPainter
            // to the corresponding element
            button.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    elementPainter.setActiveElement(elementType);
                }
            });

            buttons[i] = button;
            table.add(button).expandX().fillX().pad(2);
            table.row();
        }
    }

}

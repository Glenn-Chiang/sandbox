package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.glennchiang.sandbox.elements.ElementType;

// Handles rendering of element buttons for ElementPainter
public class ElementPanel {
    private final ElementPainter elementPainter;
    private final ElementType[] elementTypes;
    private final Button[] buttons;
    public final VerticalGroup container = new VerticalGroup(); // Layout group containing all the buttons

    public ElementPanel(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
        this.elementTypes = elementPainter.getElementTypes();
        this.buttons = new Button[elementTypes.length];

        container.setDebug((true));
        container.pad(10).space(4);

        for (int i = 0; i < elementTypes.length; i++) {
            ElementType elementType = elementTypes[i];

            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
            style.font = new BitmapFont();
            style.fontColor = elementType.color;
            Button button = new TextButton(elementType.name(), style);

            // When button is clicked, set the active element of elementPainter
            // to the corresponding element
            button.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Clicked");
                    elementPainter.setActiveElement(elementType);
                }
            });

            buttons[i] = button;
            container.addActor(button);
        }
    }

}

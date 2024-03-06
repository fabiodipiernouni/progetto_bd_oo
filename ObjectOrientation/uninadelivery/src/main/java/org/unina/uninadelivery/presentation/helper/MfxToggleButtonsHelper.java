package org.unina.uninadelivery.presentation.helper;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class MfxToggleButtonsHelper {
    private final ToggleGroup toggleGroup;

    public MfxToggleButtonsHelper(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    public ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) wrapper.getIcon().setRotate(rotate);
        return toggleNode;
    }

}

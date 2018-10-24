package net.superblaubeere27.clientbase.gui.clickgui;


import net.minecraft.client.gui.Gui;
import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.gui.clickgui.components.AbstractComponentUI;
import net.superblaubeere27.clientbase.gui.clickgui.components.CheckBoxUI;
import net.superblaubeere27.clientbase.gui.clickgui.components.ComboBoxUI;
import net.superblaubeere27.clientbase.gui.clickgui.components.SliderUI;
import net.superblaubeere27.clientbase.modules.Module;
import net.superblaubeere27.clientbase.valuesystem.BooleanValue;
import net.superblaubeere27.clientbase.valuesystem.ModeValue;
import net.superblaubeere27.clientbase.valuesystem.NumberValue;
import net.superblaubeere27.clientbase.valuesystem.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright © 2015 - 2017 | CCBlueX | All rights reserved.
 * <p>
 * LiquidBase - By CCBlueX(Marco)
 */

public class Button {
    private Panel panel;
    private Module module;
    private boolean isExtended = false;
    @NotNull
    private List<AbstractComponentUI<?>> settings = new ArrayList<>();

    public Button(Panel panel, Module module) {
        this.panel = panel;
        this.module = module;
        List<Value> allValuesFrom = ClientBase.INSTANCE.valueManager.getAllValuesFrom(module.getName());

        if (allValuesFrom == null) {
            return;
        }
        for (Value value : allValuesFrom) {
            if (value instanceof BooleanValue) {
                settings.add(new CheckBoxUI((BooleanValue) value));
            }
            if (value instanceof ModeValue) {
                settings.add(new ComboBoxUI((ModeValue) value));
            }
            if (value instanceof NumberValue) {
                settings.add(new SliderUI((NumberValue) value));
            }
        }
    }

    public void click() {
        module.setState(!module.getState());
    }

    public Module getModule() {
        return module;
    }

    public boolean isHover(final int x, final int y, final int widht, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x + widht && mouseY >= y && mouseY <= y + height;
    }

    public int renderExtended(int x, int y) {
        int offset = y + 1;
        int maxX = 0;
        if (isExtended) {
            for (AbstractComponentUI<?> setting : settings) {
                setting.update();
                setting.setX(x);
                setting.setY(offset);
                offset += setting.getHeight() + 2;
                maxX = Math.max(setting.getWidth(), maxX);
            }
            offset += 1;
            Gui.drawRect(x, y, maxX + x, offset, ClickGUI.PANEL_MAIN_COLOR.getRGB());
            for (AbstractComponentUI<?> setting : settings) {
                setting.draw();
            }
        }
        return offset;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public void setExtended(boolean extended) {
        isExtended = extended;
    }

    public void onMouseClick(int x, int y, int mouseButton) {
        for (AbstractComponentUI<?> setting : settings) {
            if (isExtended) {
                setting.onMouseClick(x, y, mouseButton);
            }
        }
    }
}
package de.themoep.inventorygui;

/*
 * Copyright 2017 Max Lee (https://github.com/Phoenix616/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Mozilla Public License as published by
 * the Mozilla Foundation, version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Mozilla Public License v2.0 for more details.
 *
 * You should have received a copy of the Mozilla Public License v2.0
 * along with this program. If not, see <http://mozilla.org/MPL/2.0/>.
 */

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Represents an element in a gui
 */
public class GuiStaticElement extends GuiElement {
    private ItemStack item;
    private int number = 1;
    private String[] text;

    /**
     * Represents an element in a gui
     * @param slotChar The character to replace in the gui setup string
     * @param item     The item this element displays
     * @param action   The action to run when the player clicks on this element
     * @param text     The text to display on this element, if it's not set/empty the item's default name will be used
     */
    public GuiStaticElement(char slotChar, ItemStack item, Action action, String... text) {
        super(slotChar, action);
        this.item = item;
        setAction(action);
        setText(text);
    }

    /**
     * Represents an element in a gui that doesn't have any action when clicked
     * @param slotChar  The character to replace in the gui setup string
     * @param item      The item this element displays
     * @param text      The text to display on this element, if it's not set/empty the item's default name will be used
     */
    public GuiStaticElement(char slotChar, ItemStack item, String... text) {
        this(slotChar, item, null, text);
    }


    /**
     * Set the item that is displayed by this element
     * @param item  The item that should be displayed by this element
     */
    public void setItem(ItemStack item) {
        this.item = item;
        setText(getText());
    }

    @Override
    public ItemStack getItem(int slot) {
        return item;
    }

    /**
     * Set this element's display text. If this is an empty array the item's name will be displayed
     * @param text  The text to display on this element
     */
    public void setText(String... text) {
        this.text = text;
        InventoryGui.setItemText(item, text);
    }

    /**
     * Get the text that this element displays
     * @return  The text that is displayed on this element
     */
    public String[] getText() {
        return text;
    }

    /**
     * Set the number that this element should display (via the Item's amount)
     * @param number    The number, 1 will not display the number
     * @throws IllegalArgumentException If the number is below 1 or above the max stack count (currently 64)
     */
    public void setNumber(int number) throws IllegalArgumentException {
        if (number < 1 || number > 64) {
            throw new IllegalArgumentException("Only numbers from 1 to 64 are allowed. (" + number + ")");
        }
        this.number = number;
        this.item.setAmount(number);
    }

    /**
     * Get the number that this element should display
     * @return The number (item amount) that this element currently has
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the ids of the slots where this element is assigned to
     * @param slots An array of the slot ids where this element is displayed
     */
    public void setSlots(int[] slots) {
        this.slots = slots;
    }

    /**
     * Get the index that this slot has in the list of slots that this element is displayed in
     * @param slot  The id of the slot
     * @return      The index in the list of slots that this id has or <tt>-1</tt> if it isn't in that list
     */
    public int getSlotIndex(int slot) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == slot) {
                return i;
            }
        }
        return -1;
    }
}

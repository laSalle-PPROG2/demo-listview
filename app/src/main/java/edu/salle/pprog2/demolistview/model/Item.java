package edu.salle.pprog2.demolistview.model;

import java.util.Comparator;

/**
 * Created by Eduard on 02/03/2018.
 */

public class Item {
    private String title;
    private String text;

    public static final Comparator<Item> COMPARATOR =
            new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            };

    public Item(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        return text != null ? text.equals(item.text) : item.text == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}

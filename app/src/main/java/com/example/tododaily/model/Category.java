package com.example.tododaily.model;

public class Category {
    private String color;
    private  String category;
    private  String id;
    private boolean selected;
    public Category(String color, String category, boolean selected) {
        this.color = color;
        this.category = category;
        this.selected = selected;
    }
    public Category(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

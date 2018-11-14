package ru.makproductions.firebasemessenger.model.message;

public abstract class IncludedMedia {
    protected String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

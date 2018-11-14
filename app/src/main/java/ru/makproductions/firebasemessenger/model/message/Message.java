package ru.makproductions.firebasemessenger.model.message;

import java.util.ArrayList;

public class Message {
    private String text;
    private String authorName;
    private ArrayList<IncludedImage> images;
    private ArrayList<IncludedSound> sounds;
    private ArrayList<IncludedVideo> videos;
    private long timeOfCreation;
    private long timeOfChange;

    Message() {
    }

    public void setImages(ArrayList<IncludedImage> images) {
        this.images = images;
    }

    public void setSounds(ArrayList<IncludedSound> sounds) {
        this.sounds = sounds;
    }

    public void setVideos(ArrayList<IncludedVideo> videos) {
        this.videos = videos;
    }

    public void addImage(IncludedImage includedImage) {
        images.add(includedImage);
    }

    public void addSound(IncludedSound includedSound) {
        sounds.add(includedSound);
    }

    public void addVideo(IncludedVideo includedVideo) {
        videos.add(includedVideo);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(long timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public long getTimeOfChange() {
        return timeOfChange;
    }

    public void setTimeOfChange(long timeOfChange) {
        this.timeOfChange = timeOfChange;
    }
}

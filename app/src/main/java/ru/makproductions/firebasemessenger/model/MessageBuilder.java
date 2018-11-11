package ru.makproductions.firebasemessenger.model;

public class MessageBuilder {
    private Message message;

    MessageBuilder() {
        message = new Message();
    }

    public MessageBuilder withText(String text) {
        message.setText(text);
        return this;
    }

    public MessageBuilder withAuthorName(String authorName) {
        message.setText(authorName);
        return this;
    }

    public MessageBuilder withImage(IncludedImage image) {
        message.addImage(image);
        return this;
    }

    public MessageBuilder withSound(IncludedSound sound) {
        message.addSound(sound);
        return this;
    }

    public MessageBuilder withVideo(IncludedVideo video) {
        message.addVideo(video);
        return this;
    }

    public MessageBuilder withCreationTime(long creationTime) {
        message.setTimeOfCreation(creationTime);
        return this;
    }

    public MessageBuilder withChangeTime(long changeTime) {
        message.setTimeOfChange(changeTime);
        return this;
    }

    public Message build() {
        if (message.getText() == null || message.getAuthorName() == null
                || message.getTimeOfCreation() == 0)
            throw new RuntimeException("Message is not properly built");
        return this.message;
    }
}

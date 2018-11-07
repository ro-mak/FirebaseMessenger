package ru.makproductions.firebasemessenger.model;

    public class MessageBuilder {
    private Message message;

    MessageBuilder(Message message) {
        this.message = message;
    }

    public Message withText(String text) {
        message.setText(text);
        return this.message;
    }

    public Message withAuthorName(String authorName) {
        message.setText(authorName);
        return this.message;
    }

    public Message withImage(IncludedImage image) {
        message.addImage(image);
        return this.message;
    }

    public Message withSound(IncludedSound sound) {
        message.addSound(sound);
        return this.message;
    }

    public Message withVideo(IncludedVideo video) {
        message.addVideo(video);
        return this.message;
    }

    public Message withCreationTime(long creationTime) {
        message.setTimeOfCreation(creationTime);
        return this.message;
    }

    public Message withChangeTime(long changeTime) {
        message.setTimeOfChange(changeTime);
        return this.message;
    }

    public Message build() {
        if (message.getText() == null || message.getAuthorName() == null
                || message.getTimeOfCreation() == 0)
            throw new RuntimeException("Message is not properly built");
        return this.message;
    }
}

package ru.makproductions.firebasemessenger.homework;

public interface Builder {
    HTMLBuilder openTag(String tag);

    HTMLBuilder closeTag(String tag);

    HTMLBuilder addText(String text);

    String build();

}

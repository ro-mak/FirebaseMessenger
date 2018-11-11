package ru.makproductions.firebasemessenger.homework;

public class SingletoneProxy {
    private static HTMLBuilder htmlBuilder;

    public SingletoneProxy() {
    }

    public static HTMLBuilder getHtmlBuilder() {
        if (htmlBuilder == null) htmlBuilder = new HTMLBuilder();
        return htmlBuilder;
    }
}

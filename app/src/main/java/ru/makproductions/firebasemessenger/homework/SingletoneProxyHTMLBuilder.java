package ru.makproductions.firebasemessenger.homework;

public class SingletoneProxyHTMLBuilder implements Builder {
    private static HTMLBuilder htmlBuilder;
    private static SingletoneProxyHTMLBuilder singletoneProxyHTMLBuilder;

    private SingletoneProxyHTMLBuilder() {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
    }

    @Override
    public HTMLBuilder addText(String text) {
        return htmlBuilder.addText(text);
    }

    @Override
    public HTMLBuilder openTag(String tag) {
        return htmlBuilder.openTag(tag);
    }

    @Override
    public String build() {
        return htmlBuilder.build();
    }

    @Override
    public HTMLBuilder closeTag(String tag) {
        return htmlBuilder.closeTag(tag);
    }

    public static Builder getBuilder() {
        if (singletoneProxyHTMLBuilder == null)singletoneProxyHTMLBuilder =  new SingletoneProxyHTMLBuilder();
        return singletoneProxyHTMLBuilder;
    }
}

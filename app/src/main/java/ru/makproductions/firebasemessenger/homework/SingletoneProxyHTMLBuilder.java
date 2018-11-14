package ru.makproductions.firebasemessenger.homework;

public class SingletoneProxyHTMLBuilder implements Builder {
    private static HTMLBuilder htmlBuilder;

    public SingletoneProxyHTMLBuilder() {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
    }

    @Override
    public HTMLBuilder addText(String text) {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
        return htmlBuilder.addText(text);
    }

    @Override
    public HTMLBuilder openTag(String tag)
    {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
        return htmlBuilder.openTag(tag);
    }

    @Override
    public String build()
    {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
        return htmlBuilder.build();
    }

    @Override
    public HTMLBuilder closeTag(String tag)
    {
        if(htmlBuilder == null) htmlBuilder = new HTMLBuilder();
        return htmlBuilder.closeTag(tag);
    }

}

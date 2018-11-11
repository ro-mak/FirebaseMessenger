package ru.makproductions.firebasemessenger.homework;

import android.util.Log;

public class HTMLBuilder {
    private static final String TAG = "TAG";
    private StringBuilder htmlPage;
    private boolean tagClosed = true;
    private boolean isChild = false;
    private HTMLBuilder parent;
    private HTMLBuilder child;
    private String tagName;

    HTMLBuilder() {
        htmlPage = new StringBuilder();
    }

    private HTMLBuilder(boolean isChild, HTMLBuilder parent, String tagName) {
        this.tagName = tagName;
        htmlPage = new StringBuilder();
        this.isChild = isChild;
        this.parent = parent;
    }

    public HTMLBuilder addText(String text) {
        Log.e(TAG, "addText: " + htmlPage.toString() + " + " + text);
        htmlPage.append(text);
        return this;
    }

    public HTMLBuilder openTag(String tag) {
        Log.e(TAG, "openTag: " + tag);
        tagClosed = false;
        addTag(true, tag);
        child = new HTMLBuilder(true, this, tag);
        return child;
    }

    public HTMLBuilder closeTag(String tag) {
        Log.e(TAG, "closeTag: " + tag);
        if (!clearFromBrackets(tag).contains(clearFromBrackets(getTagName()))) {
            throw new RuntimeException("Closing wrong tag. Should be "
                    + getTagName() + ". But you are closing " + tag + ".");
        }
        tagClosed = true;
        addTag(false, tag);
        if (isChild) {
            parent.addText(build());
            return parent;
        }
        return this;
    }

    private void addTag(boolean isOpening, String tag) {
        String clearedTag = clearFromBrackets(tag);
        String openBracket = "<";
        String closeBracket = ">";
        String closingBracket = "/>";
        if (!isOpening) {
            if (!isChild) return;
            addText(openBracket);
            addText(clearedTag);
            addText(closingBracket);
        } else {
            addText(openBracket);
            addText(clearedTag);
            addText(closeBracket);
        }
    }

    public String build() {
        if (parent != null && !parent.isChild) {
            parent.closeTag(parent.getTagName());
        }
        if (!tagClosed) throw new RuntimeException("Tag is not closed "
                + "TagName = " + getTagName());
        Log.e(TAG, "build: " + getTagName() + " " + htmlPage.toString());
        return htmlPage.toString();
    }

    private String getTagName() {
        if (tagName == null && child != null) {
            return child.getTagName();
        } else if (tagName == null)
            throw new RuntimeException("Unexpected noname tag with no children");
        return tagName;
    }

    private String clearFromBrackets(String tag) {
        return tag.replaceAll("<", "")
                .replaceAll(">", "")
                .replaceAll("/", "");
    }
}

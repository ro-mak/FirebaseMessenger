package ru.makproductions.firebasemessenger.homework;

import android.support.annotation.NonNull;

import java.util.Iterator;

public class StringToIterableAdapter implements Iterable<String> {
    public String[] getStrings() {
        return strings;
    }

    public String getString(int position) {
        if (position >= strings.length) throw new RuntimeException("Position out of bounds");
        return strings[position];
    }

    private String[] strings;

    public StringToIterableAdapter(String... strings) {
        this.strings = strings;
    }

    private Iterator<String> iterator = new Iterator<String>() {
        int position = -1;

        @Override
        public boolean hasNext() {
            if (position + 1 >= strings.length) {
                position = -1;
                return false;
            } else {
                return true;
            }
        }

        @Override
        public String next() {
            position++;
            return strings[position];
        }
    };

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return iterator;
    }
}

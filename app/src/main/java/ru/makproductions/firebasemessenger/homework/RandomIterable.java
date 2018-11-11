package ru.makproductions.firebasemessenger.homework;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.Random;

/**
 * Адаптер уровня объекта
 * <p>
 * использует композицию
 */
public class RandomIterable implements Iterable<Integer> {

    private Random random;
    private int numberOfNumbers;

    public RandomIterable(Random random, int numberOfNumbers) {
        this.random = random;
        this.numberOfNumbers = numberOfNumbers;
    }

    // Here is factory method pattern
    @NonNull
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int position = 0;

            @Override
            public boolean hasNext() {
                if (position >= numberOfNumbers) {
                    position = 0;
                    return false;
                }
                return true;
            }

            @Override
            public Integer next() {
                position++;
                return random.nextInt();
            }
        };
    }
}

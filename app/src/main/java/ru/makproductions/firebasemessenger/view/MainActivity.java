package ru.makproductions.firebasemessenger.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Iterator;
import java.util.Random;

import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.homework.Builder;
import ru.makproductions.firebasemessenger.homework.RandomIterable;
import ru.makproductions.firebasemessenger.homework.SingletoneProxyHTMLBuilder;
import ru.makproductions.firebasemessenger.homework.StringToIterableAdapter;
import ru.makproductions.firebasemessenger.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User petya = new User();
        petya.setName("Petya");
        petya.setSurname("Fedorov");
        //TASK 2 and 4 HTMLBuilder and Proxy

        Builder htmlBuilder = new SingletoneProxyHTMLBuilder();
        String status = htmlBuilder.openTag("<html>")
                .openTag("head").closeTag("head")
                .openTag("<body>").addText("Hello!").closeTag("<body/>")
                .closeTag("html").build();
        petya.setStatus(status);
        binding.setUser(petya);
        //TASK 3.3 Адаптер для String
        String[] testStrings = {"Hi", "Hello", "Hohoho"};

        for (String string : testStrings) {
            Log.e("WithoutAdapter", string);
        }
        StringToIterableAdapter strings = new StringToIterableAdapter(testStrings);
        for (String string : strings) {
            Log.e("TestingAdapter", string);
        }
        Iterator<String> stringIterator = strings.iterator();
        while (stringIterator.hasNext()) {
            Log.e("WhileHasNext", stringIterator.next());
        }
        StringToIterableAdapter[] individualStrings
                = {new StringToIterableAdapter("Hi"),
                new StringToIterableAdapter("Hello"),
                new StringToIterableAdapter("Hohoho")};
        for (StringToIterableAdapter string : individualStrings) {
            Log.e("IndAdapters", string.getString(0));
        }
        //TASK 3
        RandomIterable randomIterable = new RandomIterable(new Random(), 5);
        for (Integer integer : randomIterable) {
            Log.e("RandomIterable", integer + "");
        }

    }
}

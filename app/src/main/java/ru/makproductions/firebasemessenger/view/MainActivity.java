package ru.makproductions.firebasemessenger.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Iterator;
import java.util.Random;

import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.homework.RandomIterable;
import ru.makproductions.firebasemessenger.homework.StringToIterableAdapter;
import ru.makproductions.firebasemessenger.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this, binding);
        binding.setViewmodel(mainActivityViewModel);
        mainActivityViewModel.onCreate();

        //region oldhomework
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
        //endregion homework
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityViewModel.onResume();
    }
}

package ru.makproductions.firebasemessenger.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    @Component(modules = {MainActivityViewModel.class})
    public interface MainActivityViewModelCreator{
            MainActivityViewModel createViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = DaggerMainActivity_MainActivityViewModelCreator.builder().build().createViewModel();
        mainActivityViewModel.setActivity(this);
        mainActivityViewModel.setBinding(binding);
        binding.setViewmodel(mainActivityViewModel);
        mainActivityViewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityViewModel.onResume();
    }
}

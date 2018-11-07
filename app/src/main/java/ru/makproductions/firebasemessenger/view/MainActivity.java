package ru.makproductions.firebasemessenger.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        User petya = new User();
        petya.setName("Petya");
        petya.setSurname("Fedorov");
        petya.setStatus("Offline");
        binding.setUser(petya);
    }
}

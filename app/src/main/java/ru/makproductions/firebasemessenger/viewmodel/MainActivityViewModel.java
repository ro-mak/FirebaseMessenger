package ru.makproductions.firebasemessenger.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.model.DataBaseHelper;
import ru.makproductions.firebasemessenger.model.User;

public class MainActivityViewModel {
    private Activity activity;
    private DataBaseHelper dataBaseHelper;
    private ActivityMainBinding binding;

    public MainActivityViewModel(Activity activity, ActivityMainBinding binding) {
        this.activity = activity;
        this.binding = binding;
        dataBaseHelper = new DataBaseHelper();
    }

    public void onCreate() {
        final User petya = new User();
        petya.setName("Petya");
        petya.setSurname("Fedorov");
//        //TASK 2 and 4 HTMLBuilder and Proxy
//        Builder htmlBuilder = new SingletoneProxyHTMLBuilder();
//        String status = htmlBuilder.openTag("<html>")
//                .openTag("head").closeTag("head")
//                .openTag("<body>").addText("Hello!").closeTag("<body/>")
//                .closeTag("html").build();
//        petya.setStatus(status);
        binding.setUser(petya);

        final EditText messageField = activity.findViewById(R.id.message_text_view);
        final Button submitButton = activity.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petya.setSurname(messageField.getText().toString());
                petya.setMessage(messageField.getText());
            }
        });
    }

    public void onResume() {
        TextView textView = activity.findViewById(R.id.user_name);
        textView.setText(dataBaseHelper.load());
    }

}

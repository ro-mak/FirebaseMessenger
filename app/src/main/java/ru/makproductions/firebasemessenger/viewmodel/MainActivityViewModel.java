package ru.makproductions.firebasemessenger.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.makproductions.firebasemessenger.BR;
import ru.makproductions.firebasemessenger.R;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.model.DataBaseHelper;
import ru.makproductions.firebasemessenger.model.History;
import ru.makproductions.firebasemessenger.model.user.User;

public class MainActivityViewModel extends BaseObservable {
    private Activity activity;
    private DataBaseHelper dataBaseHelper;
    private ActivityMainBinding binding;
    private User user;

    public MainActivityViewModel(Activity activity, ActivityMainBinding binding) {

        this.activity = activity;
        this.binding = binding;
        dataBaseHelper = new DataBaseHelper();
    }

    public void onCreate() {
        user = new User();
        user.setName("Petya");
        user.setSurname("Fedorov");
        final EditText messageField = activity.findViewById(R.id.message_text_view);
        final Button submitButton = activity.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSurname(messageField.getText().toString());
                setMessage(messageField.getText());
                Log.e("MAVM", "onClick: " + messageField.getText());
            }
        });
    }

    public void onResume() {
        TextView textView = activity.findViewById(R.id.user_name);
        textView.setText(dataBaseHelper.load());
    }

    @Bindable
    public Editable getMessage() {
        return user.getMessage();
    }

    public void setMessage(Editable message) {
        user.setMessage(message);
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public History getHistory(){
        return user.getHistory();
    }

    public void setHistory(History history) {
        user.setHistory(history);
        notifyPropertyChanged(BR.history);
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSurname() {
        return user.getSurname();
    }

    public void setSurname(String surname) {
        user.setSurname(surname);
        notifyPropertyChanged(BR.surname);
    }

    @Bindable
    public String getStatus() {
        return user.getStatus();
    }

    public void setStatus(String status) {
        user.setStatus(status);
        notifyPropertyChanged(BR.status);
    }
}

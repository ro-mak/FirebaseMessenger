package ru.makproductions.firebasemessenger.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import ru.makproductions.firebasemessenger.BR;
import ru.makproductions.firebasemessenger.databinding.ActivityMainBinding;
import ru.makproductions.firebasemessenger.model.DataBaseHelper;
import ru.makproductions.firebasemessenger.model.History;
import ru.makproductions.firebasemessenger.model.user.User;
import ru.makproductions.firebasemessenger.model.user.UserBuilder;
import ru.makproductions.firebasemessenger.model.user.UserRepository;
import ru.makproductions.firebasemessenger.model.user.UserUnitOfWork;

public class MainActivityViewModel extends BaseObservable implements LifecycleOwner {
    private Activity activity;
    private DataBaseHelper dataBaseHelper;
    private ActivityMainBinding binding;
    private User user;
    private MutableLiveData<User> userMLD;
    private UserRepository userRepository;
    private LifecycleRegistry lifecycleRegistry;

    @Inject
    public MainActivityViewModel(Activity activity, ActivityMainBinding binding) {
        this.activity = activity;
        this.binding = binding;
        dataBaseHelper = new DataBaseHelper();
    }

    public void onCreate() {
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
        UserUnitOfWork.init(activity);
        userMLD = new MutableLiveData<User>();
        userMLD.setValue(new UserBuilder()
                .withName("Petya")
                .withSurname("Fedorov")
                .build());
        userRepository = UserUnitOfWork.getInstance();
        userRepository.addUser(userMLD);
        UserUnitOfWork.getInstance().commit();
        user = userRepository.getUser(1).getValue();
        final EditText messageField = binding.messageTextView;
        final Button submitButton = binding.submitButton;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSurname(messageField.getText().toString());
                setMessage(messageField.getText());
                Log.e("MAVM", "onClick: " + messageField.getText());
            }
        });

        final Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.e("MainViewModel", "onChange: " + user.getName());
                MainActivityViewModel.this.user = user;
            }
        };
        userMLD.observe(this, userObserver);
    }

    public void onResume() {
        Log.e("MainViewModel", "onResume: " + userMLD.getValue().getName());
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
        if (userMLD != null && userMLD.getValue() != null) {
            User user1 = userMLD.getValue();
            user1.setName(dataBaseHelper.load());
            userMLD.setValue(user1);
        }
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
    public History getHistory() {
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

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}

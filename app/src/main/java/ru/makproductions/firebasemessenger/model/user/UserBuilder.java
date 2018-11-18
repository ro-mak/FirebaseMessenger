package ru.makproductions.firebasemessenger.model.user;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withSurname(String surname) {
        user.setName(surname);
        return this;
    }

    public UserBuilder withStatus(String status) {
        user.setName(status);
        return this;
    }

    public UserBuilder withId(long id) {
        user.setUserId(id);
        return this;
    }

    public User build() {
        if (user != null && user.getName() != null && user.getSurname() != null && user.getStatus() != null) {
            return user;
        } else {
            throw new RuntimeException("User parameters haven't been set");
        }
    }
}

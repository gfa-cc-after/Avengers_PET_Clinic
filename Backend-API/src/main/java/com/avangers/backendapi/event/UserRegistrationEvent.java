package com.avangers.backendapi.event;

import com.avangers.backendapi.models.User;
import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2685172945219633123L;
    private User user;

    public UserRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

package com.avangers.backendapi.event;

import com.avangers.backendapi.models.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

public class UserRegistrationEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -2685172945219633123L;
    private final User user;

    public UserRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

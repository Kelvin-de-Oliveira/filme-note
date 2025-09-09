package com.kelvin.filme_note.dto.user;
import com.kelvin.filme_note.domain.model.User;
import java.util.UUID;

public  class UserResponse {
    public UUID id;
    public String name;
    public String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

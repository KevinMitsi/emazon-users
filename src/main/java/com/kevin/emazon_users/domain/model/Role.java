package com.kevin.emazon_users.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
    private String id;
    private String name;
    private String description;

    public Role() {//not yet
        //
    }

    public Role(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

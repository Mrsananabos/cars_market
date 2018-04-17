package ru.job4j.Collections.Generic;

public class Role extends Base {

    protected Role(String id) {
        super(id);
    }


    @Override
    public String toString() {
        return "Role{" + "id: " + getId() + "}";
    }
}

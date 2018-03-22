package ru.job4j.Collections.Generic;

import static javafx.scene.input.KeyCode.T;

public abstract class AbstractStore<T extends Base> {

    public abstract int searchIndexById(String id);

    public abstract T getObject(int index);



}




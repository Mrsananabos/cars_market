package ru.job4j.Collections.Set;
import ru.job4j.Collections.List.DynamicContainer;

public class SimpleSet<T> extends DynamicContainer<T>{

        public SimpleSet(int size) {
            super(size);
        }

        public void add(T value) {
            for (int i = 0; i<super.index;i++){
                if (value.equals(super.container[i])) {
                    return;
                }
            }
            super.add(value);
        }

    }



package ru.job4j.Collections.List;

import java.util.Iterator;

public class A {

    protected void print() {
        System.out.println("бббббрррр");
    }

    public class B extends A {

        public void ss() {
            super.print();
        }

        public void print() {
            {
                System.out.println("бббббрзззззр");
            }
        }


    }

}

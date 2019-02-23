package ru.job4j.threads;

public class OplimisticException extends RuntimeException {
    public OplimisticException(String msg) {
        super(msg);
    }
}

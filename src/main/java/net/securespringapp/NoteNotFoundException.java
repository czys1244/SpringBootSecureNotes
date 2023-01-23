package net.securespringapp;

public class NoteNotFoundException extends Throwable {
    public NoteNotFoundException(String message) {
        super(message);
    }
}

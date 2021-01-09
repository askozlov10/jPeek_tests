package org.jpeek.patterns.example4;

public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}

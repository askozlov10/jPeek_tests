package org.jpeek.patterns.example4;

public class Button {
//    private Fan fan;
//
//    public void press() {
//        if (fan.isOn()) {
//            fan.turnOff();
//        } else {
//            fan.turnOn();
//        }
//    }

    private Mediator mediator;

    public void press() {
        mediator.press();
    }
}

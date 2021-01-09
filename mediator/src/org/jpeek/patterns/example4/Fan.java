package org.jpeek.patterns.example4;

public class Fan {
//    private Button button;
//    private PowerSupplier powerSupplier;
//    private boolean isOn = false;
//
//    // constructor, getters and setters
//
//    public void turnOn() {
//        powerSupplier.turnOn();
//        isOn = true;
//    }
//
//    public void turnOff() {
//        isOn = false;
//        powerSupplier.turnOff();
//    }
//
//    public boolean isOn() {
//        return isOn;
//    }

    private Mediator mediator;
    private boolean isOn = false;

    // constructor, getters and setters

    public void turnOn() {
        mediator.start();
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
        mediator.stop();
    }

    public boolean isOn() {
        return isOn;
    }

}

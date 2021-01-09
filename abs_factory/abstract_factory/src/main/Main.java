package main;

interface Button {}
interface Select {}
interface TextField {}

interface GUIFactory {

    Button createButton();
    TextField createTextField();
    Select createSelect();

}

class WindowsButton implements Button {
}

class WindowsSelect implements Select {
}

class WindowsTextField implements TextField {
}

class MacButton implements Button {
}

class MacSelect implements Select {
}

class MacTextField implements TextField {
}

class WindowsGUIFactory implements GUIFactory {
    public WindowsGUIFactory() {
        System.out.println("Creating gui factory for Windows OS");
    }

    public Button createButton() {
        System.out.println("Creating Button for Windows OS");
        return new WindowsButton();
    }

    public TextField createTextField() {
        System.out.println("Creating TextField for Windows OS");
        return new WindowsTextField();
    }

    public Select createSelect() {
        System.out.println("Creating Select for Windows OS");
        return new WindowsSelect();
    }
}

class MacGUIFactory implements GUIFactory {
    public MacGUIFactory() {
        System.out.println("Creating gui factory for macOS");
    }

    @Override
    public Button createButton() {
        System.out.println("Creating Button for macOS");
        return new MacButton();
    }

    @Override
    public TextField createTextField() {
        System.out.println("Creating TextField for macOS");
        return new MacTextField();
    }

    @Override
    public Select createSelect() {
        System.out.println("Creating Select for macOS");
        return new MacSelect();
    }
}

class OrderCoffeeForm {
    private final TextField customerNameTextField;
    private final Select coffeTypeSelect;
    private final Button orderButton;

    public OrderCoffeeForm(GUIFactory factory) {
        System.out.println("Creating order coffee form");
        customerNameTextField = factory.createTextField();
        coffeTypeSelect = factory.createSelect();
        orderButton = factory.createButton();
    }
}

public class Main {

    private OrderCoffeeForm orderCoffeeForm;

    public void drawOrderCoffeeForm() {

// Определим имя операционной системы, получив значение системной проперти через System.getProperty

        String osName = System.getProperty("os.name").toLowerCase();
        GUIFactory guiFactory;

        if (osName.startsWith("win")) {
// Для windows

            guiFactory = new WindowsGUIFactory();
        } else if (osName.startsWith("mac")) {
// Для mac

            guiFactory = new MacGUIFactory();
        } else {
            System.out.println("Unknown OS, can't draw form :( ");
            return;
        }
        orderCoffeeForm = new OrderCoffeeForm(guiFactory);
    }

    public static void main(String[] args) {
	// write your code here
        Main application = new Main();
        application.drawOrderCoffeeForm();
    }
}

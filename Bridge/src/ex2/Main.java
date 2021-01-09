package ex2;

public class Main {

    public static void main(String[] args) {
        Shape square = new Square(new Blue());
        square.draw();
    }
}

interface Color {
    String fill();
}

class Blue implements Color {
    @Override
    public String fill() {
        return "Color is Blue";
    }
}

abstract class Shape {
    protected Color color = new Color() {
        @Override
        public String fill() {
            return "Color is Blue";
        }
    };

    public Shape(Color color) {

    }

    //standard constructors

    abstract public String draw();
}

class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return "Square drawn. " + color.fill();
    }
}


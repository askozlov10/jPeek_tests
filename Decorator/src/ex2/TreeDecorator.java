package ex2;

public abstract class TreeDecorator implements ChristmasTree {
    private ChristmasTree tree;

    public TreeDecorator(ChristmasTree tree1) {
        tree = tree1;
    }

    // standard constructors
    @Override
    public String decorate() {
        return tree.decorate();
    }
}
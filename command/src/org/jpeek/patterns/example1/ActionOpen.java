package org.jpeek.patterns.example1;

public class ActionOpen implements ActionListenerCommand {
    private Document doc;

    public ActionOpen(Document doc) {
        this.doc = doc;
    }

    @Override
    public void execute() {
        doc.open();
    }
}

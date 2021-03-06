package org.jpeek.patterns.example1;

public class MenuOptions {

    private ActionListenerCommand openCommand;
    private ActionListenerCommand saveCommand;

    public MenuOptions(ActionListenerCommand clickOpen, ActionListenerCommand clickSave) {
        this.openCommand = clickOpen;
        this.saveCommand = clickSave;
    }

    public void clickSave() {
        saveCommand.execute();
    }

    public void clickOpen() {
        openCommand.execute();
    }
}

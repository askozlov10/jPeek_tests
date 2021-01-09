package org.jpeek.patterns.example2;

public class FileInvoker {
    public Command command;

    public FileInvoker(Command c) {
        this.command = c;
    }

    public void execute() {
        this.command.execute();
    }
}

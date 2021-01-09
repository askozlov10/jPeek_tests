package org.jpeek.patterns.example2;

public interface ChatMediator {
    public void sendMessage(String msg, User user);

    void addUser(User user);
}

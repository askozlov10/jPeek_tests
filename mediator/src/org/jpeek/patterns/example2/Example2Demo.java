package org.jpeek.patterns.example2;

public class Example2Demo {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Parker");
        User user2 = new UserImpl(mediator, "Lisa");
        User user3 = new UserImpl(mediator, "Joseph");
        User user4 = new UserImpl(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }
}

package org.jpeek.patterns.example1;

public class Example1Demo {
    public static void main(String[] args) {

        ChatRoom chat = new ChatRoomImpl();

        User1 u1 = new User1(chat);
        u1.setName("Test User");
        u1.sendMsg("Hi Test User! how are you?");


        User2 u2 = new User2(chat);
        u2.setName("Admin USer");
        u2.sendMsg("I am Fine ! You tell?");
    }
}

package org.jpeek.patterns.example2;

public class IteratorDemo {
    public static void main(String[] args) {
        CollectionOfNames cmpnyRepository = new CollectionOfNames();

        for (Iterator iter = cmpnyRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}

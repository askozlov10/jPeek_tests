package org.jpeek.patterns.example2;

public class CollectionOfNames implements Container {

    @Override
    public Iterator getIterator() {
        return new CollectionOfNamesIterate();
    }
}

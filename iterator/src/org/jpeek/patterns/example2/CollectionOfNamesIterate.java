package org.jpeek.patterns.example2;

class CollectionOfNamesIterate implements Iterator {
    int i;
    String[] name = {"Ashwani Rajput", "Soono Jaiswal", "Rishi Kumar", "Rahul Mehta", "Hemant Mishra"};

    @Override
    public boolean hasNext() {
        if (i < name.length) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return name[i++];
        }
        return null;
    }
}

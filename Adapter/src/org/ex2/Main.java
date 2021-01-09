package org.ex2;

public class Main {

    public static void main(String[] args) {
        RandomGeneratorAdapter adapter = new RandomGeneratorAdapter();
        SequenceGenerator generator = new SequenceGenerator(adapter);

        for (int i: generator.generate(10)) {
            System.out.print(i + " ");
        }
    }
}

interface Generator {

    public int next();

}

class SequenceGenerator {

    private Generator generator;

    public SequenceGenerator(Generator generator) {
        super();
        this.generator = generator;
    }

    public int[] generate(int length) {
        int ret[] = new int[length];

        for (int i=0; i<length; i++) {
            ret[i] = generator.next();
        }

        return ret;
    }
}

class RandomGenerator {

    public int getRandomNumber() {
        return 4;
    }
}

class RandomGeneratorAdapter extends RandomGenerator implements Generator {

    @Override
    public int next() {
        return getRandomNumber();
    }

}

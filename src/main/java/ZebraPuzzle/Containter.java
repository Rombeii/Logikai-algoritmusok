package ZebraPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Containter {

    private List<Literal> literals;

    public Containter() {
        initLiterals();
    }

    private void initLiterals() {
        literals = new ArrayList<>();
        int counter = 1;
        for (int houseNumber = 1; houseNumber < 6; houseNumber++) {
             for (Attribute attribute : Attribute.values()) {
                literals.add(new Literal(houseNumber, attribute, counter));
                counter++;
             }
        }
    }

    public int getIndexOfLiteral(int houseNumber, Attribute attribute) {
        return literals.stream()
                .filter(e -> e.houseNumber == houseNumber)
                .filter(e -> e.attribute.equals(attribute))
                .map(e -> e.index)
                .findFirst()
                .get();

    }

    public String indexToString(int index) {
        Literal literal = literals.stream().filter(x -> x.index == index).findFirst().get();
        return literal.houseNumber + ". House - " + literal.attribute;
    }

    private static class Literal {
        private final int houseNumber;
        private final Attribute attribute;
        private final int index;

        public Literal(int houseNumber, Attribute attribute, int index) {
            this.houseNumber = houseNumber;
            this.attribute = attribute;
            this.index = index;
        }
    }


}

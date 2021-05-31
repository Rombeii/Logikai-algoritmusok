package ZebraPuzzle;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.*;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.EnumSet;

//https://en.wikipedia.org/wiki/Zebra_Puzzle
public class ZebraPuzzle {

    public static void main(String[] args) throws ContradictionException, TimeoutException {
        ISolver solver = SolverFactory.newDefault();
        Containter containter = new Containter();

        solver.newVar(125);

        //2. The Englishman lives in the red house.
        solver.addAllClauses(implication(Attribute.ENGLISHMAN, Attribute.RED, containter));

        //3. The Spaniard owns the dog.
        solver.addAllClauses(implication(Attribute.SPANIARD, Attribute.DOG, containter));

        //4. Coffee is drunk in the green house.
        solver.addAllClauses(implication(Attribute.GREEN, Attribute.COFFEE, containter));

        //5. The Ukrainian drinks tea.
        solver.addAllClauses(implication(Attribute.UKRANIAN, Attribute.TEA, containter));

        //6. The green house is immediately to the right of the ivory house.
            //first house is not green
        VecInt c1 = new VecInt();
        c1.push(-containter.getIndexOfLiteral(1, Attribute.GREEN));
        solver.addClause(c1);
            //last house is not ivory
        VecInt c2 = new VecInt();
        c2.push(-containter.getIndexOfLiteral(5, Attribute.IVORY));
        solver.addClause(c2);
            //x = ivory, x + 1 = green
        for (int houseNumber = 1; houseNumber < 5; houseNumber++) {
            VecInt clause = new VecInt();
            clause.push(-containter.getIndexOfLiteral(houseNumber, Attribute.IVORY));
            clause.push(containter.getIndexOfLiteral(houseNumber + 1, Attribute.GREEN));
            solver.addClause(clause);
        }

        //7. The Old Gold smoker owns snails.
        solver.addAllClauses(implication(Attribute.OLD_GOLD, Attribute.SNAIL, containter));

        //8. Kools are smoked in the yellow house
        solver.addAllClauses(implication(Attribute.YELLOW, Attribute.KOOLS, containter));

        //9. Milk is drunk in the middle house.
        VecInt c3 = new VecInt();
        c3.push(containter.getIndexOfLiteral(3, Attribute.MILK));
        solver.addClause(c3);

        //10. The Norwegian lives in the first house.
        VecInt c4 = new VecInt();
        c4.push(containter.getIndexOfLiteral(1, Attribute.NORWEGIAN));
        solver.addClause(c4);

        //11. The man who smokes Chesterfields lives in the house next to the man with the fox.
        solver.addAllClauses(neighbour(Attribute.FOX, Attribute.CHESTERFIELD, containter));

        //12. Kools are smoked in the house next to the house where the horse is kept.
        solver.addAllClauses(neighbour(Attribute.HORSE, Attribute.KOOLS, containter));

        //13. The Lucky Strike smoker drinks orange juice.
        solver.addAllClauses(implication(Attribute.LUCKY_STRIKE, Attribute.ORANGE_JUICE, containter));

        //14. The Japanese smokes Parliaments.
        solver.addAllClauses(implication(Attribute.JAPANESE, Attribute.PARLIAMENT, containter));

        //15. The Norwegian lives next to the blue house.
        solver.addAllClauses(neighbour(Attribute.BLUE, Attribute.NORWEGIAN, containter));

        Object[] combinations = Sets.combinations(ImmutableSet.of(1, 2, 3, 4, 5), 2).toArray();

        //Each attribute has to appear at least once
        solver.addAllClauses(atLeastOne(Attribute.nationalities, containter));
        solver.addAllClauses(atLeastOne(Attribute.colors, containter));
        solver.addAllClauses(atLeastOne(Attribute.drinks, containter));
        solver.addAllClauses(atLeastOne(Attribute.pets, containter));
        solver.addAllClauses(atLeastOne(Attribute.smokes, containter));

        //Ex.: If you test two houses if they are RED, at least one of them needs to be false
        for (Attribute nationality : Attribute.nationalities) {
            solver.addAllClauses(maxOneAttributeEach(nationality, containter, combinations));
        }
        for (Attribute color : Attribute.colors) {
            solver.addAllClauses(maxOneAttributeEach(color, containter, combinations));
        }
        for (Attribute drink : Attribute.drinks) {
            solver.addAllClauses(maxOneAttributeEach(drink, containter, combinations));
        }
        for (Attribute pet : Attribute.pets) {
            solver.addAllClauses(maxOneAttributeEach(pet, containter, combinations));
        }
        for (Attribute a : Attribute.smokes) {
            solver.addAllClauses(maxOneAttributeEach(a, containter, combinations));
        }

        //Ex.: If you test a house if it is RED or GREEN at least one of them needs to be false
        for (int houseNumber = 1; houseNumber < 6; houseNumber++) {
            solver.addAllClauses(noConflictingAttributes(Attribute.nationalities, containter, combinations, houseNumber));
            solver.addAllClauses(noConflictingAttributes(Attribute.colors, containter, combinations, houseNumber));
            solver.addAllClauses(noConflictingAttributes(Attribute.drinks, containter, combinations, houseNumber));
            solver.addAllClauses(noConflictingAttributes(Attribute.pets, containter, combinations, houseNumber));
            solver.addAllClauses(noConflictingAttributes(Attribute.smokes, containter, combinations, houseNumber));
        }

        System.out.println(solver.isSatisfiable());
        Arrays.stream(solver.model()).filter(a -> a > 0).forEach(x -> System.out.println(containter.indexToString(x)));
    }

    private static IVec implication(Attribute a1, Attribute a2, Containter containter) {
        IVec clauses = new Vec();
        for (int houseNumber = 1; houseNumber < 6; houseNumber++) {
            VecInt clause = new VecInt();
            clause.push(-containter.getIndexOfLiteral(houseNumber, a1));
            clause.push(containter.getIndexOfLiteral(houseNumber, a2));
            clauses.push(clause);
        }
        return clauses;
    }

    private static IVec neighbour(Attribute a1, Attribute a2, Containter containter) {
        IVec clauses = new Vec();
        for (int houseNumber = 2; houseNumber < 5; houseNumber++) {
            VecInt clause = new VecInt();
            clause.push(-containter.getIndexOfLiteral(houseNumber, a1));
            clause.push(containter.getIndexOfLiteral(houseNumber - 1, a2));
            clause.push(containter.getIndexOfLiteral(houseNumber + 1, a2));
            clauses.push(clause);
        }


        VecInt c1 = new VecInt();
        c1.push(-containter.getIndexOfLiteral(1, a1));
        c1.push(containter.getIndexOfLiteral(2, a2));
        clauses.push(c1);

        VecInt c2 = new VecInt();
        c2.push(-containter.getIndexOfLiteral(5, a1));
        c2.push(containter.getIndexOfLiteral(4, a2));
        clauses.push(c2);

        return clauses;
    }

    private static IVec maxOneAttributeEach(Attribute attribute, Containter containter, Object[] combinations) {
        IVec clauses = new Vec();
        for (Object combination : combinations) {
            VecInt clause = new VecInt();
            Object[] asd = ((AbstractSet) combination).toArray();
            clause.push(-containter.getIndexOfLiteral((Integer) asd[0], attribute));
            clause.push(-containter.getIndexOfLiteral((Integer) asd[1], attribute));
            clauses.push(clause);
        }

        return clauses;
    }


    private static IVec noConflictingAttributes(EnumSet<Attribute> attributes, Containter containter,
                                                Object[] combinations, int houseNumber) {
        IVec clauses = new Vec();
        for (Object combination : combinations) {
            VecInt clause = new VecInt();
            Object[] asd = ((AbstractSet) combination).toArray();
            clause.push(-containter.getIndexOfLiteral(houseNumber, (Attribute) attributes.toArray()[(Integer) asd[0] - 1]));
            clause.push(-containter.getIndexOfLiteral(houseNumber, (Attribute) attributes.toArray()[(Integer) asd[1] - 1]));
            clauses.push(clause);
        }

        return clauses;
    }

    private static IVec atLeastOne(EnumSet<Attribute> attributes, Containter containter) {
        IVec clauses = new Vec();
        for (Attribute attribute : attributes) {
            VecInt clause = new VecInt();
            clause.push(containter.getIndexOfLiteral(1, attribute));
            clause.push(containter.getIndexOfLiteral(2, attribute));
            clause.push(containter.getIndexOfLiteral(3, attribute));
            clause.push(containter.getIndexOfLiteral(4, attribute));
            clause.push(containter.getIndexOfLiteral(5, attribute));
            clauses.push(clause);
        }
        return clauses;
    }
}

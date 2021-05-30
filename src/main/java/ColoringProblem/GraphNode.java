package ColoringProblem;

import com.github.javabdd.BDD;
import com.github.javabdd.BDDFactory;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    List<BDD> color;

    public GraphNode(BDDFactory bddFactory, int index) {
        color = new ArrayList<>();
        color.add(bddFactory.ithVar(index));
        color.add(bddFactory.ithVar(index + 1));
        color.add(bddFactory.ithVar(index + 2));
    }

    public BDD isColored() {
        //3 változó xOr-ja így nézne ki: (a ^ b ^ c) && !(a && b && c)
        BDD onlyOneIsColored = color.get(0).xor(color.get(1)).xor(color.get(2));
        BDD notAllTheSame = color.get(0).and(color.get(1)).and(color.get(2)).not();
        return onlyOneIsColored.and(notAllTheSame);
    }

    public void free() {
        color.forEach(BDD::free);
    }
}
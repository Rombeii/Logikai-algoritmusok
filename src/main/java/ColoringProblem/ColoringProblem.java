package ColoringProblem;

import com.github.javabdd.BDD;
import com.github.javabdd.BDDFactory;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ColoringProblem {

    public static void main(String[] args) {
        BDDFactory bddFactory = BDDFactory.init(1000, 1000);
        BDD problem = bddFactory.one();
        bddFactory.setVarNum(12);
        BDD solution;

        Pair<List<GraphNode>, List<GraphEdge>> graph = Util.getSecondSetup(bddFactory);
        List<GraphNode> nodes = graph.getKey();
        List<GraphEdge> edges = graph.getValue();


        //minden Nodenak 1 színe van
        for (GraphNode node : nodes) {
            problem.andWith(node.isColored());
        }

        //ha két Node között van él, akkor nem lehetnek azonos színűek
        for (GraphEdge edge : edges) {
            problem.andWith(edge.colorDiffers());
        }

        double result = problem.satCount();
        System.out.println("There are " + (long) result + " solutions.");

        solution = problem.satOne();
        solution.printSet();

        bddFactory.done();

        solution.free();
        problem.free();
        nodes.forEach(GraphNode::free);
    }
}

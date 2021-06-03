package ColoringProblem;

import com.github.javabdd.BDDFactory;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Util {

    //https://slideplayer.hu/slide/12308167/
    public static Pair<List<GraphNode>, List<GraphEdge>> getFirstSetup(BDDFactory bddFactory) {
        List<GraphNode> nodes = new ArrayList<>();

        GraphNode s0 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s0);
        GraphNode s1 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s1);
        GraphNode s2 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s2);
        GraphNode s3 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s3);
        GraphNode s4 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s4);


        List<GraphEdge> edges = new ArrayList<>();
        edges.add(new GraphEdge(s0, s1));
        edges.add(new GraphEdge(s0, s2));
        edges.add(new GraphEdge(s0, s3));

        edges.add(new GraphEdge(s1, s2));
        edges.add(new GraphEdge(s1, s3));

        edges.add(new GraphEdge(s2, s4));

        edges.add(new GraphEdge(s3, s4));

        return new ImmutablePair<>(nodes, edges);
    }

    //https://hu.wikipedia.org/wiki/Gráfok_színezése#/media/Fájl:Graph_with_all_three-colourings_2.svg
    public static Pair<List<GraphNode>, List<GraphEdge>> getSecondSetup(BDDFactory bddFactory) {
        List<GraphNode> nodes = new ArrayList<>();

        GraphNode s0 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s0);
        GraphNode s1 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s1);
        GraphNode s2 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s2);
        GraphNode s3 = new GraphNode(bddFactory, nodes.size() * 3);
        nodes.add(s3);

        List<GraphEdge> edges = new ArrayList<>();

        edges.add(new GraphEdge(s0, s1));
        edges.add(new GraphEdge(s0, s2));

        edges.add(new GraphEdge(s1, s2));

        edges.add(new GraphEdge(s2, s3));

        return new ImmutablePair<>(nodes, edges);
    }
}

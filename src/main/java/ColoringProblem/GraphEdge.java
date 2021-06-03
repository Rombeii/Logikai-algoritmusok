package ColoringProblem;

import com.github.javabdd.BDD;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class GraphEdge {
    Pair<GraphNode, GraphNode> nodes;

    public GraphEdge(GraphNode n1, GraphNode n2) {
        nodes = new ImmutablePair<>(n1, n2);
    }

    public GraphNode getLeft() {
        return nodes.getKey();
    }

    public GraphNode getRight() {
        return nodes.getValue();
    }

    public BDD colorDiffers() {
        BDD firstColorDiffers = getLeft().color.get(0).and(getRight().color.get(0)).not();
        BDD secondColorDiffers = getLeft().color.get(1).and(getRight().color.get(1)).not();
        BDD thirdColorDiffers = getLeft().color.get(2).and(getRight().color.get(2)).not();

        return firstColorDiffers.and(secondColorDiffers).and(thirdColorDiffers);
    }
}

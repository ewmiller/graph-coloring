public class GraphNode {
  int id;
  GraphNode parent;
  List<GraphNode> children;

  public GraphNode(int id, GraphNode parent, List<GraphNode> children){
    this.id = id;
    this.parent = parent;
    this.children = children;
  }
}

import java.io.*;
import java.util.*;

public class GraphColoring {

  // private variables to manage DFS process
  private boolean is2C;
  private int vertices;
  private List<List<Integer>> graph;
  private boolean[] colors;
  private boolean[] visited;
  private int[] parent;
  private Stack<Integer> cycle;

  // constructor initializes variables
  public GraphColoring(List<List<Integer>> graph){
    this.graph = graph;
    this.vertices = graph.size();
    this.colors = new boolean[vertices + 1]; // zero index will not be used
    this.visited = new boolean[vertices + 1]; // same
    this.parent = new int[vertices + 1]; // same
    this.is2C = true;
  }

  // uses a DFS to determine if graph is two-colorable
  public boolean isTwoColorable(){
    //for every vertex, if it hasn't been visited, do a DFS from it
    for(int i = 0; i < graph.size(); i++){
      if(!visited[i]){
        // try to see if this can be done synchronously
        // wait for dfs call to return before iterating again?
        dfs(graph, i);
      }
    }
    System.out.println(Arrays.toString(parent));
    return is2C;
  }

  private void dfs(List<List<Integer>> graph, int v){
    //set that v has been visited
    visited[v] = true;

    // use adjacency list to find adjacent vertices
    for(int i = 0; i < graph.get(v).size(); i ++){
      // assign reference to the next vertex that v is adjacent to
      int w = graph.get(v).get(i);
      // stops the process if necessary
      if(cycle != null){
        return;
      }
      // 12/1: there must be something wrong with this
      // if adjacent vertex hasn't been visited, recurse
      if(!visited[w]){
        parent[w] = v;
        colors[w] = !colors[v];
        dfs(graph, w);
      }
      // if a neighboring vertex has same color, graph is not two-colorable
      else if (colors[w] == colors[v]) {
        is2C = false;
        cycle = new Stack<Integer>();
        cycle.push(w);

        //for some reason, two nodes with same color both have 0 as a parent,
        //but they're adjacent to each other. This shouldn't be possible.
        //is the iterative visiting process outpacing the dfs?
        for (int x = v; x != w; x = parent[x]) {
          if(x == 0){
            System.out.println("Vertex " + x + " has no parent.");
            break;
          }
          System.out.println("Adding " + x + " to stack.");
          cycle.push(x);
        }
        cycle.push(v);
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<List<Integer>>();
    graph = scanGraph(args[0]);
    GraphColoring gc = new GraphColoring(graph);
    System.out.println(gc.isTwoColorable());
  }

  // scans graph file in and converts it to an adjacency list
  private static List<List<Integer>> scanGraph(String arg){

    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<String> scanned = new ArrayList<String>();
    String filePath = "";

    if(arg.equals("1")){
      System.out.println("Scanning file: largegraph1");
      filePath = String.format("./src/main/resources/largegraph1");
    }
    else if(arg.equals("2")){
      System.out.println("Scanning file: largegraph2");
      filePath = String.format("./src/main/resources/largegraph2");
    }
    else if(arg.equals("3")){
      System.out.println("Scanning file: smallgraph");
      filePath = String.format("./src/main/resources/smallgraph");
    }
    else {
      System.exit(0);
    }
    try { // file input
      Scanner fileScanner = new Scanner(new File(filePath));
      while(fileScanner.hasNextLine()){
        String str = fileScanner.nextLine();
        scanned.add(str);
      }
    } catch(FileNotFoundException e) {
      e.printStackTrace(System.out);
    }

    //initialize adjacency list with correct number of vertices
    //the 0 index will be empty for ease of reference later
    int v = Integer.parseInt(scanned.get(0));
    System.out.println("Total vertices: " + v);
    for(int i = 0; i <= v; i++){
      res.add(new ArrayList<Integer>());
    }

    for(int i = 1; i < scanned.size(); i++){ // for each line of the file
      String[] line = scanned.get(i).split(" ");
      int currentVertex = Integer.parseInt(line[0]);
      res.get(currentVertex).add(Integer.parseInt(line[1]));
    }
    return res;
  }
}

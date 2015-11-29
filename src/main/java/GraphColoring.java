import java.io.*;
import java.util.*;

public class GraphColoring {

  public GraphColoring(){}

  public boolean isTwoColorable(List<List<Integer>> graph){
    return true;
  }

  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<List<Integer>>();
    graph = scanGraph(args[0]);
    GraphColoring gc = new GraphColoring();
    System.out.println(gc.isTwoColorable(graph));
  }

  private static List<List<Integer>> scanGraph(String arg){
    // method for scanning graph file in and converting it to an adjacency list

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

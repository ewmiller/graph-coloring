import java.io.*;
import java.util.*;

public class GraphColoring {

  public GraphColoring(){}

  public boolean isTwoColorable(List<List<Integer>> graph){
    return true;
  }

  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<List<Integer>>();

    if(args[0].equals("1")){
      System.out.println("Scanning file: largegraph1");
      graph = scanLG1();
    }
    else if(args[0].equals("2")){
      System.out.println("Scanning file: largegraph2");
      graph = scanLG2();
    }
    else if(args[0].equals("3")){
      System.out.println("Scanning file: smallgraph");
      graph = scanSG();
    }
    else {
      System.exit(0);
    }
    GraphColoring gc = new GraphColoring();
    System.out.println(gc.isTwoColorable(graph));
  }

  private static List<List<Integer>> scanLG1(){
    return new ArrayList<List<Integer>>();
  }
  private static List<List<Integer>> scanLG2(){
    return new ArrayList<List<Integer>>();
  }
  private static List<List<Integer>> scanSG(){
    return new ArrayList<List<Integer>>();
  }
}

package examples.jugs;

import engine.BestFirstEngine;
import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.HillClimbingEngine;

import java.util.List;

public class JugsSearchApp {

    /**
     * Main for Jugs App.
     * @param args contents Jug A and contents Jug B are expected.
     */
    public static void main(String[] args) {
        
        // Para correrlo con datos obtenidos en tiempo de ejecucion
        /*if ((args.length > 2) || (args.length == 0)) {
            System.out.println("*** Usage: java JugsSearchApp <int> <int>");
        } 
        else {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            
            JugsStateProblem sp1 = new JugsStateProblem(a,b);
            
        }*/

        JugsStateProblem sp1 = new JugsStateProblem(0,0);
        
        /*
        * App using BREADTH-FIRST search 
        */ 
        BreadthFirstEngine<JugsState,JugsStateProblem> engineBFS = new BreadthFirstEngine<JugsState,JugsStateProblem>(sp1);
        JugsState successBfS = engineBFS.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Breadth-first search ---------");
        System.out.println("Solution found? " + successBfS.toString());
            
        if (! (successBfS == null)) {
            System.out.print("Path to goal: ");
            List<JugsState> pathBFS = engineBFS.getPath();
            for (int i = 0; i < pathBFS.size(); i++) {
                JugsState current = (JugsState) pathBFS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBFS.report();
            

        /*
        * App using DEPTH-FIRST visited search 
        */ 
        DepthFirstEngine<JugsState,JugsStateProblem> engineDFS = new DepthFirstEngine<JugsState,JugsStateProblem>(sp1);
        JugsState sucessDFS = engineDFS.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Depth-first search ---------");
        System.out.println("Solution found? " + sucessDFS.toString());

        if (! (sucessDFS == null)) {
            System.out.print("Path to goal: ");
            List<JugsState> pathDFS = engineDFS.getPath();
            for (int i = 0; i < pathDFS.size(); i++) {
                JugsState current = (JugsState) pathDFS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineDFS.report();


        /*
        * App using ITERATIVE DEEPENING visited search 
        */ 
        DepthFirstEngine<JugsState,JugsStateProblem> engineIterativeDeep = new DepthFirstEngine<JugsState,JugsStateProblem>(sp1);
        JugsState sucessIterative = engineIterativeDeep.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Iterative Deepening search ---------");
        System.out.println("Solution found? " + sucessIterative.toString());

        if (! (sucessIterative == null)) {
            System.out.print("Path to goal: ");
            List<JugsState> pathIterative = engineIterativeDeep.getPath();
            for (int i = 0; i < pathIterative.size(); i++) {
                JugsState current = (JugsState) pathIterative.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineIterativeDeep.report();


        /*
        * App using BEST-FIRST search
        */ 
        JugsStateProblemInformed sp2 = new JugsStateProblemInformed(0,0);


        BestFirstEngine<JugsStateInformed,JugsStateProblemInformed> engineBestFS = new BestFirstEngine<JugsStateInformed,JugsStateProblemInformed>(sp2);
        JugsStateInformed successBestFS = engineBestFS.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Best-first search ---------");
        System.out.println("Solution found? " + successBestFS.toString());
            
        if (! (successBestFS == null)) {
            System.out.print("Path to goal: ");
            List<JugsStateInformed> pathBestFS = engineBestFS.getPath();
            for (int i = 0; i < pathBestFS.size(); i++) {
                JugsStateInformed current = (JugsStateInformed) pathBestFS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBestFS.report();


        /*
        * App using Hill Climbing search
        */ 
        HillClimbingEngine<JugsStateInformed,JugsStateProblemInformed> engineHillClimbing = new HillClimbingEngine<JugsStateInformed,JugsStateProblemInformed>(sp2);
        JugsStateInformed succesHillClimbing = engineHillClimbing.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Hill Climbing Search ---------");
        if (succesHillClimbing != null){
            System.out.println("Solution found? " + succesHillClimbing.toString());
        } else {
            System.out.println("There was no successful state found with this technique");
        }
            
        if (! (succesHillClimbing == null)) {
            System.out.print("Path to goal: ");
            List<JugsStateInformed> pathHillClimbing = engineHillClimbing.getPath();
            for (int i = 0; i < pathHillClimbing.size(); i++) {
                JugsStateInformed current = (JugsStateInformed) pathHillClimbing.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineHillClimbing.report();


        /*
        * App using Steepest Hill Climbing search
        */ 
        HillClimbingEngine<JugsStateInformed,JugsStateProblemInformed> engineSteepest = new HillClimbingEngine<JugsStateInformed,JugsStateProblemInformed>(sp2);
        JugsStateInformed successSteepest = engineSteepest.performSearch();

        System.out.println();     
        System.out.println("--------- Result using Steepest Hill Climbing Search ---------");
        if (successSteepest != null){
            System.out.println("Solution found? " + successSteepest.toString());
        } else {
            System.out.println("There was no successful state found with this technique");
        }
            
        if (! (successSteepest == null)) {
            System.out.print("Path to goal: ");
            List<JugsStateInformed> pathSteepest = engineSteepest.getPath();
            for (int i = 0; i < pathSteepest.size(); i++) {
                JugsStateInformed current = (JugsStateInformed) pathSteepest.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineSteepest.report();
    } 
}

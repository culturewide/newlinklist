package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    Queue<Integer> queue;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        //queue队列不可以重新初始化，如Queue queue，那么成员变量会被忽略
        queue = new LinkedList<Integer>();
        queue.offer(s);
        distTo[s] = 0;
        edgeTo[s] = s;
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();
        queue.remove();

        if(v == t){
            targetFound = true;
        }

        if(targetFound){
            return;
        }

        for(int w: maze.adj(v)) {
            if(!marked[w]) {
                marked[w] = true;
                queue.offer(w);
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
            }
        }
        bfs(queue.element());

        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
    }


    @Override
    public void solve() {
         bfs(s);
    }
}


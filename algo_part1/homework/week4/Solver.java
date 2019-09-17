import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int hammDist, manhDist;
    private int moveStep;

    private class SearchNode implements Comparable<SearchNode>{
        public final Board board;
        public final SearchNode prev;
        public final int step;
        public final int priority;

        public SearchNode(Board board, SearchNode prev){
            this.board = board;
            this.prev = prev;
            this.step = prev == null ? 0 : prev.step+1;
            this.priority =  board.manhattan() + step;
        }

        public void insertNeighbors(MinPQ<SearchNode> pq){
            for (Board neighbor : board.neighbors()){
                if(prev != null && neighbor.equals(prev.board)) continue;

                SearchNode node = new SearchNode(neighbor, this);
                pq.insert(node);
            }
        }
        @Override
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }

        public
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<SearchNode> solution = new MinPQ<>();
        MinPQ<SearchNode> twinSolution = new MinPQ<>();

        // init
        SearchNode init = new SearchNode(initial,null);
        solution.insert(init);

        SearchNode twinInit = new SearchNode(initial.twin(),null);
        twinSolution.insert(twinInit);


        
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return false;
    }

    // min number of moves to solve initial board
    public int moves(){
        if (isSolvable()){
            return
        }else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){

    }

    // test client (see below)
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}

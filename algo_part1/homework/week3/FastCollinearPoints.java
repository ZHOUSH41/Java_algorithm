import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Quick3way;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] segments = null;
    private int n;
    public FastCollinearPoints(Point[] points){
        if(points == null) throw new IllegalArgumentException();

        int N = points.length;

        Point[] aux = new Point[points.length];
        // 检查每一个元素是否为null
        for (int i = 0; i < N; i++){
            if(points[i] == null)
                throw new IllegalArgumentException();
            aux[i] = points[i];
        }

//        // 检查有没有重复元素
//        for(int i = 0; i < N; i++){
//            for(int j = i+1; j < N; j++){
//                if(points[i].slopeTo(points[j]) == 0){
//                    throw new IllegalArgumentException();
//                }
//            }
//        }

        Arrays.sort(aux);
        //ArrayList<LineSegment> lineSegments = new ArrayList<>();
        // 链表结构
        List<LineSegment> lineSegments = new LinkedList<LineSegment>();
        Point prev = null;

        for (int i = 0; i < N; i++){
            Point p = aux[i];

            // 先找有没有重复元素,因为已经排过序,所以如果有重复元素应该在相邻位置
            if(prev != null && p.compareTo(prev) == 0){
                throw new IllegalArgumentException();
            }else{
                prev = p;
            }

            Point[] slopeOrder = aux.clone();
            Arrays.sort(slopeOrder,p.slopeOrder());


            double lastSlope = Double.NEGATIVE_INFINITY;
            int slopeStartIndex = 0;

            for(int j = 1; j < N; j++){
                Point q = slopeOrder[j];
                double curSlope = p.slopeTo(q);

                boolean isLastPoint = j == N-1;

                if(Double.compare(curSlope,lastSlope) != 0){
                    if(j - slopeStartIndex >= 3){
                        if(p.compareTo(slopeOrder[slopeStartIndex]) <= 0){
                            LineSegment segment = new LineSegment(p, slopeOrder[j-1]);
                            lineSegments.add(segment);
                        }
                    }

                    slopeStartIndex = j;

                }else if (isLastPoint){
                    if(j - slopeStartIndex >= 2){
                        if(p.compareTo(slopeOrder[slopeStartIndex]) <= 0){
                            LineSegment segment = new LineSegment(p,q);
                            lineSegments.add(segment);
                        }
                    }
                }

                lastSlope = curSlope;
            }
        }

        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }     // finds all line segments containing 4 or more points
    public           int numberOfSegments(){
        return segments.length;
    }        // the number of line segments
    public LineSegment[] segments(){
        return segments.clone();
    }                // the line segments
}

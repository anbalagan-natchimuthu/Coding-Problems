package interview.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * K Closest Points to the Origin
 * https://www.youtube.com/watch?v=eaYX0Ee0Kcg.
 */
public class KClosesPoint {

    public static void main(String[] args) {
        KClosesPoint closesPoint = new KClosesPoint();
        int[][] points = { { 1, 0 }, { -1, 0 }, { 2, 0 }, { 5, 3 }, { 7, 4 } };
        int[][] closesKPoints = closesPoint.findClosesPoint(points, new int[] { 0, 0 }, 3);
        System.out.println("********** CLOSES POINTS ***********\n\n" + Arrays.deepToString(closesKPoints));
    }

    private int[][] findClosesPoint(int[][] points, int[] coordinates, int k) {

        // Max Heap
        PriorityQueue<ClosesPoints> closestPointsQueue = new PriorityQueue<>(k + 1, (c1, c2) -> {

            /* If  you don't have access to modify ClosesPoints, use below sorting
            double c1Dist = getDistance(c1, coordinates);
            double c2Dist = getDistance(c2, coordinates);
            if (c1Dist < c2Dist) {
                return 1;
            } else if (c1Dist > c2Dist) {
                return -1;
            } else {
                return 0;
            }*/

            //Reverse Order
            if (c1.dist < c2.dist) {
                return 1;
            } else if (c1.dist > c2.dist) {
                return -1;
            } else {
                return 0;
            }
        });

        for (int[] point : points) {
            double dist = getDistance(point, coordinates);
            if (closestPointsQueue.size() == k) {
                if (dist < closestPointsQueue.peek().dist) {
                    closestPointsQueue.add(new ClosesPoints(dist, point));
                    closestPointsQueue.poll();
                }
            } else {
                closestPointsQueue.add(new ClosesPoints(dist, point));
            }
        }

        /*List<int[]> returnList = new ArrayList<>();
        while (!closestPointsQueue.isEmpty()) {
            returnList.add(closestPointsQueue.poll().points);
        }*/

        int[][] returnList = new int[closestPointsQueue.size()][2];
        int i = 0;
        while (!closestPointsQueue.isEmpty()) {
            returnList[i++] = closestPointsQueue.poll().points;
        }

        return returnList;
    }

    class ClosesPoints {

        double dist;

        int[] points;

        ClosesPoints(double dist, int[] points) {
            this.dist = dist;
            this.points = points;
        }
    }

    private double getDistance(int[] point, int[] coordinates) {
        return Math.pow((point[0] - coordinates[0]), 2) + Math.pow((point[1] - coordinates[1]), 2);
        //We don't need the actual distances, just relative distances.
        // So, skip calculating the square roots (computationally heavy).  just store the sum of  squares as "distance"
        //return Math.sqrt(Math.pow((point[0] - coordinates[0]), 2) + Math.pow((point[1] - coordinates[1]), 2));
    }

    private double getDistance(ClosesPoints point, int[] coordinates) {
        return Math.pow((point.points[0] - coordinates[0]), 2) + Math.pow((point.points[1] - coordinates[1]), 2);
    }
}

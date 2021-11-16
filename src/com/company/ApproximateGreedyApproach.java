package com.company;

import java.util.ArrayList;

/**
 * This class will use the Approximate Greedy Approach to solve the tessellation.
 * This class is the improvement of the brute-force approach performance.
 * It take one point and find the closes tessellation valid. Once the closes valid tessellation is valid, an edge is made.
 * Once an edge is made then the point between the two edges will be removed and will use recursion.
 */

public class ApproximateGreedyApproach
{
    private ArrayList<Point> pointArrayList;
    private ArrayList<Edges> interiorEdges;
    private Edges minInteriorEdge;
    private Point points;

    public ApproximateGreedyApproach()
    {
        pointArrayList = new ArrayList<>();
        interiorEdges = new ArrayList<>();
        points = new Point();
    }

    //Approximately Greedy Approach for finding tessellation
    public int approximateGreedyApproach(ArrayList<Point> pointArrayList, ArrayList<Edges> interiorEdges)
    {
        double minDist = 0;
        int minVert = 0;
        //Base Case
        if (pointArrayList.size() <= 3)
        {
            return 0;
        }
        else
        {
            for (int i = 0; i < pointArrayList.size(); i++)
            {
                double comparingDist;
                //If the point is at zero
                if (i == 0)
                {
                    minInteriorEdge = new Edges(pointArrayList.get(i + 1), pointArrayList.get(pointArrayList.size() - 1));
                    //Setting the first interior as the smallest distance
                    minDist = minInteriorEdge.calculatingEdgeDist();
                    minVert = i;
                }
                //If the point is the last point
                else if (i == pointArrayList.size() - 1)
                {
                    minInteriorEdge = new Edges(pointArrayList.get(0), pointArrayList.get(i - 1));
                    comparingDist = minInteriorEdge.calculatingEdgeDist();
                    //Comparing if the new distance is smaller than the previous
                    if (comparingDist < minDist)
                    {
                        minVert = i;
                    }
                }
                //if the point is not the first or last point
                else
                {
                    minInteriorEdge = new Edges(pointArrayList.get(i + 1), pointArrayList.get(i - 1));
                    comparingDist = minInteriorEdge.calculatingEdgeDist();
                    //Comparing if the new distance is smaller than the previous
                    if (comparingDist < minDist)
                    {
                        minVert = i;
                    }
                }
            }
            //Add the smallest interior edge to the list
            interiorEdges.add(minInteriorEdge);
            //Removing the point from the arraylist
            pointArrayList.remove(minVert);
            approximateGreedyApproach(pointArrayList, interiorEdges);
            return 0;
        }
    }

    //Calculating the total distance of the greedy approach
    public double totalDistance()
    {
        double totalDistance = 0;

        for (Edges ie : interiorEdges) {
            totalDistance += ie.calculatingEdgeDist();
        }
        return totalDistance;
    }

    //Test case 4-side Polygon
    public ArrayList<Edges> test4side()
    {
        pointArrayList = points.fourSidePolygon();

        //Possible Combination of points
        Edges e1 = new Edges(pointArrayList.get(0), pointArrayList.get(2));
        System.out.println(e1.calculatingEdgeDist());
        Edges e2 = new Edges(pointArrayList.get(1), pointArrayList.get(3));
        System.out.println(e2.calculatingEdgeDist());

        //Comparing the points
        approximateGreedyApproach(pointArrayList, interiorEdges);
        System.out.println("Greedy Distance Value: " + totalDistance());
        return interiorEdges;
    }

    //Test case 5-side Polygon
    public ArrayList<Edges> test5side()
    {
        pointArrayList = points.fiveSidePolygon();
        //Possible Combination of points
        Edges e1 = new Edges(pointArrayList.get(0), pointArrayList.get(2));
        System.out.println(e1.calculatingEdgeDist());
        Edges e2 = new Edges(pointArrayList.get(0), pointArrayList.get(3));
        System.out.println(e2.calculatingEdgeDist());
        Edges e3 = new Edges(pointArrayList.get(1), pointArrayList.get(3));
        System.out.println(e3.calculatingEdgeDist());
        Edges e4 = new Edges(pointArrayList.get(1), pointArrayList.get(4));
        System.out.println(e4.calculatingEdgeDist());
        Edges e5 = new Edges(pointArrayList.get(2), pointArrayList.get(4));
        System.out.println(e5.calculatingEdgeDist());

        //Comparing the points
        approximateGreedyApproach(pointArrayList, interiorEdges);
        System.out.println("Greedy Distance Value: " + totalDistance());
        return interiorEdges;
    }

    //Test case 6-side Polygon
    public ArrayList<Edges> test6side()
    {
        pointArrayList = points.sixSidePolygon();

        Edges e1 = new Edges(pointArrayList.get(0), pointArrayList.get(2));
        System.out.println(e1.calculatingEdgeDist());
        Edges e2 = new Edges(pointArrayList.get(0), pointArrayList.get(3));
        System.out.println(e2.calculatingEdgeDist());
        Edges e3 = new Edges(pointArrayList.get(0), pointArrayList.get(4));
        System.out.println(e3.calculatingEdgeDist());
        Edges e4 = new Edges(pointArrayList.get(1), pointArrayList.get(3));
        System.out.println(e4.calculatingEdgeDist());
        Edges e5 = new Edges(pointArrayList.get(1), pointArrayList.get(4));
        System.out.println(e5.calculatingEdgeDist());
        Edges e6 = new Edges(pointArrayList.get(1), pointArrayList.get(5));
        System.out.println(e6.calculatingEdgeDist());
        Edges e7 = new Edges(pointArrayList.get(2), pointArrayList.get(4));
        System.out.println(e7.calculatingEdgeDist());
        Edges e8 = new Edges(pointArrayList.get(2), pointArrayList.get(5));
        System.out.println(e8.calculatingEdgeDist());
        Edges e9 = new Edges(pointArrayList.get(3), pointArrayList.get(5));
        System.out.println(e9.calculatingEdgeDist());

        //Comparing the points
        approximateGreedyApproach(pointArrayList, interiorEdges);
        System.out.println("Greedy Distance Value: " + totalDistance());
        return interiorEdges;
    }

    //Test case 7-side Polygon
    public ArrayList<Edges> test7side()
    {
        pointArrayList = points.sevenSidePolygon();

        //possible Combination of points
        Edges e1 = new Edges(pointArrayList.get(0), pointArrayList.get(2));
        System.out.println(e1.calculatingEdgeDist());
        Edges e2 = new Edges(pointArrayList.get(0), pointArrayList.get(3));
        System.out.println(e2.calculatingEdgeDist());
        Edges e3 = new Edges(pointArrayList.get(0), pointArrayList.get(4));
        System.out.println(e3.calculatingEdgeDist());
        Edges e4 = new Edges(pointArrayList.get(0), pointArrayList.get(5));
        System.out.println(e4.calculatingEdgeDist());
        Edges e5 = new Edges(pointArrayList.get(1), pointArrayList.get(3));
        System.out.println(e5.calculatingEdgeDist());
        Edges e6 = new Edges(pointArrayList.get(1), pointArrayList.get(4));
        System.out.println(e6.calculatingEdgeDist());
        Edges e7 = new Edges(pointArrayList.get(1), pointArrayList.get(5));
        System.out.println(e7.calculatingEdgeDist());
        Edges e8 = new Edges(pointArrayList.get(1), pointArrayList.get(6));
        System.out.println(e8.calculatingEdgeDist());
        Edges e9 = new Edges(pointArrayList.get(2), pointArrayList.get(4));
        System.out.println(e9.calculatingEdgeDist());
        Edges e10 = new Edges(pointArrayList.get(2), pointArrayList.get(5));
        System.out.println(e10.calculatingEdgeDist());
        Edges e11 = new Edges(pointArrayList.get(2), pointArrayList.get(6));
        System.out.println(e11.calculatingEdgeDist());
        Edges e12 = new Edges(pointArrayList.get(3), pointArrayList.get(5));
        System.out.println(e12.calculatingEdgeDist());
        Edges e13 = new Edges(pointArrayList.get(3), pointArrayList.get(6));
        System.out.println(e13.calculatingEdgeDist());
        Edges e14 = new Edges(pointArrayList.get(4), pointArrayList.get(6));
        System.out.println(e14.calculatingEdgeDist());

        //Comparing the points
        approximateGreedyApproach(pointArrayList, interiorEdges);
        System.out.println("Greedy Distance Value: " + totalDistance());
        return interiorEdges;
    }

    public static void main(String[] args)
    {
        ApproximateGreedyApproach aga = new ApproximateGreedyApproach();
        System.out.println("---------Test Case 4 side---------");
        aga.test4side();
        System.out.println("---------Test Case 5 side---------");
        aga.test5side();
        System.out.println("---------Test Case 6 side---------");
        aga.test6side();
        System.out.println("---------Test Case 7 side---------");
        aga.test7side();
    }
}
package com.company;

import java.util.ArrayList;

/**
 * The class will use the brute force approach to solve the tessellation problem.
 * This will solve all valid possibility of tessellation.
 */
public class BruteForceApproach
{
    private ArrayList<Point> pointArrayList;
    private ArrayList<ArrayList<Edges>> listOfInteriorEdges;
    private ArrayList<Double> listOfDistance;
    private Point points;
    private ArrayList<Edges> interiorEdges;

    public BruteForceApproach()
    {
        listOfInteriorEdges = new ArrayList<>();
        listOfDistance = new ArrayList<>();
        points = new Point();
        interiorEdges = new ArrayList<>();
    }

    //Calculating the distance of the interior edges
    public ArrayList<Double> distanceCalculations(ArrayList<ArrayList<Edges>> listOfInteriorEdges)
    {
        double value;
        for (ArrayList<Edges> ListIE : listOfInteriorEdges)
        {
            value = 0;
            for (Edges StoredIE : ListIE)
            {
                //Getting the total distance
                value += StoredIE.calculatingEdgeDist();
            }
            //Storing the list of distance in a list
            listOfDistance.add(value);
        }
        return listOfDistance;
    }

    //Find the smallest edge
    public ArrayList<Edges> smallestEdge(ArrayList<Double> listOfDistance)
    {
        double minDistance = listOfDistance.get(0);
        int indexTracker = 0;
        for(int i = 1 ; i < listOfDistance.size(); i ++)
        {
            double comparingNumber = listOfDistance.get(i);
            if(comparingNumber < minDistance)
            {
                minDistance = comparingNumber;
                indexTracker = i;
            }
        }
        return listOfInteriorEdges.get(indexTracker);
    }

    //Checking if the interior edge being added is in the list of added interior edge list
    public boolean checkingCopyInteriorEdges(ArrayList<Edges> interiorEdges)
    {
        if (interiorEdges != null)
        {
            //Interior Edges being checked to be stored
            for (Edges IE : interiorEdges)
            {
                int matches = 0;
                //list of Stored Interior Edges
                for (ArrayList<Edges> ListIE : listOfInteriorEdges)
                {
                    //Interior Edges inside of stored list
                    for (Edges StoredIE : ListIE)
                    {
                        //Checking if the edges have been repeated
                        if (checkingEdges(IE, StoredIE))
                        {
                            matches++;
                        }
                    }
                }
                //Checks the number of matching edges
                if (matches >= interiorEdges.size())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }

    //Brute force approach for finding tessellation
    public int bruteForceApproach(ArrayList<Point> pointArrayList, ArrayList<Edges> interiorEdges)
    {
        ArrayList<Point> temporaryPoints;
        ArrayList<Edges> tempinteriorEdges;
        //Base case
        if (pointArrayList.size() <= 3)
        {
            //Have a condition which checks if the interior edges are the same or not
            if (!checkingCopyInteriorEdges(interiorEdges))
            {
                //Storing the interiorEdges once it reaches 3 edges
                listOfInteriorEdges.add(interiorEdges);
            }
            return 0;
        }
        else
        {
            for (int i = 0; i < pointArrayList.size(); i++)
            {
                //Storing the points into a temporary array
                temporaryPoints = (ArrayList<Point>) pointArrayList.clone();
                //Creating a temporary array
                tempinteriorEdges = (ArrayList<Edges>) interiorEdges.clone();
                //If the point taken is the first point
                if (i == 0)
                {
                    //Storing the interior Edges
                    tempinteriorEdges.add(new Edges(temporaryPoints.get(i + 1), temporaryPoints.get(temporaryPoints.size() - 1)));
                }
                //if the point is the last point
                else if (i == temporaryPoints.size() - 1)
                {
                    //Storing the interior Edges
                    tempinteriorEdges.add(new Edges(temporaryPoints.get(0), temporaryPoints.get(i - 1)));
                }
                else
                {
                    //Storing the interior Edges
                    tempinteriorEdges.add(new Edges(temporaryPoints.get(i + 1), temporaryPoints.get(i - 1)));
                }
                //Removing the point
                temporaryPoints.remove(i);
                //Recursion
                bruteForceApproach(temporaryPoints, tempinteriorEdges);
            }
            return 1;
        }
    }

    //Checking if two edges are the same
    public boolean checkingEdges(Edges IE, Edges StoredIE)
    {
        //Checking if the two edges are the same
        if ((IE.getStart() == StoredIE.getStart() && IE.getEnd() == StoredIE.getEnd()) || (IE.getEnd() ==
                StoredIE.getStart() && IE.getStart() == StoredIE.getEnd()))
        {
            return true;
        }
        return false;
    }

    public ArrayList<Double> getListOfDistance()
    {
        return listOfDistance;
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
        bruteForceApproach(pointArrayList, interiorEdges);
        distanceCalculations(listOfInteriorEdges);
        System.out.println(listOfDistance);
        return smallestEdge(listOfDistance);
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
        bruteForceApproach(pointArrayList, interiorEdges);
        distanceCalculations(listOfInteriorEdges);
        System.out.println(listOfDistance);
        return smallestEdge(listOfDistance);
    }

    //Test case 6-side Polygon
    public ArrayList<Edges> test6side()
    {
        pointArrayList = points.sixSidePolygon();

        //Possible Combination of points
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
        bruteForceApproach(pointArrayList, new ArrayList<Edges>());
        distanceCalculations(listOfInteriorEdges);
        System.out.println(listOfDistance);
        return smallestEdge(listOfDistance);
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
        bruteForceApproach(pointArrayList, new ArrayList<Edges>());
        distanceCalculations(listOfInteriorEdges);
        System.out.println(listOfDistance);
        return smallestEdge(listOfDistance);
    }

    public static void main(String[] args)
    {
        BruteForceApproach bfa = new BruteForceApproach();
        System.out.println("---------Test Case 4 side---------");
        bfa.test4side();
        System.out.println("---------Test Case 5 side---------");
        bfa.test5side();
        System.out.println("---------Test Case 6 side---------");
        bfa.test6side();
        System.out.println("---------Test Case 7 side---------");
        bfa.test7side();
    }
}
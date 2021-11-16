package com.company;

import java.util.ArrayList;

/**
 * The class will use the Exact Approach is efficiently solves the tessellation problem.
 * The Exact approach will form a 2D table with arraylist inside hence a 3D array.Then the
 * program will iterate through the rows and columns and find all possible solutions.
 * The program will iterate through all possiblle solutions and find the smallest distance and then
 * forms the tessellation/interior edges.
 */

public class ExactApproach
{
    private ArrayList<Point> pointArrayList;
    private Point points;

    public ExactApproach()
    {
        points = new Point();
    }

    //Exact approach to find the tessellation
    public ArrayList<Edges> exactApproach(ArrayList<Point> pointArrayList)
    {
        ArrayList<ArrayList<Edges>> collectionSolutions;
        ArrayList<Edges>[][] tableContent = new ArrayList[pointArrayList.size()][pointArrayList.size()];

        //Initalising the 2D table of arraylist
        for (int rows = 0; rows < tableContent.length - 2; rows++)
        {
            for (int cols = rows + 2; cols < tableContent.length; cols++)
            {
                tableContent[rows][cols] = new ArrayList<>();
            }
        }
        int difference = 3;
        int middleStart = 2;
        //While the last box is empty
        while (tableContent[0][pointArrayList.size() - 1].isEmpty())
        {
            for (int rows = 0; rows < tableContent[rows].length - 2; rows++)
            {
                collectionSolutions = new ArrayList<>();
                for (int cols = rows + difference; (cols == rows + difference) && (cols < tableContent[rows].length); cols++)
                {
                    //Getting solution for columns - 1
                    Edges interiorEdge1 = new Edges(pointArrayList.get(rows), pointArrayList.get(cols - 1));
                    ArrayList<Edges> firstCurrentSolutions = (ArrayList<Edges>) tableContent[rows][cols - 1].clone();
                    firstCurrentSolutions.add(interiorEdge1);
                    collectionSolutions.add(firstCurrentSolutions);

                    //Getting solution for rows + 1
                    Edges interiorEdges2 = new Edges(pointArrayList.get(rows + 1), pointArrayList.get(cols));
                    ArrayList<Edges> secondCurrentSolutions = (ArrayList<Edges>) tableContent[rows + 1][cols].clone();
                    secondCurrentSolutions.add(interiorEdges2);
                    collectionSolutions.add(secondCurrentSolutions);

                    for (int midPoints = rows + middleStart; midPoints <= tableContent[cols].length - middleStart && (cols - rows >= 4); midPoints++)
                    {
                        //Getting solution from rows to mid points
                        Edges interiorEdge3 = new Edges(pointArrayList.get(rows), pointArrayList.get(midPoints));
                        ArrayList<Edges> thirdCurrentSolutions = (ArrayList<Edges>) tableContent[rows][midPoints].clone();
                        thirdCurrentSolutions.add(interiorEdge3);

                        //Getting solution from edge cols to mid point
                        Edges interiorEdge4 = new Edges(pointArrayList.get(midPoints), pointArrayList.get(cols));
                        ArrayList<Edges> fourthCurrentSolutions = tableContent[midPoints][cols];
                        fourthCurrentSolutions.add(interiorEdge4);
                        thirdCurrentSolutions.addAll(fourthCurrentSolutions);
                        collectionSolutions.add(thirdCurrentSolutions);
                    }

                    //Finding the smallest possible combination
                    double minDist = Double.MAX_VALUE;
                    ArrayList<Edges> minCombo = null;
                    for (ArrayList<Edges> cs : collectionSolutions)
                    {
                        double sum = 0;
                        for (Edges edges : cs)
                        {
                            sum += edges.calculatingEdgeDist();
                        }
                        if (sum < minDist)
                        {
                            minDist = sum;
                            minCombo = cs;
                        }
                    }
                    tableContent[rows][cols] = minCombo;
                }
            }
            difference++;
        }
        return tableContent[0][pointArrayList.size() - 1];
    }

    //Displaying the distance for the exact approach
    public void displayingDistance(ArrayList<Edges> tableContent)
    {
        for (Edges edges : tableContent)
        {
            System.out.println("Exact Approach distance: " + edges.calculatingEdgeDist());
        }
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

        //Displaying the distance for the Exact Approach
        displayingDistance(exactApproach(pointArrayList));

        return exactApproach(pointArrayList);
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

        //Displaying the distance for the Exact Approach
        displayingDistance(exactApproach(pointArrayList));
        return exactApproach(pointArrayList);
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

        //Displaying the distance for the Exact Approach
        displayingDistance(exactApproach(pointArrayList));
        return exactApproach(pointArrayList);
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

        //Displaying the distance for the Exact Approach
        displayingDistance(exactApproach(pointArrayList));
        return exactApproach(pointArrayList);
    }

    public static void main(String[] args)
    {
        ExactApproach ea = new ExactApproach();
        System.out.println("---------Test Case 4 side---------");
        ea.test4side();
        System.out.println("---------Test Case 5 side---------");
        ea.test5side();
        System.out.println("---------Test Case 6 side---------");
        ea.test6side();
        System.out.println("---------Test Case 7 side---------");
        ea.test7side();

    }
}
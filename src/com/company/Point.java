package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 *The class will create a point with x and y coordinates. It also contains different size polyons with methods to paint
 * the points.
 */
public class Point
{
    private int x;
    private int y;
    private ArrayList<Point> pointList;
    private Color color;

    public Point()
    {
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
        color = new Color(0, 0, 0);
    }

    //Gets the x value of the point
    public int getX()
    {
        return x;
    }

    //Gets the y value of the point
    public int getY()
    {
        return y;
    }

    //4-side Polygon
    public ArrayList<Point> fourSidePolygon()
    {
        //4 Vertices
        Point v0 = new Point(1,1);
        Point v1 = new Point(4,7);
        Point v2 = new Point(6,5);
        Point v3 = new Point(7,1);

        //Creating List of Vertices
        pointList = new ArrayList<>();
        pointList.add(v0);
        pointList.add(v1);
        pointList.add(v2);
        pointList.add(v3);
        return pointList;
    }

    //5-side Polygon
    public ArrayList<Point> fiveSidePolygon()
    {
        //5 Vertices
        Point v0 = new Point(2,6);
        Point v1 = new Point(4,7);
        Point v2 = new Point(6,5);
        Point v3 = new Point(7,1);
        Point v4 = new Point(1,1);

        //Creating List of Vertices
        pointList = new ArrayList<>();
        pointList.add(v0);
        pointList.add(v1);
        pointList.add(v2);
        pointList.add(v3);
        pointList.add(v4);

        return pointList;
    }

    //6-side Polygon
    public ArrayList<Point> sixSidePolygon()
    {
        //6 Vertices
        Point v0 = new Point(3, 2);
        Point v1 = new Point(3, 4);
        Point v2 = new Point(6, 5);
        Point v3 = new Point(7, 4);
        Point v4 = new Point(8, 2);
        Point v5 = new Point(6, 1);

        //Creating list of vertices
        pointList = new ArrayList<>();
        pointList.add(v0);
        pointList.add(v1);
        pointList.add(v2);
        pointList.add(v3);
        pointList.add(v4);
        pointList.add(v5);

        return pointList;
    }

    //7-side Polygon
    public ArrayList<Point> sevenSidePolygon()
    {
        //7 Vertices
        Point v0 = new Point(3, 2);
        Point v1 = new Point(3, 4);
        Point v2 = new Point(4, 5);
        Point v3 = new Point(6, 5);
        Point v4 = new Point(7, 4);
        Point v5 = new Point(7, 2);
        Point v6 = new Point(6, 1);

        //Creating list of vertices
        pointList = new ArrayList<>();
        pointList.add(v0);
        pointList.add(v1);
        pointList.add(v2);
        pointList.add(v3);
        pointList.add(v4);
        pointList.add(v5);
        pointList.add(v6);

        return pointList;
    }

    @Override
    public String toString()
    {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    //Setting the size of the point
    public int getSize()
    {
        return 10;
    }

    //Drawing the vertices
    public void drawBall(Graphics g)
    {
        g.setColor(color);
        g.fillOval(getX() * 30, getY() * 30, getSize(), getSize());
    }

    //Drawing lines to the outside and inside vertices
    public void drawEdgesLine(Graphics g, ArrayList<Point> pointList, ArrayList<Edges> sIE)
    {
        for (int i = 0; i < pointList.size(); i++)
        {
            //If the point is the last point
            if (i == pointList.size() - 1)
            {
                g.drawLine((pointList.get(pointList.size() - 1).getX() * 30) + getSize() / 2,
                        (pointList.get(pointList.size() - 1).getY() * 30) + getSize() / 2, (pointList.get(0).getX() * 30) + getSize() / 2,
                        (pointList.get(0).getY() * 30) + getSize() / 2);
            }
            //If the point is not the last point
            else
            {
                g.drawLine((pointList.get(i).getX() * 30) + getSize() / 2, (pointList.get(i).getY() * 30) + getSize() / 2,
                        (pointList.get(i + 1).getX() * 30) + getSize() / 2, (pointList.get(i + 1).getY() * 30) + getSize() / 2);
            }
        }
        //Painting inside edges
        if (sIE != null)
        {
            for (int i = 0; i < sIE.size(); i++)
            {
                g.drawLine((sIE.get(i).getStart().getX() * 30) + getSize() / 2, (sIE.get(i).getStart().getY() * 30) + getSize() / 2
                        , (sIE.get(i).getEnd().getX() * 30) + getSize() / 2, (sIE.get(i).getEnd().getY() * 30) + getSize() / 2);
            }
        }
    }
}
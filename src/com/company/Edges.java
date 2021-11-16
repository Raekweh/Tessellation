package com.company;

/**
 * This class will create an edge between two points.
 */

public class Edges
{
    private Point start, end;

    public Edges(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Point getStart()
    {
        return start;
    }

    public Point getEnd()
    {
        return end;
    }

    //Calculating the distance between two edges
    public double calculatingEdgeDist()
    {
        return Math.sqrt(Math.pow(getEnd().getX() - getStart().getX(), 2) + (Math.pow(getEnd().getY() - getStart().getY(), 2)));
    }

    @Override
    public String toString()
    {
        return "Edges{" + "start=" + start + ", end=" + end + '}';
    }
}
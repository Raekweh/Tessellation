package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The class will display a GUI which gives the user functionality of different approachs for finding tessellation. The
 * GUI will display the polygon with the tessellation given to the specific method such as Exact, Greedy and Brute Force
 * methods.
 */
public class GraphGUI extends JPanel implements ActionListener
{
    private Timer timer;
    private JPanel southPanel;
    private DrawPanel drawPanel;
    private JComboBox polygonSelection, polygonMethods;
    private JButton solveButton;
    private BruteForceApproach bfa;
    private ApproximateGreedyApproach aga;
    private ExactApproach ea;
    private Point points;
    private ArrayList<Point> pointCollection;
    private ArrayList<Edges> interiorEdges;
    private ArrayList<Double> distanceListbf;

    public GraphGUI()
    {
        super(new BorderLayout());
        //Listing out the types of Polygon
        String listOfPolygon[] = {"Polygon 4", "Polygon 5", "Polygon 6", "Polygon 7"};
        String methods[] = {"Brute Force", "Approximately Greedy", "Exact"};

        //Creating a Combobox
        polygonSelection = new JComboBox(listOfPolygon);
        polygonMethods = new JComboBox(methods);
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);

        //Setting the components in the south panel
        southPanel = new JPanel();
        southPanel.add(polygonSelection);
        southPanel.add(polygonMethods);
        southPanel.add(solveButton);

        //Adding the south panel to the south layout
        add(southPanel, BorderLayout.WEST);

        //Drawing the panel
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        timer = new Timer(10, this);
        timer.start();

        points = new Point();
        pointCollection = new ArrayList<>();
        interiorEdges = new ArrayList<>();
        distanceListbf = new ArrayList<>();
        bfa = new BruteForceApproach();
        aga = new ApproximateGreedyApproach();
        ea = new ExactApproach();
    }

    //Drawing the panel
    public class DrawPanel extends JPanel
    {
        public DrawPanel()
        {
            super();
            setPreferredSize(new Dimension(500, 500));
            setBackground(Color.WHITE);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            for (Point point : pointCollection)
            {
                point.drawBall(g);
            }
            points.drawEdgesLine(g, pointCollection, interiorEdges);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (timer.isRunning())
        {
            drawPanel.revalidate();
            drawPanel.repaint();
        }

        //Pressing the solve button
        if (source == solveButton)
        {
            if (polygonMethods.getSelectedIndex() == 0)
            {
                //4-side polygon
                if (polygonSelection.getSelectedIndex() == 0) {
                    pointCollection = points.fourSidePolygon();
                    interiorEdges = bfa.test4side();
                    distanceListbf = bfa.getListOfDistance();
                }

                //5-side polygon
                else if (polygonSelection.getSelectedIndex() == 1)
                {
                    pointCollection = points.fiveSidePolygon();
                    interiorEdges = bfa.test5side();
                    distanceListbf = bfa.getListOfDistance();
                }

                //6-side polygon
                else if (polygonSelection.getSelectedIndex() == 2)
                {
                    pointCollection = points.sixSidePolygon();
                    interiorEdges = bfa.test6side();
                    distanceListbf = bfa.getListOfDistance();
                }

                //7-side polygon
                else if (polygonSelection.getSelectedIndex() == 3)
                {
                    pointCollection = points.sevenSidePolygon();
                    interiorEdges = bfa.test7side();
                    distanceListbf = bfa.getListOfDistance();
                }
                JOptionPane.showMessageDialog(this, distanceListbf);
            }

            //Greedy Approach
            if (polygonMethods.getSelectedIndex() == 1)
            {
                //4-side polygon
                if (polygonSelection.getSelectedIndex() == 0)
                {
                    pointCollection = points.fourSidePolygon();
                    aga.test4side();
                }

                //5-side polygon
                else if (polygonSelection.getSelectedIndex() == 1)
                {
                    pointCollection = points.fiveSidePolygon();
                    interiorEdges = aga.test5side();
                }

                //6-side polygon
                else if (polygonSelection.getSelectedIndex() == 2)
                {
                    pointCollection = points.sixSidePolygon();
                    interiorEdges = aga.test6side();
                }

                //7-side polygon
                else if (polygonSelection.getSelectedIndex() == 3)
                {
                    pointCollection = points.sevenSidePolygon();
                    interiorEdges = aga.test7side();
                }
            }

            //If the method is Exact Approach
            if (polygonMethods.getSelectedIndex() == 2)
            {
                //4-side polygon
                if (polygonSelection.getSelectedIndex() == 0)
                {
                    pointCollection = points.fourSidePolygon();
                    interiorEdges = ea.test4side();
                }

                //5-side polygon
                else if (polygonSelection.getSelectedIndex() == 1)
                {
                    pointCollection = points.fiveSidePolygon();
                    interiorEdges = ea.test4side();
                }

                //6-side polygon
                else if (polygonSelection.getSelectedIndex() == 2)
                {
                    pointCollection = points.sixSidePolygon();
                    interiorEdges = ea.test6side();
                }

                //7-side polygon
                else if (polygonSelection.getSelectedIndex() == 3)
                {
                    pointCollection = points.sevenSidePolygon();
                    interiorEdges = ea.test7side();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Graphs");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GraphGUI());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
    }
}
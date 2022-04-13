/**
 to run this program, you need Planet.java, StdDraw.java, ./images/, and ./data/planets.txt
 after javac the above (or NBody.java only maybe), run the following in terminal:
 java NBody 157788000.0 25000.0 data/planets.txt
 */

import java.util.List;
import java.util.*;

public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static int readPlanetNum(String fileName){
        In in = new In(fileName);
        int planetNum = in.readInt();
        return planetNum;
    }


    public static Planet[] readPlanets(String fileName){
        //List<Planet> allPlanet = new ArrayList<Planet>();
        In in = new In(fileName);
        int planetNum = in.readInt();
        double radius = in.readDouble();

        Planet[] allPlanet = new Planet[planetNum];

        for(int i = 0; i < planetNum; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String image = in.readString();
            allPlanet[i] = new Planet(xP, yP, xV, yV, m, image);
        }
        return allPlanet;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String planetsFileName = args[2];
        int planetNum = readPlanetNum(planetsFileName);
        double radius = readRadius(planetsFileName);
        Planet[] p = readPlanets(planetsFileName);
        StdDraw.setScale(0 - radius, radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        //StdDraw.picture(0, 0, "./images/starfield.jpg");
        //StdDraw.show();
        //StdDraw.pause(2000);

        //p[0].draw();
        //p[1].draw();
        //p[2].draw();
        //p[3].draw();
        //p[4].draw();


        for(double t = 0; t <= T; t = t + dt){
            double[] xForce = new double[planetNum];
            double[] yForce = new double[planetNum];
            for(int i = 0; i < planetNum; i++){
                xForce[i] = p[i].calcNetForceExertedByX(p);
                yForce[i] = p[i].calcNetForceExertedByY(p);

            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for(int i = 0; i < planetNum; i++){
                p[i].update(dt, xForce[i], yForce[i]);
                p[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
        }

        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }


    }
}


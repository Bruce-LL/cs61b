import java.lang.Math;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67e-11f; // make it private because AutoGrate doesn't want to see it.

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dis;
        double xDif = this.xxPos - p.xxPos;
        double yDif = this.yyPos - p.yyPos;
        dis = Math.sqrt(xDif * xDif + yDif * yDif);
        return dis;
    }

    public double calcForceExertedBy(Planet p){
        double dis = this.calcDistance(p);
        double force = this.G * this.mass * p.mass / (dis * dis);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double ForceX = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
        return ForceX;
    }

    public double calcForceExertedByY(Planet p){
        double ForceY = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
        return ForceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanet){
        double netForceX = 0;
        for(int i = 0; i < allPlanet.length; i ++){
            if(this.equals(allPlanet[i])){
                continue;
            }
            netForceX = netForceX + this.calcForceExertedByX(allPlanet[i]);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet){
        double netForceY = 0;
        for(int i = 0; i < allPlanet.length; i ++){
            if(this.equals(allPlanet[i])){
                continue;
            }
            netForceY = netForceY + this.calcForceExertedByY(allPlanet[i]);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw(){
        String imageFilePath = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, imageFilePath);
    }


    /**public static void main(String[] args){
        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
    }*/
}

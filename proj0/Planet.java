public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
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
        double distance = Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
        return distance;


    }
    public double calcForceExertedBy(Planet p){
        double dist = this.calcDistance(p);
        double G = 6.67*Math.pow(10,-11);
        double force =G*p.mass*this.mass/(dist*dist);
        return force;

    }
    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        double Fx = force*(p.xxPos - this.xxPos)/dist;
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double dist = this.calcDistance(p);
        double Fy = force*(p.yyPos - this.yyPos)/dist;
        return Fy;
    }
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) { // 排除当前行星对自身的力
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) { // 排除当前行星对自身的力
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }
    public void update(double dt,double Fx, double Fy){
        double ax = Fx/this.mass;
        double ay = Fy/this.mass;
        this.xxVel = this.xxVel+ax*dt;
        this.yyVel = this.yyVel+ay*dt;
        this.xxPos = this.xxPos+this.xxVel*dt;
        this.yyPos = this.yyPos+this.yyVel*dt;

    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);


    }

}
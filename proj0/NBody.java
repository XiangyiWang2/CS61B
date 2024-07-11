public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    public static Planet[] readPlanets(String filename){

        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] fruits = new Planet[num];
        for (int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            fruits[i] =new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
        }
        return fruits;

    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);  // 总时间
        double dt = Double.parseDouble(args[1]);
        String filename =  args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(0- radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < 5; i++) {
            Planet planet = planets[i];
            planet.draw(); }
            StdDraw.show();
            StdDraw.enableDoubleBuffering();
            double t = 0;
            while (t<T){
                double[] xForce =new double[5];
                double[] yForce =new double[5];
                for (int i=0; i<5;i++){
                    xForce[i]=planets[i].calcNetForceExertedByX(planets);
                    yForce[i]=planets[i].calcNetForceExertedByY(planets);

                }
                for (int i = 0; i<5;i++){
                    planets[i].update(dt,xForce[i], yForce[i]);
                    StdDraw.picture(0, 0, "images/starfield.jpg");
                    for (Planet planet : planets) {
                        planet.draw();

                    }
                    StdDraw.show();
                    StdDraw.pause(10);
                    t += dt;

            }

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }


}
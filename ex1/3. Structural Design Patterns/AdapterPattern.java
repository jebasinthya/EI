import java.util.*;

class RoundHole {
    private double radius;
    public RoundHole(double radius) { this.radius = radius; }
    public boolean fits(RoundPeg peg) { return this.radius >= peg.getRadius(); }
}

class RoundPeg { private double radius; public RoundPeg(double r){radius=r;} public double getRadius(){return radius;} }
class SquarePeg { private double width; public SquarePeg(double w){width=w;} public double getWidth(){return width;} }
class SquarePegAdapter extends RoundPeg {
    public SquarePegAdapter(SquarePeg peg){ super(peg.getWidth() * Math.sqrt(2)/2); }
}

public class AdapterPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter round hole radius: ");
        double holeRadius = sc.nextDouble();

        System.out.print("Enter square peg width: ");
        double pegWidth = sc.nextDouble();

        RoundHole hole = new RoundHole(holeRadius);
        SquarePeg squarePeg = new SquarePeg(pegWidth);
        SquarePegAdapter adapter = new SquarePegAdapter(squarePeg);

        System.out.println("Does square peg fit? " + hole.fits(adapter));
        sc.close();
    }
}

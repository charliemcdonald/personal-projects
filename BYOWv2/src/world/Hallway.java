package world;

public class Hallway {

    // hallways connect p1 and p2 together
    Coordinate leftPoint;
    Coordinate rightPoint;
    Coordinate bendLocation;

    public Hallway(Coordinate p1, Coordinate p2) {

        this.leftPoint = p1;
        this.rightPoint = p2;

        if (p1.getUserX() > p2.getUserX()) {
            leftPoint = p2;
            rightPoint = p1;
        }

        bendLocation = new Coordinate(p2.getUserX(), p1.getUserY());
    }

    public void updateWorld(World world) {
        int midX = (leftPoint.getUserX() + rightPoint.getUserX()) / 2;
        int midY = (leftPoint.getUserY() + rightPoint.getUserY()) / 2;
        Coordinate midPoint = new Coordinate(midX, midY);

        new Hallway(leftPoint, midPoint).updateWorldHelper(world);
        new Hallway(midPoint, rightPoint).updateWorldHelper(world);
    }

    public void updateWorldHelper(World world) {

        generateHorizontal(leftPoint, bendLocation, world);

        Coordinate top = bendLocation;
        Coordinate bottom = rightPoint;

        if (bendLocation.getUserY() < rightPoint.getUserY()) {
            top = rightPoint;
            bottom = bendLocation;
        }

        generateVertical(bottom, top, world);

    }

    public void generateHorizontal(Coordinate p1, Coordinate p2, World world) {

        // p1 is left of p2
        Room hallway = new Room(p2.getUserX() - p1.getUserX() + 2, 3, new Coordinate(p1.getUserX() - 1, p1.getUserY() - 1));
        hallway.updateWorld(world);
    }

    public void generateVertical(Coordinate p1, Coordinate p2, World world) {

        // p1 is below p2
        Room hallway = new Room(3, p2.getUserY() - p1.getUserY() + 2, new Coordinate(p1.getUserX() - 1, p1.getUserY() - 1));
        hallway.updateWorld(world);
    }
}

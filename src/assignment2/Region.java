package assignment2;

public class Region {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Region(int minX, int minY, int maxX, int maxY) {
        if (minX < 0 || minY < 0 || maxX < minX || maxY < minY || maxX == minX || maxY == minY) {
            throw new IllegalArgumentException("Invalid Region");
        }
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean contains(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x >= minX && x <= maxX && y <= maxY && y >= minY;
    }
}

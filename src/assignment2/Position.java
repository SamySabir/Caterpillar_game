package assignment2;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reset(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public int getDistance(Position position) {
        int distance = Math.abs(this.x - position.x) + Math.abs(this.y - position.y);
        if (distance > (Math.pow(2,31) - 1)) {
            throw new RuntimeException("Overflow");
        }
        return distance;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void moveWest() {
        this.x--;
    }

    public void moveEest() {
        this.x++;
    }

    public void moveNorth() {
        this.y--;
    }

    public void moveSouth() {
        this.y++;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        if (this.x == other.getX() && this.y == other.getY()) return true;
        return false;
    }

}

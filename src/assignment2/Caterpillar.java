package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position> {
    private int size;

    public Caterpillar() {
        this.size = 1;
        Position initialPos = new Position(7,7);
        add(initialPos);
    }

    public Position getHead() {
        return this.peekFirst();
    }

    public boolean isAdjacentTo(Position position) {
        return (this.getHead().getX() == position.getX() && this.getHead().getDistance(position) == 1) ||
                (this.getHead().getY() == position.getY() && this.getHead().getDistance(position) == 1) ;
    }

    public void eat(Position position) {
        if (!this.isAdjacentTo(position) || this.selfCollision(position)) {
            throw new IllegalArgumentException("Input position is not orthogonally adjacent to current head");
        }
        this.addFirst(position);
        this.size++;
    }

    public void move(Position position) {
        if (!this.isAdjacentTo(position)) {
            throw new IllegalArgumentException("Input position is not orthogonally adjacent to current head");
        }
        if (this.selfCollision(position)) {
            throw new IllegalArgumentException("Self Collision");
        }
        this.addFirst(position);
        this.remove();
    }

    public boolean selfCollision(Position position) {
        for (Position pos: this) {
            if (position.equals(pos)) {
                return true;
            }
        }
        return false;
    }
}

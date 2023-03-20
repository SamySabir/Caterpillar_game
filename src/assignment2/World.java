package assignment2;

public class World {
    private Caterpillar caterpillar;
    private Position foodPos;
    private Region region;
    private ActionQueue actions;
    private TargetQueue targets;
    private GameState state;

    public World(TargetQueue targets, ActionQueue actions) {
        this.actions = actions;
        this.targets = targets;
        this.region = new Region(0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        this.foodPos = targets.dequeue();
        this.state = GameState.MOVE;
    }

    public void step() {
        if (actions.isEmpty()) {
            state = GameState.NO_MORE_ACTION;
        }
        if (state != GameState.MOVE && state != GameState.EAT) {
            return;
        }
        Direction nextDir = actions.dequeue();
        Position currHead = this.getCaterpillar().getHead();
        Position nextHead;
        if (nextDir == Direction.NORTH) {
            currHead.moveNorth();
            nextHead = new Position(currHead.getX(), currHead.getY());
            currHead.moveSouth();
        } else if (nextDir == Direction.SOUTH) {
            currHead.moveSouth();
            nextHead = new Position(currHead.getX(), currHead.getY());
            currHead.moveNorth();
        } else if (nextDir == Direction.EAST) {
            currHead.moveEast();
            nextHead = new Position(currHead.getX(), currHead.getY());
            currHead.moveWest();
        } else if (nextDir == Direction.WEST) {
            currHead.moveWest();
            nextHead = new Position(currHead.getX(), currHead.getY());
            currHead.moveEast();
        } else {
            throw new IllegalArgumentException("Invalid action");
        }
        if (!region.contains(nextHead)) {
            state = GameState.WALL_COLLISION;
        } else if (caterpillar.selfCollision(nextHead)) {
            state = GameState.SELF_COLLISION;
        } else if (nextHead.equals(foodPos)) {
            caterpillar.eat(foodPos);
            if (targets.isEmpty()) {
                state = GameState.DONE;
            } else {
                foodPos = targets.dequeue();
                state = GameState.EAT;
            }
        } else {
            this.caterpillar.move(nextHead);
            state = GameState.MOVE;
        }
    }

    public GameState getState() {
        return this.state;
    }

    public Caterpillar getCaterpillar() {
        return this.caterpillar;
    }

    public Position getFood() {
        return this.foodPos;
    }

    public boolean isRunning() {
        return state == GameState.MOVE || state == GameState.EAT;
    }
}
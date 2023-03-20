package assignment2;

public class ActionQueue extends MyQueue<Direction>{
    private MyStack<String> stack;
    private String repeat;
    private String directions;

    public ActionQueue() {
        super();
        stack = new MyStack<String>();
        repeat = "";
        directions = "";
    }

    public void clear() {
        super.clear();
        this.stack.clear();
        this.repeat = "";
        this.directions = "";
    }

    public void loadFromEncodedString(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        MyStack<Character> brackets = new MyStack<>();
        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            if (Character.isDigit(c)) {
                repeat += c;
            } else if (c == '[') {
                if (repeat.isEmpty()) {
                    throw new IllegalArgumentException("No number before brackets");
                }
                if (!repeat.isEmpty()) {
                    stack.push(repeat);
                }
                if (!directions.isEmpty()) {
                    stack.push(directions);
                }
                this.directions = "";
                this.repeat = "";
            } else if (c == ']') {
                if (directions.isEmpty() || stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                String times = this.stack.pop();
                if (times.equals("")) {
                    throw new IllegalArgumentException("Invalid Syntax");
                }
                String decoded = "";
                int numTimes = Integer.parseInt(times);
                for (int j = 0; j < numTimes; j++) {
                    decoded += this.directions;
                }
                System.out.println(decoded);
                for (int k = 0; k < decoded.length(); k++) {
                    char directionChar = decoded.charAt(k);
                    Direction direction;
                    switch (directionChar) {
                        case 'N':
                            direction = Direction.NORTH;
                            break;
                        case 'S':
                            direction = Direction.SOUTH;
                            break;
                        case 'W':
                            direction = Direction.WEST;
                            break;
                        case 'E':
                            direction = Direction.EAST;
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown character");
                    }
                    super.enqueue(direction);
                }
                this.directions = "";
            } else if (c == 'N' || c == 'S' || c == 'W' || c == 'E') {
                this.directions += c;
            } else {
                throw new IllegalArgumentException("Syntax Error: Unknown Character" + c);
            }
        }
        /*if (!this.stack.isEmpty() || !this.directions.isEmpty() || !this.repeat.isEmpty()) {
            throw new IllegalArgumentException("Syntax Error: Missing ']'");
        }*/
    }

    public static void main(String[] args) {
        ActionQueue test = new ActionQueue();
        test.loadFromEncodedString("10[2[E]]" );
    }
}

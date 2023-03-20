package assignment2;

public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> positions;
    private String num;

    public TargetQueue() {
        super();
        positions = new MyStack<String>();
        num = "";
    }

    @Override
    public void clear() {
        super.clear();
        positions.clear();
        num = "";
    }

    public void addTargets(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        int i = 0;
        boolean period = false;
        int numCounter = 0;
        int periodCounter = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == '(') {
                if (!positions.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                if (i > 0 && !period) {
                    throw new IllegalArgumentException("No period separator");
                }
                if (periodCounter != 1 && i > 0) {
                    throw new IllegalArgumentException("Multiple periods");
                }
                periodCounter = 0;
                positions.push("(");
                period = false;
            } else if (Character.isDigit(c)) {
                if (positions.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                num += c;
            } else if (c == ',') {
                if (num.isEmpty() || positions.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                positions.push(num);
                numCounter++;
                positions.push(",");
                num = "";
            } else if (c == ')') {
                if (positions.getSize() < 3|| num.isEmpty() || !positions.peek().equals(",")) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                if (numCounter != 1) {
                    throw new IllegalArgumentException("Extra number");
                }
                numCounter = 0;
                positions.pop();
                int y = Integer.parseInt(num);
                int x = Integer.parseInt(positions.pop());
                super.enqueue(new Position(x,y));
                num = "";
                positions.clear();
            } else if (c == '.') {
                if (!positions.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                period = true;
                periodCounter++;
            } else {
                throw new IllegalArgumentException("Invalid character");
            }
            i++;
        }
        if (!positions.isEmpty() || !num.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}

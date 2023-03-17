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
    }

    public void addTargets(String s) {
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == '(') {
                if (!positions.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                positions.push("(");
            } else if (Character.isDigit(c)) {
                if (positions.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                num += c;
            } else if (c == ',') {
                if (num.isEmpty() || !positions.isEmpty() || positions.peek().equals("(")) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                positions.push(num);
                positions.push(",");
                num = "";
            } else if (c == ')') {
                if (!(positions.getSize() == 3) || num.isEmpty() || !positions.peekLast().equals("(") ||
                        !Character.isDigit(positions.peek().charAt(0)) || !positions.get(2).equals(",")) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                positions.pop();
                int y = Integer.parseInt(num);
                int x = Integer.parseInt(positions.pop());
                super.enqueue(new Position(x,y));
                num = "";
            } else if (c == '.') {
                if (!positions.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
            } else {
                throw new IllegalArgumentException("Invalid character");
            }
        }
        if (!positions.isEmpty() || !num.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}

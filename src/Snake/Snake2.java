package Snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FreeFly on 03.04.2015.
 */
public class Snake2 {
    private List<BodyPart> body;
    private Direction direction;

    public Snake2(int x, int y, Direction direction) {
        this.direction = direction;
        body = new ArrayList<BodyPart>();
        body.add(new BodyPart(x, y));
        body.add(new BodyPart(x - direction.deltaX(), y - direction.deltaY()));
        body.add(new BodyPart(x - direction.deltaX() * 2, y - direction.deltaY() * 2));
    }

    public void move() {
        moveBody();
        moveHead();
    }

    private void moveBody() {
        for (int i = body.size() - 1; i > 0; i--) {
            BodyPart current = body.get(i);
            BodyPart previous = body.get(i - 1);
            current.setX(previous.getX());
            current.setY(previous.getY());
        }
    }

    private void moveHead() {
        head().setX(head().getX() + direction.deltaX());
        head().setY(head().getY() + direction.deltaY());
    }

    public BodyPart head() {
        return body.get(0);
    }

    public List<BodyPart> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}


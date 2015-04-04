package Snake;

import Snake.Apple;
import Snake.BodyPart;
import Snake.Direction;
import Snake.Snake;
/**
 * Created by FreeFly on 03.04.2015.
 */

import java.awt.*;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import static Snake.Constants.*;

public class MainScene extends Scene {
    private Snake snake;
    private Snake2 snake2;
    private Apple apple;
    private Delay delay;

    public MainScene(Game game) {
        super(game);
        snake = new Snake(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, Direction.RIGHT);
        snake2 = new Snake2(WORLD_WIDTH/4,WORLD_HEIGHT/4,Direction.LEFT);
        placeApple();
        delay = new Delay(300);
    }

    @Override
    public void update(long nanosPassed) {
        if (isGameOver()) {
            game.setScene(new GameOverScene(game));
            return;
        }

        processInput();

        if (delay.updateAndCheck(nanosPassed)) {
            snake.move();
            snake2.move();
            BodyPart head = snake.head();
            BodyPart head2 = snake2.head();

            if (head.getX() < 1) {
                head.setX(WORLD_WIDTH);
            }

            if (head.getX() > WORLD_WIDTH) {
                head.setX(1);
            }

            if (head.getY() < 1) {
                head.setY(WORLD_HEIGHT);
            }

            if (head.getY() > WORLD_HEIGHT) {
                head.setY(1);
            }

            if (head2.getX() < 1) {
                head2.setX(WORLD_WIDTH);
            }

            if (head2.getX() > WORLD_WIDTH) {
                head2.setX(1);
            }

            if (head2.getY() < 1) {
                head2.setY(WORLD_HEIGHT);
            }

            if (head2.getY() > WORLD_HEIGHT) {
                head2.setY(1);
            }

            if (head.getX() == apple.getX() && head.getY() == apple.getY()) {
              //  List<BodyPart> body = snake.getBody();
              //  BodyPart lastPart = body.get(body.size() - 1);
               // body.add(new BodyPart(lastPart.getX(), lastPart.getY()));

                java.util.List<BodyPart> body = snake.getBody();

                BodyPart LastPart = body.get(body.size()-1);


                body.add(new BodyPart(LastPart.getX(),LastPart.getY()));

                /*if (isGameOver()) {
                    game.setScene(new GameOverScene(game));
                } else {
                 java.util.List<BodyPart> body2 = snake2.getBody();
                 BodyPart LastPart2 = body2.get(body2.size()-1);
                 body2.add(new BodyPart(LastPart.getX(),LastPart.getY()));
                }*/
                placeApple();
            }
            if (head2.getX() == apple.getX() && head2.getY() == apple.getY()){
            java.util.List<BodyPart> body2 = snake2.getBody();
            BodyPart LastPart2 = body2.get(body2.size()-1);
            body2.add(new BodyPart(LastPart2.getX(),LastPart2.getY()));
                placeApple();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);
        drawSnake(g);
        drawSnake2(g);
        drawApple(g);
    }

    private boolean isGameOver() {
        if ((snake.getBody().size() == WORLD_WIDTH * WORLD_HEIGHT)||
                (snake2.getBody().size() == WORLD_WIDTH * WORLD_HEIGHT)) {
            return true;
        }

        for (BodyPart bodyPart : snake.getBody()) {
            if ((bodyPart != snake.head()
                    && snake.head().getX() == bodyPart.getX()
                    && snake.head().getY() == bodyPart.getY())||(bodyPart != snake2.head()
                    && snake2.head().getX() == bodyPart.getX()
                    && snake2.head().getY() == bodyPart.getY()
                    )
                    )
            {
                return true;
            }
        }
        for (BodyPart bodyPart : snake2.getBody()) {
            if ((bodyPart != snake2.head()
                    && snake.head().getX() == bodyPart.getX()
                    && snake.head().getY() == bodyPart.getY())||(bodyPart != snake2.head()
                    && snake2.head().getX() == bodyPart.getX()
                    && snake2.head().getY() == bodyPart.getY()
            )
                    )
            {
                return true;
            }
        }
        return false;
    }


    private void drawSnake(Graphics2D g) {
        BufferedImage image = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        g.setColor(Color.red);
        for (BodyPart bodyPart : snake.getBody()) {
            g.fillRect(
                    bodyPart.getX() * CELL_SIZE - CELL_SIZE,
                    game.getScreenSize().height - (bodyPart.getY() * CELL_SIZE),
                    CELL_SIZE,
                    CELL_SIZE
            );
        }
    }


    private void drawSnake2(Graphics2D g) {
        g.setColor(Color.blue);
        for (BodyPart bodyPart : snake2.getBody()) {
            g.fillRect(
                    bodyPart.getX() * CELL_SIZE - CELL_SIZE,
                    game.getScreenSize().height - (bodyPart.getY() * CELL_SIZE),
                    CELL_SIZE,
                    CELL_SIZE
            );
        }
    }

    private void drawApple(Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(
                apple.getX() * CELL_SIZE - CELL_SIZE,
                game.getScreenSize().height - (apple.getY() * CELL_SIZE),
                CELL_SIZE,
                CELL_SIZE
        );
    }

    private void placeApple() {
        int x = 1 + (int) (Math.random() * WORLD_WIDTH);
        int y = 1 + (int) (Math.random() * WORLD_HEIGHT);
        while (!isCellEmpty(x, y)) {
            if (x < WORLD_WIDTH) {
                x++;
            } else {
                if (y < WORLD_HEIGHT) {
                    x = 1;
                    y++;
                } else {
                    x = 1;
                    y = 1;
                }
            }
        }
        apple = new Apple(x, y);
    }

    private boolean isCellEmpty(int x, int y) {
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart.getX() == x && bodyPart.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private void processInput() {
        for (KeyEvent event : game.getInput().getKeyPressedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_W:
                    snake2.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_D:
                    snake2.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_S:
                    snake2.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_A:
                    snake2.setDirection(Direction.LEFT);
                    break;
            }
        }
    }
}

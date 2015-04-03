package Snake;

/**
 * Created by FreeFly on 03.04.2015.
 */

import java.awt.*;

import static Snake.Constants.*;

public class DesktopLauncher {
    public static void main(String[] args) {
        int screenWidth = WORLD_WIDTH * CELL_SIZE;
        int screenHeight = WORLD_HEIGHT * CELL_SIZE;
        Dimension screenSize = new Dimension(screenWidth, screenHeight);
        Game game = DesktopGameBuilder.build(screenSize);
        game.setScene(new MainScene(game));
        game.start();
    }
}
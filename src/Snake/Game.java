package Snake;

import java.awt.*;

/**
 * Created by FreeFly on 03.04.2015.
 */
public interface Game {
    void start();

    void pause();

    Dimension getScreenSize();

    Input getInput();

    void setScene(Scene scene);
}

//import java.util.Random;
import javax.swing.JPanel;
import java.awt.*;
//import java.awt.event.*;

public class GameBoard extends JPanel{

    static final int BOARD_HEIGHT = 600;
    static final int BOARD_WIDTH = 600;
    static final int CELL_SIZE = 25;
    static final int CELL_COUNT = ((BOARD_HEIGHT * BOARD_WIDTH) / CELL_SIZE);
    boolean running = false;

    GameBoard(){
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        //this.addKeyListener();
        startGame();
    }

    public void startGame(){
        running = true;
    }
    public void draw(Graphics g){
        for (int i=0; i<(BOARD_WIDTH/CELL_SIZE);i++) {
            //draw vertical lines: (x1, y1) = (CELL_SIZE, 0) to (x2,y2) = (SAME, BOARD_HEIGHT)
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, BOARD_HEIGHT);
            g.drawLine(0, i*CELL_SIZE, BOARD_WIDTH, i*CELL_SIZE);
        }
    }
    
}
import java.util.Random;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel{

    static final int BOARD_HEIGHT = 600;
    static final int BOARD_WIDTH = 600;
    static final int CELL_SIZE = 20;
    static final int CELL_COUNT = ((BOARD_HEIGHT * BOARD_WIDTH) / CELL_SIZE);

    final int x[] = new int[CELL_COUNT];
    final int y[] = new int[CELL_COUNT];
    int foodX;
    int foodY;
    int snakeSize = 2;
    boolean running = false;
    Random random;

    GameBoard(){
        random = new Random();
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        startGame();
    }
    public void startGame(){
        newFood();
        running = true;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        for (int i=0; i<(BOARD_WIDTH/CELL_SIZE);i++) {
            //draw vertical lines: (x1, y1) = (CELL_SIZE, 0) to (x2,y2) = (SAME, BOARD_HEIGHT)
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, BOARD_HEIGHT);
            g.drawLine(0, i*CELL_SIZE, BOARD_WIDTH, i*CELL_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(foodX, foodY, CELL_SIZE, CELL_SIZE);

        for (int i=0; i<snakeSize; i++) {
            if (i == 0) {
                // set head color to different shade of green for improved visibility
                g.setColor(new Color(50, 200, 20));
                g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
            }else {
                g.setColor(Color.GREEN);
                g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
            }
        }
    }
    public void newFood() {
        //set random x and y coordinates for next food location
        foodX = random.nextInt((int)(BOARD_WIDTH/CELL_SIZE))*CELL_SIZE;
        foodY = random.nextInt((int)(BOARD_HEIGHT/CELL_SIZE))*CELL_SIZE;
    }
    public void moveSnake(){
        //Change coordinates of the snake moving from HEAD to TAIL
        for (int i=snakeSize; i>0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
    }

}
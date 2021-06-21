import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener{

    static final int BOARD_HEIGHT = 600;
    static final int BOARD_WIDTH = 600;
    static final int CELL_SIZE = 20;
    static final int CELL_COUNT = ((BOARD_HEIGHT * BOARD_WIDTH) / CELL_SIZE);
    static final int SPEED = 100;
    //represents x, y coordinates of snake, with the head at [0][0]
    final int snakeX[] = new int[CELL_COUNT];
    final int snakeY[] = new int[CELL_COUNT];

    int foodX;
    int foodY;
    int snakeSize = 5;
    char direction = 'R';
    boolean running = false;
    Random random;
    Timer timer;

    GameBoard(){
        random = new Random();
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new gameKeyAdapter());
        startGame();
    }
    public void startGame(){
        newFood();
        running = true;
        timer = new Timer(SPEED,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //drawing gridlines
        for (int i=0; i<(BOARD_WIDTH/CELL_SIZE);i++) {
            //draw vertical lines: (x1, y1) = (CELL_SIZE, 0) to (x2,y2) = (SAME, BOARD_HEIGHT)
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, BOARD_HEIGHT);
            g.drawLine(0, i*CELL_SIZE, BOARD_WIDTH, i*CELL_SIZE);
        }
        //drawing food
        g.setColor(Color.red);
        g.fillOval(foodX, foodY, CELL_SIZE, CELL_SIZE);
        //drawing the snake
        for (int i=0; i<snakeSize; i++) {
            if (i == 0) {
                // set head color to different shade of green for improved visibility
                g.setColor(new Color(50, 200, 0));
                g.fillRect(snakeX[i], snakeY[i], CELL_SIZE, CELL_SIZE);
            }
            else {
                g.setColor(Color.GREEN);
                g.fillRect(snakeX[i], snakeY[i], CELL_SIZE, CELL_SIZE);
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
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        switch(direction) {
            case 'U':
                //top is at  y=0. shift y coords UP
                snakeY[0] = snakeY[0] - CELL_SIZE;
                break;
            case 'D':
                snakeY[0] = snakeY[0] + CELL_SIZE;
                break;
            case 'L':
                //left is at x=0. shift x coords LEFT
                snakeX[0] = snakeX[0] - CELL_SIZE;
                break;
            case 'R': 
                snakeX[0] = snakeX[0] + CELL_SIZE;
                break;
        }
    }
    public void checkFood(){

    }
    public void checkCollisions() {
        //iterate over snake body. if collision occurs, set running to false
        for (int i=snakeSize; i>0; i--) {
            if ((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
                running = false;
            }
        }
        //check for snake-border collisions by x coordinate
        if ((snakeX[0] < 0) || (snakeX[0] > BOARD_WIDTH)) {
            running = false;
        }
        //check for snake-border collisions by y coordinate
        if ((snakeY[0] < 0) || (snakeY[0] > BOARD_HEIGHT)) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (running) {
            moveSnake();
            checkFood();
            checkCollisions();
        }
        repaint();
    }
    public class gameKeyAdapter extends KeyAdapter {

    }
}
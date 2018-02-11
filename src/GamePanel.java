import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener{

    Timer timer = new Timer(1, this);
    private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
    private static int dim = 10;
    private static int cols = width / dim;
    private static int rows = height / dim;
    private int[][] grid;
    private int x = cols/2;
    private int y = rows/2;

    private int UP = 0;
    private int RIGHT = 1;
    private int DOWN = 2;
    private int LEFT = 3;

    private int dir = UP;

    private int generations = 0;


    public GamePanel() {
        timer.start();
        setup();
    }

    private void setup() {

        grid = new int[cols][rows];

    }

    private void draw(Graphics g) {

        for(int k = 0; k <5; k++) {
            int state = grid[x][y];
            if (state == 0) {
                turnRight();
                grid[x][y] = 1;
                forward();
            } else if (state == 1) {
                turnLeft();
                grid[x][y] = 0;
                forward();
            }

            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    if (grid[i][j] == 1) {
                        g.fillRect(i * dim, j * dim, dim, dim);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(i * dim, j * dim, dim, dim);
                    }
                    g.setColor(Color.BLACK);
                    g.drawRect(i * dim, j * dim, dim, dim);
                }
            }
        }
    }

    private void forward() {
        if(dir == UP) {
            y--;
        }else if(dir == RIGHT) {
            x++;
        }else if(dir == DOWN) {
            y++;
        }else if(dir == LEFT) {
            x--;
        }

        if( x > cols-1) {
            x = 0;
        }else if(x< 0) {
            x = cols-1;
        }
        if( y > rows-1) {
            y = 0;
        }else if(y< 0) {
            y = rows-1;
        }
    }

    private void turnRight() {
        dir++;
        if(dir > LEFT) {
            dir = UP;
        }
    }

    private void turnLeft() {
        dir--;
        if(dir < UP) {
            dir = LEFT;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == timer) {
            repaint();
            generations++;
            System.out.println(generations*5);
        }
    }
}
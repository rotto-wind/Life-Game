import javax.swing.*;
import java.awt.event.*;

public class show {
    JFrame window;
    JPanel panel;
    volatile boolean waiting = true;
    int cellsize = 50;
    ImageIcon white = new ImageIcon("./resources/white.png");
    ImageIcon black = new ImageIcon("./resources/black.png");

    public void init() {
        window = new JFrame("Life Game"); // create a window
        panel = new JPanel(null); // create a panel
        panel.setPreferredSize(new java.awt.Dimension(lifegame.width * cellsize, lifegame.length * cellsize));
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true); // display the window
    }

    public void display(boolean[][] grid) {
        panel.removeAll();
        for (int i = 0; i < lifegame.length; i++) {
            for (int j = 0; j < lifegame.width; j++) {
                JLabel img = new JLabel(grid[i][j] ? white : black);
                img.setBounds(j*cellsize, i*cellsize, cellsize, cellsize);
                panel.add(img);
            }
            //System.out.println();
        }
        //System.out.println();
        panel.revalidate();
        panel.repaint(); // refresh the window
    }

    public boolean getWaiting() {return waiting;}

    public void init_grid(boolean[][] grid) {
        //display(grid);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / cellsize;
                int y = e.getY() / cellsize;
                if (x < lifegame.width && y < lifegame.length) {
                    grid[y][x] = !grid[y][x];
                    display(grid);
                }
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
         .put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
        panel.getActionMap().put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waiting = waiting ? false : true;
                System.out.println("Enter pressed!");
            }
        });

    }
}
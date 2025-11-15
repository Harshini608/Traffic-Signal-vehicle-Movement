import javax.swing.*;
import java.awt.*;

public class RealisticTrafficSimulator extends JPanel {

    private int carX = -80;              
    private String light = "GREEN";     
    private int timerCount = 0;          

    private final int SIGNAL_X = 350;  
    private final int CAR_STOP_X = 300;  

    public RealisticTrafficSimulator() {
        Timer t = new Timer(30, e -> update());
        t.start();
    }

    private void update() {
        timerCount++;

        
        if (timerCount == 200) light = "YELLOW";
        if (timerCount == 260) light = "RED";
        if (timerCount == 380) {
            light = "GREEN";
            timerCount = 0;
        }

        
        if (carX + 80 < CAR_STOP_X) { 
            carX += 3; 
        } else {
            // Car reached near signal; move only if green
            if (light.equals("GREEN")) {
                carX += 3;
            }
        }

        
        if (carX > 700) carX = -80;

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Sky
        g.setColor(new Color(140, 200, 255));
        g.fillRect(0, 0, 700, 400);

        // Road
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 170, 700, 120);

        // Dashed line
        g.setColor(Color.YELLOW);
        for (int i = 0; i < 700; i += 60)
            g.fillRect(i, 225, 40, 5);

        drawTrafficLight(g);
        drawCar(g);
    }

    private void drawTrafficLight(Graphics g) {

        
        int x = SIGNAL_X;

        g.setColor(Color.BLACK);
        g.fillRect(x, 80, 50, 120);

        // RED
        g.setColor(light.equals("RED") ? Color.RED : Color.DARK_GRAY);
        g.fillOval(x + 10, 90, 30, 30);

        // YELLOW
        g.setColor(light.equals("YELLOW") ? Color.YELLOW : Color.DARK_GRAY);
        g.fillOval(x + 10, 130, 30, 30);

        // GREEN
        g.setColor(light.equals("GREEN") ? Color.GREEN : Color.DARK_GRAY);
        g.fillOval(x + 10, 170, 30, 30);
    }

    private void drawCar(Graphics g) {
        g.setColor(new Color(0, 100, 255));
        g.fillRect(carX, 190, 80, 35);

        g.setColor(new Color(0, 70, 200));
        g.fillRect(carX + 10, 175, 60, 20);

        g.setColor(Color.WHITE);
        g.fillRect(carX + 18, 178, 44, 15);

        g.setColor(Color.BLACK);
        g.fillOval(carX + 10, 220, 20, 20);
        g.fillOval(carX + 50, 220, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Traffic Simulator");
        frame.add(new RealisticTrafficSimulator());
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


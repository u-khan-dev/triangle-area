import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application extends JFrame {
    private JPanel mainPanel;
    private JLabel headerLabel;
    private JLabel pointA_label;
    private JLabel pointA_x_label;
    private JTextField pointA_x_text;
    private JLabel pointA_y_label;
    private JTextField pointA_y_text;
    private JTextField pointB_x_text;
    private JTextField pointB_y_text;
    private JTextField pointC_x_text;
    private JTextField pointC_y_text;
    private JLabel pointB_label;
    private JLabel pointC_label;
    private JLabel pointB_x_label;
    private JLabel pointB_y_label;
    private JLabel pointC_x_label;
    private JLabel pointC_y_label;
    private JPanel outputPanel;
    private JButton calculateAreaButton;
    private JTextPane outputPane;
    private JButton clearFormButton;

    class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Point() {
            this(0, 0);
        }

        double distTo(Point B) {
            return Math.sqrt(Math.pow(this.x - B.x, 2) + Math.pow(this.y - B.y, 2));
        }
    }

    public Application() {
        super("Triangle Area");
        setSize(230, 285);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        calculateAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double Ax = Double.parseDouble(pointA_x_text.getText());
                    double Ay = Double.parseDouble(pointA_y_text.getText());
                    double Bx = Double.parseDouble(pointB_x_text.getText());
                    double By = Double.parseDouble(pointB_y_text.getText());
                    double Cx = Double.parseDouble(pointC_x_text.getText());
                    double Cy = Double.parseDouble(pointC_y_text.getText());

                    Point A = new Point(Ax, Ay);
                    Point B = new Point(Bx, By);
                    Point C = new Point(Cx, Cy);

                    double area = getArea(A, B, C);

                    String areaString = (area == 0) ? "not a triangle" : String.valueOf(round(area, 4));
                    outputPane.setText("Triangle area: " + areaString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter numeric values only.");
                }
            }

            private static double round(double value, int places) {
                if (places < 0) throw new IllegalArgumentException();

                BigDecimal bd = new BigDecimal(Double.toString(value));
                bd = bd.setScale(places, RoundingMode.HALF_UP);
                return bd.doubleValue();
            }

            double getArea(Point A, Point B, Point C) {
                double a = B.distTo(C);
                double b = A.distTo(C);
                double c = A.distTo(B);

                if (c + a == b) return 0;

                double s = (a + b + c)/2;

                return Math.sqrt(s*(s - a)*(s - b)*(s - c));
            }
        });

        clearFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pointA_x_text.setText("");
                pointA_y_text.setText("");
                pointB_x_text.setText("");
                pointB_y_text.setText("");
                pointC_x_text.setText("");
                pointC_y_text.setText("");
                outputPane.setText("");
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Application app = new Application();
    }

}

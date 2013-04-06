/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queens;

/**
 *
 * @author Sarah-Andrew
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author Sarah-Andrew
 */
public class Board extends JFrame{
    private static final int WIDTH = 900;
    private static final int HEIGHT = 800;
    
    private JButton startB, exitB;
    private JRadioButton fr, cr, el;
    private JTextField noQ;
    private ButtonGroup group = new ButtonGroup();
    private JLabel choice, directions;
    
    boa board = new boa();
    
    ImageIcon queen = new ImageIcon("freddie.jpg");
    Image q = queen.getImage().getScaledInstance(20, 20, ERROR);
    ImageIcon crown = new ImageIcon("crown.jpg");
    Image c = crown.getImage().getScaledInstance(20, 20, ERROR);
    ImageIcon eliza = new ImageIcon("elizabeth.jpg");
    Image e = eliza.getImage().getScaledInstance(20, 20, ERROR);
 
    private StartButtonHandler sbHandler;
    private ExitButtonHandler ebHandler;

    public Board(int n){
        noQ = new JTextField(5);
        noQ.setText("0");
        
        fr = new JRadioButton("The best Queen");
        cr = new JRadioButton("The basic queen");
        el = new JRadioButton("The actual Queen");
        group.add(fr);
        group.add(cr);
        group.add(el);
        
        JLabel fred = new JLabel(queen);
        fred.setSize(50, 50);
        JLabel crow = new JLabel(crown);
        crow.setSize(50, 50);
        JLabel eliz = new JLabel(eliza);
        eliz.setSize(50, 50);
        
        choice = new JLabel("Choose your pieces:", SwingConstants.LEFT);
        directions = new JLabel("Enter an N value in the text field. "
                + "Queens are initially differentiated by blue spaces. "
                + "You may have to click start again to see changes.", SwingConstants.LEFT);
        
        board.setLocation(50, 50);
        Dimension d = new Dimension(700,700);
        board.setPreferredSize(d);
        
        startB = new JButton("Start");
        startB.setSize(20, 20);
        sbHandler = new StartButtonHandler();
        startB.addActionListener(sbHandler);
        
        exitB = new JButton("Exit");
        ebHandler = new ExitButtonHandler();
        exitB.addActionListener(ebHandler);
        
        setTitle("N-Queens");
        SpringLayout nlayout = new SpringLayout();
        Container npane = getContentPane();
        npane.setLayout(nlayout);
        
        npane.add(noQ);
        npane.add(startB);
        npane.add(exitB);
        npane.add(board);
        npane.add(choice);
        npane.add(directions);
        npane.add(fr);
        npane.add(fred);
        npane.add(cr);
        npane.add(crow);
        npane.add(el);
        npane.add(eliz);
        
        nlayout.putConstraint(SpringLayout.WEST, directions, 75, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, directions, 5, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, startB, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, startB, 100, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, exitB, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, exitB, 150, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, noQ, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, noQ, 50, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, board, 25, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, board, 25, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, choice, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, choice, 200, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, fred, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, fred, 230, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, fr, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, fr, 300, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, crow, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, crow, 330, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, cr, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, cr, 380, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, eliz, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, eliz, 410, SpringLayout.NORTH, npane);
        nlayout.putConstraint(SpringLayout.WEST, el, 750, SpringLayout.WEST, npane);
        nlayout.putConstraint(SpringLayout.NORTH, el, 460, SpringLayout.NORTH, npane);
        
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        
        Graphics g = board.getGraphics();
        board.paint(g);
    }
    
    public class StartButtonHandler implements ActionListener{
        public StartButtonHandler(){}
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int N = Integer.parseInt(noQ.getText());
            if(N <= 0 || N > 30 || N == 2 || N == 3){
                String s = "N's value must be greater than 0 "
                        + "and less than or equal to 30.\n"
                        + " Also, if N is equal to 2 or 3, there is no"
                        + " solution.";
                JOptionPane.showMessageDialog(null, s,
                        "YOU BROKE THE INTERNET...", JOptionPane.OK_CANCEL_OPTION);
            }
            else{
                Queens.changeN(N);
                Graphics g = board.getGraphics();
                Rectangle r = board.getBounds();
                g.setColor(board.getBackground());
                g.fillRect(r.x, r.y, r.width, r.height);
                board.drawBoard(N, g);
            }
        }
    }

    private static class ExitButtonHandler implements ActionListener{

        public ExitButtonHandler() {}

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    public class boa extends JPanel{
        public void drawBoard(int N, Graphics g){
            int row, col, x, y;
            //Graphics g = this.getGraphics();
            for (row = 0; row < N; row++) {
                for (col = 0; col < N; col++) {
                    x = col * 20 + 50;
                    y = row * 20 + 50;
                    if(Queens.queens[row] == col){
                        g.setColor(Color.blue);
                    }
                    else if((row % 2) == (col % 2)) {
                        g.setColor(Color.white);
                    } 
                    else{
                        g.setColor(Color.black);
                    }
                    g.fillRect(x, y, 20, 20);
                }
            }
            for (row = 0; row < N; row++) {
                for (col = 0; col < N; col++) {
                    x = col * 20 + 50;
                    y = row * 20 + 50;
                    if(Queens.queens[row] == col && fr.isSelected()){
                        g.drawImage(q, x, y, null);
                    }
                    else if(Queens.queens[row] == col && cr.isSelected()){
                        g.drawImage(c, x, y, null);
                    }
                    else if(Queens.queens[row] == col && el.isSelected()){
                        g.drawImage(e, x, y, null);
                    }
                }
            }
        }
    }
}

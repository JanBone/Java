package peg_solitare;

import javax.swing.AbstractAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Plansza extends JPanel {
    private Pionek first_clicked;
    private Pionek second_clicked;
    private Pionek peg_to_delete;
    private Pionek last_peg;
    private JPanel panel;
    private Pionek peg;
    private BufferedImage source_peg;
    private BufferedImage source;
    private BufferedImage source_wood;
    private BufferedImage source_hole;
    private List<Pionek> pegs;
    private int pegs_number;
    private JLabel label;
    private int game_mode;
    private boolean started;
    private JLabel start_game;

    public Plansza() throws IOException {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setLayout(new GridLayout(7, 7, 0, 0));
        add(panel, BorderLayout.CENTER);
        try {
            this.source_peg = ImageIO.read(Plansza.class.getResourceAsStream("/resources/peg.png"));
            this.source_wood = ImageIO.read(Plansza.class.getResourceAsStream("/resources/wood.png"));
            source_hole  = ImageIO.read(Plansza.class.getResourceAsStream("/resources/peg2.png"));
            ///C:\Users\48796\IdeaProjects\Struktury\src\Samotnik_gra\src\resources
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        started = false;
        game_mode = 0;
        pegs_number = 32;



    }

    public void getJlabel(JLabel label){
        this.label = label;
    }

    public void start(){
        started = true;
        panel.removeAll();
        if (game_mode == 0){
            pegs_number = 32;
        }
        else{
            pegs_number = 36;
        }
        pegs = new ArrayList<>();
        initUI();
    }

    public void setMode(int i){
        game_mode = i;
        if (i == 1){
            pegs_number = 36;
        }
        else{
            pegs_number = 32;
        }

    }


    private void initUI() {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if ((i < 2 || i >=5) && (j < 2 || j >=5)){
                    if ((i==1 || i== 5) && (j == 1 || j==5) && game_mode == 1){
                        source = source_peg;
                        peg = new Pionek(source);
                        peg.setType("peg");
                        peg.SetClicable();
                    }
                    else{
                        source = source_wood;
                        peg = new Pionek(source);
                        peg.setType("empty");
                    }

                }

                else if (i == 3 && j == 3){
                    source = source_hole;
                    peg = new Pionek(source);
                    peg.setType("hole");
                    peg.SetClicable();
                }
                else{
                    source = source_peg;
                    peg = new Pionek(source);
                    peg.setType("peg");
                    peg.SetClicable();

                }
                peg.putClientProperty("position", new Point(i, j));
                pegs.add(peg);
            }
        }

        for (int i = 0; i < 49; i++) {
            Pionek peg = pegs.get(i);
            panel.add(peg);
            peg.setBorder(BorderFactory.createLineBorder(Color.gray));
            peg.addActionListener(new Action());
        }


    }
    private class Action extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkPeg(e);
            condition();
        }
        private void checkPeg(ActionEvent e) {

            for (Pionek peg : pegs) {
                if (peg.isClicked()) {
                    if (first_clicked == null) {
                        first_clicked = peg;
                    }
                    else {
                        if (first_clicked.equals(peg)){
                            first_clicked.setBorder();
                            first_clicked = null;
                        }
                        else{
                            second_clicked = peg;
                        }
                    }
                    peg.clicked = false;
                    break;
                }

            }
            if (first_clicked != null && second_clicked != null) {
                Point p = get_peg_to_delete(first_clicked, second_clicked);
                if (p.equals(new Point(-1,-1))){
                    first_clicked.setBorder();
                    second_clicked.setBorder();
                    first_clicked = null;
                    second_clicked = null;
                }
                else{
                    for (Pionek peg : pegs) {
                        if (peg.getClientProperty("position").equals(p)) {
                            peg.delete = true;
                            peg_to_delete = peg;
                            break;
                        }
                    }
                    if (valid(first_clicked, second_clicked, peg_to_delete)){
                        updatePegs();
                    }
                    else{
                        first_clicked = null;
                        second_clicked = null;
                    }
                }


            }
        }



        private Point get_peg_to_delete(Pionek first, Pionek second){
            Point p1 = (Point) first.getClientProperty("position");
            Point p2 = (Point) second.getClientProperty("position");
            double x1 = p1.getX();
            double x2 = p2.getX();
            double y1 = p1.getY();
            double y2 = p2.getY();
            double y3;
            double x3;
            if (x1 == x2 && Math.abs(y1 - y2)==2){
                if (y1 > y2){
                    y3 = y1 - 1;
                }
                else{
                    y3 = y1 + 1;
                }
                x3 = x2;
            }
            else if(y1 == y2 && Math.abs(x1 - x2)== 2){
                if (x1 > x2){
                    x3 = x1 - 1;
                }
                else{
                    x3 = x1 + 1;
                }
                y3 = y2;
            }
            else {
                x3 = y3 = -1;
            }
            return new Point((int)x3,(int)y3);
        }


        private void updatePegs() {
            first_clicked.setIcon(source_hole);
            second_clicked.setIcon(source_peg);
            peg_to_delete.setIcon(source_hole);
            first_clicked.setType("hole");
            second_clicked.setType("peg");
            peg_to_delete.setType("hole");
            first_clicked.setBorder();
            second_clicked.setBorder();
            last_peg = second_clicked;
            first_clicked = null;
            second_clicked = null;
            peg_to_delete = null;
            pegs_number -= 1;
            panel.removeAll();
            for (JComponent peg : pegs) {
                panel.add(peg);
            }
            panel.validate();
        }



        private boolean valid(Pionek peg1,Pionek peg2, Pionek peg3)
        {
            if (peg1.getType() == "peg" && peg3.getType() == "peg" && peg2.getType() == "hole"){
                return true;
            }
            else{
                return false;
            }
        }
    }
    private boolean won(){
        if (pegs_number == 1 && last_peg.getClientProperty("position").equals(new Point(3,3))){
            return true;
        }
        else{
            return false;
        }
    }

    public void condition(){
        if (won()){
            label.setText("Wygrałeś!");
        }
        else{
            label.setText("Zostało " + pegs_number + " pionków na planszy");
        }
    }


}

package peg_solitare;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Samotnik extends JFrame
{
    private Plansza plansza;
    private JLabel label;
    private String stan;
    private JRadioButtonMenuItem english;
    private JRadioButtonMenuItem european;

    public Samotnik() throws IOException {

        this.setLayout(new BorderLayout());
        plansza = new Plansza();
        create_Jlabel();
        plansza.getJlabel(label);
        this.add(plansza,BorderLayout.CENTER);
        this.setTitle("Peg solitaire");
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu menuBar = new Menu();
        setJMenuBar(menuBar);
        pack();
        setVisible(true);

    }

    private void create_Jlabel(){
        label = new JLabel("Samotnik", JLabel.CENTER);
        label.setForeground(Color.BLACK);
        this.add(label,BorderLayout.PAGE_END);
        label.setBorder(new EmptyBorder(100,100,100,100));
        label.setFont(new Font("", Font.ITALIC, 40));
        plansza.getJlabel(label);
    }

    private void show_game(){
        this.add(plansza,BorderLayout.CENTER);
    }

    private void close(){
        this.dispose();
    }

    private class Menu extends JMenuBar {


        JMenu submenu_gra ,ruchy , ustawienia, pomoc;
        JMenuItem nowa, koniec;

        public Menu() {
            submenu_gra = new JMenu("Gra");
            ruchy = new JMenu("Ruchy");
            ustawienia = new JMenu("Ustawienia");
            pomoc = new JMenu("Pomoc");
            nowa = new JMenuItem("Nowa");
            koniec = new JMenuItem("Koniec");
            english = new JRadioButtonMenuItem("Brytyjska plansza");
            european = new JRadioButtonMenuItem("Europejska plansza");
            ustawienia.add(english);
            ustawienia.add(european);
            submenu_gra.add(nowa);
            submenu_gra.add(koniec);
            this.add(submenu_gra);
            this.add(ruchy);
            this.add(ustawienia);
            this.add(pomoc);
            initializeItems();

        }

        public void initializeItems(){

            koniec.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    close();
                }
            });
            nowa.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {

                    show_game();
                    plansza.start();
                    label.setText("Nowa gra rozpoczęta");
                    label.setBorder(new EmptyBorder(0,0,0,0));
                    label.setFont(new Font("", Font.ITALIC, 20));
                    pack();

                }
            });

            english.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {
                    plansza.setMode(1);
                    european.setSelected(false);

                }
            });
            european.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {
                    plansza.setMode(0);
                    english.setSelected(false);

                }
            });

        }
    }
}

package peg_solitare;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class Pionek extends JButton {
    public boolean clicked;
    public boolean delete;
    public String type;
    public boolean clickable = false;

    public Pionek(Image img) {
        super(new ImageIcon(img));
        initUI();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void SetClicable() {
        clickable = true;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setIcon(Image img){
        this.setIcon(new ImageIcon(img));
    }

    public void setBorder(){
        setBorder(BorderFactory.createLineBorder(Color.gray));
    }


    private void initUI() {

        BorderFactory.createLineBorder(Color.gray);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
                if (clickable){

                    setBorder(BorderFactory.createLineBorder(Color.red));
                    clicked = true;
                }
            }

        });

    }
}

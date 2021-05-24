import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Kalendarz extends JFrame
{
    ArrayList<Month> listOfPanels;
    JPanel p, year_panel;
    JPanel p1, p2;
    Lista listModel1, listModel2, listModel3;
    Month current_month;
    JComboBox month;
    JComboBox year;
    JTabbedPane kalendarz;
    String y;
    String weekdays[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int year_num, month_num;
    public Kalendarz(String title) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        current_month = new Month();
        kalendarz = new JTabbedPane(JTabbedPane.TOP);
        String y = String.valueOf(current_month.getYear());
        kalendarz.addTab(y, createYearPanel(current_month.getYear()));
        kalendarz.addTab(current_month.getMonthString(),createMonthPanel());
        add(kalendarz);
        add(createJToolBar());
        setTitle(title);
        pack();
    }

    private Month createMonths(int year,int month) {
        Month m = new Month(year,month);
        return m;
    }


    private JPanel createYearPanel(int year){
        year_panel = new JPanel(new GridLayout(3, 4));
        addMonth(year_panel, year);
        return year_panel;
    }
    private void addMonth(JPanel year_panel, int year){
        PanelListener listener = new PanelListener();
        listOfPanels = new ArrayList();
        for (int k =0; k< 12; k++){
            Month m = new Month(year,k);
            m.addMouseListener(listener);
            listOfPanels.add(m);
            year_panel.add(m);
        }
        year_panel.revalidate();
        year_panel.repaint();
    }



    private JPanel createMonthPanel(){
        p = new JPanel(new GridLayout(0, 3));
        listModel1 = new Lista(getPreviousMonthAndYear(current_month.getYear(),current_month.month));
        listModel2 = new Lista(current_month);
        listModel3 = new Lista(getNextMonthAndYear(current_month.getYear(),current_month.month));
        JList list1 = new JList(listModel1);
        JList list2 = new JList(listModel2);
        JList list3 = new JList(listModel3);
        list1.setCellRenderer(new MyCellRenderer());
        list2.setCellRenderer(new MyCellRenderer());
        list3.setCellRenderer(new MyCellRenderer());
        p.add(list1);
        p.add(list2);
        p.add(list3);
        return p;

    }

    private void clearPanel(){
        for (Month l : listOfPanels)
        {
            year_panel.remove(l);
        }
        year_panel.revalidate();
        year_panel.repaint();
        listOfPanels.clear();
    }
    private JToolBar createJToolBar(){
        JToolBar bar = new JToolBar("Tool bar", JToolBar.VERTICAL);
        bar.setVisible(true);
        bar.setLayout(new GridLayout(1,3));

        int  min = 1970;
        int max = 3000;
        int step = 1;
        int value = current_month.getYear();
        SpinnerNumberModel model = new SpinnerNumberModel(current_month.getYear(), 1970, 3000, 1);
        JSpinner s = new JSpinner(model);
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        s.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                repaintYearPanel((Integer) s.getValue());
            }
        });
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaintYearPanel(current_month.getYear() - 1);
            }
        } );
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                repaintYearPanel(current_month.getYear() + 1);

            }
        } );
        bar.add(prev);
        bar.add(s);
        bar.add(next);
        return bar;

    }

    public void repaintYearPanel(int year){
        int month = current_month.getMonth();
        current_month = new Month(year,month);
        clearPanel();
        addMonth(year_panel, year);
        kalendarz.setTitleAt(0,String.valueOf(current_month.getYear()));
   }


    public void updateLists(int year, int month){
        listModel1.update(getPreviousMonthAndYear(year,month));
        Month month_for_list = new Month(year,month);
        listModel2.update(month_for_list);
        listModel3.update(getNextMonthAndYear(year,month));
        kalendarz.setTitleAt(1,month_for_list.getMonthString());

    }

    public void updatePanels(JPanel pressedpanel){
        for (Month l : listOfPanels)
        {
            if (!l.equals(pressedpanel)){
                l.setBackground(Color.lightGray);
            }
        }
    }
    public Month getNextMonthAndYear(int year, int month){
        if (month == 11){
            year = year + 1;
            month = 0;
        }
        else{
            month = month + 1;
        }
        return new Month(year,month);
    }

    public Month getPreviousMonthAndYear(int year, int month){
        if (month == 0){
            year = year - 1;
            month = 11;
        }
        else{
           month = month - 1;

        }
        return new Month(year,month);
    }

    private class PanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {

            Object source = event.getSource();
            if(source instanceof Month){
                Month panelPressed = (Month) source;
                panelPressed.setBackground(Color.PINK);
                updatePanels(panelPressed);
                updateLists(panelPressed.getYear(), panelPressed.getMonth());




            }
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {}

        @Override
        public void mouseExited(MouseEvent arg0) {}

        @Override
        public void mousePressed(MouseEvent arg0) {}

        @Override
        public void mouseReleased(MouseEvent arg0) {}

    }

    private class Lista extends AbstractListModel {

        ArrayList days;
        private  int size;
        private Month month;
        public Lista(Month month) {
            month = month;
            days = month.daysOfmonth();
            size = days.size();
        }
        @Override
        public int getSize() {
            return size;
        }

        @Override
        public Object getElementAt(int index) {
            return days.get(index);
        }

        public void update(Month month){
            this.month = month;
            days = month.daysOfmonth();
            size = days.size();
            fireContentsChanged(this, 0, size);
        }

    }

    class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
        public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
        {
            String s = value.toString();




            setText(s);
            if (isSelected) {
                setBackground(Color.pink);
            } else {
                if (s.contains("Niedziela")){
                    setBackground(Color.RED);
                }
                else{
                    setBackground(Color.WHITE);
                    setForeground(list.getForeground());
                }
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }
}
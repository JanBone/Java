import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.Border;
import java.sql.Array;
import java.util.*;


public class Month extends JPanel{
    private String[] dayNames = {"Pn", "Wt", "Śr", "Cz", "Pt", "Sb", "Nd" };
    private String[] dayNamesfull = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
    private String[]  monthNames = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Pazdziernik","Listopad", "Grudzień"};
    int lastday;
    Calendar cal;
    int dayOfWeek;
    int year;
    int month;
    int offset;

    Month () {
        cal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.lastday = cal.getActualMaximum(cal.DATE);
        dayOfWeek = firstDayOfWeek(cal.get(cal.DAY_OF_WEEK));
        createJpanel();

    }

    Month (int year, int month) {
        this.year = year;
        this.month = month;
        cal = new GregorianCalendar(year, month, 1);
        lastday = cal.getActualMaximum(cal.DATE);
        dayOfWeek = firstDayOfWeek(cal.get(cal.DAY_OF_WEEK));
        offset = dayOfWeek -1 ;
        createJpanel();



    }

    public ArrayList daysOfmonth(){
        int day = dayOfWeek - 1;
        ArrayList daylist = new ArrayList();
        daylist.add(monthNames[month]);
        for (int k = 1; k<=lastday; k++){
            if (day == 7){
                day = 0;
            }
            daylist.add(k + " " + dayNamesfull[day]);
            day += 1;

        }

        return daylist;
    }

    public String dayOfWeek(int day){
        cal.set(year,month,day);
        int dayofweek = cal.get(cal.DAY_OF_WEEK) - 1;
        return dayNamesfull[dayofweek];
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public String getMonthString(){
        return monthNames[month];
    }


    public int firstDayOfWeek(int i){
        if (i - 1 == 0){
            return 7;
        }
       else{
           return i-1;
        }
    }


    public int numberOfDays(){
        return lastday;
    }

    public void createJpanel(){
        JPanel p = new JPanel();

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel nazwa = new JLabel(monthNames[month]);
        nazwa.setHorizontalAlignment(JLabel.CENTER);
        nazwa.setVerticalAlignment(JLabel.TOP);
        this.add(nazwa);
        p.setLayout(new GridLayout(0, 7));
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        for (int i = 0; i < 7; i++)
            labelArray.add(new JLabel(dayNames[i]));
        for (int i = 0; i < offset; i++)
            labelArray.add(new JLabel(" "));
        for (int i = 1; i <= lastday; i++)
            labelArray.add(new JLabel(String.valueOf(i)));

        Iterator<JLabel> ite = labelArray.iterator();
        while (ite.hasNext()) {
            JLabel label = (JLabel) ite.next();
            label.setHorizontalAlignment(JLabel.TRAILING);
            p.add(label);
        }
        p.setSize(100, 100);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.lightGray);
        this.add(p);


    }

}
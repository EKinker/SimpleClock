package SimpleClock;//package SimpleClock.SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;
    JToggleButton toggle1224 = new JToggleButton("12 Hour / 24 Hour");
    JToggleButton toggleGMT = new JToggleButton("EST / GMT");


    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout(0, 50, 50));
        this.setSize(570, 320);
        this.setResizable(false);


        timeFormat = new SimpleDateFormat("  hh:mm:ss a  ");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 34));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
        toggle1224.addItemListener(change1224);
        toggleGMT.addItemListener(changeGMT);


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.add(toggle1224, BorderLayout.NORTH);
        this.add(toggleGMT);
        this.setVisible(true);

        setTimer();
    }

    public void setTimer() {
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    ItemListener change1224 = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent event) {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                timeFormat = new SimpleDateFormat("  HH:mm:ss a  ");
            } else {
                timeFormat = new SimpleDateFormat("  hh:mm:ss a  ");
            }
        }
    };

    ItemListener changeGMT = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent event) {
            int state = event.getStateChange();
            if(state == ItemEvent.SELECTED){
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            } else {
                timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
            }

        }
    };
    public static void main(String[] args) {
        new SimpleClock();
    }
}

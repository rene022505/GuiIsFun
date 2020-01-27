import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

class AgainstTheClock {

    private static double timeLeft;

    private static int counter;
    private static int best = Integer.MIN_VALUE;

    private static Random rand = new Random();

    private static DecimalFormat df = new DecimalFormat("0.00");

    private static Timer timerGame = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GuiMain.labelTime.setText(df.format(timeLeft -= 0.01) + "s");
            timerGame.start();
        }
    });

    private static Timer timerStopGame = new Timer(10950, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timerGame.stop();
            stop();
        }
    });

    static void startTimeGame() {
        counter = 0;
        timeLeft = 10;
        GuiMain.alles(false);
        GuiMain.labelTime.setVisible(true);
        GuiMain.labelTime.setEnabled(true);

        GuiMain.labelHits.setText("0");
        GuiMain.labelHits.setEnabled(true);
        GuiMain.labelHits.setVisible(true);

        GuiMain.panelAgainstClock.setEnabled(true);
        GuiMain.panelAgainstClock.setVisible(true);

        placeTarget();

        timerGame.start();
        timerStopGame.start();
    }

    static void targetHit() {
        counter++;
        placeTarget();
        GuiMain.labelHits.setText(String.valueOf(counter));
    }

    private static void stop() {
        GuiMain.alles(true);
        GuiMain.panelAgainstClock.setEnabled(false);
        GuiMain.panelAgainstClock.setVisible(false);
        GuiMain.labelTime.setVisible(false);
        GuiMain.labelTime.setEnabled(false);
        GuiMain.labelHits.setEnabled(false);
        GuiMain.labelHits.setVisible(false);

        if (counter > best) {
            best = counter;
        }
        setScores();
    }

    private static void placeTarget() {
        int x = rand.nextInt((GuiMain.frameWidth - 14) - GuiMain.boxSize.getValue()); // 14 Rand
        int y = rand.nextInt((GuiMain.frameHeight - 35) - GuiMain.boxSize.getValue()); // 35 Rand
        GuiMain.panelAgainstClock.setBounds(x, y, GuiMain.boxSize.getValue(), GuiMain.boxSize.getValue());
    }

    static void setScores() {
        if (best == Integer.MIN_VALUE) {
            GuiMain.labelBest.setText("Best: n/a");
            GuiMain.labelLast.setText("Last: n/a");
        } else {
            GuiMain.labelBest.setText("Best: " + best);
            GuiMain.labelLast.setText("Last: " + counter);
        }
    }

}

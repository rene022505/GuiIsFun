import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class ReactionTest {

    private static Random rand = new Random();

    private static long start;

    private static Double best = Double.MAX_VALUE;
    private static double last;

    private static int delay = rand.nextInt(5000) + 2000;

    private static Timer timerGame = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GuiMain.panelReaction.setEnabled(true);
            GuiMain.panelReaction.setBackground(Color.GREEN);
            start = System.currentTimeMillis();
        }
    });

    static void startReactionGame() {
        GuiMain.alles(false);

        GuiMain.panelReaction.setBounds(0, 0, GuiMain.frameWidth, GuiMain.frameHeight);
        GuiMain.panelReaction.setBackground(Color.RED);

        GuiMain.panelReaction.setEnabled(false);
        GuiMain.panelReaction.setVisible(true);

        delay = rand.nextInt(5000) + 2000;

        timerGame.start();
    }

    static void targetHit() {
        long stop = System.currentTimeMillis();

        if (GuiMain.panelReaction.isEnabled()) {
            GuiMain.alles(true);

            extraWurst(false);

            last = (double) (stop - start) / 1000;

            GuiMain.labelLast.setText("Last: " + last + "s");
            if (last < best) {
                best = last;
            }
            GuiMain.labelBest.setText("Best: " + best + "s");
        } else {
            cheating();
        }
    }

    private static void cheating() {
        timerGame.stop();
        GuiMain.alles(true);
        GuiMain.labelLast.setText("Last: n/a");
        last = -1;

        extraWurst(false);
    }

    static void setScores() {
        if (best == Double.MAX_VALUE || last < 0) {
            GuiMain.labelBest.setText("Best: n/a");
            GuiMain.labelLast.setText("Last: n/a");
        } else {
            GuiMain.labelBest.setText("Best: " + best + "s");
            GuiMain.labelLast.setText("Last: " + last + "s");
        }
    }

    private static void extraWurst(boolean b) {
        GuiMain.panelReaction.setVisible(b);
        GuiMain.panelReaction.setEnabled(b);
        GuiMain.boxSize.setEnabled(b);
        GuiMain.boxSize.setVisible(b);
        GuiMain.squareSize.setVisible(b);
        GuiMain.squareSize.setEnabled(b);
        GuiMain.exampleBox.setVisible(b);
        GuiMain.exampleBox.setEnabled(b);
    }

}

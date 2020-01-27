import java.util.Random;

class DefaultGame {

    private static int counter;

    private static Random rand = new Random();

    private static long start;

    private static Double best = Double.MAX_VALUE;
    private static Double last;

    static void startDefualtGame() {
        counter = 0;

        GuiMain.alles(false);

        GuiMain.panelDefault.setEnabled(true);
        GuiMain.panelDefault.setVisible(true);

        placeTarget();
        start = System.currentTimeMillis();
    }

    private static void placeTarget() {
        int x = rand.nextInt((GuiMain.frameWidth - 14) - GuiMain.boxSize.getValue()); // 14 Rand
        int y = rand.nextInt((GuiMain.frameHeight - 35) - GuiMain.boxSize.getValue()); // 35 Rand
        GuiMain.panelDefault.setBounds(x, y, GuiMain.boxSize.getValue(), GuiMain.boxSize.getValue());
    }

    static void targetHit() {
        counter++;
        if (counter < 5) {
            placeTarget();
        } else {
            long stop = System.currentTimeMillis();
            GuiMain.alles(true);
            GuiMain.panelDefault.setVisible(false);
            GuiMain.panelDefault.setEnabled(false);

            last = (double) (stop - start) / 1000;

            GuiMain.labelLast.setText("Last: " + last + "s");
            if (last < best) {
                best = last;
            }
            GuiMain.labelBest.setText("Best: " + best + "s");

        }
    }

    static void setScores() {
        if (best == Double.MAX_VALUE) {
            GuiMain.labelBest.setText("Best: n/a");
            GuiMain.labelLast.setText("Last: n/a");
        } else {
            GuiMain.labelBest.setText("Best: " + best + "s");
            GuiMain.labelLast.setText("Last: " + last + "s");
        }
    }

}

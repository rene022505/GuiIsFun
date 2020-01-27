import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

class GuiMain extends JFrame {

    static JPanel panelDefault = new JPanel(null, true);
    static JPanel panelReaction = new JPanel(null, true);
    static JPanel panelAgainstClock = new JPanel(null, true);
    static JPanel exampleBox = new JPanel(null, true);

    static JLabel labelLast = new JLabel();
    static JLabel labelBest = new JLabel();
    static JLabel labelTime = new JLabel();
    static JLabel labelHits = new JLabel();

    private static JButton startGame = new JButton();

    static JSlider boxSize = new JSlider();

    private static JComboBox<String> gamemode = new JComboBox<>();
    private static DefaultComboBoxModel<String> jComboBox1Model = new DefaultComboBoxModel<>();
    static JComboBox<String> squareSize = new JComboBox<>();
    private static DefaultComboBoxModel<String> jComboBox2Model = new DefaultComboBoxModel<>();

    private static int lastSquare;
    static int frameWidth, frameHeight;

    private static boolean custom = false;

    GuiMain() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameWidth = 600;
        frameHeight = 600;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Default");
        setResizable(true);
        setMinimumSize(new Dimension(330, 525));
        Container cp = getContentPane();
        cp.setLayout(null);

        startGame.setText("Start Game");
        startGame.setMargin(new Insets(2, 2, 2, 2));

        labelLast.setText("Last: n/a");

        labelBest.setText("Best: n/a");

        labelHits.setText("0");
        labelHits.setVisible(false);
        labelHits.setEnabled(false);

        gamemode.setModel(jComboBox1Model);
        gamemode.addItem("Default");
        gamemode.addItem("Reaction");
        gamemode.addItem("Against the Clock");

        panelDefault.setOpaque(true);
        panelDefault.setBackground(Color.GREEN);
        panelAgainstClock.setOpaque(true);
        panelAgainstClock.setBackground(Color.GREEN);

        exampleBox.setOpaque(true);
        exampleBox.setBackground(Color.GREEN);
        squareSize.setModel(jComboBox2Model);
        squareSize.addItem("Default");
        squareSize.addItem("CS4");
        squareSize.addItem("Custom");

        sizeAll();

        cp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frameWidth = cp.getWidth();
                frameHeight = cp.getHeight();
                sizeAll();
            }
        });

        boxSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                exampleBox.setBounds(((int) (frameWidth * 0.5f)) - (boxSize.getValue() / 2), ((int) (frameHeight * 0.75f)) - (boxSize.getValue() / 2),
                                     boxSize.getValue(), boxSize.getValue());
                squareSize.setSelectedIndex(2);
                if(!custom)
                    lastSquare = boxSize.getValue();
            }
        });

        cp.add(gamemode);
        cp.add(labelLast);
        cp.add(labelBest);
        cp.add(startGame);
        cp.add(panelDefault);
        cp.add(labelTime);
        cp.add(labelHits);
        cp.add(panelAgainstClock);
        cp.add(panelReaction);
        cp.add(boxSize);
        cp.add(squareSize);
        cp.add(exampleBox);

        panelDefault.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DefaultGame.targetHit();
            }
        });

        panelAgainstClock.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AgainstTheClock.targetHit();
            }
        });

        panelReaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReactionTest.targetHit();
            }
        });

        startGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gamemode.getSelectedItem().equals("Default")) {
                    DefaultGame.startDefualtGame();
                } else if (gamemode.getSelectedItem().equals("Reaction")) {
                    ReactionTest.startReactionGame();
                } else if (gamemode.getSelectedItem().equals("Against the Clock")) {
                    AgainstTheClock.startTimeGame();
                }
            }
        });

        gamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamemode.getSelectedItem().equals("Default")) {
                    DefaultGame.setScores();
                    setTitle("Default");
                } else if (gamemode.getSelectedItem().equals("Reaction")) {
                    ReactionTest.setScores();
                    setTitle("Reaction");
                } else if (gamemode.getSelectedItem().equals("Against the Clock")) {
                    AgainstTheClock.setScores();
                    setTitle("Against the Clock");
                }
                setResize();
            }
        });

        squareSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (squareSize.getSelectedItem().equals("Default")) {
                    lastSquare = boxSize.getValue();
                    custom = true;
                    boxSize.setValue(28);
                    squareSize.setSelectedIndex(0);
                } else if (squareSize.getSelectedItem().equals("CS4")) {
                    lastSquare = boxSize.getValue();
                    custom = true;
                    boxSize.setValue((int) (((((frameWidth + frameHeight) / 2) - 16)/16) * (1 - (0.7f * (3 - 5) / 5))));
                    squareSize.setSelectedIndex(1);
                } else if(custom) {
                    boxSize.setValue(lastSquare);
                    squareSize.setSelectedIndex(2);
                    custom = false;
                }
            }
        });

        setVisible(true);
    }

    private static void sizeAll() {
        startGame.setBounds((int) (frameWidth * 0.375f), ((int) (frameHeight * 0.416f)), ((int) (frameWidth * 0.25f)), ((int) (frameHeight * 0.0833f)));
        labelLast.setBounds(((int) (frameWidth * 0.0166f)), ((int) (frameHeight * 0.0166f)), 100, ((int) (frameHeight * 0.033f)));
        labelBest.setBounds(((int) (frameWidth * 0.0166f)), ((int) (frameHeight * 0.05f)), 100, ((int) (frameHeight * 0.033f)));
        labelHits.setBounds(((int) (frameWidth * 0.0166f)), ((int) (frameHeight * 0.0166f)), 100, ((int) (frameHeight * 0.033f)));
        gamemode.setBounds(((int) (frameWidth * 0.375f)), ((int) (frameHeight * 0.5033f)), ((int) (frameWidth * 0.25f)), ((int) (frameHeight * 0.033f)));
        labelTime.setBounds(((int) (frameWidth * 0.9166f)), ((int) (frameHeight * 0.0166f)), 100, ((int) (frameHeight * 0.033f)));
        boxSize.setBounds(((int) (frameWidth * 0.375f)), ((int) (frameHeight * 0.54166f)), ((int) (frameWidth * 0.25f)), ((int) (frameHeight * 0.04166f)));
        boxSize.setMinimum(((int) (((frameHeight + frameWidth) / 2) * 0.033f)));
        boxSize.setMaximum(((int) (((frameHeight + frameWidth) / 2) * 0.166f)));
        boxSize.setValue(((int) (((frameHeight + frameWidth) / 2) * 0.0466f)));
        exampleBox.setBounds(((int) (frameWidth * 0.5f)) - (boxSize.getValue() / 2), ((int) (frameHeight * 0.75f)) - (boxSize.getValue() / 2),
                boxSize.getValue(), boxSize.getValue());
        squareSize.setBounds(((int) (frameWidth * 0.375f)), ((int) (frameHeight * 0.59166f)), ((int) (frameWidth * 0.25f)), ((int) (frameHeight * 0.033f)));
        flipFlop(gamemode);
        flipFlop(squareSize);
    }

    private static void flipFlop(JComboBox cb) {
        if(cb.isVisible()) {
            cb.setVisible(false);
            cb.setVisible(true);
        }
    }

    static void alles (boolean b) {
        GuiMain.startGame.setEnabled(b);
        GuiMain.startGame.setVisible(b);
        GuiMain.labelBest.setEnabled(b);
        GuiMain.labelBest.setVisible(b);
        GuiMain.labelLast.setEnabled(b);
        GuiMain.labelLast.setVisible(b);
        GuiMain.gamemode.setVisible(b);
        GuiMain.gamemode.setEnabled(b);
        GuiMain.squareSize.setVisible(b);
        GuiMain.squareSize.setEnabled(b);
        GuiMain.exampleBox.setEnabled(b);
        GuiMain.exampleBox.setVisible(b);
        GuiMain.boxSize.setVisible(b);
        GuiMain.boxSize.setEnabled(b);
    }

    private static void setResize() {
        if(gamemode.getSelectedItem().equals("Reaction")) {
            boxSize.setEnabled(false);
            boxSize.setVisible(false);
            exampleBox.setVisible(false);
            exampleBox.setEnabled(false);
            GuiMain.squareSize.setVisible(false);
            GuiMain.squareSize.setEnabled(false);
            GuiMain.exampleBox.setEnabled(false);
            GuiMain.exampleBox.setVisible(false);
            GuiMain.boxSize.setVisible(false);
            GuiMain.boxSize.setEnabled(false);
        } else {
            boxSize.setEnabled(true);
            boxSize.setVisible(true);
            exampleBox.setVisible(true);
            exampleBox.setEnabled(true);
            GuiMain.squareSize.setVisible(true);
            GuiMain.squareSize.setEnabled(true);
            GuiMain.exampleBox.setEnabled(true);
            GuiMain.exampleBox.setVisible(true);
            GuiMain.boxSize.setVisible(true);
            GuiMain.boxSize.setEnabled(true);
        }
    }

}

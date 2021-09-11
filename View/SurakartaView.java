package View;

import Service.ClientSocketGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SurakartaView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextArea messagesTextArea;
    private JTextField inputMessageTextField;
    private JButton sendMessageButton;
    private JPanel boardImagePanel;
    private ClientSocketGame clientSocketGame;
    private String name;

    public SurakartaView(ClientSocketGame clientSocketGame) {
        setupUI();
        triggerInputMessageAction();
        triggerSendButtonAction();
        this.clientSocketGame = clientSocketGame;
        name = JOptionPane.showInputDialog("Entre com seu nome:");
        this.clientSocketGame.setName(name);
        setTitle("Surakarta ["+name+"]");
    }

    private void setupUI() {
        setContentPane(mainPanel);
        setTitle("Surakarta");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        disableTextArea();
        setupBackground();
        setLayout(null);
        messagesTextArea.setLineWrap(true);
    }

    private void disableTextArea() {
        messagesTextArea.setEditable(false);
    }

    private void setupBackground() {
        boardImagePanel.setLayout(new CardLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        JLabel imageContent = new JLabel();

        imageContent.setIcon(
                new ImageIcon(
                        new ImageIcon("src/Files/backgroundBoard.jpg")
                                .getImage()
                                .getScaledInstance(
                                        700,
                                        700,
                                        Image.SCALE_DEFAULT
                                )
                )
        );
        imageContent.setSize(700,700);
        imageContent.setLocation(0,0);

        JButton p1 = new JButton();
        p1.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p1.setSize(40,40);
        p1.setLocation(148,150);
        p1.addActionListener(this);
        p1.setName("red");

        JButton p2 = new JButton();
        p2.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p2.setSize(40,40);
        p2.setLocation(219,150);
        p2.addActionListener(this);
        p2.setName("red");

        JButton p3 = new JButton();
        p3.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p3.setSize(40,40);
        p3.setLocation(294,150);
        p3.addActionListener(this);
        p3.setName("red");

        JButton p4 = new JButton();
        p4.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p4.setSize(40,40);
        p4.setLocation(365,150);
        p4.addActionListener(this);
        p4.setName("red");

        JButton p5 = new JButton();
        p5.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p5.setSize(40,40);
        p5.setLocation(440,150);
        p5.addActionListener(this);
        p5.setName("red");

        JButton p6 = new JButton();
        p6.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p6.setSize(40,40);
        p6.setLocation(511,150);
        p6.addActionListener(this);
        p6.setName("red");

        JButton p7 = new JButton();
        p7.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p7.setSize(40,40);
        p7.setLocation(148,221);
        p7.addActionListener(this);
        p7.setName("red");

        JButton p8 = new JButton();
        p8.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p8.setSize(40,40);
        p8.setLocation(219,221);
        p8.addActionListener(this);
        p8.setName("red");

        JButton p9 = new JButton();
        p9.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p9.setSize(40,40);
        p9.setLocation(294,221);
        p9.addActionListener(this);
        p9.setName("red");

        JButton p10 = new JButton();
        p10.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p10.setSize(40,40);
        p10.setLocation(365,221);
        p10.addActionListener(this);
        p10.setName("red");

        JButton p11 = new JButton();
        p11.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p11.setSize(40,40);
        p11.setLocation(440,221);
        p11.addActionListener(this);
        p11.setName("red");

        JButton p12 = new JButton();
        p12.setIcon(new ImageIcon(new ImageIcon("src/Files/dotRed.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        p12.setSize(40,40);
        p12.setLocation(511,221);
        p12.addActionListener(this);
        p12.setName("red");

        JButton q1 = new JButton();
        q1.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q1.setSize(40,40);
        q1.setLocation(148,440);
        q1.addActionListener(this);
        q1.setName("blue");

        JButton q2 = new JButton();
        q2.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q2.setSize(40,40);
        q2.setLocation(219,440);
        q2.addActionListener(this);
        q2.setName("blue");

        JButton q3 = new JButton();
        q3.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q3.setSize(40,40);
        q3.setLocation(294,440);
        q3.addActionListener(this);
        q3.setName("blue");

        JButton q4 = new JButton();
        q4.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q4.setSize(40,40);
        q4.setLocation(365,440);
        q4.addActionListener(this);
        q4.setName("blue");

        JButton q5 = new JButton();
        q5.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q5.setSize(40,40);
        q5.setLocation(440,440);
        q5.addActionListener(this);
        q5.setName("blue");

        JButton q6 = new JButton();
        q6.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q6.setSize(40,40);
        q6.setLocation(511,440);
        q6.addActionListener(this);
        q6.setName("blue");

        JButton q7 = new JButton();
        q7.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q7.setSize(40,40);
        q7.setLocation(148,511);
        q7.addActionListener(this);
        q7.setName("blue");

        JButton q8 = new JButton();
        q8.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q8.setSize(40,40);
        q8.setLocation(219,511);
        q8.addActionListener(this);
        q8.setName("blue");

        JButton q9 = new JButton();
        q9.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q9.setSize(40,40);
        q9.setLocation(294,511);
        q9.addActionListener(this);
        q9.setName("blue");

        JButton q10 = new JButton();
        q10.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q10.setSize(40,40);
        q10.setLocation(365,511);
        q10.addActionListener(this);
        q10.setName("blue");

        JButton q11 = new JButton();
        q11.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q11.setSize(40,40);
        q11.setLocation(440,511);
        q11.addActionListener(this);
        q11.setName("blue");

        JButton q12 = new JButton();
        q12.setIcon(new ImageIcon(new ImageIcon("src/Files/dotBlue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        q12.setSize(40,40);
        q12.setLocation(511,511);
        q12.addActionListener(this);
        q12.setName("blue");

        layeredPane.add(imageContent, 1);

        layeredPane.add(p1, 0);
        layeredPane.add(p2, 0);
        layeredPane.add(p3, 0);
        layeredPane.add(p4, 0);
        layeredPane.add(p5, 0);
        layeredPane.add(p6, 0);
        layeredPane.add(p7, 0);
        layeredPane.add(p8, 0);
        layeredPane.add(p9, 0);
        layeredPane.add(p10, 0);
        layeredPane.add(p11, 0);
        layeredPane.add(p12, 0);

        layeredPane.add(q1, 0);
        layeredPane.add(q2, 0);
        layeredPane.add(q3, 0);
        layeredPane.add(q4, 0);
        layeredPane.add(q5, 0);
        layeredPane.add(q6, 0);
        layeredPane.add(q7, 0);
        layeredPane.add(q8, 0);
        layeredPane.add(q9, 0);
        layeredPane.add(q10, 0);
        layeredPane.add(q11, 0);
        layeredPane.add(q12, 0);


        boardImagePanel.add(layeredPane);
    }

    public void addMessageToTextArea(String message, String from) {
        messagesTextArea.append(from + ":" + message + "\r\n");
        playSound(Sounds.NEWMESSAGE.value);
    }

    public void appendMessageToTextArea(String message) {
        messagesTextArea.append(message + "\r\n");
    }

    private void playSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    void triggerInputMessageAction() {
        inputMessageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientSocketGame.sendMessage(name + ":" +inputMessageTextField.getText());
                } catch (IOException io) {
                    System.out.println(io.getMessage());
                }

                addMessageToTextArea(inputMessageTextField.getText(), name);
                inputMessageTextField.setText("");
            }
        });
    }

    void triggerSendButtonAction() {
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientSocketGame.sendMessage(inputMessageTextField.getText());
                } catch (IOException io) {
                    System.out.println(io.getMessage());
                }

                addMessageToTextArea(inputMessageTextField.getText(), name);
                inputMessageTextField.setText("");
            }
        });
    }

    public void movePlayer(JButton currentButton, String moveTo) {
        DotsEnum dotsEnum = DotsEnum.valueOf(moveTo);
        switch (dotsEnum) {
            case a1:
                currentButton.setLocation(148, 150);
                break;
            case a2:
                currentButton.setLocation(148, 221);
                break;
            case a3:
                currentButton.setLocation(148,292);
                break;
            case a4:
                currentButton.setLocation(148,363);
                break;
            case a5:
                currentButton.setLocation(148,440);
                break;
            case a6:
                currentButton.setLocation(148,511);
                break;

            case b1:
                currentButton.setLocation(219, 150);
                break;
            case b2:
                currentButton.setLocation(219, 221);
                break;
            case b3:
                currentButton.setLocation(219,292);
                break;
            case b4:
                currentButton.setLocation(219,363);
                break;
            case b5:
                currentButton.setLocation(219,440);
                break;
            case b6:
                currentButton.setLocation(219,511);
                break;

            case c1:
                currentButton.setLocation(294, 150);
                break;
            case c2:
                currentButton.setLocation(294, 221);
                break;
            case c3:
                currentButton.setLocation(294,292);
                break;
            case c4:
                currentButton.setLocation(294,363);
                break;
            case c5:
                currentButton.setLocation(294,440);
                break;
            case c6:
                currentButton.setLocation(294,511);
                break;

            case d1:
                currentButton.setLocation(365, 150);
                break;
            case d2:
                currentButton.setLocation(365, 221);
                break;
            case d3:
                currentButton.setLocation(365,292);
                break;
            case d4:
                currentButton.setLocation(365,363);
                break;
            case d5:
                currentButton.setLocation(365,440);
                break;
            case d6:
                currentButton.setLocation(365,511);
                break;

            case e1:
                currentButton.setLocation(440, 150);
                break;
            case e2:
                currentButton.setLocation(440, 221);
                break;
            case e3:
                currentButton.setLocation(440,292);
                break;
            case e4:
                currentButton.setLocation(440,363);
                break;
            case e5:
                currentButton.setLocation(440,440);
                break;
            case e6:
                currentButton.setLocation(440,511);
                break;

            case f1:
                currentButton.setLocation(511, 150);
                break;
            case f2:
                currentButton.setLocation(511, 221);
                break;
            case f3:
                currentButton.setLocation(511,292);
                break;
            case f4:
                currentButton.setLocation(511,363);
                break;
            case f5:
                currentButton.setLocation(511,440);
                break;
            case f6:
                currentButton.setLocation(511,511);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String moveTo = JOptionPane.showInputDialog("Para onde quer ir?");
        JButton currentButton = (JButton)e.getSource();
        System.out.println(currentButton.getName());
        /*
        * pode fazer um if para saber se minhas peças são red ou blue
        * e me permitir controlar apenas as minhas peças.
        * é possível tbm mandar pro server essa info...
        * tem algumas possibilidades
        * */
        movePlayer(currentButton, moveTo);
    }

    private enum Sounds {
        NEWMESSAGE("src/Files/new-message.wav");

        private String value;
        private Sounds(String value) {
            this.value = value;
        }
    }

    enum DotsEnum {
        a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,
        a11,a12,
        b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,
        b11,b12,
        c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
        c11,c12,
        d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,
        d11,d12,
        e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,
        e11,e12,
        f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,
        f11,f12
    }
}
package Poker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PokerTableGUI extends JFrame implements ActionListener {
    
    private JLabel background;
    private ImageIcon tableImage;
    private JLabel[] playerCards;
    private ImageIcon[] cardImages;
    private JButton raiseButton, callButton, foldButton, checkButton;
    public int numPlayers = 5;

    public PokerTableGUI() {
        super("Poker Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // load the table image
        tableImage = new ImageIcon("C:\\Users\\nader\\Downloads\\Texas-Holdem-WAFA-main\\poker table grass copy.jpeg");
        background = new JLabel(tableImage);
        add(background, BorderLayout.CENTER);

        // load the card images
        cardImages = new ImageIcon[numPlayers * 2];
        for (int i = 0; i < cardImages.length; i++) {
        	cardImages[i] = new ImageIcon("C:\\Users\\nader\\Downloads\\Texas-Holdem-WAFA-main\\Back of Card\\back.png");
        }

        // set up the player cards
        playerCards = new JLabel[numPlayers * 2];
        for (int i = 0; i < numPlayers; i++) {
            playerCards[i * 2] = new JLabel(cardImages[i * 2]);
            playerCards[i * 2].setBounds(50 + i * 150, 500, 71, 96);
            playerCards[i * 2 + 1] = new JLabel(cardImages[i * 2 + 1]);
            playerCards[i * 2 + 1].setBounds(123 + i * 150, 500, 71, 96);
            background.add(playerCards[i * 2]);
            background.add(playerCards[i * 2 + 1]);
        }
        
        // set up the buttons
        raiseButton = new JButton("Raise");
        raiseButton.setBounds(350, 200, 100, 30);
        raiseButton.addActionListener(this);
        background.add(raiseButton);

        callButton = new JButton("Call");
        callButton.setBounds(460, 200, 100, 30);
        callButton.addActionListener(this);
        background.add(callButton);

        foldButton = new JButton("Fold");
        foldButton.setBounds(570, 200, 100, 30);
        foldButton.addActionListener(this);
        background.add(foldButton);

        checkButton = new JButton("Check");
        checkButton.setBounds(680, 200, 100, 30);
        checkButton.addActionListener(this);
        background.add(checkButton);

        // show the frame
        setSize(1000, 800);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // handle button presses here
        if (e.getSource() == raiseButton) {
            System.out.println("Raise button pressed");
        } else if (e.getSource() == callButton) {
            System.out.println("Call button pressed");
        } else if (e.getSource() == foldButton) {
            System.out.println("Fold button pressed");
        } else if (e.getSource() == checkButton) {
            System.out.println("Check button pressed");
        }
    }
    
    public static void main(String[] args)
    {
    	new PokerTableGUI();
    }
}

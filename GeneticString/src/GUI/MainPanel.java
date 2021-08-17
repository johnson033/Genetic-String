package GUI;

import GeneticAlgo.LaunchThread;
import GeneticAlgo.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    private final JTextField userInput;
    private final JLabel bestOrganismDNA;
    private final JLabel numberCorrectLabel;
    private final JLabel currentGenerationLabel;
    private boolean Locked = false;


    public MainPanel(int width, int height){
        this.setLayout(null);
        this.setBounds(0,0,width,height);
        this.setBackground(new Color(5, 8, 21, 255));

        userInput = new JTextField(20);
        userInput.setBounds(width/2 - (int)((width*.66) / 2),(int) (height *.33),(int)(width*.66),(int) (height*.1));
        userInput.setBackground(this.getBackground());
        userInput.setForeground(new Color(61, 90, 241));
        userInput.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(61, 90, 241)));
        userInput.setFont(new Font("Comic Sans", Font.BOLD, 40));
        userInput.setHorizontalAlignment(JTextField.CENTER);
        userInput.addActionListener(this);


        bestOrganismDNA = new JLabel("");
        bestOrganismDNA.setBounds(userInput.getX(), userInput.getY() + (int) (height*.126), userInput.getWidth(), userInput.getHeight());
        bestOrganismDNA.setHorizontalAlignment(JLabel.CENTER);
        bestOrganismDNA.setForeground(new Color(67, 221, 230));
        bestOrganismDNA.setFont(new Font("Comic Sans", Font.BOLD, 40));
        bestOrganismDNA.setBorder(BorderFactory.createMatteBorder(3,0,0,0,new Color(67, 221, 230)));

        numberCorrectLabel = new JLabel("");
        numberCorrectLabel.setBounds(userInput.getX(), userInput.getY() - (userInput.getY() / 2), userInput.getWidth(), userInput.getHeight());
        numberCorrectLabel.setFont(new Font("Comic Sans", Font.BOLD, 70));
        numberCorrectLabel.setHorizontalAlignment(JLabel.CENTER);
        numberCorrectLabel.setForeground(Color.white);
        numberCorrectLabel.setVisible(false);

        currentGenerationLabel = new JLabel();
        currentGenerationLabel.setBounds(userInput.getX(), numberCorrectLabel.getY() + (numberCorrectLabel.getY() / 2) , userInput.getWidth(), userInput.getHeight());
        currentGenerationLabel.setFont(new Font("Comic Sans", Font.ITALIC, 35));
        currentGenerationLabel.setHorizontalAlignment(JLabel.CENTER);
        currentGenerationLabel.setForeground(Color.white);
        currentGenerationLabel.setVisible(false);

        JButton quitButton = new JButton("Quit");
        quitButton.setBackground(this.getBackground());
        quitButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE));
        quitButton.setForeground(Color.white);
        quitButton.setBounds(width / 2 - ((int) (bestOrganismDNA.getWidth() *.33) / 2) , (int)(height * .6), (int) (bestOrganismDNA.getWidth() *.33), bestOrganismDNA.getHeight() / 2);
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        this.add(currentGenerationLabel);
        this.add(numberCorrectLabel);
        this.add(quitButton);
        this.add(bestOrganismDNA);
        this.add(userInput);

        new LaunchThread(this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.Locked) {
            this.Locked = true;
            userInput.setEditable(false);
            new Population(userInput.getText(), this);
            new Population(userInput.getText(), this);
        }
    }

    public void setBestOrganismDNA(String bestString){
        bestOrganismDNA.setText(bestString);
    }
    public void setLocked(boolean val){
        this.Locked = val;
        userInput.setEditable(true);
    }
    public void setNumberCorrectLabel(int number, int targetLength){
        this.numberCorrectLabel.setText(number+"/"+ targetLength);
    }

    public void setCorrectLabelVisible(){
        numberCorrectLabel.setVisible(true);
    }
    public void setCurrentGenerationLabel(int number){
        this.currentGenerationLabel.setText("Generation: " + number);
    }

    public void setCurrentGenerationLabelVisible(){
        currentGenerationLabel.setVisible(true);
    }
}

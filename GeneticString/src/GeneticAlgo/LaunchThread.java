package GeneticAlgo;

import GUI.MainPanel;

public class LaunchThread extends Thread {
    private final String[] launchStrings = new String[]{
            "Default Population Size is 2000",
            "                               ",
            "Default Maximum Generations is 1000",
            "                                   ",
            "Mutation rate changes based on String Size",
            "                                          ",
            "abcdefghijklmnopqrstuvwxyz",
            "                          ",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "                          ",
            "1234567890",
            "          ",
            "!@#$%^&*()_+=-[]{}|;':;<>,.?/",
            "                             ",
            "Are all Valid Characters to Enter",
            "                                 ",
            "Made by Kyle Johnson",
            "                    ",
            "* * * Made On 4/15/2021 * * *",
            "                             "
    };
    private int index = 0;
    private final MainPanel panel;
    public LaunchThread(MainPanel mainPanel){
        panel = mainPanel;
    }

    public void run(){
        while(index < launchStrings.length){
            Population currentString = new Population(launchStrings[index], panel);
            while(!currentString.complete()) {
                try {
                    Sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(index%2 == 0){
                try {
                    Sleep((int) (launchStrings[index].length() * 44L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    Sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index++;
        }
        panel.setCorrectLabelVisible();
        panel.setNumberCorrectLabel(0,0);
        panel.setCurrentGenerationLabelVisible();
        panel.setCurrentGenerationLabel(0);
    }

    private void Sleep(int time) throws InterruptedException {
        sleep(time);
    }
}

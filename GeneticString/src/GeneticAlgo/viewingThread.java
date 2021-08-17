package GeneticAlgo;

public class viewingThread extends Thread{
    private final Population population;
    public viewingThread(Population population){
        this.population = population;
    }

    @Override
    public void run(){
        while(true){
            if(!population.nextGeneration() && population.getCurrentGeneration() < population.getMaxGenerations()) {
                try {
                    Sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    Sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void Sleep(int time) throws InterruptedException {
        sleep(time);
    }
}

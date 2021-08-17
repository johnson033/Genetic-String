package GeneticAlgo;

import GUI.MainPanel;

import java.util.ArrayList;
import java.util.Random;

public class Population {

    private final int geneSize;
    private final int maxGenerations;
    private int currentGeneration = 0;
    private final int populationSize;
    private final int targetStringSize;
    private boolean complete = false;
    private final String targetString;
    private ArrayList<Organism> population;
    private final MainPanel panel;




    public Population(String targetString, MainPanel panel) {
        this.geneSize = targetString.length();
        this.maxGenerations = 1000;
        this.populationSize = 2000;
        this.targetString = targetString;
        this.targetStringSize = targetString.length();
        this.panel = panel;
        startEvolution();

        new viewingThread(this).start();
    }

    private void startEvolution() {
        this.population = new ArrayList<>(populationSize);
        for(int i = 0; i < populationSize; i++){
            char[] genes = new char[geneSize];
            for(int g = 0; g < geneSize; g++){
                genes[g] = (char) (new Random().nextInt(126 - 32) + 32);
            }
            this.population.add(new Organism(genes));
        }

        for(int i = 0; i < this.populationSize; i ++){
            System.out.println(this.population.get(i).getDNA());
        }
    }

    public boolean nextGeneration(){
            this.currentGeneration++;
            panel.setCurrentGenerationLabel(this.currentGeneration);
            evaluate();

            //set label on screen
            Organism best = this.population.get(0);
            for(Organism o : this.population){
                if(o.getFitness() > best.getFitness()){
                    best = o;
                }
            }
            setLabel(String.valueOf(best.getDNA()));
            panel.setNumberCorrectLabel(best.getNumCorrect(), targetStringSize);
            if(best.getFitness() >= 1){
                panel.setLocked(false);
                this.complete = true;
                return true;
            }

            ArrayList<Organism> nextGen = nextGen();
            mutate(nextGen);
            this.population.clear();
            this.population = nextGen;
            return false;
    }

    private void setLabel(String label){
        panel.setBestOrganismDNA(label);
    }
    private void evaluate(){

       Organism best = this.population.get(0);

        for(Organism o: this.population){
            double correctChars = 0.0;
            for(int  i = 0; i < this.targetStringSize; i++){
                if(o.getDNA()[i] == targetString.charAt(i)){
                    correctChars++;
                }
            }
            o.setNumCorrect((int) correctChars);
            o.setFitness(correctChars / targetStringSize);
            if(o.getFitness() > best.getFitness()) best = o;
        }
        
        System.out.println(best.getDNA());
    }

    private ArrayList<Organism> nextGen(){
        ArrayList<Organism> nextGen = new ArrayList<>(populationSize);
        for(int i = 0; i < populationSize; i++){
            int numberOfParents = 2;
            Organism[] parents = new Organism[numberOfParents];
            for(int p = 0; p < parents.length; p++){
                parents[p] = acceptReject();
            }
            nextGen.add(crossOver(parents));
        }
        return nextGen;
    }

    private Organism acceptReject(){
        while(true) {
            Organism parent = population.get(new Random().nextInt(populationSize));
            double check = Math.random();
            if (parent.getFitness() > check) {
                return parent;
            }
        }
    }

    private Organism crossOver(Organism[] parents){
        char[] newDNA = new char[geneSize];
        for(int i = 0; i < geneSize; i++){
            int parentIndex = new Random().nextInt(parents.length);
            newDNA[i] = parents[parentIndex].getDNA()[i];
        }
        return new Organism(newDNA);
    }

    private void mutate(ArrayList<Organism> generation){
        for (Organism current : generation) {
            for (int g = 0; g < geneSize; g++) {
                if (Math.random() < .04 / targetStringSize) {
                    current.getDNA()[g] = (char) (new Random().nextInt(126 - 32) + 32);
                }
            }
        }
    }

    public int getCurrentGeneration(){
        return this.currentGeneration;
    }
    public int getMaxGenerations(){
        return this.maxGenerations;
    }

    public boolean complete(){
        return this.complete;
    }
}
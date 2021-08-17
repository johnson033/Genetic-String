package GeneticAlgo;

public class Organism {
    private final char[] DNA;
    private double fitness;
    private int numCorrect;

    public Organism(char[] DNA){
        this.DNA = DNA;
        this.numCorrect = 0;
    }

    public double getFitness(){
        return this.fitness;
    }

    public char[] getDNA(){
        return this.DNA;
    }

    public int getNumCorrect(){
        return this.numCorrect;
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    public void setNumCorrect(int num){
        this.numCorrect = num;
    }

}

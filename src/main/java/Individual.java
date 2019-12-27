import java.util.Random;

public class Individual {

    public int[] chromosome;
    public int fitness = 0;

    Random random = new Random();

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    // Initializes individual
    public Individual() {
        this.chromosome = new int[3];
    }

    public void mutate(int offset) {
        if (this.getGene(offset) == 1) {
            this.setGene(offset, 0);
        } else {
            this.setGene(offset, 1);
        }
    }

    public void evaluate() {
        int p1 =chromosome[0];
        int p2 = chromosome[1];
        int p3 = chromosome[2];

        if(p1 <= 4 && p2 <= 30){
            setFitness((2 * p1 * p2 - p2) / p1 * p3);
        }

        else if( p2 < 50){
            setFitness((3 * p1 * p2 + 200) / (2 * p2) * p3);
        }
        else{
            setFitness((3 * p1 * p1 * p2 - 5 * p2 + 1000) / (p2 * p2) * p3);
        }
    }

    @Override
    public String toString() {

        String output = "Binary gene representation: ";
        for (int i = 0; i < this.chromosome.length; i++) {
            output += this.getGene(i);
        }
        System.out.println(output);
        System.out.println("Fitness: " + this.getFitness());
        return output;
    }
}

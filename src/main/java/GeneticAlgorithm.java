
import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

    int populationSize;
    int chromosomeSize;
    double crossoverRate;
    double mutationRate;

    public Random random = new Random();

    //@Constructor
    public GeneticAlgorithm(int populationSize, int chromosomeSize, double crossoverRate, double mutationRate) {
        this.populationSize = populationSize;
        this.chromosomeSize = chromosomeSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    // Perform initialisation of population
    public Individual[] initPopulation() {
        Individual[] population = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual();
            population[i].chromosome[0] = random.nextInt((6)) + 2;
            population[i].chromosome[1] = random.nextInt((90)) + 10;
            population[i].chromosome[2] = random.nextInt((5)) + 2;
        }

        return population;
    }

    //Evaluate population
    public void evaluatePopulation(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            population[i].evaluate();
        }
    }

    //Do Crossover
    public Individual[] crossover(Individual[] population) {
        for (int i = 0; i < population.length - 1; i += 2) {

            int offspring = random.nextInt(3);

            for (int j = 0; j < offspring; j++) {
                int aux = population[i].getGene(j);
                population[i].setGene(j, population[i + 1].getGene(j));
                population[i + 1].setGene(j, aux);
            }
        }
        return population;
    }

    // Do Mutation
    public Individual[] mutate(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            for (int j = 0; j < population[i].getChromosome().length; j++) {
                if (random.nextDouble() < mutationRate) {
                    population[i].mutate(j);
                }
            }
        }
        return population;
    }

    /*
     * @param population
     *          The population to apply mutation to
     * @return The mutated population
     */
    public Individual[] mutatePopulation(Individual[] population) {
        // Initialize new population
        Individual[] newPopulation = initPopulation();

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.length; populationIndex++) {
            population[populationIndex].getFitness();

            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < chromosomeSize; geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= chromosomeSize) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (population[populationIndex].getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        population[populationIndex].setGene(geneIndex, newGene);
                    }
                }
            }

            // Add individual to population
            population = newPopulation; //setIndividual(populationIndex, individual);
        }

        // Return mutated population
        return newPopulation;
    }

    // Elitism Selection
    public Individual[] elitism(Individual[] population) {
        Individual min = population[0];
        int minOffset = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() <= min.getFitness()) {
                min = population[i];
                minOffset = i;
            }
        }
        Individual max = population[0];
        int maxOffset = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() >= max.getFitness()) {
                max = population[i];
                maxOffset = i;
            }
        }
        population[minOffset] = population[maxOffset];
        return population;
    }

    // Do Total Fitness
    public int totalFitness(Individual[] population) {
        int population_fitness = 0;
        for (int i = 0; i < population.length; i++) {
            population_fitness += population[i].getFitness();
        }
        return population_fitness;
    }

    //Do Average Fitness
    public double avgFitness(Individual[] population) {
        return (double) totalFitness(population) / population.length;
    }

    // Do Best Fitness
    public int bestFitness(Individual[] population) {
        int max = population[0].getFitness();
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() > max) {
                max = population[i].getFitness();
            }
        }
        return max;
    }

    // Do best individual
    public Individual bestIndividual(Individual[] population) {
        Individual max = population[0];
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() >= max.getFitness()) {
                max = population[i];
            }
        }
        return max;
    }

    // Perform print fitness action
    public void printFitness(Individual[] population) {
        System.out.println("Total fitness: " + totalFitness(population));
        System.out.println("Average fitness: " + avgFitness(population));
        System.out.println("Best fitness: " + bestFitness(population));
        System.out.println("Best individual: " + bestIndividual(population));
    }

    // Perform print population action
    public void printPopulation(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            System.out.println(Arrays.toString(population));
        }
    }
}

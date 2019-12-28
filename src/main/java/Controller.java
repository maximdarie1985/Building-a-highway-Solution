import java.io.IOException;

public class Controller {

    public static void main(String[] args) throws IOException {

        MyFitnessFunction.generateRandomFile("matrix.txt", 256);
        MyFitnessFunction myFitnessFunction = new MyFitnessFunction("matrix.txt");

        final int populationSize = 20;                              // population size
        final int chromosomeLength = 3;                            // chromosome length (p1,p2,p3)
        final double crossoverRate = 0.70;                          // crossover rate
        final double mutationRate = (double) 1 / populationSize;    // mutation rate
        final int numberOfGeneration = 6;                           // # of generations

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(populationSize, chromosomeLength, crossoverRate, mutationRate);

        // Initialise population of random individuals
        Individual[] population = geneticAlgorithm.initPopulation();

        // "Counting ones" fitness evaluation
        System.out.println("GEN0");
        geneticAlgorithm.evaluatePopulation(population);
        geneticAlgorithm.printFitness(population);

        int generation = 0;
        for (int i = 0; i < numberOfGeneration; i++) {

            System.out.println("\nGeneration: " + generation);

            // Elitism selection
            population = geneticAlgorithm.elitism(population);

            // Elitism winners fitness evaluation
            geneticAlgorithm.evaluatePopulation(population);

            // Single-point crossover
            population = geneticAlgorithm.crossover(population);

            // Crossover children fitness evaluation
            geneticAlgorithm.evaluatePopulation(population);

            // Apply mutation to population
            population = geneticAlgorithm.mutatePopulation(population);

            // Post-mutation population fitness evaluation
            geneticAlgorithm.evaluatePopulation(population);
            geneticAlgorithm.printFitness(population);

            generation++;

            if (geneticAlgorithm.bestFitness(population) == chromosomeLength) {
                geneticAlgorithm.printPopulation(population);
                break;
            }

            MyFitnessFunction.printLongInBin(1000, 10);
        }
    }
}

package ga;

import org.jgap.InvalidConfigurationException;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.DefaultConfiguration;

/**
 * Main class to call to GA Library.
 */
public class KnapsackProblemGA {
	private static int MAX_ALLOWED_EVOLUTIONS = 200;
	/**
	 * Main method.
	 * Create a Configuration and Initial Genotype,
   	 * with the population size of configuration for JGAP Library.
	 * Evolve the problem.
	 * @param args Arguments for the main function.
	 */
	public static void main(String[] args) throws InvalidConfigurationException {
		Configuration conf = new DefaultConfiguration();
		int[] values = new int[] {20,30,50,10};
		int[] weights = new int[] {2,5,10,5};
		KnapsackProblem myKnapsack = new KnapsackProblem(16,values,weights);
		KnapsackFitness myFunc = new KnapsackFitness(myKnapsack);

		conf.setFitnessFunction(myFunc);

		// Configuracion de los cromosomas
		Gene[] genes = new Gene[5];	// para este problema hay en total 5 items en total para meter en la mochila
		
		/**
		 * Cada uno de los genes que componen al individuo son genes Booleanos
		 * Si en el indice 2 hay un 0, significa que el item 2 no esta en la mochila, si hay un 1,
		 * ese item esta en la mochila.
		 */ 
		for (int i = 0; i < genes.length; i++){
			genes[i] = new BooleanGene(conf);
		}

		Chromosome sampleChromosome = new Chromosome(conf,genes);

		// Configurar la poblacion inicial
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(500);	// size of the population

		// setteamos la poblacion inicial
		Genotype population = Genotype.randomInitialGenotype(conf);

		// evolucionamos la poblacion inicial
		for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++){
			population.evolve();
		}

		IChromosome bestSolutionSoFar = population.getFittestChromosome();

		double fitnessValue = bestSolutionSoFar.getFitnessValue();
		System.out.println("Best Fitness Value: " + fitnessValue);

		Gene[] genesBestSolution = bestSolutionSoFar.getGenes();
		System.out.println("Items in the Knapsack:");
		for (int i = 0; i < genesBestSolution.length; i++) {
			BooleanGene booleanGene = (BooleanGene) genesBestSolution[i];
			if (booleanGene.booleanValue()) {
				System.out.println("Item " + i + " is in the Knapsack");
			}
		}
	}
}

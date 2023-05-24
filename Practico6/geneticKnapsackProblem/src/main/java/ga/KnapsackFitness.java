package ga;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.impl.BooleanGene;

/**
 * This class is used for calculate the fitness value.
 *
 */
public class KnapsackFitness extends FitnessFunction {

    /**
     * reference to knapsack instance.
     */
    private KnapsackProblem myKnapsack;

    /**
    * KnapsackFitness constructor.
    * @param knapsack instance.
    */
    public KnapsackFitness(KnapsackProblem knapsack) {
        myKnapsack = knapsack;
    }

    /**
    * Implements the fitness function, how good is the chromosome I am evaluating.
    * @param chromosome Chromosome to evaluate
    * @return fitness value
    */
    @Override
    protected double evaluate(IChromosome chromosome) {
        // The fitness value is calculated by the sum of the current amount of items in the bag and the 
        // total of value they have
        int cantItemsInBag = 0;  // quantity of items int the bag for that specific chromosome
        int[] valuesOfItemsInBag = myKnapsack.getValues();  // values of the items in the bag
        int[] weightsOfItemsInBag = myKnapsack.getWeights();  // weights of the items in the bag

        int totalValues = 0;    // total value summed up by all the items in the bag
        int totalWeight = 0;   // total weights summed up by all the items in the bag

        Gene[] genes = chromosome.getGenes();   // genes of the chromosome
        for (int i = 0; i < valuesOfItemsInBag.length; i++){
            BooleanGene booleanGene = (BooleanGene) genes[i];
            if (booleanGene.booleanValue()){
                cantItemsInBag++;
                totalValues += valuesOfItemsInBag[i];
                totalWeight += weightsOfItemsInBag[i];
            }
        }
        
        double weightFactor = 0.5;
        // no me sirve porque tiene mas items que la cantidad maxima aceptada en la mochila
        if (totalWeight > myKnapsack.getMaxCapacity()) return 0;
        return (cantItemsInBag * weightFactor) + totalValues;
    }
}

package es.unileon.rnag.operator.selection;

import es.unileon.rnag.chromosome.Chromosome;

/**
 * Class that implements the roulette selection
 * @author Adrian Casimiro Alvarez
 * @author Javier de Pedro L�pez
 */
public class RouletteSelection extends SelectionStrategy{

	@Override
	public Chromosome[] doSelection(Chromosome[] previousGeneration) {

		int i;
		double total = 0;
		for(i=0; i<previousGeneration.length; i++){
			total = total + this.previousChromosome[i].getFitness();
		}
		
		int[] roulette = new int[100];
		int actualPosition = 0;
		double probability;
		int totalCells;
		for(i=0; i<previousGeneration.length; i++){
			probability = this.previousChromosome[i].getFitness() / total;
			totalCells = (int) (probability * 100);
			for(int j=0; j<totalCells; j++){
				roulette[j+actualPosition] = i;
			}
			actualPosition = actualPosition + totalCells;
		}
		
		//If it was left empty positions (this would be almost impossible)
		for(int j = actualPosition; j<100;j++){
			roulette[j] =(int) (Math.random() * previousGeneration.length);	
		}
		
		this.newChromosome = new Chromosome[this.previousChromosome.length];
		for(i=0; i<previousGeneration.length; i++){
			this.newChromosome[i] = this.previousChromosome[roulette[(int)(Math.random() * 100)]];
		}
		
		return this.newChromosome;
	}

}

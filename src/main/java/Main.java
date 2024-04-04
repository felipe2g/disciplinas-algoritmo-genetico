import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Individual;
import Entities.Schedule;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Individual> population = initialization();
        ArrayList<Individual> fitnessPopulation = fitness(population);
        ArrayList<Individual> ordering = ordering(fitnessPopulation);
        ArrayList<Individual> crossover = crossover(ordering);

        //System.out.println(population);

        //exportToHTMLFile(fitnessPopulation);
    }

    public static ArrayList<Individual> initialization() {
        ArrayList<Individual> population = new ArrayList<Individual>();

        for (int i = 1; i <= GlobalVariables.POPULATION_SIZE; i++) {
            population.add(new Individual().generate());
        }

        return population;
    }

    public static ArrayList<Individual> fitness(ArrayList<Individual> population) {
        for (Individual individual : population) {
            Double rate = 0.0;

            int individualSize = individual.getCourse().size()/WeekDate.getList().size();
            int weekDateListSize = WeekDate.getList().size();

            for (int k = 0; k < individualSize; k++) {
                for (int i = 0; i < individualSize; i++) {
                    for (int j = 0; j < (weekDateListSize - 1) - k; j++) {
                        for (int l = 0; l < GlobalVariables.DISCIPLINES_PER_DAY; l++) {
                            int firstItemToCompare = i + (k * weekDateListSize);
                            int secondItemToCompare = i + (j * weekDateListSize) + (k * weekDateListSize) + weekDateListSize;

                            Discipline firstItem = individual.getCourse().get(firstItemToCompare).getDisciplines().get(l);
                            Discipline secondItem = individual.getCourse().get(secondItemToCompare).getDisciplines().get(l);

                            if(firstItem.getTeacher().getId().equals(secondItem.getTeacher().getId())) {
                                rate++;
                                firstItem.setScheduleConflict(true);
                                secondItem.setScheduleConflict(true);

                                System.out.println("@@@ Conflito => " + firstItem.getTeacher().getName() + " e " + secondItem.getTeacher().getName());
                            }

                            System.out.println("HORÁRIO " + l);
                            System.out.println(firstItemToCompare + " => " + secondItemToCompare);
                        }
                    }
                }
            }

            individual.setRate(rate);
        }

        return population;
    }

    public static ArrayList<Individual> ordering(ArrayList<Individual> population) {
        List<Individual> list = population.stream().sorted((a1, a2) -> a1.getRate().compareTo(a2.getRate())).toList();

        return new ArrayList<>(list);
    }

    public static ArrayList<Individual> crossover (ArrayList<Individual> population) {
        for (int i = 0; i < population.size(); i++) {
            Random random = new Random();
            int secondRandomItem = random.nextInt(population.size());

            while (secondRandomItem == i) {
                secondRandomItem = random.nextInt(population.size());
            }

            Individual actualIndividual = population.get(i);
            Individual individualToCrossover = population.get(secondRandomItem);

            int quantityCutPossibility = 25/WeekDate.getList().size();
            //TODO: Ajustar 25 para número de dias da semana
            int cutDotsCount = GlobalVariables.CUT_DOTS_COUNT == 0 ? random.nextInt(quantityCutPossibility) + 1 : GlobalVariables.CUT_DOTS_COUNT;

            ArrayList<Integer> cutDots = new ArrayList<Integer>();

            for (int j = 0; j < cutDotsCount; j++) {
                int randomCutPoint = random.nextInt(cutDotsCount);

                while (cutDots.contains(randomCutPoint)) {
                    randomCutPoint = random.nextInt(cutDotsCount);
                }

                cutDots.add(randomCutPoint);
            }

            List<Integer> sortedCutDots = cutDots.stream().sorted().toList();
            ArrayList<Integer> sortedCutDotsArray = new ArrayList<>(sortedCutDots);

            Individual firstCrossoveredIndividual = new Individual();
            Individual secondCrossoverIndividual = new Individual();

            for (int j = 0; j < sortedCutDotsArray.size(); j++) {
                int cutPoint = (sortedCutDotsArray.get(j) * WeekDate.getList().size());

                for (int k = 0; k < WeekDate.getList().size(); k++) {
                    Schedule actualSchedule = actualIndividual.getCourse().get(cutPoint + k);
                    Schedule crossoverSchedule = individualToCrossover.getCourse().get(cutPoint + k);

                    secondCrossoverIndividual.getCourse().add(actualSchedule);
                    firstCrossoveredIndividual.getCourse().add(crossoverSchedule);
                }
            }

            population.set(i, firstCrossoveredIndividual);
            population.set(secondRandomItem, secondCrossoverIndividual);
        }

        return population;
    }
}
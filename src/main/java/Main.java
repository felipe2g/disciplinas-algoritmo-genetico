import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Individual;
import Entities.Schedule;

import java.io.IOException;
import java.sql.SQLOutput;
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
                            }
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
        ArrayList<Individual> newPopulation = new ArrayList<>();
        while(!population.isEmpty()) {
            int populationMaxIndex = population.size() - 1;

            if(population.size() == 1) {
                newPopulation.add(population.get(0));
                population.remove(0);
                break;
            }
            Random random = new Random();
            int i = random.nextInt(populationMaxIndex);
            Individual actualIndividual = population.get(i);
            actualIndividual.setRate(0.0);

            int secondRandomItem = random.nextInt(populationMaxIndex);
            Individual individualToCrossover = population.get(secondRandomItem);
            individualToCrossover.setRate(0.0);

            while (secondRandomItem == i) {
                secondRandomItem = random.nextInt(populationMaxIndex);
                individualToCrossover = population.get(secondRandomItem);
            }

            int quantityCutPossibility = 25 / WeekDate.getList().size();

            for (int j = 0; j < quantityCutPossibility; j++) {
                boolean crossover = random.nextBoolean();

                if(crossover) {
                    for (int k = 0; k < WeekDate.getList().size(); k++) {
                        int positionToGet = (j * WeekDate.getList().size()) + k;
                        Schedule actualSchedule = actualIndividual.getCourse().get(positionToGet);
                        Schedule crossoverSchedule = individualToCrossover.getCourse().get(positionToGet);

                        actualIndividual.getCourse().set(positionToGet, crossoverSchedule);
                        individualToCrossover.getCourse().set(positionToGet, actualSchedule);
                    }
                }
            }

            actualIndividual.getCourse().forEach(schedule -> schedule.getDisciplines().forEach(discipline -> discipline.setScheduleConflict(false)));
            newPopulation.add(actualIndividual);
            individualToCrossover.getCourse().forEach(schedule -> schedule.getDisciplines().forEach(discipline -> discipline.setScheduleConflict(false)));
            newPopulation.add(individualToCrossover);
            population.remove(i);
            population.remove(secondRandomItem);
        }

        return newPopulation;
    }
}
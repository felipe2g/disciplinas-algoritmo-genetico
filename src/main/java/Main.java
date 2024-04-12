import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Individual;
import Entities.Schedule;

import java.io.IOException;
import java.util.*;

import static Export.ExportFile.exportJSONToFile;
import static Export.ExportFile.exportToHTMLFile;


public class Main {
    public static void main(String[] args) throws IOException {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Individual> population = initialization();
        ArrayList<Individual> fitnessPopulation = fitness(population);
        ArrayList<Individual> ordering = ordering(fitnessPopulation);
        ArrayList<Individual> crossover = crossover(ordering);
        ArrayList<Individual> fitnessCrossover = fitness(crossover);

        ArrayList<Individual> mutation = ordering(mutation(fitnessCrossover));

        exportToHTMLFile(mutation);
        exportJSONToFile(mutation);
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
            int weekDateListSize = WeekDate.getList().size();

            for (int k = 0; k < GlobalVariables.DISCIPLINES_PER_DAY; k++) {
                for (int i = 0; i < weekDateListSize; i++) {
                    Set<String> teachersName = new HashSet<>();
                    Set<String> duplicatedTeachersName = new HashSet<>();
                    for (int j = 0; j < weekDateListSize; j++) {
                        int indexToAdd = (j * 5) + i;

                        if(!teachersName.add(individual.getCourse().get(indexToAdd).getDisciplines().get(k).getTeacher().getName())) {
                            duplicatedTeachersName.add(individual.getCourse().get(indexToAdd).getDisciplines().get(k).getTeacher().getName());
                        }
                    }

                    if(!duplicatedTeachersName.isEmpty()) {
                        for (int l = 0; l < weekDateListSize; l++) {
                            int indexToAdd = (l * 5) + i;

                            Discipline discipline = individual.getCourse().get(indexToAdd).getDisciplines().get(k);
                            String teacherName = discipline.getTeacher().getName();

                            if(duplicatedTeachersName.contains(teacherName)) {
                                discipline.setScheduleConflict(true);
                                rate++;
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

    public static ArrayList<Individual> crossover(ArrayList<Individual> population) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        while (!population.isEmpty()) {
            int populationMaxIndex = population.size() - 1;

            if (population.size() == 1) {
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

                if (crossover) {
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

    public static ArrayList<Individual> mutation(ArrayList<Individual> population) {
        int itemsPerWeek = 25 / WeekDate.getList().size();

        for (Individual individual : population) {
            for (int j = 0; j < itemsPerWeek; j++) {
                Random random = new Random();
                double chance = random.nextDouble();

                if(chance < GlobalVariables.MUTATION_PROBABILITY) {
                    int firstRandomItem = random.nextInt(itemsPerWeek) + (j * itemsPerWeek);
                    int secondRandomItem = random.nextInt(itemsPerWeek) + (j * itemsPerWeek);

                    while (secondRandomItem == firstRandomItem) {
                        secondRandomItem = random.nextInt(itemsPerWeek) + (j * itemsPerWeek);
                    }

                    Schedule firstSchedule = individual.getCourse().get(firstRandomItem);
                    Schedule secondSchedule = individual.getCourse().get(secondRandomItem);

                    individual.getCourse().set(secondRandomItem, firstSchedule);
                    individual.getCourse().set(firstRandomItem, secondSchedule);
                }
            }
        }

        return population;
    }
}
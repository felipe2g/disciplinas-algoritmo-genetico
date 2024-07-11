package Common;

public class GlobalVariables {
    // Need always be positive more than 2 and even
    public static Integer POPULATION_SIZE = 999;
    public static Integer PERIOD_COUNT = 5;
    public static Integer DISCIPLINES_PER_DAY = 2;
    public static Boolean HAS_SAME_DISCIPLINE_IN_DAY = false;
    public static Long START_TIME;
    public static int CUT_DOTS_COUNT = 0;
    public static double MUTATION_PROBABILITY = 0.10;
    public static int THREAD_COUNT = 16;

    // Run statistics
    public static Boolean MEASURE_STATISTICS = true;
    public static int TOTAL_MEASUREMENTS_COUNT_FOR_PROBABILITY = 100;
    public static double MEASURE_MUTATION_INCREMENT = 0.01;
    public static double MAX_MEASURE_MUTATION = 0.9;
    public static int MAX_PROCESSING_COUNTS = 100000;
}

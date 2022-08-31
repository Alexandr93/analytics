package analytics;

public class Main {
    private static final String FILE_PATH = "src/main/resources/input.txt";


    public static void main(String[] args) {

        Analytics analytics = new Analytics();
        analytics.getAnalytics(FILE_PATH).forEach(System.out::println);

    }
}

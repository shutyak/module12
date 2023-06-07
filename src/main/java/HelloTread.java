import java.util.Scanner;

/*
Завдання 1
        Напишіть програму, яка кожну секунду відображає на екрані дані про час, що минув від моменту запуску програми.
        Другий потік цієї ж програми кожні 5 секунд виводить повідомлення Минуло 5 секунд.
*/
public class HelloTread {
    public static void main(String[] args) {
        Thread everySecondTask = new Thread(() -> {
            final long startTime = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread has been interrupted");
                    Thread.currentThread().interrupt();
                    continue;
                }
                long milsFromStart = System.currentTimeMillis() - startTime;
                System.out.println("Пройшло " + milsFromStart / 1000 + " сек.");
            }
        });
        Thread fiveSecondTask = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread has been interrupted");
                    Thread.currentThread().interrupt();
                    continue;
                }
                System.out.println("Минуло 5 секунд");
            }
        });

        everySecondTask.start();
        fiveSecondTask.start();
        final var scanner = new Scanner(System.in);
        System.out.println("Press Enter to interrupt");
        scanner.nextLine();
        everySecondTask.interrupt();
        fiveSecondTask.interrupt();
    }
}

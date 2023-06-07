import java.util.function.IntConsumer;

class FizzBuzz {
    private final int n;
    private int num = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 15 == 0) {
                printFizzBuzz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 == 0 && num % 5 != 0) {
                printFizz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 == 0) {
                printBuzz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 != 0) {
                printNumber.accept(num);
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public static void main(String[] args) {
        int n = 20; // Set the value of 'n' as desired

        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadFizzBuzz = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("FizzBuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadFizz = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("Fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadBuzz = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("Buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadNumber = new Thread(() -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadFizzBuzz.start();
        threadFizz.start();
        threadBuzz.start();
        threadNumber.start();

    }
}
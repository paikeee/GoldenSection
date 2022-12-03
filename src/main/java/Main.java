public class Main {

   public static void main(String[] args) throws InterruptedException {
       long startTime;
       double elapsedTime;

       // one thread
       GoldenSection oneThreadTest = new GoldenSection(-7, 1);
       startTime = System.nanoTime();
       oneThreadTest.start();
       oneThreadTest.join();
       elapsedTime = ((double) (System.nanoTime() - startTime)) / 1_000_000;
       System.out.println("One thread TEST:" +
               "\nmin = " + oneThreadTest.min + " max = " + oneThreadTest.max +
               "\nelapsed time: " + elapsedTime);

       // two threads
       GoldenSectionMin twoThreadTest1 = new GoldenSectionMin(-7, 1);
       GoldenSectionMax twoThreadTest2 = new GoldenSectionMax(-7, 1);
       startTime = System.nanoTime();
       twoThreadTest1.start();
       twoThreadTest2.start();
       twoThreadTest1.join();
       twoThreadTest2.join();
       elapsedTime = ((double) (System.nanoTime() - startTime)) / 1_000_000;
       System.out.println("\nTwo threads TEST:" +
               "\nmin = " + twoThreadTest1.min + " max = " + twoThreadTest2.max +
               "\nelapsed time: " + elapsedTime);

       // test 1000 times
       double elapsedTimeOneThread = 0;
       double elapsedTimeTwoThreads = 0;
       for (int i = 0; i < 1000; i++) {

           // one thread
           oneThreadTest = new GoldenSection(getRandomNum(-10, 5), getRandomNum(-10, 5));
           startTime = System.nanoTime();
           oneThreadTest.start();
           oneThreadTest.join();
           elapsedTimeOneThread += ((double) (System.nanoTime() - startTime)) / 1_000_000;

           // two threads
           twoThreadTest1 = new GoldenSectionMin(getRandomNum(-10, 5), getRandomNum(-10, 5));
           twoThreadTest2 = new GoldenSectionMax(getRandomNum(-10, 5), getRandomNum(-10, 5));
           startTime = System.nanoTime();
           twoThreadTest1.start();
           twoThreadTest2.start();
           twoThreadTest1.join();
           twoThreadTest2.join();
           elapsedTimeTwoThreads += ((double) (System.nanoTime() - startTime)) / 1_000_000;
       }
       System.out.println("\n\nMedian One thread: " + elapsedTimeOneThread / 1000 +
               "\nMedian Two threads: " + elapsedTimeTwoThreads / 1000);
   }

   private static int getRandomNum(int min, int max) {
       return (int) (Math.random() * ((max - min) + 1)) + min;
   }
}

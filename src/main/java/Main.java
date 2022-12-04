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

           int from = getRandomNum(-10, 4);
           int to = getRandomNum(from, 5);
           // one thread
           oneThreadTest = new GoldenSection(from, to);
           startTime = System.nanoTime();
           oneThreadTest.start();
           oneThreadTest.join();
           elapsedTimeOneThread += ((double) (System.nanoTime() - startTime)) / 1_000_000;

           // two threads
           twoThreadTest1 = new GoldenSectionMin(from, to);
           twoThreadTest2 = new GoldenSectionMax(from, to);
           startTime = System.nanoTime();
           twoThreadTest1.start();
           twoThreadTest2.start();
           twoThreadTest1.join();
           twoThreadTest2.join();
           elapsedTimeTwoThreads += ((double) (System.nanoTime() - startTime)) / 1_000_000;
       }
       System.out.println("\nMedian One thread: " + elapsedTimeOneThread / 1000 +
               "\nMedian Two threads: " + elapsedTimeTwoThreads / 1000);
   }

   private static int getRandomNum(int min, int max) {
       return (int) (Math.random() * ((max - min) + 1)) + min;
   }
}

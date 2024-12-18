1. Create a program to reproduce the Counter Thread interference issue, run several times and 
explain the result.
2. Come up with a synchronization mechanism (other than using synchronized keyword) to 
make the Counter work as expected.
3. Create a Singleton Class. And write a code to test if your singleton class work in multithread 
environment?
4. Solve the following deadlock situation:
    public class driver{
    public static void main(String args[]){
    	 	 Object key1 = new Object();
    	 	 Object key2 = new Object();
    	 	 Thread t8 = new Thread( () -> {
    	 	 	 synchronized (key1) {
    	 	 	 	 System.out.println(“t8 has key 1.”);
    	 	 	 	 try {
    	 	 	 	 	 Thread.sleep(5000);
    	 	 	 	 } catch (InterruptedException e){
    	 	 	 	 	 e.printStackTrace();
    	 	 	 	 }
    	 	 	 	 synchronized (key2) {
    	 	 	 	 	 System.out.println(“t8 has key 2”);
    	 	 	 	 }
    	 	 	 }
    	 	 });
    	 	 Thread t9 = new Thread( () -> {
    	 	 	 synchronized (key2) {
    	 	 	 	 System.out.println(“t9 has key 2.”);
    	 	 	 	 synchronized (key1) {
    	 	 	 	 	 System.out.println(“t9 has key 1”);
    	 	 	 	 }
    	 	 	 }
    	 	 });
    	 	 t8.start();
    	 	 t9.start();
    }
    }

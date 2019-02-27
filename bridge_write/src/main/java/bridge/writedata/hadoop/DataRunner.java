package bridge.writedata.hadoop;



import java.io.IOException;
import java.util.Scanner;

public class DataRunner {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Manager manager = new Manager();
        Thread t1 = new Thread(manager);
        String cmd = "";
        Scanner scanner = new Scanner(System.in);

        t1.start();
        System.out.println("job is running");
        System.out.println("input any key to stop job");
        cmd = scanner.nextLine();
        manager.stop = true;
        System.out.println("job is stopping,plz waiting for job to be finish");
        t1.join();
        System.out.println("job already stopped");
    }

}

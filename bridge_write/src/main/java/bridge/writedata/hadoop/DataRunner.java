package bridge.writedata.hadoop;



import java.io.IOException;
import java.util.Scanner;

public class DataRunner {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {


        Manager manager = new Manager();
        if(args == null | args.length <1){

        }else{
            StaticData.dataNum = Integer.parseInt(args[0]);
            System.out.println();
            System.out.println();
            System.out.println(StaticData.dataNum);
            System.out.println();
            System.out.println();

            if(args.length>2){
                manager.count = Integer.parseInt(args[1]);
            }
        }

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

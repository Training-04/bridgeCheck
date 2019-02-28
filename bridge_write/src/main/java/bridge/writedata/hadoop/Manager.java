package bridge.writedata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/*
 * Job管理类
 * 实现了多线程接口，可以在其他线程运行
 * 可以管理执行多少次，什么时候停止
 * 可以设置延时
 */
public class Manager implements Runnable {
    public boolean stop = false;
    Long nowTime = 0L;
    public int count = 20;

    public void run() {
        int index = 0;
        nowTime = System.currentTimeMillis();
        while (!stop) {
            index++;
            try {
                runJob("/demo/input");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (index == count) {
//                break;
//            }
        }
        System.out.println("job stop");

    }

    public void runJob(String inputPath) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "master");
        //设置当前时间，用来做rowkey
//        Long beforeTime = System.currentTimeMillis();
        //使用nowTime，人工赋值，保证时间一致性
        conf.set("time", nowTime + "");
        Job job = Job.getInstance(conf);

        job.setMapperClass(ScanMapper.class);
        job.setReducerClass(PutReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, new Path(inputPath));

        job.setJarByClass(Manager.class);

        TableMapReduceUtil.initTableReducerJob("sensor", PutReducer.class, job);

        job.waitForCompletion(true);

        System.out.println("run success");
        //等待时间,当前时间-开始时间,如果小于60s，就等待够60s
        //提前一分钟写
        nowTime += StaticData.dataNum * 1000;
        //设使用了45s写入120s的数据，即现在处于120s, 实际时间为45s
        // 120-45s = 75s
        // 所以应该等待为 75s -60s =15s
        // 等待后数据库时间为120s ，实际时间为60s
        // 下一次写入的时间为45s 数据库处于240s，实际处于45s +60s = 105s
        // 240 - 105s = 135s
        // 等待时间 135s - 60s = 75s
        // 等待后 75 s +105s = 180s 和现在差60s

        //所以实际上现在时间比数据库时间永远少60s
        // 这个时间实际上是dataNum*1000 - 60 * 1000
        Long timeSpan = nowTime - System.currentTimeMillis();
        System.out.println("timeSpan:" + timeSpan);
        if (timeSpan - 60 * 1000  > 0) {
            Thread.sleep(timeSpan - 60 * 1000 );
        }
        //循环给时间
        System.out.println("next begin");

    }
}

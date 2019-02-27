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

    public void run() {
        int index = 0;
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
            if (index == 10) {
                break;
            }
        }
        System.out.println("job stop");

    }

    public void runJob(String inputPath) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "localhost");
        //设置当前时间，用来做rowkey
        Long beforeTime = System.currentTimeMillis();
        conf.set("time", beforeTime + "");
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

        Long timeSpan = System.currentTimeMillis() - beforeTime;
        System.out.println("timeSpan:"+timeSpan);
        if (60*1000-timeSpan > 0) {
            Thread.sleep(60 * 1000 - timeSpan);
        }
        System.out.println("next begin");

    }
}

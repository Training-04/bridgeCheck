package bridge.writedata.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class ScanMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Long sensorValue = 0L;
        String id = "";
        Long minValue = 0L;
        Long maxValue = 0L;
        String line = value.toString();
        String[] datas = StringUtils.split(line, ' ');

        if (datas.length >= 3) {

            id = datas[0];
            try {
                minValue = Long.parseLong(datas[1]);
                maxValue = Long.parseLong(datas[2]);
            } catch (Exception e) {
                e.getStackTrace();
            }
            //两分钟
            for(int i=0;i<StaticData.dataNum;i++) {
                Random random = new Random(UUID.randomUUID().hashCode());
                sensorValue = random.nextInt(maxValue.intValue()-minValue.intValue()) + minValue;
                context.write(new Text(id), new LongWritable(sensorValue));
            }
        }
    }
}
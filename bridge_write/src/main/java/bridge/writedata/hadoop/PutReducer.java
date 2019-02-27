package bridge.writedata.hadoop;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutReducer extends TableReducer<Text, LongWritable, ImmutableBytesWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        String column = "sensor" + key.toString();
        Long finalValue = 0L;
        String timeStr = context.getConfiguration().get("time");
        Long rowKey = Long.parseLong(timeStr);
        for (LongWritable value : values) {
            finalValue = value.get();

            //每个sensor写dataNum条
            Put put = new Put(Bytes.toBytes(rowKey+""));
            put.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes(column), Bytes.toBytes(finalValue+""));

            context.write(new ImmutableBytesWritable(Bytes.toBytes(rowKey+"")), put);
            rowKey += 1000;
        }
    }
}

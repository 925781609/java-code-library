package com.liuil;

import com.liuil.springboot.hbase.Application;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class HBaseTest {
    private static final String TABLE_NAME = "table_name";
    /**
     * HBase列簇名
     */
    private static final byte[] FAMILY = "family_name".getBytes();

    /**
     * 列名
     */
    private static final byte[] ID = "id".getBytes();
    private static final byte[] UID = "uid".getBytes();
    private static final byte[] TYPE = "type".getBytes();
    private static final byte[] CONTENT = "content".getBytes();

    @Autowired
    private HbaseTemplate hbaseTemplate;

    /**
     * HBase scan
     */
    @Test
    public void testScan() {
        // FilterList.Operator.MUST_PASS_ALL 相当于mysql中的and
        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        // FilterList.Operator.MUST_PASS_ONE 相当于mysql中的or
        //FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        // 页大小
        PageFilter pageFilter = new PageFilter(5);

        // uid = 12339
        SingleColumnValueFilter uidValueFilter =
                new SingleColumnValueFilter(FAMILY, UID, CompareFilter.CompareOp.EQUAL, Bytes.toBytes("12339"));

        // type >=9 && type <= 10
        SingleColumnValueFilter typeGreaterFilter = new SingleColumnValueFilter(FAMILY, TYPE, CompareFilter.CompareOp.GREATER_OR_EQUAL, "10".getBytes());
        SingleColumnValueFilter typeLesserFilter = new SingleColumnValueFilter(FAMILY, TYPE, CompareFilter.CompareOp.LESS_OR_EQUAL, "9".getBytes());
        FilterList typeAndFilter = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        typeAndFilter.addFilter(typeGreaterFilter);
        typeAndFilter.addFilter(typeLesserFilter);

        // content like
        RegexStringComparator comp = new RegexStringComparator(".英语录音.");
        SingleColumnValueFilter contentLike = new SingleColumnValueFilter(FAMILY, CONTENT, CompareFilter.CompareOp.EQUAL, comp);


        filters.addFilter(Arrays.asList(pageFilter, uidValueFilter, typeAndFilter, contentLike));

        Scan scan = new Scan()
                .withStartRow("431691".getBytes())
                .withStopRow("431797".getBytes())
                .setFilter(filters)
                .addColumn(FAMILY, UID)
                .addColumn(FAMILY, ID)
                .addColumn(FAMILY, TYPE)
                .addColumn(FAMILY, CONTENT);


        List<Map<String, Object>> result = hbaseTemplate.find(TABLE_NAME, scan, getRowMapper());
        System.out.println(result);
    }


    /**
     * 往HBase中写
     * 注意Bytes.toBytes("431691") 与Bytes.toBytes(431691)的区别
     */
    @Test
    public void testPut() {

        Object result = hbaseTemplate.execute(TABLE_NAME, new TableCallback<Object>() {
            @Override
            public Object doInTable(HTableInterface htable) throws Throwable {
                Put put = new Put(Bytes.toBytes("431691"))
                        .addColumn(FAMILY, UID, Bytes.toBytes("12338"))
                        .addColumn(FAMILY, ID, Bytes.toBytes("431691"));
                htable.put(put);
                return 1;
            }
        });
        System.out.println(result);
    }

    /**
     * 根据rowKey查询
     */
    @Test
    public void testGetByRowKey() {
        // rowName是rowKey，qualifier是列的名字
        String id = "431691";
        Map<String, Object> result = hbaseTemplate.get(TABLE_NAME, id, new String(FAMILY), new String(UID), getRowMapper());
        System.out.println(result);
    }

    /**
     * Helper方法，获得RowMapper， 用于对查询得到的结果进行转换
     *
     * @return
     */
    private RowMapper<Map<String, Object>> getRowMapper() {

        return new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Map<String, Object>> returnMap = new HashMap<String, Map<String, Object>>();
                String row = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                        String quality = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        map.put(family + "_" + quality, value);
                    }
                    map.put("row", row);
                }
                return map;
            }
        };

    }
}

package excel;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.alibaba.excel.EasyExcel;

/**
 * 写的常见写法
 *
 * @author Jiaju Zhuang
 */
public class WriteTest {
    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "d://1.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    /**
     *
     */
    @Test
    public void simpleRead() {
        // 写法1
        String fileName = "d://1.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet("模板").doRead();
    }



    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setUsername("字符串" + i);
            data.setPassword("字符串" + "password"+i);
            list.add(data);
        }
        return list;
    }

}

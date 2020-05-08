package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-05 15:51
 */
public class ExcelListener extends AnalysisEventListener<DemoData>{

    //逐行读取
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println(data);
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }
}

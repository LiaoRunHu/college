package cool.delete.edu.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-05 16:14
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneLevelSubjectName;
    @ExcelProperty(index = 1)
    private String twoLevelSubjectName;
}

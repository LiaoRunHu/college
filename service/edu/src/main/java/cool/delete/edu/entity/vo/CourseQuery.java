package cool.delete.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-07 20:34
 */
@Data
public class CourseQuery {
    @ApiModelProperty( value="课程名称")
    private String title;
    @ApiModelProperty( value="课程状态")
    private String status;
    @ApiModelProperty( value="课程大类Id")
    private String oneLevelId;
    @ApiModelProperty( value="课程分类Id")
    private String twoLevelId;
    @ApiModelProperty( value="教师id")
    private String teacherId;
    /**使用String类型,前端传过来的数据无雳进行类型转换*/
    @ApiModelProperty( value="查询开始时间", example="2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty( value="查询结束时间", example="2019-12-01 10:10:10")
    private String end;
}

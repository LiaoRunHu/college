package cool.delete.edu.entity.vo;

import lombok.Data;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-07 19:05
 */
@Data
public class CoursePublishVo {
        private String id;
        private String title;
        private String cover;
        private Integer lessonNum;
        private String oneSubject;
        private String twoSubject;
        private String teacherName;
        /**只用于显示*/
        private String price;
}

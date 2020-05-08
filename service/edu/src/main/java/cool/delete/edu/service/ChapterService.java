package cool.delete.edu.service;

import cool.delete.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import cool.delete.edu.entity.vo.TreeCollectionVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程id查询课程章节
     * @param courseId
     * @return
     */
    List<TreeCollectionVo> getChapterByAssembled(String courseId);

    /**
     * 根据章节Id删除章节 并判断章节下是否有小节,如存在 则不执行删除
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);
}

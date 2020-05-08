package cool.delete.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.edu.entity.Chapter;
import cool.delete.edu.entity.Subject;
import cool.delete.edu.entity.Video;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.mapper.ChapterMapper;
import cool.delete.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cool.delete.edu.service.VideoService;
import cool.delete.servicebase.handler.CollegeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoService videoService;

    /**
     * 根据课程id查询课程章节
     *
     * @param courseId
     * @return
     */
    @Override
    public List<TreeCollectionVo> getChapterByAssembled(String courseId) {
        //查询chapterList
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper();
        chapterQueryWrapper.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);
        //返回用数组
        List<TreeCollectionVo> returnCollectionVo = new ArrayList<>();
        //存储二级元素用数组
        List<TreeCollectionVo> childrenList = new ArrayList<>();
        TreeCollectionVo children;

        for (Chapter chapter : chapterList) {
            children = new TreeCollectionVo();
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper();
            videoQueryWrapper.eq("chapter_id", chapter.getId());
            childrenList.clear();

            List<Video> videoList = videoService.list(videoQueryWrapper);

            videoList.forEach(video -> {
                childrenList.add(new TreeCollectionVo(video.getId(), video.getTitle()));
            });
            children.setId(chapter.getId());
            children.setTitle(chapter.getTitle());

            List tempList = new ArrayList();
            tempList.addAll(childrenList);
            children.setChildren(tempList);

            returnCollectionVo.add(children);
        }
        return returnCollectionVo;
    }

    /**
     * 根据章节Id删除章节 并判断章节下是否有小节,如存在 则不执行删除
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", chapterId);
        int count = videoService.count(videoQueryWrapper);
        if (count > 0) {
            throw new CollegeException(200,"章节中存在小节");
        }else {
            int deleteResult = baseMapper.deleteById(chapterId);
            if (deleteResult == 0) {
                throw new CollegeException(200,"章节删除失败");
            }
            return true;
        }
    }
}

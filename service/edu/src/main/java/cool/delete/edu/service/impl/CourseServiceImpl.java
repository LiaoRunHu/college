package cool.delete.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.Const;
import cool.delete.commonutils.Result;
import cool.delete.edu.client.VodClient;
import cool.delete.edu.entity.*;
import cool.delete.edu.entity.vo.CourseFrontVo;
import cool.delete.edu.entity.vo.CoursePublishVo;
import cool.delete.edu.entity.vo.CourseVo;
import cool.delete.edu.entity.vo.CourseWebVo;
import cool.delete.edu.mapper.CourseMapper;
import cool.delete.edu.service.ChapterService;
import cool.delete.edu.service.CourseDescriptionService;
import cool.delete.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cool.delete.edu.service.VideoService;
import cool.delete.servicebase.handler.CollegeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    final CourseDescriptionService courseDescriptionService;

    final VideoService videoService;

    final ChapterService chapterService;

    final VodClient vodClient;

    public CourseServiceImpl(CourseDescriptionService courseDescriptionService, VideoService videoService, ChapterService chapterService, VodClient vodClient) {
        this.courseDescriptionService = courseDescriptionService;
        this.videoService = videoService;
        this.chapterService = chapterService;
        this.vodClient = vodClient;
    }

    /**
     * 添加课程
     * @param courseVo
     * @return
     */
    @Override
    public String addCourseInfo(CourseVo courseVo) {
        //1.向课程表添加课程基本信息
        Course course=new Course();
        BeanUtils.copyProperties(courseVo,course);
        int insert = baseMapper.insert(course);
        if (insert<=0){
            throw new CollegeException(500,"添加course失败");
        }
        CourseDescription courseDescription=new CourseDescription();
        courseDescription.setDescription(courseVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }

    /**
     * 根据id查询courseVo
     *
     * @param id
     * @return
     */
    @Override
    public CourseVo getCourseVoById(String id) {
        Course course = baseMapper.selectById(id);
        CourseVo courseVo=new CourseVo();
        BeanUtils.copyProperties(course,courseVo);
        CourseDescription description = courseDescriptionService.getById(id);
        courseVo.setDescription(description.getDescription());
        return courseVo;
    }

    /**
     * 更新CourseVo
     * @param courseVo
     */
    @Override
    public void updateCourseInfo(CourseVo courseVo) {
        Course course=new Course();
        BeanUtils.copyProperties(courseVo,course);
        int updateResult = baseMapper.updateById(course);
        if (updateResult==0){
            throw new CollegeException(500,"修改course失败");
        }

        CourseDescription description=new CourseDescription();
        description.setId(courseVo.getId());
        description.setDescription(courseVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    /**
     * 获取课程发布总信息
     *
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        CoursePublishVo coursePublishInfo = baseMapper.getCoursePublishInfo(id);
        return coursePublishInfo;
    }

    /**
     * 删除课程及相关资源
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeCourse(String id) {
        QueryWrapper<Video> videoQueryWrapper=new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoQueryWrapper.select("video_source_id");
        List<Video> videos = videoService.list(videoQueryWrapper);

        ArrayList<String> arrayList = new ArrayList<>();
        videos.forEach(video -> {
            if (StringUtils.isNotBlank(video.getVideoSourceId())){
                arrayList.add(video.getVideoSourceId());
            }
        });
        if (arrayList.size()>0){
            Result result = vodClient.deleteVideos(arrayList);
            if (result.getCode()== Const.ResponseCode.ERROR){
                throw new CollegeException(Const.ResponseCode.ERROR,"删除视频失败,熔断器");
            }
        }
        videoQueryWrapper=new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoService.remove(videoQueryWrapper);

        QueryWrapper<Chapter> chapterQueryWrapper=new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterService.remove(chapterQueryWrapper);

        int i = baseMapper.deleteById(id);
        if (i==0){
            throw new CollegeException(400,"课程不存在");
        }
        return i>0;
    }

    /**
     * 根据条件分页查询课程
     *
     * @param coursePage
     * @param courseFrontVo
     * @return
     */
    @Override
    public Map<String, Object> getCourseList(Page<Course> coursePage, CourseFrontVo courseFrontVo) {
        QueryWrapper<Course> courseQueryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(courseFrontVo.getSubjectParentId())){
            courseQueryWrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }if (StringUtils.isNotBlank(courseFrontVo.getSubjectId())){
            courseQueryWrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }if (StringUtils.isNotBlank(courseFrontVo.getBuyCountSort())){
            courseQueryWrapper.orderByDesc("buy_count");
        }if (StringUtils.isNotBlank(courseFrontVo.getGmtCreateSort())){
            courseQueryWrapper.orderByDesc("gmt_create");
        }if (StringUtils.isNotBlank(courseFrontVo.getPriceSort())){
            courseQueryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(coursePage,courseQueryWrapper);
        //把分页数据获取出来，放到map集合里面
        Map<String,Object> map= new HashMap<>(16);
        List<Course> records = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();

        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    /**
     * 课程详情类Vo
     *
     * @param id
     * @return
     */
    @Override
    public CourseWebVo getBaseCourseInfo(long id) {
        return baseMapper.getBaseCourseInfo(Long.toString(id));
    }
}

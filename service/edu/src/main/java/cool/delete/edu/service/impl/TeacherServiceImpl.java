package cool.delete.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.edu.EduApplication;
import cool.delete.edu.entity.Teacher;
import cool.delete.edu.mapper.TeacherMapper;
import cool.delete.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Map<String, Object> getTeacherList(Page<Teacher> teacherPage) {
        QueryWrapper<Teacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //分页的数据封装到pageTeacher对象
        baseMapper.selectPage(teacherPage,wrapper);

        //把分页数据获取出来，放到map集合里面
        Map<String,Object> map= new HashMap<>(16);
        List<Teacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}

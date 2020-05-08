package cool.delete.edu.service.impl;

import cool.delete.edu.entity.Video;
import cool.delete.edu.mapper.VideoMapper;
import cool.delete.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}

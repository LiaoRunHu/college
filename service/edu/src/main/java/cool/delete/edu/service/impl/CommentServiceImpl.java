package cool.delete.edu.service.impl;

import cool.delete.edu.entity.Comment;
import cool.delete.edu.mapper.CommentMapper;
import cool.delete.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

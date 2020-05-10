package cool.delete.cms.service;

import cool.delete.cms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-08
 */
public interface BannerService extends IService<Banner> {

    /**
     * 查询所有的Banner
     * @return
     */
    List<Banner> selectAllBanner();
}

package cool.delete.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.cms.entity.Banner;
import cool.delete.cms.mapper.BannerMapper;
import cool.delete.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-08
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    /**
     * 查询所有的Banner
     *
     * @return
     */
    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<Banner> selectAllBanner() {
        QueryWrapper<Banner> bannerQueryWrapper=new QueryWrapper<>();
        bannerQueryWrapper.orderByDesc("gmt_modified");
        bannerQueryWrapper.last("limit 5");
        List<Banner> bannerList = baseMapper.selectList(bannerQueryWrapper);
        return bannerList;
    }
}

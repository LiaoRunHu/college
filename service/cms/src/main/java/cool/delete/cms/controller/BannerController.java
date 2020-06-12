package cool.delete.cms.controller;


import cool.delete.cms.entity.Banner;
import cool.delete.cms.service.BannerService;
import cool.delete.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/cms/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;


    @GetMapping("")
    public Result pageTeacherCondition() {
        List<Banner> bannerList= bannerService.selectAllBanner();
        return Result.ok().data("data",bannerList);
    }

}


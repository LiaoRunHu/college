package cool.delete.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.cms.entity.Banner;
import cool.delete.cms.entity.vo.BannerQuery;
import cool.delete.cms.service.BannerService;
import cool.delete.commonutils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 0:18
 */
@RestController
@RequestMapping("/cms/admin/banner")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    BannerService bannerService;


    @GetMapping("Search/page={page}&limit={limit}")
    public Result pageTeacherCondition(@PathVariable long page, @PathVariable long limit, BannerQuery bannerQuery) {
        Page<Banner> bannerPage = new Page<>(page, limit);
        String status = bannerQuery.getStatus();

        if (page==0&&limit==0){
            List<Banner> teachers = bannerService.list();
            return Result.ok().data("data",teachers);
        }
        QueryWrapper<Banner> wrapper=new QueryWrapper<>();
        //判断条件是否为空,构建条件查询
        if (StringUtils.isNotBlank(status)){
            wrapper.like("status",status);
        }
        wrapper.orderByDesc("gmt_create");
        bannerService.page(bannerPage, wrapper);
        return Result.ok().data("data",bannerPage);
    }

    @PostMapping("")
    public Result addTeacher(@RequestBody Banner banner){
        boolean save = bannerService.save(banner);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 通过路径接收需要删除的bannerId
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        if (bannerService.removeById(id)) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
    @GetMapping("{id}")
    public Result getTeacherById(@PathVariable String id){
        Banner banner = bannerService.getById(id);
        return Result.ok().data("data",banner);
    }
    @PutMapping("{id}")
    public Result updateTeacherById(@PathVariable String id, @RequestBody Banner banner){
        banner.setId(id);
        boolean update = bannerService.updateById(banner);
        if (update){
            return Result.ok().data("data",banner);
        }else {
            return Result.error();
        }
    }
}

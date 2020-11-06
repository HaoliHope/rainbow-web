package org.rainbow.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.system.SysMenu;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/10/23 10:10
 */
@Api(tags = "用户菜单接口", description = "MenuController")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/list")
    @ApiOperation("分页查询")
    public Result list(SysMenu sysMenu) {
        List<SysMenu> sysMenuList = sysMenuService.list(sysMenu);
        return Result.success("查询成功", sysMenuList);
    }


    @PostMapping("/add")
    @ApiOperation("添加菜单")
    public Result add(@RequestBody SysMenu sysMenu) {
        sysMenuService.add(sysMenu);
        return Result.success();
    }

    @PutMapping("/update/{id}")
    @ApiOperation("修改菜单信息")
    public Result update(@PathVariable Long id, @RequestBody SysMenu sysMenu) {
        sysMenu.setId(id);
        sysMenuService.update(sysMenu);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除菜单信息")
    public Result delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return Result.success();
    }
}

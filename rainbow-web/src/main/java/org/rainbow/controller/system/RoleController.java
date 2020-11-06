package org.rainbow.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.system.SysRole;
import org.rainbow.beans.system.dto.RelatedMenuDTO;
import org.rainbow.beans.vo.PageResult;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihao3
 * @Date 2020/10/23 10:10
 */
@Api(tags = "用户角色接口", description = "RoleController")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/list/{pageSize}/{pageNum}")
    @ApiOperation("分页查询角色信息")
    public PageResult list(SysRole sysRole, @PathVariable Integer pageSize, @PathVariable Integer pageNum) {
        Page<SysRole> list = sysRoleService.list(sysRole, pageSize, pageNum);
        return PageResult.success(list);
    }

    @PutMapping("/related/menu")
    @ApiOperation("修改角色菜单权限")
    public Result relatedMenu(@RequestBody RelatedMenuDTO relatedMenuDTO) {
        sysRoleService.relatedMenu(relatedMenuDTO);
        return Result.success("操作成功!");
    }

    @PostMapping("/add")
    @ApiOperation("添加角色")
    public Result add(@RequestBody SysRole sysRole) {
        sysRoleService.add(sysRole);
        return Result.success("添加成功");
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除角色信息")
    public Result delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return Result.success();
    }
}

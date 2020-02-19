package com.it.friday.controller.api;

import com.it.friday.domain.SysPermission;
import com.it.friday.domain.SysRole;
import com.it.friday.domain.SysUser;
import com.it.friday.service.SysPermissionService;
import com.it.friday.service.SysRoleService;
import com.it.friday.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/16 15:57
 */
@Controller
@RequestMapping("${api-url}")
public class ApiController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        return modelAndView;
    }

    @GetMapping("/user/add")
    public String addUser(Model model){
        model.addAttribute(new SysUser());
        return "user/user-add";
    }

    @GetMapping("/user/edit")
    public String editUser(Model model,SysUser sysUser){
        model.addAttribute(sysUserService.getUserById(sysUser.getId()));
        return "user/user-edit";
    }

    @GetMapping("/role/add")
    public String addRole(Model model) {
        model.addAttribute("sysRole",new SysRole());
        return "role/role-add";
    }

    @GetMapping("/role/edit")
    public String editRole(Model model,SysRole sysRole){
        model.addAttribute("sysRole",sysRoleService.getRoleById(sysRole.getId()));
        return "role/role-edit";
    }

    @GetMapping("/permission/add")
    public String addPermission(Model model) {
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @GetMapping("/permission/edit")
    public String editPermission(Model model, SysPermission permission) {
        model.addAttribute("sysPermission",sysPermissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }

}

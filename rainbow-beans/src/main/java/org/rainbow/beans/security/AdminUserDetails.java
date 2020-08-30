package org.rainbow.beans.security;

import org.rainbow.beans.entity.SysMenu;
import org.rainbow.beans.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lihao3
 * @Date 2020/8/30 11:34
 */
public class AdminUserDetails implements UserDetails {

    private SysUser user;

    private List<SysMenu> menuList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return menuList
                .stream()
                .filter(menu -> menu.getPerms() != null)
                .map(menu -> new SimpleGrantedAuthority(menu.getPerms()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }

    public AdminUserDetails(SysUser user, List<SysMenu> menuList) {
        this.user = user;
        this.menuList = menuList;
    }
}

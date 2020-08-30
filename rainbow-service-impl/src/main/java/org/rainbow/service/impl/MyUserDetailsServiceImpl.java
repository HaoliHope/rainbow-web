// package org.rainbow.service.impl;
//
// import org.rainbow.mapper.SysUserMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.LockedException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;
//
// @Service
// public class MyUserDetailsServiceImpl implements UserDetailsService {
//
//     @Autowired
//     private SysUserMapper sysUserMapper;
//
//     @Override
//     public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
//         // 加载基础用户信息
//         MyUserDetails myUserDetails = sysUserMapper.findByLoginName(loginName);
//         if (myUserDetails == null) {
//             throw new UsernameNotFoundException("用户名不存在");
//         }else if(myUserDetails.getStatus()==false){
//             throw new LockedException("用户名已经被封禁");
//         }
//         Set<String> permissions = sysUserMapper.findPermissionsByLoginName(loginName);
//         List<String> collect = permissions.stream().collect(Collectors.toList());
//         List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", collect));
//         return new MyUserDetails(loginName, myUserDetails.getPassword(), myUserDetails.getStatus(), grantedAuthorities);
//     }
// }

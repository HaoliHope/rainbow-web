// package org.rainbow.service;
//
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.util.AntPathMatcher;
//
// import javax.servlet.http.HttpServletRequest;
// import java.util.List;
//
// @Component("rabcService")
// public class MyRBACService {
//
//     private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//     /**
//      * 判断某用户是否具有该request资源的访问权限
//      *
//      * @param request
//      * @param authentication
//      * @return
//      */
//     public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//         // 获取登陆用户实体
//         Object principal = authentication.getPrincipal();
//         // 转换实体
//         if (principal instanceof UserDetails) {
//             UserDetails userDetails = ((UserDetails) principal);
//             List<GrantedAuthority> authorityList =
//                     AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());
//             return userDetails.getAuthorities().contains(authorityList.get(0));
//         }
//         return false;
//     }
// }

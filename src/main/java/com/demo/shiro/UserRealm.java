package com.demo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by Queric on 2017/5/7.
 */
public class UserRealm extends AuthorizingRealm {
    //认证，登录时调用
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        return null;
    }
    //授权，验证权限时调用
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

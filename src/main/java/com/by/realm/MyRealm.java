package com.by.realm;

import com.by.model.User;
import com.by.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.krb5.Credentials;

import javax.sound.sampled.Line;
import java.security.Principal;
import java.util.Set;

/**
 * Created by zyj on 2019/7/4.
 */

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
            Set<String> roles=userService.selectByrole(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1=(UsernamePasswordToken) token;
        String username = token1.getUsername();
        User users=userService.query(username);
        if (users==null){
            return null;
        }else {
            String Principal = users.getUserName();
            String  Credentials = users.getUserPswd();
            String realmName = getName();
            ByteSource credentialsSalt=ByteSource.Util.bytes(username);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(Principal, Credentials,credentialsSalt, realmName);
            return info;
        }

    }
}

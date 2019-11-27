package weibo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import weibo.Service.UserService;
import weibo.pojo.User;

public class userRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("---------------授权 -------------");
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        //   这里存放权限信息（也就是从数据库获取到的权限信息放在SimpleAuthorizationInfo里面，相当于赋予角色这个权限）
        simpleAuthorInfo.addStringPermission("how_are_you");//给当前用户授权url为hello的权限码
        System.out.println("经试验：并不是每次调用接口就会执行，而是调用需要操作码（permission）的接口就会执行");
        return simpleAuthorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        System.out.println("---------------认证 -------------");
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //        这个username是shiro自动把登录的username传入给token带进来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String account = token.getUsername();
        User user = userService.findUser(account);//根据登陆名account从库中查询user对象
        if(user==null){throw new AuthenticationException("用户不存在");}

        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         * 第二个参数必须放密码，
         * 第三个参数放 当前realm的名字，因为可能有多个realm*/
        AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        //AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user,user.getPassword(),new MySimpleByteSource(account), this.getName());

        //清之前的授权信息
        super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        return authcInfo;//返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        //如果有问题，向上抛异常，一直抛到控制器
    }

    }
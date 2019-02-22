package group.bridge.web.realm;

import com.ctc.wstx.util.StringUtil;
import group.bridge.web.controller.PermissionController;
import group.bridge.web.controller.RoleController;
import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.RoleService;
import group.bridge.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    //MyShiroRealm继承 AuthorizingRealm，
    //重写doGetAuthorizationInfo授权方法，和doGetAuthenticationInfo认证方法
    @Autowired
    private UserService userService;


    //realm的名称
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "myRealm";
    }
    //验证token是否是有效的token
    @Override
    public boolean supports(AuthenticationToken arg0) {
        // TODO Auto-generated method stub
        return arg0 instanceof UsernamePasswordToken;
    }


    /**
     * 1.授权方法，在请求需要操作码的接口时会执行此方法。不需要操作码的接口不会执行
     * 2.实际上是 先执行 AuthorizingRealm，自定义realm的父类中的 getAuthorizationInfo方法，
     * 逻辑是先判断缓存中是否有用户的授权信息（用户拥有的操作码），如果有 就直返回不调用自定义 realm的授权方法了，
     * 如果没缓存，再调用自定义realm，去数据库查询。
     * 用库查询一次过后，如果 在安全管理器中注入了 缓存，授权信息就会自动保存在缓存中，下一次调用需要操作码的接口时，
     * 就肯定不会再调用自定义realm授权方法了。   网上有分析AuthorizingRealm，shiro使用缓存的过程
     * 3.AuthorizingRealm 有多个实现类realm，推测可能是把 自定义realm注入了安全管理器，所以才调用自定义的
     */

    //重写获取用户信息的方法----》认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        User user = null;

        //获取用户的输入的账号
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //获取用户名
        String user_name= (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到则成功登陆，没找到则在后台显示问题，页面仍然是登陆界面.
        //通过用户名获取用户对象
        List<User> users= this.userService.findUserByName(user_name);
        //将获得的List数组取值给User对象
        if (users.size()!=0){
            user = users.get(0);
            System.out.println("----->>userInfo="+user);
            if (user==null){
                //throw new AuthenticationException("用户不存在");
                return null;
            }
        }
        else {
            //throw new AuthenticationException("用户不存在");
            return null;
        }


        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /* 第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         * 第二个参数必须放密码，
         * 第三个参数放 当前realm的名字，因为可能有多个realm
         */

        //AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
          user.getUser_name(),//用户名
          user.getPassword(),//密码
          getName()//realm name
        );

        System.out.println(user.getUser_name());
        System.out.println(user.getPassword());
        //清缓存中的授权信息，保证每次登陆 都可以重新授权。因为AuthorizingRealm会先检查缓存有没有 授权信息，再调用授权方法
        //super.clearCachedAuthorizationInfo(authenticationInfo.getPrincipals());

        //SecurityUtils.getSubject().getSession().setAttribute("login", user);
        //返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        //如果有问题，向上抛异常，一直抛到控制器
        return authenticationInfo;
    }

    //进行角色的添加和权限的添加----》授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        // 给资源进行授权
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        // 获取当前登录用户
        //这个值是认证方法中的SimpleAuthenticationInfo对象的第一个参数的值即user.getUsername()
        String user_name=(String)principalCollection.getPrimaryPrincipal();
        List<User> users=(List<User>) this.userService.findUserByName(user_name);
        //将获得的List数组取值给User对象
        User user = users.get(0);
        //User user= (User) principalCollection.getPrimaryPrincipal();

        System.out.println("user_name:"+user.getUser_name()+",password:"+user.getPassword());
        //getRoleE(),getPermissionE()分别对应Role和Permission的英文信息
        // 到数据库查询当前登录用户的授权字符串
        //Set<Role> roles=user.getRoles();
        //Role role= (Role) roles;
        for (Role role:user.getRoles()){
            simpleAuthorInfo.addRole(role.getRoleE());
            System.out.println("用户"+user.getUser_name()+"具有的角色:"+role.getRoleE());
            for (Permission permission:role.getPermissions()){
                simpleAuthorInfo.addStringPermission(permission.getPermissionE());
                System.out.println("用户"+user.getUser_name()+"具有的权限："+permission.getPermissionE());
            }
        }
        return simpleAuthorInfo;
    }
}

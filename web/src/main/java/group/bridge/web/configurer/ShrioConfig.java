package group.bridge.web.configurer;

import group.bridge.web.realm.MyShiroRealm;
import group.bridge.web.reslover.MyExceptionResolver;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


//首先要配置的是ShiroConfig类，Apache Shiro 核心通过 Filter 来实现，
// 就好像SpringMvc 通过DispachServlet 来主控制一样。
//既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
// 所以我们需要定义一系列关于URL的规则和访问权限。
@Configuration
public class ShrioConfig {
    //ShiroFilterFactoryBean 处理拦截资源文件问题。
    //注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
    //	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
    //	 * 3、部分过滤器可指定参数，如perms，roles
    //SecurityManager：安全管理器，需要将自定义realm注入其中，以后还可以将缓存、remeberme等注入其中
    //
    //自定义reaml：认证授权会执行它，需要自己写
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //解决登录成功后无法跳转到successUrl的问题
//        Map<String, Filter> map = new LinkedHashMap<>();
//        map.put("authc", new MyFormAuthenticationFilter());
//        shiroFilterFactoryBean.setFilters(map);


        //设置进入登陆界面
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");


        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接 顺序判断
        //静态资源
        filterChainDefinitionMap.put("/**/*.css", "anon");
        filterChainDefinitionMap.put("/**/*.jpg", "anon");
        filterChainDefinitionMap.put("/**/*.js", "anon");
        filterChainDefinitionMap.put("/url.xml", "anon");
        filterChainDefinitionMap.put("/sysmanagement/404","anon");

        //filterChainDefinitionMap.put("/templates/login/login","anon");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边-->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问;user:remember me的可以访问-->
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //访问的是后端url地址为 /login的接口
        //对权限URL设置为需要认证
        filterChainDefinitionMap.put("/**", "authc");
        //filterChainDefinitionMap.put("/templates/sysmanagement/permissionmanagement/**", "authc");
        //filterChainDefinitionMap.put("/templates/sysmanagement/rolemanagement/**", "authc");
        //filterChainDefinitionMap.put("/templates/sysmanagement/usermanagement/**", "authc");

        //filterChainDefinitionMap.put("/**/*.html", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    //凭证匹配器
    //用于密码用密文加密过的
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
//        //散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //散列的次数，比如散列两次，相当于 md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(2);
//        return hashedCredentialsMatcher;
//    }

    /*
        当没有权限时跳转404，
        解决不跳转而在控制台报错的问题
     */
    @Bean
    public HandlerExceptionResolver solver(){
        HandlerExceptionResolver handlerExceptionResolver = new MyExceptionResolver();
        return handlerExceptionResolver;
    }

    //身份认证realm; (这个需要自己写，账号密码校验；权限等)
    //@return
    //因为手写的Realm，默认是读取不到的,所以我们要在ShiroConfig过滤器那边，
    // Realm注入到SecurityManager中
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm ){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm);
        //将缓存注入安全管理器，就不会反复执行  realm的授权方法了；只要实现了shiro的cache接口、CacheManager接口就可以用来注入安全管理器
        //shiro自带的一个内存缓存，本质是hashmap，MemoryConstrainedCacheManager()，试验没问题，非常轻，简单的登录用这个
        //securityManager.setCacheManager(new MemoryConstrainedCacheManager());

        //注入记住我管理器;
        //securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    /**
     * 配置shiro redisManager
     * 网上的一个 shiro-redis 插件，实现了shiro的cache接口、CacheManager接口就
     * @return
     */
//    @Bean
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("localhost");
//        redisManager.setPort(6379);
//        redisManager.setExpire(18000);// 配置过期时间
//        // redisManager.setTimeout(timeout);
//        // redisManager.setPassword(password);
//        return redisManager;
//    }
    /**
     * cacheManager 缓存 redis实现
     * 网上的一个 shiro-redis 插件
     * @return
     */
//    @Bean
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }

    /**
     * shiro session的管理
     */
//    public DefaultWebSessionManager SessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        return sessionManager;
//    }

//    @Bean
//    public SimpleCookie rememberMeCookie(){
//
//        System.out.println("ShiroConfiguration.rememberMeCookie()");
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//        simpleCookie.setMaxAge(259200);
//        simpleCookie.setHttpOnly(true);
//        return simpleCookie;
//    }
    /**
     * cookie管理对象;
     * @return
     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(){
//
//        System.out.println("ShiroConfiguration.rememberMeManager()");
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        // cookieRememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        return cookieRememberMeManager;
//    }

    /**
     * Shiro生命周期处理器
     * @return
     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//    @Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
//        SimpleMappingExceptionResolver s=new SimpleMappingExceptionResolver();
//        Properties mappings=new Properties();
//        //数据库异常处理
//        mappings.setProperty("DatabaseException", "databaseError");
//        mappings.setProperty("UnauthorizedException","404");
//        // None by default
//        s.setExceptionMappings(mappings);
//        // No default
//        s.setDefaultErrorView("error");
//        // Default is "exception"
//        s.setExceptionAttribute("ex");
//        return s;
//    }


    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


//    private class MyFormAuthenticationFilter extends FormAuthenticationFilter implements Filter {
//        @Override
//        protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//            String successUrl = "/index";
//            WebUtils.issueRedirect(request,response,successUrl);
//            return false;
//        }
//    }
}

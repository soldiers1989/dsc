package com.yixin.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.web.utils.CurrentUserAop1;
import com.yixin.web.utils.CustomCasRealm;

/**
 * Shiro集成Cas配置
 */
@Configuration
public class ShiroCasConfiguration {
	private static final Logger log = LoggerFactory.getLogger(ShiroCasConfiguration.class);
	@Value("${spring.casServerUrlPrefix}")
	public String casServerUrlPrefix;
	@Value("${spring.casService}")
	public String casService;

	@Bean(name = "springContextUtil")
	public SpringContextUtil springContextUtil() {
		SpringContextUtil springContextUtil = new SpringContextUtil();
		return springContextUtil;
	}

	@Bean(name = "customCasRealm")
	public CustomCasRealm customCasRealm() {
		CustomCasRealm realm = new CustomCasRealm();
		realm.setCasServerUrlPrefix(casServerUrlPrefix);
		realm.setCasService(casService);
		return realm;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setOrder(2);
		filterRegistration.addServletNames("default");
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean logOutFilter(SingleSignOutFilter singleSignOutFilter) {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(singleSignOutFilter);
		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.addServletNames("default");
		return filterRegistration;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter ssof = new SingleSignOutFilter();
		ssof.setLogoutParameterName(casServerUrlPrefix);
		return ssof;
	}

	@Bean
	public ServletListenerRegistrationBean logoutListener() {
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
		return servletListenerRegistrationBean;
	}

	// @Bean(name = "lifecycleBeanPostProcessor")
	// public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
	// return new LifecycleBeanPostProcessor();
	// }

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomCasRealm myShiroCasRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroCasRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		// dwsm.setCacheManager(getEhCacheManager());
		// 指定 SubjectFactory
		// dwsm.setSubjectFactory(new CasSubjectFactory());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		aasa.setOrder(0);
		return aasa;
	}

	@Bean(name = "casFilter")
	public CasFilter getCasFilter() {
		CasFilter casFilter = new CasFilter();
		casFilter.setName("casFilter");
		// casFilter.setEnabled(true);
		// 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
		casFilter.setFailureUrl("/pages/errors/denied.jsp");// 我们选择认证失败后再打开登录页面
		return casFilter;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			DefaultWebSecurityManager securityManager, CasFilter casFilter) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl(casServerUrlPrefix
				+ "/login?service=" + casService);
		// 登录成功后要跳转的连接
		// 添加casFilter到shiroFilter中
		Map<String, Filter> filters = new HashMap<>();
		filters.put("casFilter", casFilter);
		shiroFilterFactoryBean.setFilters(filters);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/shiro-cas", "casFilter");// shiro集成cas后，首先添加该规则
		filterChainDefinitionMap.put("/static", "user");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean(name = "loginInterceptor")
	public CurrentUserAop1 currentUserAop1() {
		CurrentUserAop1 currentUserAop1 = new CurrentUserAop1();
		return currentUserAop1;
	}

	@Bean(name = "otherProxy")
	public AspectJExpressionPointcutAdvisor getAspectJExpressionPointcutAdvisor() {
		AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
		aspectJExpressionPointcutAdvisor.setAdvice(currentUserAop1());
		aspectJExpressionPointcutAdvisor
				.setExpression("execution(* com.yixin.web.controller..*.*(..))");
		return aspectJExpressionPointcutAdvisor;
	}

}
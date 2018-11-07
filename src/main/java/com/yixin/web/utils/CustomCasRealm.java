package com.yixin.web.utils;

/**
 * @author YixinCapital -- ${user}
 *         ${date} ${time}
 */

import java.io.IOException;

import com.yixin.common.utils.InvokeResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.yixin.oa.web.dto.UserDto;

/**
 * Created by chenqi on 2016/2/28.
 */
public class CustomCasRealm extends CasRealm {

    private static Logger logger = LoggerFactory.getLogger(CustomCasRealm.class);
   /* @Value("${getAdjunctByDomainAccountAndSystemId}")
    private String url1;*/

    @Value("${spring.findResourceByUser}")
    private String findResourceByUser;

    private  UserDto  duddo(String username) throws  IOException{
         String url = findResourceByUser;
//        DefaultHttpClient httpClient = new DefaultHttpClient();
        url = url+"?username="+username;
//        HttpPost httpPost = new HttpPost(url);
        String messageResult = HttpClientHelper.sendPost(url,null);
//        HttpResponse response = httpClient.execute(httpPost);
//        String messageResult = EntityUtils.toString(response.getEntity(), "utf-8");
        return  (UserDto) Json.toObject(messageResult, UserDto.class);
    }



    /**
     * 自行从库中查询权限，放到AuthorizationInfo里，暂时不加缓存，压力测试出结果后，考虑缓存策略
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();//只有一个realm的时候，暂时不考虑有多个realm的realm链
        Session session = SecurityUtils.getSubject().getSession();
        SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        AuthorizationInfo authorizationInfo = null;
        if (session.getAttribute("userInfo_shiro") == null) {
            try {
                UserDto userDto = duddo(username);
                authorizationInfo = userDto.getSimpleAuthorizationInfo();
               session.setAttribute("userInfo_shiro", authorizationInfo);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            authorizationInfo = (AuthorizationInfo) session.getAttribute("userInfo_shiro");
        }
        //缓存直接加在service的实现层
        return authorizationInfo;
        //return new SimpleAuthorizationInfo();
    }

    /**
     * 用casRealm的认证方法进行验证用户的登录信息，暂不从cas server端获取用户的非登录信息（角色、权限等）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo doGetAuthenticationInfo = super.doGetAuthenticationInfo(token);
        PrincipalCollection principals = doGetAuthenticationInfo.getPrincipals();
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        String username = String.valueOf(primaryPrincipal);
        logger.info("doGetAuthenticationInfo认证方法 username:{}",username);
        Session session = SecurityUtils.getSubject().getSession();
        String username_session = (String) session.getAttribute("username_shiro");//session里域账号
       if (session.getAttribute("authenticationInfo_shiro") == null||!(username.equalsIgnoreCase(username_session))) {
           try {
               UserDto userDto = duddo(username);

            session.setAttribute("authenticationInfo_shiro", doGetAuthenticationInfo);
            session.setAttribute("username_shiro", userDto.getUsername());//域账号
            session.setAttribute("departmentId_shiro", userDto.getDepartmentId());//部门id
            session.setAttribute("departmentName_shiro", userDto.getDepartmentName());//部门名称
            session.setAttribute("cnName_shiro", userDto.getCnName());//中文名称
            session.setAttribute("userId_shiro", userDto.getId());//用户id
            session.setAttribute("employeeNumber_shiro", userDto.getEmployeesNumber());//用户编号
            //部门和渠道信息
            session.setAttribute("business_department_id", userDto.getBusiness_department_id());//业务部门id
            session.setAttribute("business_department_name", userDto.getBusiness_department_name());//业务部门name
            session.setAttribute("clue_inner_channel_id", userDto.getClue_inner_channel_id());//线索内部渠道id
            session.setAttribute("clue_outer_channel_id", userDto.getClue_outer_channel_id());//线索外部渠道id
            session.setAttribute("decision_inner_channel_id", userDto.getDecision_inner_channel_id());//决策内部渠道id
            session.setAttribute("decision_outer_channel_id", userDto.getDecision_outer_channel_id());//决策外部渠道id
            session.setAttribute("clue_inner_channel_name", userDto.getClue_inner_channel_name());//线索内部渠道name
            session.setAttribute("clue_outer_channel_name", userDto.getClue_outer_channel_name());//线索外部渠道name
            session.setAttribute("decision_inner_channel_name", userDto.getDecision_inner_channel_name());//决策内部渠道name
            session.setAttribute("decision_outer_channel_name", userDto.getDecision_outer_channel_name());//决策外部渠道name
            session.setAttribute("next_all_Level_department_ids", userDto.getNext_all_Level_department_ids());//当前人行政部门以及下属行政部门递归id串，以逗号分隔

            session.setAttribute("manager_department_ids", userDto.getManager_department_ids());//当前人管理的行政部门id串，以逗号分隔
            session.setAttribute("manager_business_department_ids", userDto.getManager_business_department_ids());//当前人管理的业务部门id串，以逗号分隔
            //获取兼职行政部门以及归属行政部门id的递归串
              /* DefaultHttpClient httpClient = new DefaultHttpClient();
               url1 = url1+"?domainAccount="+userDto.getUsername()+"&systemId=10006";
               HttpPost httpPost = new HttpPost(url1);
               // 执行请求
               HttpResponse response = httpClient.execute(httpPost);
               // 打印执行结果
               String messageResult = EntityUtils.toString(response.getEntity(), "utf-8");
               InvokeResult<String> invokeResult = (InvokeResult)Json.toObject(messageResult,InvokeResult.class);
               session.setAttribute("adjunct_department_ids", invokeResult.getData());//当前人兼职的行政部门以及归属行政部门递归id串，以逗号分隔*/
           }catch (Exception e){
               e.printStackTrace();
           }
        } else {
            doGetAuthenticationInfo = (AuthenticationInfo) session.getAttribute("authenticationInfo_shiro");
        }
        return doGetAuthenticationInfo;
    }
}

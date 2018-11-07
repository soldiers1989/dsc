package com.yixin.kepler.common;/**
 * Created by liushuai2 on 2018/5/19.
 */

import com.yixin.common.exception.BzException;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Map;

/**
 * Package : com.yixin.compressor.core.utils
 *
 * @author YixinCapital -- liushuai2
 *         2018年05月19日 19:20
 */
public class RestUtil {
    public static final Logger logger = LoggerFactory.getLogger(RestUtil.class);


    public static Object postJson(String target, Map<String, Object> params, Class responseType){
        String paramsJson = JacksonUtil.fromObjectToJson(params);
        Object result = postJson(target, paramsJson, responseType);
        return result;
    }

    public static Object postJson(String target, String data, Class responseType){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        Object retult = template.postForObject(target, formEntity, responseType);
        logger.info("post 请求结束，返回：{}", JacksonUtil.fromObjectToJson(retult));
        return retult;
    }

    public static String postJson(String target, String data){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        String retult = (String) template.postForObject(target, formEntity, String.class);
        logger.info("post 请求结束，返回：{}", retult);
        return retult;
    }

    /**
     * get origin file and write to the target file
     * @param origin
     * @param target
     * @return
     */
    public static String getFile(String origin, String target) throws BzException{
        logger.info("获取并写入指定文件 origin：{}，target：{}", origin, target);
        Assert.notNull(origin, "origin can't be null");
        Assert.notNull(target, "target can't be null");
        Assert.isTrue(origin.indexOf("http") <=0 , "origin must be start with 'http'");
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bytes = getFile(origin);
            fileOutputStream = new FileOutputStream(new File(target));
            IOUtils.write(bytes, fileOutputStream);
            logger.info("写入文件成功");
        } catch (FileNotFoundException e) {
            logger.error("找不到对应的文件" , e);
            throw  new BzException("找不到target文件", e);
        } catch (IOException ioe){
            logger.error("写入文件失败", ioe);
            throw  new BzException("写入target文件失败", ioe);
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        return target;
    }
    public static byte[] getFile(String origin) throws BzException{
        Assert.notNull(origin, " origin can't be null");
        logger.info("下载附件并转换为bytes origin:{}", origin);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Accept", MediaType.ALL_VALUE);
        ResponseEntity<Resource> responseEntity = template.exchange(origin, HttpMethod.GET, null,  Resource.class);

        InputStream responseInputStream = null;
        byte[] bytes = null;
        try {
            responseInputStream = responseEntity.getBody().getInputStream();
            bytes = IOUtils.toByteArray(responseInputStream);
            logger.info("下载附件成功");
        }catch (IOException e){
            logger.error("下载附件信息失败", e);
            throw new BzException("下载附件信息失败", e);
        }finally {
            IOUtils.closeQuietly(responseInputStream);
            template = null;
        }
        return bytes;
    }

    public static void main(String[] args) throws Exception{
//        String origin = "http://filestore.uat.yixincapital.com/group1/M00/01/B5/CgECMFr9ViGAVg53AAGv9uaGU0I372.jpg";
//        String target ="D:\\abc.jpg";
//
//        getFile(origin, target);



//        byte[] array = IOUtils.toByteArray(new FileInputStream(new File("D:\\abc.jpg")));
//        Map<String, Object> map = new HashedMap();
//        map.put("fileSource", array);
//        map.put("sourceFileName","abc.jpg");
//        String result = HttpUtils.sendPost("http://192.168.145.99:8080/fileserver/api/fileUpload/uploadFile", JSON.toJSONString(map),CommonConstant.APPLICATION_CONTENTTYPE);
//        logger.info("上传文件结束,result={}", result);
//        if(StringUtils.hasText(result)){
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            String fileId = JSONObject.parseObject(jsonObject.getString("data")).getString("fileId");
//            System.out.println(fileId);
//        }
//

        byte[] array = IOUtils.toByteArray(new FileInputStream(new File("D:\\abc.jpg")));
        Map<String, Object> map = new HashedMap();
        map.put("fileSource", array);
        map.put("sourceFileName","abc.jpg");
        postJson("http://192.168.145.99:8080/fileserver/api/fileUpload/uploadFile", map, String.class);


    }
}

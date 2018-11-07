package com.yixin.kepler.common;

import com.yixin.common.utils.RedisTools;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RedisClientUtil {
    protected final static Logger logger = LoggerFactory.getLogger(RedisClientUtil.class);
    private JedisCluster jedisCluster;
    private RedisTools handle;

    public String getRedisServers() {
        return redisServers;
    }

    public void setRedisServers(String redisServers) {
        this.redisServers = redisServers;
    }

    @Value("${spring.redis.cluster.nodes}")
    private String redisServers;

    @PostConstruct
    public void init() {
        logger.info("Init Redis Service : " + redisServers);
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        for (String server : redisServers.split(",")) {
            jedisClusterNodes.add(new HostAndPort(server.split(":")[0], Integer.parseInt(server.split(":")[1])));
        }
        jedisCluster = new JedisCluster(jedisClusterNodes);
        handle = new RedisTools(jedisCluster);
        logger.info("Init Redis Service success!");
    }

	/*@PreDestroy
    public void close() throws IOException {
		jedisCluster.close();
	}*/

    /**
     * 根据key值获得所有字段值，以Map返回
     *
     * @param key
     * @return
     */
    public Map<String, String> getDataByKey(String key) {
        return handle.getHGetAll(key);
    }

    /**
     * 根据key获得value
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) {
        return handle.get(key, "");
    }


    /**
     * 根据key获得value
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        return handle.incr(key);
    }


    /**
     * 存
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        handle.set(key, value);
    }

    /**
     * 存
     *
     * @param key
     * @param value
     * @param sec
     */
    public void set(String key, String value, int sec) {
        logger.info("[redis]设置数据 key={} value={} sec={}", key, value, sec);
        handle.set(key, value, sec);
    }


    /**
     * 如果key存在，返回0，如果不存在，则设置成功。
     *
     * @param key   键
     * @param value 值
     * @return 0-失败，1-成功
     */
    public long setnx(String key, String value) {
        return jedisCluster.setnx(key, value);
    }


    /**
     * 设置新值，并返回旧值
     *
     * @param key   键
     * @param value 值
     * @return 旧值
     */
    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }

    /**
     * 设置有效时间
     *
     * @param key 键值
     * @param sec 有效期(单位s)
     */
    public void expire(String key, int sec) {
        logger.info("[redis]设置有效时间 key={} sec={}", key, sec);
        handle.expire(key, sec);
    }

    /**
     * 根据key删除所有字段
     *
     * @param key
     */
    public void delDataByKey(String key) {
        Object keys[] = handle.getHGetAll(key).keySet().toArray();
        String fields[] = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            fields[i] = (String) keys[i];
        }
        if (null != fields && fields.length > 0) {
            handle.delHSet(key, fields);
        }
    }

    /**
     * 根据key追加value，并设置有效期
     *
     * @param key
     * @param newValue
     * @param sec
     */
    public void append(String key, String newValue, int sec) {
        String oldValue = handle.get(key, "");
        StringBuffer value = new StringBuffer();
        if (StringUtils.isNotBlank(oldValue)) {
            value.append(oldValue).append(",").append(newValue);
        } else {
            value.append(newValue);
        }
        if (sec > 0) {
            handle.set(key, value.toString(), sec);
        } else {
            handle.set(key, value.toString());
        }
    }

    /**
     * 根据key，字段名，字段值添加字段
     *
     * @param key
     * @param fieldName
     * @param value
     */
    public void setFieldValue(String key, String fieldName, String value) {
        handle.setHSet(key, fieldName, value);
    }


    public void setFieldValue(String key, Map<String, String> value) {
        jedisCluster.hmset(key, value);
    }

    /**
     * 根据字段名，key获取字段值
     *
     * @param key
     * @param fieldName
     * @return
     */
    public String getFieldValue(String key, String fieldName) {
        return handle.getHSet(fieldName, key);
    }

    /**
     * 根据key删除数据
     */
    public void delByKey(String key) {
        handle.del(key);
    }

    public boolean isExits(String key) {
        return handle.exist(key);
    }

    /**
     * 获取set所有成员
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return handle.smembers(key);
    }

    /**
     * 将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略
     *
     * @param key
     * @param member
     * @return
     */
    public Long sadd(String key, String... member) {
        return handle.sadd(key, member);
    }

    /**
     * 删除成员
     *
     * @param key
     * @param member
     * @return
     */
    public Long srem(String key, String... member) {
        return handle.srem(key, member);
    }


    /**
     * 获取存储在键的散列的所有字段和值
     *
     * @param key 键值
     * @return
     */
    public Map<String, String> hGetAll(String key) {
        return handle.getHGetAll(key);
    }


    /**
     * 设置HashSet对象
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public boolean hSet(String key, String field, String value) {
        handle.setHSet(key, field, value);
        return true;
    }


    /**
     * 删除HashSet对象
     *
     * @param field
     * @param key
     * @return
     */
    public long hDel(String key, String field) {
        return handle.delHSet(field, key);
    }
}
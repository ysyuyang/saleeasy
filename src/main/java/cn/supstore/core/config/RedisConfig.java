package cn.supstore.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configurable
@PropertySource(value={"classpath:redis.properties"})
public class RedisConfig {

	@Autowired
    Environment env;
	
	@Bean(name="jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(env.getRequiredProperty("redis.maxIdle")));
		config.setTestOnBorrow(Boolean.getBoolean(env.getRequiredProperty("redis.testOnBorrow")));
		return config;
	}
	
	@Bean(name="jedisConnectionFactory")
	@Autowired
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
		factory.setUsePool(true);
		factory.setHostName(env.getRequiredProperty("redis.host"));
		factory.setPort(Integer.parseInt(env.getRequiredProperty("redis.port")));
		factory.setTimeout(Integer.parseInt(env.getRequiredProperty("redis.timeout")));
		factory.setDatabase(Integer.parseInt(env.getRequiredProperty("redis.default.db")));
		factory.setPassword(env.getRequiredProperty("redis.default.pwd"));
		return factory;
	}
	
	@Bean(name="stringRedisSerializer")
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedis = new StringRedisSerializer();
		return stringRedis;
	}
	
	@Bean(name="redisTemplate")
	@Autowired
	public RedisTemplate<String, Object> redisTemplate(StringRedisSerializer stringRedisSerializer, JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(/*new JdkSerializationRedisSerializer()*/stringRedisSerializer);
		return template;
	}
	
}

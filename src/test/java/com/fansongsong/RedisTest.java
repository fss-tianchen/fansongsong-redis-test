package com.fansongsong;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fansongsong.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class RedisTest {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 
	 * @Title: testJDKSer 
	 * @Description: 使用JDK的序列化方式
	 * @return: void
	 */
	@Test
	public void testJDKSer() {
		//获取开始时间
		Long start = System.currentTimeMillis();
		
		//计数
		Integer num = 0;
		
		System.out.println("使用了JDK的序列化方式");
		
		//循环50000次
		for(int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			user.setGender(UserUtils.getSex());
			user.setPhoneNum("13"+StringUtils.getRandomNumber(9));
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			
			//存入到redis
			redisTemplate.opsForValue().set("user"+i, user);
			num++;
		}
		//获取结束时间
		Long end = System.currentTimeMillis();
		
		System.out.println("插入了"+num+"条数据");
		System.out.println("共使用了"+(end-start)+"毫秒");
	}

	/**
	 * 
	 * @Title: testJSONSer 
	 * @Description: 使用JSON的序列化方式
	 * @return: void
	 */
	@Test
	public void testJSONSer() {
		//获取开始时间
		Long start = System.currentTimeMillis();
		
		//计数
		Integer num = 0;
		
		System.out.println("使用了JSON的序列化方式");
		
		//循环50000次
		for(int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			user.setGender(UserUtils.getSex());
			user.setPhoneNum("13"+StringUtils.getRandomNumber(9));
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			
			//存入到redis
			redisTemplate.opsForValue().set("user"+i, user);
			num++;
		}
		//获取结束时间
		Long end = System.currentTimeMillis();
		
		System.out.println("插入了"+num+"条数据");
		System.out.println("共使用了"+(end-start)+"毫秒");
	}

	/**
	 * 
	 * @Title: testHashSer 
	 * @Description: 使用Hash的序列化方式
	 * @return: void
	 */
	@Test
	public void testHashSer() {
		//获取开始时间
		Long start = System.currentTimeMillis();
		
		//计数
		Integer num = 0;
		
		System.out.println("使用了Hash的序列化方式");
		
		//使用HashMap的方式进行存储
		HashMap<String,User> map = new HashMap<String, User>();
		
		//循环50000次
		for(int i = 1; i <= 50000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			user.setGender(UserUtils.getSex());
			user.setPhoneNum("13"+StringUtils.getRandomNumber(9));
			user.setEmail(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			num++;
			map.put("user"+i, user);
		}
		//存入Hash类型数据
		redisTemplate.opsForHash().putAll("users", map);
		
		//获取结束时间
		Long end = System.currentTimeMillis();
		
		System.out.println("插入了"+num+"条数据");
		System.out.println("共使用了"+(end-start)+"毫秒");
	}
	
}

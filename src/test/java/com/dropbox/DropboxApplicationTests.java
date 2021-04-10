package com.dropbox;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dropbox.entity.User;
import com.dropbox.mapper.UserMapper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DropboxApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void DBTest(){
		List<User> userList = userMapper.selectList(null);
		userList.forEach(System.out::println);

		Map<String, Object> map = new HashMap<>();
		map.put("username", "draco");
		map.put("username", "draco");

		userMapper.insert(new User("draco", "draco"));

		List<User> userList1 = userMapper.selectByMap(map);

		userMapper.insert(new User("dfff", "drfff"));

		assert userList1.size() == 1;

	}

	@Test
	void MybatisPlusTest(){
		User user = new User("test", "test");
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.setEntity(user);
		List<Object> users = userMapper.selectObjs(wrapper);
	}

//	@Test
//	void jwtTest(){
//		JwtBuilder jwtBuilder = Jwts.builder()
//				.setId("8888")
//				.setSubject("User")
//				.setIssuedAt(new Date())
//				.signWith(SignatureAlgorithm.HS256, "xxxx");
//		String token = jwtBuilder.compact();
//		System.out.println(token);
//	}

}

package org.property.core.domain.mapper;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.User;

public interface UserMapper {

	public User queryUserByAccount(@Param("account") String account);
	
}

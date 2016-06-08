package org.property.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.UserSubscribedProperty;

public interface UserSubscribedPropertyMapper {

	public List<UserSubscribedProperty> querySubscriptionOfAccount(@Param("account") String account);
	
	public int insertSubscriptions(List<UserSubscribedProperty> subscriptions);
	
	public int deleteSubscriptions(@Param("account") String account,@Param("propertyId") String propertyId);
}

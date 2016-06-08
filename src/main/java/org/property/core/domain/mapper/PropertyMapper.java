package org.property.core.domain.mapper;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.Property;

public interface PropertyMapper {

	public 	Property queryPropertyByPropertyId(@Param("propertyId") String propertyId);
}

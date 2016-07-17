package org.property.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.Property;

public interface PropertyMapper {

	public 	Property queryPropertyByPropertyId(@Param("propertyId") String propertyId);
	public 	List<Property> queryPropertyByPropertyName(@Param("nameKeyword") String nameKeyword,@Param("n")int n);


}

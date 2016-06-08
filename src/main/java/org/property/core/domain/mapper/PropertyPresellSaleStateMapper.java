package org.property.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.PropertyPresellSaleState;

public interface PropertyPresellSaleStateMapper {
	
	
	public List<PropertyPresellSaleState> queryPresellSaleStateByPropertyPresellIdAndStateChangeTime(@Param("propertyId")String propertyId,@Param("presellId")String presellId,@Param("stateChangeTimeBegin")String stateChangeTimeBegin,@Param("stateChangeTimeEnd")String stateChangeTimeEnd);

	public List<PropertyPresellSaleState> queryPresellSaleStateByPropertyIdAndStateChangeTime(@Param("propertyId")String propertyId,@Param("stateChangeTimeBegin")String stateChangeTimeBegin,@Param("stateChangeTimeEnd")String stateChangeTimeEnd);

	public List<PropertyPresellSaleState> queryPresellSaleStateByPropertyId(@Param("propertyId")String propertyId);
	

	
}

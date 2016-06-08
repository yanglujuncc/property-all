package org.property.core.domain.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.PropertyHouseSaleState;

public interface PropertyHouseSaleStateMapper {
	
	public List<PropertyHouseSaleState> queryHouseSaleStateByPropertyIdAndStateChangeTime(@Param("propertyId")String propertyId,@Param("stateChangeTimeBegin")String stateChangeTimeBegin,@Param("stateChangeTimeEnd")String stateChangeTimeEnd);

	
	public List<PropertyHouseSaleState> queryHouseSaleStateByPropertyBuildingIdAndStateChangeTime(@Param("propertyId")String propertyId,@Param("buildingId")String buildingId ,@Param("stateChangeTimeBegin")String stateChangeTimeBegin,@Param("stateChangeTimeEnd")String stateChangeTimeEnd);

}

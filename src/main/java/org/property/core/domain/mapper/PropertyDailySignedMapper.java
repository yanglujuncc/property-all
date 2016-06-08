package org.property.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.property.core.domain.PropertyDailySigned;

public interface PropertyDailySignedMapper {
	public List<PropertyDailySigned> queryPropertyDailySignedByDate(@Param("signedDate")String signedDate);
	public List<PropertyDailySigned> queryPropertyDailySignedByDatePropertyId(@Param("signedDate")String signedDate,@Param("propertyId")String propertyId);
	public List<PropertyDailySigned> queryPropertyDailySignedByDateDistrict(@Param("signedDate")String signedDate,@Param("district")String district);
	public List<PropertyDailySigned> queryPropertyDailySignedByDatePropertyTypeCode(@Param("signedDate")String signedDate,@Param("propertyTypeCode")String propertyTypeCode);
	public List<PropertyDailySigned> queryPropertyDailySignedByDatePropertyTypeCodes(@Param("signedDate")String signedDate,@Param("propertyTypeCodes")List<String> propertyTypeCodes);
	public List<PropertyDailySigned> queryPropertyDailySignedByDateRangePropertyId(@Param("signedDateFrom")String signedDateFrom,@Param("signedDateTo")String signedDateTo,@Param("propertyId")String propertyId);
}

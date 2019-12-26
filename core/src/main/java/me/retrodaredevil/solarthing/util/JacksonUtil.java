package me.retrodaredevil.solarthing.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JacksonUtil {
	private JacksonUtil(){ throw new UnsupportedOperationException(); }

	public static ObjectMapper defaultMapper(ObjectMapper mapper){
		mapper.setConfig(
				mapper.getDeserializationConfig()
						.with(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
		);
//		.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) We can use this when deserializing in solarthing-android
		return mapper;
	}
	public static ObjectMapper defaultMapper(){
		return defaultMapper(new ObjectMapper());
	}
}

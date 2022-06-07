package com.api.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ObjectMapperConfig {

	/*
	 * JSON을 POJO로 변환할 때 맵핑되는 필드가 전혀 존재하지 않으면 기본적으로 예외가 발생한다. 
	 * 예외 발생을 원하지 않을 경우 SerializationFeature.FAIL_ON_EMPTY_BEANS 옵션을 비활성화하면 예외 없이 비어있는 POJO를 반환한다.
	 * 
	 * POJO를 JSON으로 변환할 때 날짜, 시간 타입의 데이터는 기본적으로 오브젝트의 형태가 그대로 변환되어 가독성이 심하게 떨어진다.
	 * 특히 주 용도가 REST API일 경우 특히 문제가 된다. SerializationFeature.WRITE_DATES_AS_TIMESTAMPS 옵션을 비활성화하고 
	 * JavaTimeModule 모듈을 등록하면 적절히 ISO 8601 형식의 문자열로 변환해준다.
	 */
    @Bean("objectMapper")
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
            .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .modules(new JavaTimeModule())
            .build();
    }
}
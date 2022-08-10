package com.api.server.util;

import java.sql.Clob;
import java.sql.SQLException;

import org.apache.commons.collections4.map.ListOrderedMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultMap extends ListOrderedMap<Object, Object> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Object put(Object key, Object value){
		Object setValue = null;
		
		Class<?> dataType = value.getClass();
		
		if(dataType.toString().toUpperCase().indexOf("CLOB") > -1) {
			try {
				Clob clob = (Clob) value;
				
				int size = (int) clob.length();
				setValue = (Object) clob.getSubString(1, size);
				
				value = setValue;
			} catch(SQLException se) {
				log.error("{}", se);
			}
		}
		
		return super.put(convert2CamelCase((String) key), value);
	}
	
	public String convert2CamelCase(String underScore) {
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		
		if(underScore.indexOf("_") < 0 && Character.isLowerCase(underScore.charAt(0))) {
			return underScore;
		}
		
		int len = underScore.length();
		
		for(int i = 0; i < len; i +=1) {
			char currentChar = underScore.charAt(i);
			
			if(currentChar == '_') {
				nextUpper = true;
			} else {
				if(nextUpper) {
					result.append(Character.toUpperCase(currentChar));
					nextUpper = false;
				} else {
					result.append(Character.toLowerCase(currentChar));
				}
			}
		}
		
		return result.toString();
		
	}
}


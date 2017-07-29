package com.github.querysearch.translators;

import java.util.Map;
import java.util.Objects;

public interface OrderByTranslator {
	
	default String translate(String name) {
		Map<String, String> mapTranslate = getTranslate();
		Objects.requireNonNull(mapTranslate);
		
		String nameTranslated = mapTranslate.get(name);
		return nameTranslated != null ? nameTranslated : name;
	}

	Map<String, String> getTranslate();

}

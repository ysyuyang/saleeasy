package cn.supstore.core.base.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class LocalDateTimeModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3141192727339823056L;

	public LocalDateTimeModule(DateTimeFormatter dtf) {
		super(PackageVersion.VERSION);
		
		LocalDateTimeSerializer instance = new LocalDateTimeSerializer(dtf);
		addSerializer(LocalDateTime.class, instance);
	}
}

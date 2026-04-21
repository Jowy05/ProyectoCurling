package com.urijoel.curling.config;

import com.urijoel.curling.model.Level;
import com.urijoel.curling.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
            new StringToLevelConverter(),
            new StringToRoleConverter()
        ));
    }

    @ReadingConverter
    static class StringToLevelConverter implements Converter<String, Level> {
        @Override
        public Level convert(String source) {
            return Level.fromString(source);
        }
    }

    @ReadingConverter
    static class StringToRoleConverter implements Converter<String, Role> {
        @Override
        public Role convert(String source) {
            if (source == null) return Role.USER;
            try { return Role.valueOf(source.toUpperCase().trim()); }
            catch (Exception e) { return Role.USER; }
        }
    }
}

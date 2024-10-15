package com.br.dojo360.mapper;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ModelMappingConfiguration {

    @Bean
    public CustomModelMapper modelMapper() {
        CustomModelMapper modelMapper = new CustomModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true) //
                .setAmbiguityIgnored(true);
        return modelMapper;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    static class CustomModelMapper extends ModelMapper {

        Set<Class<?>> allowedNullSources = new HashSet<>();

        @Override
        public <D> D map(Object source, Class<D> destinationType) {
            if (source == null && allowedNullSources.contains(destinationType)) {
                source = new Object();
            }
            return super.map(source, destinationType);
        }

        void allowNullSources(Class<?> destinationType) {
            allowedNullSources.add(destinationType);
        }
    }
}

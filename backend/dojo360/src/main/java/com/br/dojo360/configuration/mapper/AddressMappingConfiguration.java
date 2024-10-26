package com.br.dojo360.configuration.mapper;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.address.AddressEntity;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {
        modelMapper.createTypeMap(AddressData.class, AddressEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected AddressEntity convert(AddressData addressData) {
                        AddressEntity entity;
                        if (addressData.getUuid() == null) {
                            entity = new AddressEntity();
                        } else {
                            entity = new AddressEntity(addressData.getUuid());
                        }
                        entity.setStreet(addressData.getStreet());
                        entity.setCep(addressData.getCep());
                        entity.setCity(addressData.getCity());
                        entity.setNeighborhood(addressData.getNeighborhood());
                        entity.setState(addressData.getState());
                        entity.setComplement(addressData.getComplement());
                        entity.setNumber(addressData.getNumber());

                        return entity;
                    }
                });

        modelMapper.createTypeMap(AddressEntity.class, AddressData.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected AddressData convert(AddressEntity entity) {
                        AddressData addressData = new AddressData();
                        addressData.setUuid(entity.getId());
                        addressData.setStreet(entity.getStreet());
                        addressData.setCep(entity.getCep());
                        addressData.setCity(entity.getCity());
                        addressData.setNeighborhood(entity.getNeighborhood());
                        addressData.setState(entity.getState());
                        addressData.setComplement(entity.getComplement());
                        addressData.setNumber(entity.getNumber());

                        return addressData;
                    }
                });
    }
}

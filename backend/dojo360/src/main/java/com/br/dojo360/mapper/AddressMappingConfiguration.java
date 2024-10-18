package com.br.dojo360.mapper;

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
                        AddressEntity entity = new AddressEntity();
                        entity.setStreet(addressData.logradouro());
                        entity.setCep(addressData.cep());
                        entity.setCity(addressData.localidade());
                        entity.setNeighborhood(addressData.bairro());
                        entity.setState(addressData.uf());
                        entity.setComplement(addressData.complemento());
                        entity.setNumber(addressData.numero());
                        return entity;
                    }
                });

        modelMapper.createTypeMap(AddressEntity.class, AddressData.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected AddressData convert(AddressEntity entity) {
                        return new AddressData(
                                entity.getStreet(),
                                entity.getCep(),
                                entity.getCity(),
                                entity.getNeighborhood(),
                                entity.getState(),
                                entity.getComplement(),
                                entity.getNumber()
                        );
                    }
                });
    }
}

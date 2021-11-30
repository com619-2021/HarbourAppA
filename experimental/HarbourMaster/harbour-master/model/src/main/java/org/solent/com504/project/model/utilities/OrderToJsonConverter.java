/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.solent.com504.project.model.order.dto.Order;
import org.solent.com504.project.model.resource.dto.Characteristic;

// see https://stackoverflow.com/questions/16373811/how-to-map-an-arraylist-of-primitives-to-a-single-column

@Converter
public class OrderToJsonConverter implements AttributeConverter<Order, String> {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Order attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Order convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            // return mapper.readValue(dbData, List.class);
            // see https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
            return mapper.readValue(dbData, new TypeReference<Order>() {});
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
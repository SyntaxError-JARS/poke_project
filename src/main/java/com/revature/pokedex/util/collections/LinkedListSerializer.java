package com.revature.pokedex.util.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.pokedex.models.Trainer;

import java.io.IOException;

public class LinkedListSerializer extends StdSerializer<LinkedList> {

    public LinkedListSerializer() {
        this(null);
    }

    public LinkedListSerializer(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            Trainer trainer = (Trainer) linkedList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("fname", trainer.getFname());
            jsonGenerator.writeStringField("lname", trainer.getLname());
            jsonGenerator.writeStringField("email", trainer.getEmail());
            jsonGenerator.writeStringField("password", trainer.getPassword());
            jsonGenerator.writeStringField("dob", trainer.getDob());
            jsonGenerator.writeEndObject();
        }

    }
}

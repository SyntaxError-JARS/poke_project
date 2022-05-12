package com.revature.pokedex.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.LinkedList;

import java.io.IOException;

public class LinkedListSerializerAbilties extends StdSerializer<LinkedList> {

    public LinkedListSerializerAbilties() {
        this(null);
    }

    public LinkedListSerializerAbilties(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            Abilities ability = (Abilities) linkedList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("abilityName", ability.getAbilityName());
            jsonGenerator.writeNumberField("atkMultiplier", ability.getAtkMultiplier());
            jsonGenerator.writeNumberField("dmgType", ability.getDmgType());
            jsonGenerator.writeEndObject();
        }

    }
}

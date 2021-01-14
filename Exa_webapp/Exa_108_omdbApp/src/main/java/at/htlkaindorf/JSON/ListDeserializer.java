/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.JSON;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gottl
 */
public class ListDeserializer extends StdDeserializer<List>{
    public ListDeserializer() {
		super(List.class);
	}

	@Override
	public List deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new ArrayList(Arrays.asList(p.getValueAsString().split(", ")));
	}
}

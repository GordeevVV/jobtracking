package com.job_tracking_system.handlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.job_tracking_system.entity.GetMessage;
import com.job_tracking_system.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageParser {
    Message parse(String jsonObject) {

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonObject);
        if(jsonElement.isJsonObject()){
            if(jsonElement.getClass().equals(GetMessage.class)){
                GetMessage getMessage=new GetMessage();
                getMessage.setColumn(jsonElement.getAsJsonArray().get(0).toString());
                return getMessage;
            }
        } else {
            throw new JsonParseException("Message can't be read as json object");
        }
        return null;
    }
}

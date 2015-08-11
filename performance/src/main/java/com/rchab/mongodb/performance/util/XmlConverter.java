package com.rchab.mongodb.performance.util;

import org.json.JSONObject;
import org.json.XML;

public class XmlConverter {

    public static JSONObject convertXMLtoJSON(String xmlText) {
        return  XML.toJSONObject(xmlText);
    }
}

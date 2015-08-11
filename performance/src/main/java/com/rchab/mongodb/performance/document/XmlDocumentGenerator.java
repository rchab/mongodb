package com.rchab.mongodb.performance.document;

import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.Document;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class XmlDocumentGenerator {

    private static Random random = new Random();

    public static Document generateFullDocument() {
        return new Document()
                .append("chapters", new BsonArray(Arrays.asList(
                        generateDateSchemeDocument(),
                        generateExecutionDoseDocument(),
                        generateExecutionTimeIntervalDocument()
                )))
                .append("header", generateHeaderDocument());
    }

    private static BsonDocument generateDateSchemeDocument() {
        return new BsonDocument("DateSchemeEntryDocumentChapter",
                new BsonDocument().append("type", new BsonString("/cgm/g3/his/sma/scheduling/schemeentryinstance/date/DateSchemeEntryDocumentChapter"))
                .append("from", new BsonDateTime(random.nextLong()))
                .append("multiplier", new BsonDouble(random.nextDouble()))
                .append("schemaEntryType", new BsonString("DOSAGE"))
                .append("to", new BsonDateTime(random.nextLong())));

    }

    private static BsonDocument generateExecutionDoseDocument() {
        return new BsonDocument("ExecutionDoseChapter", new BsonDocument()
                .append("type", new BsonString("/cgm/g3/his/med/execution/document/ExecutionDoseChapter"))
                .append("chapters", new BsonArray(
                        Arrays.asList(generateExecutionDoseItemDocument()))));

    }

    private static BsonDocument generateExecutionDoseItemDocument(){
        return new BsonDocument("ExecutionDoseItemChapter", new BsonDocument("type", new BsonString("/cgm/g3/his/med/execution/document/ExecutionDoseItemChapter"))
        .append("doseUnit", new BsonString("mg"))
        .append("drugId", new BsonString(UUID.randomUUID().toString()))
        .append("plannedDose", new BsonDocument("type", new BsonString("/cgm/g3/his/med/medication/dosage/DecimalDoseDTO"))
        .append("administered", new BsonBoolean(random.nextBoolean()))
        .append("value", new BsonInt64(random.nextInt())))
        .append("plannedFormattedDose", new BsonInt64(random.nextInt())));
    }

    private static BsonDocument generateExecutionTimeIntervalDocument(){
        return new BsonDocument("type", new BsonString("/cgm/g3/his/med/execution/document/ExecutionTimeInterval"))
                .append("start", new BsonDateTime(random.nextLong()))
                .append("end", new BsonDateTime(random.nextLong()));
    }

    private static BsonDocument generateHeaderDocument(){
        return new BsonDocument("header",
                new BsonDocument("type", new BsonString("/cgm/g3/his/sma/document/ServiceDocumentHeader"))
                        .append("documentId", new BsonDocument("type", new BsonString("/cgm/g3/his/sma/document/DocumentId"))
                        .append("id", new BsonString(UUID.randomUUID().toString())))
        .append("serviceInstanceDTO", new BsonDocument("type", new BsonString("/cgm/g3/his/tsm/core/lifecycle/interfaces/instance/dto/ServiceInstanceDTO")))
                        .append("episodeMc", new BsonInt64(random.nextInt()))
                        .append("id", new BsonString(UUID.randomUUID().toString()))
                        .append("parent", new BsonString(UUID.randomUUID().toString()))
                        .append("parentStatefulObjectId", new BsonString(UUID.randomUUID().toString()))
                        .append("parentVersion", new BsonInt64(random.nextInt()))
                        .append("processDefId", new BsonString("/cgm/g3/his/sma/statemachine/ScheduledPreparedDone"))
                        .append("processType", new BsonString("EXECUTION"))
                        .append("serviceId", new BsonString("StandardMedica4300"))
                        .append("state", new BsonString("Scheduled"))
                        .append("statefulObjectId", new BsonString(UUID.randomUUID().toString()))
                        .append("version", new BsonInt64(random.nextInt())));
    }
}

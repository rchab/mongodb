package com.rchab.mongodb.performance;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rchab.mongodb.performance.document.XmlDocumentGenerator;
import com.rchab.mongodb.performance.util.XmlConverter;
import org.bson.Document;

import java.util.Date;

public class App {

    public static final String XML_TEXT = "<Document type=\"/cgm/g3/his/tsm/core/document/interfaces/dto/Document\">\n" +
            "\t<chapters type=\"@DataObject\">\n" +
            "\t\t<DateSchemeEntryDocumentChapter type=\"/cgm/g3/his/sma/scheduling/schemeentryinstance/date/DateSchemeEntryDocumentChapter\">\n" +
            "\t\t\t<from>2014-11-26 14:00:00.000</from>\n" +
            "\t\t\t<multiplier>240.0</multiplier>\n" +
            "\t\t\t<schemeEntryType>DOSAGE</schemeEntryType>\n" +
            "\t\t\t<to>2014-11-26 14:00:00.000</to>\n" +
            "\t\t</DateSchemeEntryDocumentChapter>\n" +
            "\t\t<ExecutionDoseChapter type=\"/cgm/g3/his/med/execution/document/ExecutionDoseChapter\">\n" +
            "\t\t\t<chapters type=\"@DataObject\">\n" +
            "\t\t\t\t<ExecutionDoseItemChapter type=\"/cgm/g3/his/med/execution/document/ExecutionDoseItemChapter\">\n" +
            "\t\t\t\t\t<doseUnit>mg</doseUnit>\n" +
            "\t\t\t\t\t<drugId>SORD17S1Y3Q2M283LN</drugId>\n" +
            "\t\t\t\t\t<plannedDose type=\"/cgm/g3/his/med/medication/dosage/DecimalDoseDTO\">\n" +
            "\t\t\t\t\t\t<administered>false</administered>\n" +
            "\t\t\t\t\t\t<value>240</value>\n" +
            "\t\t\t\t\t</plannedDose>\n" +
            "\t\t\t\t\t<plannedFormattedDose>240</plannedFormattedDose>\n" +
            "\t\t\t\t</ExecutionDoseItemChapter>\n" +
            "\t\t\t</chapters>\n" +
            "\t\t</ExecutionDoseChapter>\n" +
            "\t\t<ExecutionTimeInterval type=\"/cgm/g3/his/med/execution/document/ExecutionTimeInterval\">\n" +
            "\t\t\t<end>2014-11-26 14:00:00.000</end>\n" +
            "\t\t\t<start>2014-11-26 14:00:00.000</start>\n" +
            "\t\t</ExecutionTimeInterval>\n" +
            "\t</chapters>\n" +
            "\t\n" +
            "\t<header type=\"/cgm/g3/his/sma/document/ServiceDocumentHeader\">\n" +
            "\t\t<documentId type=\"/cgm/g3/his/sma/document/DocumentId\">\n" +
            "\t\t\t<id>HQSU6UMDNS73Y677AK</id>\n" +
            "\t\t</documentId>\n" +
            "\t\t<serviceInstanceDTO type=\"/cgm/g3/his/tsm/core/lifecycle/interfaces/instance/dto/ServiceInstanceDTO\">\n" +
            "\t\t\t<episodeMc>210000004</episodeMc>\n" +
            "\t\t\t<id>HQSU6UMDNS73Y677AK</id>\n" +
            "\t\t\t<parent>HQSU4WPE5RH5CVMQLC</parent>\n" +
            "\t\t\t<parentStatefulObjectId>HQSU4WWUUNTNWB69MH</parentStatefulObjectId>\n" +
            "\t\t\t<parentVersion>1</parentVersion>\n" +
            "\t\t\t<processDefId>/cgm/g3/his/sma/statemachine/ScheduledPreparedDone</processDefId>\n" +
            "\t\t\t<processType>EXECUTION</processType>\n" +
            "\t\t\t<serviceId>StandardMedica4300</serviceId>\n" +
            "\t\t\t<state>Scheduled</state>\n" +
            "\t\t\t<statefulObjectId>HQSU6UGQLEF6FL6550</statefulObjectId>\n" +
            "\t\t\t<version>1</version>\n" +
            "\t\t</serviceInstanceDTO>\n" +
            "\t</header>\n" +
            "</Document>";

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("cgmdb");
        MongoCollection<Document> cgmCollection = db.getCollection("cgmCollection");
        System.out.println(new Date());
//        for (int i = 0; i < 100000; i++) {
//
//            cgmCollection.insertOne(XmlDocumentGenerator.generateFullDocument());
//            System.out.println(i);
//        }

        System.out.println(XmlConverter.convertXMLtoJSON(XML_TEXT).toString(4));
        System.out.println(new Date());
        mongoClient.close();
    }
}

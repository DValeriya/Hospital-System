package com.teama.hospitalsystem.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.teama.hospitalsystem.models.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class AppointmentDeserializer extends StdDeserializer<Appointment> {
    Logger LOGGER = LoggerFactory.getLogger(AppointmentDeserializer.class);
    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public AppointmentDeserializer(){
        this(null);
    }
    public AppointmentDeserializer(Class<?> vc){
        super(vc);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC +2"));
    }

    @Override
    public Appointment deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        BigInteger id = null;
        if(node.has("id")){
            id = node.get("id").bigIntegerValue();
        }
        BigInteger doctorId = node.get("doctorId").bigIntegerValue();
        BigInteger patientId = null;
        if(node.has("patientId")){
            patientId = node.get("patientId").bigIntegerValue();
        }
        String diagnosis = null;
        if(node.has("diagnosis")){
            diagnosis = node.get("diagnosis").asText();
        }
        String referral = null;
        if(node.has("referral")){
            referral = node.get("referral").asText();
        }
        BigInteger nextAppointment = null;
        if (node.has("nextAppointment")) {
            nextAppointment = node.get("nextAppointment").bigIntegerValue();
        }

        AppointmentStatus status;
        if (node.get("status").isNumber()) {
            status = AppointmentStatus.fromId(node.get("status").bigIntegerValue());
        } else {
            status = AppointmentStatus.valueOf(node.get("status").asText());
        }
        try {
            Date expectedStart = sdf.parse(node.get("expectedStart").asText());
            Date actualStart = null;
            if (node.has("actualStart")) {
                actualStart = sdf.parse(node.get("actualStart").asText());
            }
            Date expectedEnd = sdf.parse(node.get("expectedEnd").asText());
            Date actualEnd = null;
            if (node.has("actualEnd")) {
                actualEnd = sdf.parse(node.get("actualEnd").asText());
            }
            return new Appointment.Builder(id, expectedStart, expectedEnd,doctorId, status)
                    .withNextAppointment(nextAppointment)
                    .withReferral(referral)
                    .withDiagnosis(diagnosis)
                    .withPatientId(patientId)
                    .withActualStart(actualStart)
                    .withActualEnd(actualEnd)
                    .build();
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

}

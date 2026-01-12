package org.example.patientservice.Kafka;

import org.example.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer{
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String,byte[]> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient){
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PatientCreated")
                .build();

        try {
            kafkaTemplate.send("patient", event.toByteArray());
            // ADD THIS LOG LINE:
            log.info("Sent Kafka event for Patient ID: {}", patient.getId());
        } catch (Exception e) {
            log.error("Error sending event patient created even:", e);
        }
    }

}

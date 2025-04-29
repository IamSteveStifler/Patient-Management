package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> resultFromDb = patientRepository.findAll();
        return resultFromDb.stream().map(PatientMapper::patientToPatientResponse).toList();
    }

    public PatientResponseDTO savePatient(PatientRequestDTO patientRequest) {
        return PatientMapper.patientToPatientResponse(
                patientRepository.save(PatientMapper.patientRequestTOPatient(patientRequest))
        );
    }

}

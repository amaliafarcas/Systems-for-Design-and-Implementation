package a1.Service;

import a1.DTO.join.MedicalRecordDTO;
import a1.DTO.update.MedicalRecordReadDTO;
import a1.Domain.MedicalRecord;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecordDTO> getMedicalRecords();
    MedicalRecordDTO getMedicalRecordById(Long medicalRecordId);
    MedicalRecord addNewMedicalRecord(MedicalRecordDTO medicalRecordDTO);
    void deleteMedicalRecord(Long medicalRecordId);
    void updateMedicalRecord(MedicalRecordReadDTO medicalRecordDTO);
    Page<MedicalRecordDTO> findAll(int page, int size);

}

package a1.Controller;

import a1.DTO.join.MedicalRecordDTO;
import a1.DTO.update.MedicalRecordReadDTO;
import a1.Domain.MedicalRecord;
import a1.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public ResponseEntity<Page<MedicalRecordDTO>> getAllShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MedicalRecordDTO> items = medicalRecordService.findAll(page, size);
        return ResponseEntity.ok(items);
    }

/*
    @GetMapping
    public List<MedicalRecordDTO> getMedicalRecords(){
        return medicalRecordService.getMedicalRecords();
    }
*/

    @GetMapping(path = "{medicalRecordId}")
    public MedicalRecordDTO getMedicalRecordById(@PathVariable("medicalRecordId") Long medicalRecordId){
        return medicalRecordService.getMedicalRecordById(medicalRecordId);
    }

    @PostMapping
    public MedicalRecord addNewMedicalRecord(@RequestBody MedicalRecordDTO medicalRecord) {
        MedicalRecord newMedicalRecord = medicalRecordService.addNewMedicalRecord(medicalRecord);
        if (newMedicalRecord != null) {
            return newMedicalRecord;
        }
        return null;
    }

    @DeleteMapping(path = "{medicalRecordId}")
    public void deleteMedicalRecord(@PathVariable("medicalRecordId") Long medicalRecordId){
        medicalRecordService.deleteMedicalRecord(medicalRecordId);
    }

    @PutMapping
    public void updateMedicalRecord(@RequestBody MedicalRecordReadDTO medicalRecord){
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

}

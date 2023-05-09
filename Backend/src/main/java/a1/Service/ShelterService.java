package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import a1.DTO.reports.VaccinatedAnimalsRatioSheltersDTO;
import a1.Domain.CustomPageable;
import a1.Domain.Shelter;
import a1.Repository.ShelterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShelterService implements IShelterService{

    private final ShelterRepository shelterRepository;

    @Autowired
    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }


    @Override
    public Long getCount() {
        return shelterRepository.count();
    }
    public Long getCountCapacity(int capacity) {
        return shelterRepository.countCapacity(capacity);
    }

    public List<ShelterIdDTO> getShelters() {
        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findAll()){
            shelters.add(new ShelterIdDTO(a));
        }

        return shelters;
    }

    /*public List<Optional<Shelter>> getSheltersFilter(String name, String city) {
        if(name!=null){
            return Collections.singletonList(shelterRepository.findShelterByName(name));
        }
        if(city!=null){
            return Collections.singletonList(shelterRepository.findShelterByCity(city));
        }
        return null;
    }*/

    public Page<ShelterIdDTO> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findAll(pageable)){
            shelters.add(new ShelterIdDTO(a));
        }

        return new PageImpl<>(shelters, pageable, shelters.size());
    }

    public ShelterListDTO getShelterById(Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElseThrow(()->new IllegalStateException("shelter with id" + shelterId + " does not exist"));

        ShelterListDTO shelterDTO = new ShelterListDTO(shelter);

        return shelterDTO;
    }

    public void addNewShelter(ShelterDTO shelterDTO) {
        Optional<Shelter> shelterOptional = shelterRepository.findShelterByPhoneNumber(shelterDTO.getPhoneNumber());
        if(shelterOptional.isPresent()){
            throw new IllegalStateException("Phone number already exists");
        }

        shelterOptional = shelterRepository.findShelterByPostalCode(shelterDTO.getPostalCode());
        if(shelterOptional.isPresent()){
            throw new IllegalStateException("Postal code already exists");
        }

        if(shelterDTO.getPhoneNumber().toString().length()!=11){
            throw new IllegalStateException("Invalid phone number");
        }

        if(shelterDTO.getCapacity()<=0){
            throw new IllegalStateException("Invalid capacity");
        }

        shelterRepository.save(new Shelter(shelterDTO.getName(), shelterDTO.getCity(), shelterDTO.getPostalCode(), shelterDTO.getPhoneNumber(), shelterDTO.getCapacity()));
    }

    public void deleteShelter(Long shelterId) {
        boolean exists = shelterRepository.existsById(shelterId);
        if(!exists){
            throw  new IllegalStateException("shelter with id" + shelterId + " does not exist");
        }
        shelterRepository.deleteById(shelterId);
    }

    public void updateShelter(ShelterDTO shelterDTO) {
        Shelter updateShelter = shelterRepository.findById(shelterDTO.getId()).orElseThrow(() -> new IllegalStateException("shelter with id" + shelterDTO.getId() + "does not exist"));

        if(shelterDTO.getName()!=null && shelterDTO.getName().length()>0 && !Objects.equals(shelterDTO.getName(), updateShelter.getName())){
            updateShelter.setName(shelterDTO.getName());
        }

        if(shelterDTO.getCity()!=null && shelterDTO.getCity().length()>0 && !Objects.equals(shelterDTO.getCity(), updateShelter.getCity())){
            updateShelter.setCity(shelterDTO.getCity());
        }

        if(shelterDTO.getCapacity()>0 && shelterDTO.getCapacity() != updateShelter.getCapacity()){
            updateShelter.setCapacity(shelterDTO.getCapacity());
        }

        if(shelterDTO.getPhoneNumber()!=null && shelterDTO.getPhoneNumber().toString().length()==11 && !Objects.equals(shelterDTO.getPhoneNumber(), updateShelter.getPhoneNumber())){
            updateShelter.setPhoneNumber(shelterDTO.getPhoneNumber());
        }

        if(shelterDTO.getPostalCode()!=null && shelterDTO.getPostalCode().toString().length()==6 && !Objects.equals(shelterDTO.getPostalCode(), updateShelter.getPostalCode())){
            updateShelter.setPostalCode(shelterDTO.getPostalCode());
        }

        shelterRepository.save(updateShelter);
    }

    public Page<ShelterIdDTO> getSheltersGreaterCapacity(Integer capacity, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("capacity"));

        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findGreaterCapacity(pageable, capacity)){
            shelters.add(new ShelterIdDTO(a));
        }

        return new PageImpl<>(shelters, pageable, shelters.size());
    }

    public List<ShelterIdDTO> getSheltersGreaterCapacity(Integer capacity) {
        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findAll()){
            if(a.getCapacity()>capacity) {
                shelters.add(new ShelterIdDTO(a));
            }
        }

        return shelters;
    }

/*    public String exportReport(String reportFormat, List<IDTO> list, String name) throws FileNotFoundException, JRException {
        List<AverageAgeVolunteersShelterDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new AverageAgeVolunteersShelterDTO(s));
        }

        File file = ResourceUtils.getFile("classpath:" + name+".jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\" + name + ".html");
        }

        return "report generated in path C:\\Users\\Ama";
    }*/

    public List<AverageAgeVolunteersShelterDTO> exportReportVolunteer2() {
        List<AverageAgeVolunteersShelterDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new AverageAgeVolunteersShelterDTO(s));
        }

        return averageList;
/*        File file = ResourceUtils.getFile("classpath:volunteer.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(averageList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\volunteers.html");
        }

        return jasperPrint;*/
    }

    public Page<AverageAgeVolunteersShelterDTO> exportReportVolunteer2(int pageNumber, int pageSize) {
        CustomPageable pageable = new CustomPageable(pageNumber, pageSize);

        List<AverageAgeVolunteersShelterDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll(pageable)){
            averageList.add(new AverageAgeVolunteersShelterDTO(s));
        }

        return new PageImpl<>(averageList, pageable, averageList.size());
    }

    public List<VaccinatedAnimalsRatioSheltersDTO> exportReportAnimal(){
        List<VaccinatedAnimalsRatioSheltersDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll()){
            averageList.add(new VaccinatedAnimalsRatioSheltersDTO(s));
        }
/*
        File file = ResourceUtils.getFile("classpath:animalVaccine.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(averageList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created by", "Ama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\Ama"+"\\animalVaccine.html");
        }*/

        return averageList;
    }

    public Page<VaccinatedAnimalsRatioSheltersDTO> exportReportAnimal2(int pageNumber, int pageSize) {
        CustomPageable pageable = new CustomPageable(pageNumber, pageSize);

        List<VaccinatedAnimalsRatioSheltersDTO> averageList = new ArrayList<>();
        for(Shelter s: shelterRepository.findAll(pageable)){
            averageList.add(new VaccinatedAnimalsRatioSheltersDTO(s));
        }

        return new PageImpl<>(averageList, pageable, averageList.size());
    }

    public Page<ShelterIdDTO> searchByName(String name, int pageNumber, int pageSize) {
        CustomPageable pageable = new CustomPageable(pageNumber, pageSize);

        List<ShelterIdDTO> shelters = new ArrayList<>();
        for(Shelter a: shelterRepository.findByNameContainingIgnoreCase(name, pageable)){
            shelters.add(new ShelterIdDTO(a));
        }

        return new PageImpl<>(shelters, pageable, shelters.size());
    }
}

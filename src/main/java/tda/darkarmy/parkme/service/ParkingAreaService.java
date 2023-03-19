package tda.darkarmy.parkme.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tda.darkarmy.parkme.dto.ParkingAreaDto;
import tda.darkarmy.parkme.exception.ResourceNotFoundException;
import tda.darkarmy.parkme.model.ParkingArea;
import tda.darkarmy.parkme.model.ParkingSlot;
import tda.darkarmy.parkme.model.User;
import tda.darkarmy.parkme.repository.ParkingAreaRepository;
import tda.darkarmy.parkme.repository.ParkingSlotRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingAreaService {

    private final String BASE_URL = "http://localhost:8000";
    private Path fileStoragePath;
    
    @Autowired
    private ParkingAreaRepository parkingAreaRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public ParkingAreaService() {
        try {
            fileStoragePath = Paths.get("src\\main\\resources\\static\\fileStorage").toAbsolutePath().normalize();
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public List<ParkingArea> getAll(){
        return parkingAreaRepository.findAll();
    }


    public List<ParkingArea> findMyParkingArea(){
        User user = userService.getLoggedInUser();
        return parkingAreaRepository.findAll();
    }

    public ParkingArea findById(Long id){
        return parkingAreaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ParkingArea not found"));
    }

    public ParkingArea addParkingArea(ParkingAreaDto parkingAreaDto){
        User user = userService.getLoggedInUser();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(parkingAreaDto.getImage().getOriginalFilename()));
        fileName = fileName.replace(" ", "");
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(parkingAreaDto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ParkingArea parkingArea = modelMapper.map(parkingAreaDto, ParkingArea.class);
        //parkingArea.setUser(userService.getLoggedInUser());
        parkingArea.setImageUrl(BASE_URL + "/fileStorage/" + fileName);
        parkingArea.setUser(user);
        ParkingArea savedParkingArea = parkingAreaRepository.save(parkingArea);
        
        for(int i=0;i<savedParkingArea.getNumberOfSlots();i++){
            parkingSlotRepository.save(new ParkingSlot(i+1, parkingArea, false));
        }
        return savedParkingArea;
    }

    public ParkingArea updateParking(Long parkingAreaId, ParkingAreaDto parkingAreaDto){
        ParkingArea parkingArea = parkingAreaRepository.findById(parkingAreaId).orElseThrow(()-> new ResourceNotFoundException("ParkingArea not found"));
        ParkingArea parkingArea1 = modelMapper.map(parkingAreaDto, ParkingArea.class);
        //parkingArea1.setUser(userService.getLoggedInUser());
        parkingArea1.setId(parkingAreaId);
        parkingArea1.setImageUrl(parkingArea.getImageUrl());
        parkingArea1.setChargePerHour(parkingAreaDto.getChargePerHour());
        ParkingArea parkingArea2 = parkingAreaRepository.save(parkingArea1);
        for(int i=0;i<parkingArea2.getNumberOfSlots();i++){
            parkingSlotRepository.save(new ParkingSlot(i+1, parkingArea2, false));
        }
        return  parkingArea2;
    }

    public String deleteParkingArea(Long parkingAreaId){
        ParkingArea parkingArea = parkingAreaRepository.findById(parkingAreaId).orElseThrow(()-> new ResourceNotFoundException("ParkingArea not found"));
        
        parkingAreaRepository.deleteById(parkingAreaId);
        return "Parking Area deleted successfully";
    }
}

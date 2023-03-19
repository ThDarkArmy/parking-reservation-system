package tda.darkarmy.parkme.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.parkme.dto.ParkingAreaDto;
import tda.darkarmy.parkme.service.ParkingAreaService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/parkingarea")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParkingAreaController {
    @Autowired
    private ParkingAreaService parkingAreaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return status(200).body(parkingAreaService.getAll());
    }

    @GetMapping("/my-parking-area")
    public ResponseEntity<?> findMyParkingArea(){
        return status(200).body(parkingAreaService.findMyParkingArea());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long parkingAreaId){
        return status(200).body(parkingAreaService.findById(parkingAreaId));
    }

    @PostMapping()
    public ResponseEntity<?> addParkingArea( @ModelAttribute ParkingAreaDto parkingAreaDto){
        return status(201).body(parkingAreaService.addParkingArea(parkingAreaDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateParkingArea(@PathVariable("id") Long parkingAreaId, @RequestBody ParkingAreaDto parkingAreaDto){
        return status(200).body(parkingAreaService.updateParking(parkingAreaId, parkingAreaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParkingArea(@PathVariable("id") Long parkingAreaId){
        return status(200).body(parkingAreaService.deleteParkingArea(parkingAreaId));
    }
}

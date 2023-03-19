package tda.darkarmy.parkme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.parkme.dto.BookParkingSlotDto;
import tda.darkarmy.parkme.service.BookParkingSlotService;

import javax.mail.MessagingException;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/bookparkingslot")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookParkingSlotController {
    @Autowired
    private BookParkingSlotService bookParkingSlotService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookingSlots(){
        return status(200).body(bookParkingSlotService.findAll());
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<?> getMyBookings(){
        return status(200).body(bookParkingSlotService.findMyBookings());
    }

    @PostMapping("/")
    public ResponseEntity<?> bookASlot(@RequestBody BookParkingSlotDto bookParkingSlotDto) throws MessagingException {
        return status(201).body(bookParkingSlotService.bookASlot(bookParkingSlotDto));
    }

    @PutMapping("/update-booking/{id}")
    public ResponseEntity<?> updateBooking(@RequestBody BookParkingSlotDto bookParkingSlotDto, @PathVariable("id") Long parkingSlotId ){
        return status(200).body(bookParkingSlotService.updateBooking(bookParkingSlotDto, parkingSlotId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") Long parkingSlotId ){
        return status(200).body(bookParkingSlotService.cancelBooking(parkingSlotId));
    }
}

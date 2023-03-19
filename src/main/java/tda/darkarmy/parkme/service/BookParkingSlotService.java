package tda.darkarmy.parkme.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.parkme.dto.BookParkingSlotDto;
import tda.darkarmy.parkme.exception.ResourceNotFoundException;
import tda.darkarmy.parkme.model.BookParkingSlot;
import tda.darkarmy.parkme.model.ParkingSlot;
import tda.darkarmy.parkme.model.User;
import tda.darkarmy.parkme.repository.BookParkingSlotRepository;
import tda.darkarmy.parkme.repository.ParkingSlotRepository;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class BookParkingSlotService {
    @Autowired
    private BookParkingSlotRepository bookParkingSlotRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailSenderService mailSenderService;

    public List<BookParkingSlot> findAll() {
        return bookParkingSlotRepository.findAll();
    }

    public List<BookParkingSlot> findMyBookings() {
        User user = userService.getLoggedInUser();
        return bookParkingSlotRepository.findAll();
    }

    public BookParkingSlot  bookASlot(BookParkingSlotDto bookParkingSlotDto) throws MessagingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime arrivalTime = LocalDateTime.parse(bookParkingSlotDto.getArrivalDateTime(), formatter);
        LocalDateTime departureTime =  LocalDateTime.parse(bookParkingSlotDto.getDepartureDateTime(), formatter);
        User user = userService.getLoggedInUser();
        BookParkingSlot bookParkingSlot = new BookParkingSlot();
        bookParkingSlot.setVehicleName(bookParkingSlotDto.getVehicleName());
        bookParkingSlot.setVehicleNumber(bookParkingSlotDto.getVehicleNumber());
        bookParkingSlot.setDepartureDateTime(bookParkingSlotDto.getDepartureDateTime());
        bookParkingSlot.setArrivalDateTime(bookParkingSlotDto.getArrivalDateTime());
        bookParkingSlot.setUser(user);
        ParkingSlot parkingSlot = parkingSlotRepository.findById(bookParkingSlotDto.getParkingSlotId()).orElseThrow(()-> new ResourceNotFoundException("Parking slot not found"));
        parkingSlot.setBooked(true);
        parkingSlotRepository.save(parkingSlot);
        double parkingAreaCharge = parkingSlot.getParkingArea().getChargePerHour();
        bookParkingSlot.setParkingSlot(parkingSlot);
        bookParkingSlot.setTotalPrice(parkingAreaCharge * Duration.between(departureTime, arrivalTime).toHours());
        BookParkingSlot bookParkingSlot1 = bookParkingSlotRepository.save(bookParkingSlot);
        String mailBodyText="<h3>Hi "+user.getName()+",<br/><h3>Kindly find the parking details below</h3><br/></h3><br/><h3>Address: "+ bookParkingSlot1.getParkingSlot().getParkingArea().getAddress()+"</h3><br/><h3>Arrival Time : "+bookParkingSlot1.getArrivalDateTime()+"</h3><br/><h3>Departure Time: "+bookParkingSlot1.getDepartureDateTime()+"</h3></br><h3>Total Price:" +bookParkingSlot1.getTotalPrice()+"</h3></br>";
        mailSenderService.send(user, mailBodyText);;
        return bookParkingSlot1;
    }

    public BookParkingSlot updateBooking(BookParkingSlotDto bookParkingSlotDto, Long bookParkingSlotId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime arrivalTime = LocalDateTime.parse(bookParkingSlotDto.getArrivalDateTime(), formatter);
        LocalDateTime departureTime =  LocalDateTime.parse(bookParkingSlotDto.getDepartureDateTime(), formatter);
        BookParkingSlot bookParkingSlot = bookParkingSlotRepository.findById(bookParkingSlotId).orElseThrow(()-> new ResourceNotFoundException("Book Parking Slot not found"));
        BookParkingSlot bookParkingSlot1 = new BookParkingSlot();
        bookParkingSlot1.setVehicleName(bookParkingSlotDto.getVehicleName());
        bookParkingSlot1.setVehicleNumber(bookParkingSlotDto.getVehicleNumber());
        bookParkingSlot1.setDepartureDateTime(bookParkingSlotDto.getDepartureDateTime());
        bookParkingSlot1.setArrivalDateTime(bookParkingSlotDto.getArrivalDateTime());
        bookParkingSlot1.setId(bookParkingSlotId);
        bookParkingSlot.setTotalPrice(bookParkingSlot.getTotalPrice() * Duration.between(departureTime, arrivalTime).toHours());
        return bookParkingSlotRepository.save(bookParkingSlot1);
    }

    public String cancelBooking(Long bookParkingSlotId) {
        BookParkingSlot bookParkingSlot = bookParkingSlotRepository.findById(bookParkingSlotId).orElseThrow(()-> new ResourceNotFoundException("Book Parking Slot not found"));
        Optional<ParkingSlot> parkingSlotOptional = parkingSlotRepository.findById(bookParkingSlot.getParkingSlot().getId());
        if(parkingSlotOptional.isPresent()){
            ParkingSlot parkingSlot = parkingSlotOptional.get();
            parkingSlot.setBooked(false);
            parkingSlotRepository.save(parkingSlot);
        }

        bookParkingSlotRepository.deleteById(bookParkingSlotId);
        return "Booking cancelled successfully";
    }
}

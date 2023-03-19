package tda.darkarmy.parkme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer slotNumber;

    private Boolean booked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_area_id", nullable = false)
    @JsonIgnore
    private ParkingArea parkingArea;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "parking_slot_id")
    private List<BookParkingSlot> bookParkingSlot;

    public ParkingSlot() {
    }

    public ParkingSlot(Long id, Integer slotNumber, Boolean booked, ParkingArea parkingArea) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.booked = booked;
        this.parkingArea = parkingArea;
    }

    public ParkingSlot(Integer slotNumber, ParkingArea parkingArea, boolean booked) {
        this.slotNumber = slotNumber;
        this.booked = booked;
        this.parkingArea = parkingArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public ParkingArea getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(ParkingArea parkingArea) {
        this.parkingArea = parkingArea;
    }

    public List<BookParkingSlot> getBookParkingSlot() {
        return bookParkingSlot;
    }

    public void setBookParkingSlot(List<BookParkingSlot> bookParkingSlot) {
        this.bookParkingSlot = bookParkingSlot;
    }



    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotNumber=" + slotNumber +
                ", booked=" + booked +
                ", parkingArea=" + parkingArea +
                '}';
    }
}

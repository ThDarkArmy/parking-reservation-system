package tda.darkarmy.parkme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BookParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleNumber;
    private String vehicleName;
    private String arrivalDateTime;
    private String departureDateTime;
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_slot_id", nullable = false)
    @JsonIgnore
    private ParkingSlot parkingSlot;

    public BookParkingSlot() {
    }

    public BookParkingSlot(Long id, String vehicleNumber, String vehicleName, String arrivalDateTime, String departureDateTime) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }

    public BookParkingSlot(Long id, String vehicleNumber, String vehicleName, String arrivalDateTime, String departureDateTime, Double totalPrice) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookParkingSlot{" +
                "id=" + id +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                ", departureDateTime='" + departureDateTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                ", parkingSlot=" + parkingSlot +
                '}';
    }
}

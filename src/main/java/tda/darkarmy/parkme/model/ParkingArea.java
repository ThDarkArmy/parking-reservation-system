package tda.darkarmy.parkme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Integer numberOfSlots;
    private String imageUrl;
    private Double chargePerHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "parking_area_id")
    private List<ParkingSlot> parkingSlots= new ArrayList<>();

    public ParkingArea() {
    }

    public ParkingArea(Long id, String address, Integer numberOfSlots, String imageUrl) {
        this.id = id;
        this.address = address;
        this.numberOfSlots = numberOfSlots;
        this.imageUrl = imageUrl;
    }

    public ParkingArea(Long id, String address, Integer numberOfSlots, String imageUrl, Double chargePerHour) {
        this.id = id;
        this.address = address;
        this.numberOfSlots = numberOfSlots;
        this.imageUrl = imageUrl;
        this.chargePerHour = chargePerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getChargePerHour() {
        return chargePerHour;
    }

    public void setChargePerHour(Double chargePerHour) {
        this.chargePerHour = chargePerHour;
    }

    @Override
    public String toString() {
        return "ParkingArea{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", numberOfSlots=" + numberOfSlots +
                ", imageUrl='" + imageUrl + '\'' +
                ", chargePerHour=" + chargePerHour +
                '}';
    }
}

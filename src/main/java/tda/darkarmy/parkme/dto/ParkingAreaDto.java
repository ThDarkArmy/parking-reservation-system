package tda.darkarmy.parkme.dto;

import org.springframework.web.multipart.MultipartFile;

public class ParkingAreaDto {
    private String address;
    private Integer numberOfSlots;
    private Double chargePerHour;
    private MultipartFile image;

    public ParkingAreaDto() {
    }

    public ParkingAreaDto(String address, Integer numberOfSlots, Double chargePerHour, MultipartFile image) {
        this.address = address;
        this.numberOfSlots = numberOfSlots;
        this.chargePerHour = chargePerHour;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Double getChargePerHour() {
        return chargePerHour;
    }

    public void setChargePerHour(Double chargePerHour) {
        this.chargePerHour = chargePerHour;
    }

    @Override
    public String toString() {
        return "ParkingAreaDto{" +
                "address='" + address + '\'' +
                ", numberOfSlots=" + numberOfSlots +
                ", chargePerHour=" + chargePerHour +
                ", image=" + image +
                '}';
    }
}

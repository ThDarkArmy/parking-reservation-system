package tda.darkarmy.parkme.dto;

public class BookParkingSlotDto {
    private String vehicleNumber;
    private String vehicleName;
    private String arrivalDateTime;
    private String departureDateTime;
    private Long parkingSlotId;

    public BookParkingSlotDto() {
    }

    public BookParkingSlotDto(String vehicleNumber, String vehicleName, String arrivalDateTime, String departureDateTime, Long parkingSlotId) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.parkingSlotId = parkingSlotId;
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

    public Long getParkingSlotId() {
        return parkingSlotId;
    }

    public void setParkingSlotId(Long parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
    }

    @Override
    public String toString() {
        return "BookParkingSlotDto{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                ", departureDateTime='" + departureDateTime + '\'' +
                ", parkingSlotId=" + parkingSlotId +
                '}';
    }
}

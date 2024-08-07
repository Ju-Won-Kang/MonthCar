package vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserInfo implements Serializable {
    private String id;
    private String name;
    private String phoneNumber;
    private String licensePlateNumber; // 차량 번호
    private String vehicleType; // 차량종류
    private String vehicleModel; // 모델명
    private LocalDateTime voucherPurchaseDate; // 월주차권 구매 날짜
    private LocalDateTime voucherExpirationDate; // 월주차권 만료 날짜

    public UserInfo(String id, String name, String phoneNumber, String licensePlateNumber,
                    String vehicleType, String vehicleModel,LocalDateTime voucherPurchaseDate,LocalDateTime voucherExpirationDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.voucherPurchaseDate = voucherPurchaseDate;
        this.voucherExpirationDate = voucherExpirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public LocalDateTime getVoucherPurchaseDate() {
        return voucherPurchaseDate;
    }

    public void setVoucherPurchaseDate(LocalDateTime voucherPurchaseDate) {
        this.voucherPurchaseDate = voucherPurchaseDate;
    }

    public LocalDateTime getVoucherExpirationDate() {
        return voucherExpirationDate;
    }

    public void setVoucherExpirationDate(LocalDateTime voucherExpirationDate) {
        this.voucherExpirationDate = voucherExpirationDate;
    }
}

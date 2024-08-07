package vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VoucherInfo implements Serializable {
    private UserVehicle userVehicleInfo; // 구입한 사용자 자동차 정보
    private ParkingLot parkingLot; // 구매한 주차장 정보를 저장할 객체
    private LocalDateTime voucherPurchaseDate; // 월주차권 구매 날짜
    private LocalDateTime voucherExpirationDate; // 월주차권 만료 날짜

    public VoucherInfo(UserVehicle userVehicleInfo, ParkingLot parkingLot, LocalDateTime voucherPurchaseDate, LocalDateTime voucherExpirationDate) {
        this.userVehicleInfo = userVehicleInfo;
        if(parkingLot instanceof MechanicalParkingLot){
            this.parkingLot = (MechanicalParkingLot) parkingLot;
        }else if(parkingLot instanceof DriveInParkingLot){
            this.parkingLot = (DriveInParkingLot) parkingLot;
        }else this.parkingLot = null;
        this.voucherPurchaseDate = voucherPurchaseDate;
        this.voucherExpirationDate = voucherExpirationDate;
    }

    public UserVehicle getUserVehicleInfo() {
        return userVehicleInfo;
    }

    public void setUserVehicleInfo(UserVehicle userVehicleInfo) {
        this.userVehicleInfo = userVehicleInfo;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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

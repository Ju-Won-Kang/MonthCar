package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ParkingLot implements Serializable {
    private int parkingLotId; // 주차장 고유번호
    private String parkingLotName; // 주차장 이름
    private String parkingLotAddress; // 주차장 주소


    public ParkingLot() {
    }

    public ParkingLot(int parkingLotId, String parkingLotName, String parkingLotAddress) {
        this.parkingLotId = parkingLotId;
        this.parkingLotName = parkingLotName;
        this.parkingLotAddress = parkingLotAddress;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getParkingLotAddress() {
        return parkingLotAddress;
    }

    public void setParkingLotAddress(String parkingLotAddress) {
        this.parkingLotAddress = parkingLotAddress;
    }

    @Override
    public String toString() {
        return "주차장 ID : " + parkingLotId +
                ", 주차장 이름 : '" + parkingLotName + '\'' +
                ", 주차장 주소 : '" + parkingLotAddress + '\'';
    }
}

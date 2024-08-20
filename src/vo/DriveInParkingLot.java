package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class DriveInParkingLot extends ParkingLot implements Serializable {
    private int height; // 제한 높이
    private int totalParkingSpace; // 총 주차 공간
    private int registerCount; // 현재 등록 대수
    private int remainingParkingSpace; // 잔여 주차 공간
    private int driveInParkingIdentificationNumber; // 자주식 주차장 식별을 위한 고유 번호
    private ArrayList<UserInfo> parkingLotUserList; // 주차장 이용자 리스트

    public DriveInParkingLot() {
    }

    public DriveInParkingLot(int parkingLotId, String parkingLotName, String parkingLotAddress,
                             int height, int totalParkingSpace, int registerCount, int driveInParkingIdentificationNumber) {
        super(parkingLotId, parkingLotName, parkingLotAddress);
        this.height = height;
        this.totalParkingSpace = totalParkingSpace;
        this.registerCount = registerCount;
        this.remainingParkingSpace = totalParkingSpace - registerCount;
        this.driveInParkingIdentificationNumber = driveInParkingIdentificationNumber;
    }

    public int getDriveInParkingIdentificationNumber() {
        return driveInParkingIdentificationNumber;
    }

    public void setDriveInParkingIdentificationNumber(int driveInParkingIdentificationNumber) {
        this.driveInParkingIdentificationNumber = driveInParkingIdentificationNumber;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTotalParkingSpace() {
        return totalParkingSpace;
    }

    public void setTotalParkingSpace(int totalParkingSpace) {
        this.totalParkingSpace = totalParkingSpace;
    }

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }

    public int getRemainingParkingSpace() {
        return remainingParkingSpace;
    }

    public void setRemainingParkingSpace(int remainingParkingSpace) {
        this.remainingParkingSpace = remainingParkingSpace;
    }

    public ArrayList<UserInfo> getParkingLotUserList() {
        return parkingLotUserList;
    }

    public void setParkingLotUserList(ArrayList<UserInfo> parkingLotUserList) {
        this.parkingLotUserList = parkingLotUserList;
    }

    public void addParkingLotUserList(UserInfo user) {
        if (parkingLotUserList == null) {
            parkingLotUserList = new ArrayList<>();
        }
        parkingLotUserList.add(user);
    }

    @Override
    public String toString() {
        return "[자주식 주차장] | " + super.toString() + "\n\t| **제한 사항**\t" +
                "높이 : " + height + " 이하" +
                ", 전체 주차 공간 : " + totalParkingSpace + "대" +
                ", 현재 등록 대수 : " + registerCount + "대" +
                ", 잔여 주차 공간 : " + remainingParkingSpace + "대\n";
    }
}

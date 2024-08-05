package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class DriveInParkingLot extends ParkingLot implements Serializable {
    private int height; // 제한 높이
    private int totalParkingSpace; // 총 주차 공간
    private int registerCount; // 현재 등록 대수
    private int remainingParkingSpace; // 잔여 주차 공간
    private ArrayList<UserInfo> parkingLotUserList; // 주차장 이용자 리스트

    public DriveInParkingLot() {
    }

    public DriveInParkingLot(int parkingLotId,String parkingLotName, String parkingLotAddress,
                             int height, int totalParkingSpace, int registerCount) {
        super(parkingLotId, parkingLotName, parkingLotAddress);
        this.height = height;
        this.totalParkingSpace = totalParkingSpace;
        this.registerCount = registerCount;
        this.remainingParkingSpace = totalParkingSpace - registerCount;
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
    public void addParkingLotuserList(UserInfo user){
        if(parkingLotUserList == null){
            parkingLotUserList = new ArrayList<>();
        }
        parkingLotUserList.add(user);
    }

    @Override
    public String toString() {
        return super.toString() + "DriveInParkingLot{" +
                "height=" + height +
                ", totalParkingSpace=" + totalParkingSpace +
                ", registerCount=" + registerCount +
                ", remainingParkingSpace=" + remainingParkingSpace +
                '}';
    }
}

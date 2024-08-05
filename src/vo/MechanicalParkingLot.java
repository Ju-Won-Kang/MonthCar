package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class MechanicalParkingLot extends ParkingLot implements Serializable {
    private int width; // 제한 너비
    private int height; // 제한 높이
    private int length; // 제한 길이
    private int weight; // 제한 무게
    private int totalParkingSpace; // 총 주차 공간
    private int registerCount; // 현재 등록 대수
    private int remainingParkingSpace; // 잔여 주차 공간
    private int parkingTicketIdentifier; // 주차권 식별을 위한 고유 번호
    private ArrayList<UserInfo> parkingLotUserList; // 주차장 이용자 리스트

    public MechanicalParkingLot() {
    }

    public MechanicalParkingLot(int width, int height, int length, int weight, int totalParkingSpace) {

        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.totalParkingSpace = totalParkingSpace;
        this.registerCount = registerCount;
        this.remainingParkingSpace = totalParkingSpace - registerCount;
    }

    public MechanicalParkingLot(int parkingLotId, String parkingLotName, String parkingLotAddress,
                                int width, int height, int length, int weight, int totalParkingSpace, int registerCount) {
        super(parkingLotId, parkingLotName, parkingLotAddress);
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.totalParkingSpace = totalParkingSpace;
        this.registerCount = registerCount;
        this.remainingParkingSpace = totalParkingSpace - registerCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public int getParkingTicketIdentifier() {
        return parkingTicketIdentifier;
    }

    public void setParkingTicketIdentifier(int parkingTicketIdentifier) {
        this.parkingTicketIdentifier = parkingTicketIdentifier;
    }

    public ArrayList<UserInfo> getParkingLotUserList() {
        return parkingLotUserList;
    }

    public void setParkingLotUserList(ArrayList<UserInfo> parkingLotUserList) {
        this.parkingLotUserList = parkingLotUserList;
    }
    public void addParkingLotuserList(UserInfo user) {
        if(parkingLotUserList == null) {
            parkingLotUserList = new ArrayList<>();
        }
        parkingLotUserList.add(user);
    }

    @Override
    public String toString() {
        return super.toString() + "MechanicalParkingLot{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", weight=" + weight +
                ", totalParkingSpace=" + totalParkingSpace +
                ", registerCount=" + registerCount +
                ", remainingParkingSpace=" + remainingParkingSpace +
                '}';
    }
}

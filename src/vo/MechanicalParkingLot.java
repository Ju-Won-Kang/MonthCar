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
    private int mechanicalParkingIdentificationNumber; // 기계식 주차장 식별을 위한 고유 번호
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

    public int getMechanicalParkingIdentificationNumber() {
        return mechanicalParkingIdentificationNumber;
    }

    public void setMechanicalParkingIdentificationNumber(int mechanicalParkingIdentificationNumber) {
        this.mechanicalParkingIdentificationNumber = mechanicalParkingIdentificationNumber;
    }

    public MechanicalParkingLot(int parkingLotId, String parkingLotName, String parkingLotAddress,
                                int width, int height, int length, int weight, int totalParkingSpace, int registerCount, int mechanicalParkingIdentificationNumber) {
        super(parkingLotId, parkingLotName, parkingLotAddress);
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.totalParkingSpace = totalParkingSpace;
        this.registerCount = registerCount;
        this.remainingParkingSpace = totalParkingSpace - registerCount;
        this.mechanicalParkingIdentificationNumber = mechanicalParkingIdentificationNumber;
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


    public ArrayList<UserInfo> getParkingLotUserList() {
        return parkingLotUserList;
    }

    public void setParkingLotUserList(ArrayList<UserInfo> parkingLotUserList) {
        this.parkingLotUserList = parkingLotUserList;
    }

    public void addParkingLotuserList(UserInfo user) {
        if (parkingLotUserList == null) {
            parkingLotUserList = new ArrayList<>();
        }
        parkingLotUserList.add(user);
    }

    @Override
    public String toString() {
        return "[기계식 주차장] | " + super.toString() + "\n\t| **제한 사항**\t" +
                "폭 : " + width + " 이하" +
                ", 높이 : " + height + "(mm) 이하" +
                ", 전장 : " + length + "(mm) 이하" +
                ", 무게 : " + weight + "(kg) 이하" +
                ", 전체 주차 공간 : " + totalParkingSpace +
                ", 현재 등록 대수 : " + registerCount + "대" +
                ", 잔여 주차 공간 : " + remainingParkingSpace + "대\n";
    }
}

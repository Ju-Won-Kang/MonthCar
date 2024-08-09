package vo;

import java.io.Serializable;

public class UserVehicle implements Serializable {
    private String licensePlateNumber; // 차량 번호
    private String vehicleType; // 차량종류
    private String vehicleModel; // 모델명
    private int carWeight; // 차량 무게
    private int vehicleLength; // 차량 전장
    private int vehicleSpan; // 차량 전폭
    private int vehicleHeight; // 차량 높이

    public UserVehicle() {
    }

    public UserVehicle(String licensePlateNumber, String vehicleType, String vehicleModel, int carWeight, int vehicleLength, int vehicleSpan, int vehicleHeight) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.carWeight = carWeight;
        this.vehicleLength = vehicleLength;
        this.vehicleSpan = vehicleSpan;
        this.vehicleHeight = vehicleHeight;
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



    public int getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(int carWeight) {
        this.carWeight = carWeight;
    }

    public int getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(int vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public int getVehicleSpan() {
        return vehicleSpan;
    }

    public void setVehicleSpan(int vehicleSpan) {
        this.vehicleSpan = vehicleSpan;
    }

    public int getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(int vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    @Override
    public String toString() {
        return "차량 번호 : '" + licensePlateNumber + '\'' +
                ", 차량 종류 : '" + vehicleType + '\'' +
                ", 모델명 : '" + vehicleModel + '\'' +
                ", 차량 무게 : " + carWeight + " (kg)"+
                ", 차량 전장 : " + vehicleLength + " (mm)" +
                ", 차량 전폭 : " + vehicleSpan + " (mm)" +
                ", 차량 높이 : " + vehicleHeight + " (mm)"
                ;
    }
}

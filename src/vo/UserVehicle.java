package vo;

import java.io.Serializable;

public class UserVehicle implements Serializable {
    private String plateNumber; // 차량 번호
    private String vehicleType; // 차량종류
    private String vehicleModel; // 모델명
    private int vehicleWeight; // 차량 무게
    private int vehicleLength; // 차량 전장
    private int vehicleWidth; // 차량 전폭
    private int vehicleHeight; // 차량 높이

    public UserVehicle() {
    }

    public UserVehicle(String plateNumber, String vehicleType, String vehicleModel, int vehicleWeight, int vehicleLength, int vehicleWidth, int vehicleHeight) {
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.vehicleWeight = vehicleWeight;
        this.vehicleLength = vehicleLength;
        this.vehicleWidth = vehicleWidth;
        this.vehicleHeight = vehicleHeight;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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



    public int getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(int vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public int getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(int vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public int getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(int vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public int getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(int vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    @Override
    public String toString() {
        return "차량 번호 : '" + plateNumber + '\'' +
                ", 차량 종류 : '" + vehicleType + '\'' +
                ", 모델명 : '" + vehicleModel + '\'' +
                ", 차량 무게 : " + vehicleWeight + " (kg)"+
                ", 차량 전장 : " + vehicleLength + " (mm)" +
                ", 차량 전폭 : " + vehicleWidth + " (mm)" +
                ", 차량 높이 : " + vehicleHeight + " (mm)"
                ;
    }
}

package vo;

import java.io.Serializable;
import java.util.LinkedList;

public class User extends UserMain implements Serializable {
    private int userType;
    private LinkedList<UserVehicle> vehicleList = new LinkedList<>(); // 소유 자동차 리스트

    public User(String id, String pw, String name, String phoneNumber, UserVehicle userVehicle) {
        super(id, pw, name, phoneNumber);
        this.userType = 1;
        vehicleList.add(userVehicle);
    }

    public User(String id, String pw, String name, String phoneNumber) {
        super(id, pw, name, phoneNumber);
    }

    public User(String id, String pw, String name, String phoneNumber, LinkedList<UserVehicle> vehicleList) {
        super(id, pw, name, phoneNumber);
        this.userType = 1;
        this.vehicleList = vehicleList;
    }

    public User(LinkedList<UserVehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public LinkedList<UserVehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(LinkedList<UserVehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    public void addVehicle(UserVehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    @Override
    public String toString() {
        return super.toString() + "User{" +
                "vehicleList=" + vehicleList +
                '}';
    }
}

package vo;

import java.util.ArrayList;

public class Admin extends UserMain{
//    private ArrayList<ParkingLot> parkingLot = new ArrayList<>(); // 기계식 주차장, 자주식 주차장 배열
    private int userType;
    private ArrayList<ParkingLot> parkingLot = new ArrayList<>();

    public Admin() {
    }

    public Admin(String id, String pw, String name, String phoneNumber, DriveInParkingLot parkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLot.add(parkingLot);
    }
    public Admin(String id, String pw, String name, String phoneNumber, MechanicalParkingLot parkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLot.add(parkingLot);
    }

    public Admin(String id, String pw, String name, String phoneNumber, DriveInParkingLot driveInparkingLot, MechanicalParkingLot MechanicalParkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLot.add(driveInparkingLot);
        this.parkingLot.add(MechanicalParkingLot);
    }

    public ArrayList<ParkingLot> getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ArrayList<ParkingLot> parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public String toString() {
        return super.toString() + "Admin{" +
                "parkingLot=" + parkingLot +
                '}';
    }
}

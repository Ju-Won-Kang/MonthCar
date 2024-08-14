package vo;

import java.util.ArrayList;

public class Admin extends UserMain{
//    private ArrayList<ParkingLot> parkingLot = new ArrayList<>(); // 기계식 주차장, 자주식 주차장 배열
    private int userType;
    private ArrayList<ParkingLot> parkingLotList = new ArrayList<>();

    public Admin() {
    }

    public Admin(String id, String pw, String name, String phoneNumber, DriveInParkingLot parkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLotList.add(parkingLot);
    }
    public Admin(String id, String pw, String name, String phoneNumber, MechanicalParkingLot parkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLotList.add(parkingLot);
    }

    public Admin(String id, String pw, String name, String phoneNumber, DriveInParkingLot driveInparkingLot, MechanicalParkingLot MechanicalParkingLot) {
        super(id, pw, name, phoneNumber);
        this.userType = 0;
        this.parkingLotList.add(driveInparkingLot);
        this.parkingLotList.add(MechanicalParkingLot);
    }

    public ArrayList<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(ArrayList<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }
    public boolean addParkingLot(ParkingLot parkingLot) {
        // 동일한 객체가 있는지 확인
        if(!parkingLotList.contains(parkingLot)) {
            if(parkingLot instanceof MechanicalParkingLot) {
                MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
                parkingLotList.add(m);
                return true;
            }else if(parkingLot instanceof DriveInParkingLot) {
                DriveInParkingLot d =  (DriveInParkingLot) parkingLot;
                parkingLotList.add(d);
                return true;
            }
        }
        return false;
    }
    public boolean editParkingLotList(int selectIndex, ParkingLot parkingLot){
        this.parkingLotList.set(selectIndex, parkingLot);
        return true;
    }
    @Override
    public String toString() {
        return super.toString() + "Admin{" +
                "parkingLot=" + parkingLotList +
                '}';
    }
}

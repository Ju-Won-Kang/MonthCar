package vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class User extends UserMain implements Serializable {
    private int userType;
    private LinkedList<UserVehicle> vehicleList = new LinkedList<>(); // 소유 자동차 리스트
    private ArrayList<VoucherInfo> voucherList = new ArrayList<>(); // 구매한 월주차권 정보 리스트

    public User(){

    }
    public User(String id, String pw, String name, String phoneNumber, UserVehicle userVehicle) {
        super(id, pw, name, phoneNumber);
        this.userType = 1;
        vehicleList.add(userVehicle);
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

    public ArrayList<VoucherInfo> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(ArrayList<VoucherInfo> voucherList) {
        voucherList = voucherList;
    }
    /*
    * 사용자가 바우처 구입시 해당 정보 추가
    * UserVehicle, ParkingLot 객체를 받아오고 Date 객체를 이용해서 해당 날짜를 "구입 날짜", 1달 뒤를 "만료 날짜"로
    * 바우처 리스트에 추가한다.
    * */
    public void addVoucherList(UserVehicle userVehicle, ParkingLot parkingLot){
        this.voucherList.add(new VoucherInfo(userVehicle,parkingLot, LocalDateTime.now(), LocalDateTime.now().plusMonths(1)));
    }

    @Override
    public String toString() {
        return super.toString() + "User{" +
                "vehicleList=" + vehicleList +
                '}';
    }
}

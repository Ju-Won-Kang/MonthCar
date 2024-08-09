package controller;

import vo.*;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class MonthCarController {
    private TotalParkingLot tp = new TotalParkingLot();
    private UserMain user;

    public int signInUser(String id, String pw) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", id)))) {
            user = (UserMain) ois.readObject();

            if (((User) user).getId().equals(id)) {
                if (((User) user).getPw().equals(pw)) {
                    return 1;
                }
                return 2;
            }

        } catch (FileNotFoundException e) {
            return 3;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public int signInAdmin(String id, String pw) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", id)))) {
            user = (UserMain) ois.readObject();
            Admin AdminUser = (Admin) this.user;
            if (((Admin) user).getId().equals(id)) {
                if (((Admin) user).getPw().equals(pw)) {
                    ArrayList<ParkingLot> parkingLotList = tp.getTotalParkingLotList();
                    ArrayList<ParkingLot> adminParkingLotList = AdminUser.getParkingLot();
                    MechanicalParkingLot adminMParkingLot = null;
                    DriveInParkingLot adminDParkingLot = null;
                    int adminMParkingLotNum = 0, adminDParkingLotNum = 0;
                    for (int i = 0; i < adminParkingLotList.size(); i++) {
                        if (adminParkingLotList.get(i) instanceof MechanicalParkingLot) {
                            adminMParkingLot = (MechanicalParkingLot) adminParkingLotList.get(i);
                            adminMParkingLotNum = i;
                        } else if (adminParkingLotList.get(i) instanceof DriveInParkingLot) {
                            adminDParkingLot = (DriveInParkingLot) adminParkingLotList.get(i);
                            adminDParkingLotNum = i;
                        }
                    }
                    // totalparkingLotList 와 Admin 객체의 ParkingLot 필드 동기화
                    for (ParkingLot parkingLot : parkingLotList) {
                        if (parkingLot instanceof MechanicalParkingLot) {
                            MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;

                            if (m.getParkingLotId() == adminMParkingLot.getParkingLotId()) {
                                adminParkingLotList.set(adminMParkingLotNum, m);
                            }
                        } else if (parkingLot instanceof DriveInParkingLot) {
                            DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                            if (d.getParkingLotId() == adminDParkingLot.getParkingLotId()) {
                                adminParkingLotList.set(adminDParkingLotNum, d);
                            }
                        }
                    }
                    return 1;
                }
                return 2;
            }
        } catch (FileNotFoundException e) {
            return 3;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    /*
     * User의 [ID].txt 파일을 최신화
     * 로그아웃시 실행하게하여 자동으로  최신화 되게 해줌
     * */
    public void logoutUser() {
        User userInfo = (User) this.user;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", userInfo.getId())))) {
            oos.writeObject(userInfo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Admin의 [ID].txt 파일을 최신화
     * 로그아웃시 실행하게하여 자동으로  최신화 되게 해줌
     * */
    public void logoutAdmin() {
        Admin adminInfo = (Admin) this.user;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", adminInfo.getId())))) {
            oos.writeObject(adminInfo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUpUser(User user) {
        this.user = user;
        saveUser(user.getId());
        System.out.println(user.getName() + "님 회원 가입이 되었습니다.");
    }

    public void signUpAdmin(Admin admin) {
        this.user = admin;
        if (saveUser(admin.getId())) {
            tp.updateTotalParkingLot(admin.getParkingLot());
            System.out.println(admin.getName() + "님 회원 가입이 되었습니다.");
        }

    }

    public boolean saveUser(String id) {
        File userDirectory = new File("/Users/jun/Documents/KH/MonthCar/User");
        if (!userDirectory.exists()) {
            if (!userDirectory.mkdirs()) {
                System.err.println("유저 디렉터리 생성 실패 : " + userDirectory.getAbsolutePath());
                return false;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", id)))) {
            oos.writeObject(this.user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadUser(String id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", id)));) {
            if (ois.readObject() instanceof Admin) {
                this.user = (Admin) ois.readObject();
            } else if (ois.readObject() instanceof User) {
                this.user = (User) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userUpdate() {
        if (this.user instanceof User) {
            User userInfo = (User) this.user;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", userInfo.getId())))) {
                oos.writeObject(userInfo);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else if (this.user instanceof Admin) {
            Admin adminInfo = (Admin) this.user;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", adminInfo.getId())))) {
                oos.writeObject(adminInfo);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
//    public boolean userUpdate(UserVehicle vehicle) {
//        if (this.user instanceof User) {
//            ((User) this.user).addVehicle(vehicle);
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())))) {
//                oos.writeObject(this.user);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        return true;
//    }

    public TotalParkingLot getTp() {
        return tp;
    }

    public User getUser() {
        return (User) this.user;
    }

    public Admin getAdmin() {
        return (Admin) this.user;
    }

    public void updateTotalParkingLot() {
        this.tp.updateTotalParkingLot();
    }

    /*
     * User 정보 최신화 지금 로그인한 user 객체에 VoucherInfoList에 해당 정보 추가
     * */
    public void updateVoucherInfo(UserVehicle userVehicle, ParkingLot parkingLot) {
        User user = (User) this.user;
        user.addVoucherList(userVehicle, parkingLot);
        this.user = user;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())));) {
            oos.writeObject(this.user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * 월주차권 만기 날짜가 현재 날짜 이전이면 해당 월주차권을 사용자의 voucherList를 삭제하고,
     * totalParkingLotList의 잔여 주차공간 ++ 하고, 사용중인 주차공간 -- 한 뒤 해당 객체를 저장한다.
     * */
    public void checkVoucherExpirationDate() {
        User user = (User) this.user;
        ArrayList<VoucherInfo> voucherList = user.getVoucherList();
        for (int i = 0; i < voucherList.size(); i++) {
            if (voucherList.get(i).getVoucherExpirationDate().isBefore(LocalDateTime.now())) {
                ArrayList<ParkingLot> totalParkingLotList = this.tp.getTotalParkingLotList();
                for (int j = 0; j < totalParkingLotList.size(); j++) {
                    // 전체 주차장 리스트에서 사용자가 가지고 있는 월주차권의 정보중 하나인 주차장 id 로 같은게 있는지 확인 후
                    // 해당 잔여 주차공간은 +1, 사용중인 추자공간 -1
                    if (totalParkingLotList.get(j).getParkingLotId() == voucherList.get(i).getParkingLot().getParkingLotId()) {
                        if (totalParkingLotList.get(j) instanceof MechanicalParkingLot) {
                            MechanicalParkingLot m = (MechanicalParkingLot) totalParkingLotList.get(j);
                            m.setRemainingParkingSpace(m.getRemainingParkingSpace() + 1);
                            m.setRegisterCount(m.getRegisterCount() - 1);
                        } else if (totalParkingLotList.get(j) instanceof DriveInParkingLot) {
                            DriveInParkingLot d = (DriveInParkingLot) totalParkingLotList.get(j);
                            d.setRemainingParkingSpace(d.getRemainingParkingSpace() + 1);
                            d.setRegisterCount(d.getRegisterCount() - 1);
                        }
                    }
                }
                voucherList.remove(i); // 만료된 월주차권 user 객체에서 삭제
                this.user = user; // user 객체 최신화
                this.tp.setTotalParkingLotList(totalParkingLotList);
            }
        }
        // User 객체 파일 최신화
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())))) {
            oos.writeObject(this.user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // totalParkingLotList 객체 파일 최신화
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/jun/Documents/KH/MonthCar/ParkingLot/totalParkingLot.txt"))) {
            oos.writeObject(this.tp.getTotalParkingLotList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPW(String pw) {
        if (this.user.getPw().equals(pw)) {
            return true;
        }
        return false;
    }

    public boolean changePW(String newPW) {
        this.user.setPw(newPW);
        return this.userUpdate();
    }

    public boolean changePhoneNumber(String phoneNumber) {
        this.user.setPhoneNumber(phoneNumber);
        return this.userUpdate();
    }

    public int addVehicle(String licensePlateNumber, String vehicleType, String vehicleModel, int carWeight, int vehicleLength, int vehicleSpan, int vehicleHeight) {
        User user = (User) this.user;
        LinkedList<UserVehicle> userVehicleLinkedList = user.getVehicleList();
        for (UserVehicle userVehicle : userVehicleLinkedList) {
            if (userVehicle.getLicensePlateNumber().equals(licensePlateNumber)) {
                return -1; // 이미 등록된 차량 번호
            }
        }
        user.addVehicle(new UserVehicle(licensePlateNumber, vehicleType, vehicleModel, carWeight, vehicleLength, vehicleSpan, vehicleHeight));
        this.user = user;
        if (this.userUpdate()) {
            return 1;
        } else return 0;
    }
    public boolean removeVehicle(String licensePlateNumber) {
        User user = (User) this.user;
        LinkedList<UserVehicle> userVehicleLinkedList = user.getVehicleList();
        for (UserVehicle userVehicle : userVehicleLinkedList) {
            if (userVehicle.getLicensePlateNumber().equals(licensePlateNumber)) {
                userVehicleLinkedList.remove(userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            }
        }
        return false;
    }
    public boolean modifyVehicle(int selectVehicleIndex,int select, Object modifyObject){
        LinkedList<UserVehicle> userVehicleLinkedList = ((User) this.user).getVehicleList();
        UserVehicle userVehicle = userVehicleLinkedList.get(selectVehicleIndex);
        switch (select){
            case 1:
                userVehicle.setLicensePlateNumber((String)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 2:
                userVehicle.setVehicleType((String)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 3:
                userVehicle.setVehicleModel((String)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 4:
                userVehicle.setCarWeight((int)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 5:
                userVehicle.setVehicleLength((int)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 6:
                userVehicle.setVehicleSpan((int)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 7:
                userVehicle.setVehicleHeight((int)modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            default:
                return false;
        }
    }
}

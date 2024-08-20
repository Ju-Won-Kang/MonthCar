package controller;

import vo.*;

import java.io.*;
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
            this.user = (UserMain) ois.readObject();
            Admin AdminUser = (Admin) this.user;
            if (((Admin) user).getId().equals(id)) {
                if (((Admin) user).getPw().equals(pw)) {
                    // 로그인시 입력한 ID,PW가 동일 하다면 totalParkingLotList를 최신화 해준다.
                    ArrayList<ParkingLot> parkingLotList = tp.getTotalParkingLotList();
                    ArrayList<ParkingLot> adminParkingLotList = AdminUser.getParkingLotList();
                    MechanicalParkingLot adminMParkingLot = null;
                    DriveInParkingLot adminDParkingLot = null;
                    int adminMParkingLotCount = 0, adminDParkingLotNum = 0;
                    // Admin 객체가 가지고 있는 주차장 종류별 개수 카운트
                    for (int i = 0; i < adminParkingLotList.size(); i++) {
                        if (adminParkingLotList.get(i) instanceof MechanicalParkingLot) {
                            adminMParkingLot = (MechanicalParkingLot) adminParkingLotList.get(i);
                            adminMParkingLotCount = i;
                        } else if (adminParkingLotList.get(i) instanceof DriveInParkingLot) {
                            adminDParkingLot = (DriveInParkingLot) adminParkingLotList.get(i);
                            adminDParkingLotNum = i;
                        }
                    }
                    // totalparkingLotList 와 Admin 객체의 ParkingLot 필드 동기화
                    for (ParkingLot parkingLot : parkingLotList) {
                        if (parkingLot instanceof MechanicalParkingLot) {
                            if (adminMParkingLot != null) {
                                MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
                                // ParkingLotID를 이용해 Admin 사용자가 관리하는 주차장인지 확인
                                if (m.getParkingLotId() == adminMParkingLot.getParkingLotId()) {
                                    adminParkingLotList.set(adminMParkingLotCount, m);
                                }
                            }
                        } else if (parkingLot instanceof DriveInParkingLot) {
                            if (adminDParkingLot != null) {
                                DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                                if (d.getParkingLotId() == adminDParkingLot.getParkingLotId()) {
                                    adminParkingLotList.set(adminDParkingLotNum, d);
                                }
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

    public String signUpUser(User user) {
        this.user = user;
        if(saveUser()){
            this.updateUserFile();
            return user.getName() + "님 회원 가입이 완료 되었습니다.";
        }else {
            return "회원 가입에 실패했습니다.";
        }
    }

    public String signUpAdmin(Admin admin) {
        this.user = admin;
        if (saveUser()) {
            tp.addTotalParkingLotList(admin.getParkingLotList());
            this.updateUserFile();
            return user.getName() + "님 회원 가입이 완료 되었습니다.";
        }else {
            return "회원 가입에 실패했습니다.";
        }
    }

    /*
     * User 및 Admin의 [ID].txt 파일을 최신화
     * 파일 정보 최신화
     * */
    public void updateUserFile() {
        if (this.user instanceof Admin) {
            Admin adminInfo = (Admin) this.user;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", adminInfo.getId())))) {
                oos.writeObject(adminInfo);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (this.user instanceof User) {
            User userInfo = (User) this.user;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", userInfo.getId())))) {
                oos.writeObject(userInfo);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean saveUser() {
        File userDirectory = new File("/Users/jun/Documents/KH/MonthCar/User");
        if (!userDirectory.exists()) {
            if (!userDirectory.mkdirs()) {
                System.err.println("유저 디렉터리 생성 실패 : " + userDirectory.getAbsolutePath());
                return false;
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())))) {
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
            if (userVehicle.getPlateNumber().equals(licensePlateNumber)) {
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
            if (userVehicle.getPlateNumber().equals(licensePlateNumber)) {
                userVehicleLinkedList.remove(userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            }
        }
        return false;
    }

    public boolean modifyVehicle(int selectVehicleIndex, int select, Object modifyObject) {
        LinkedList<UserVehicle> userVehicleLinkedList = ((User) this.user).getVehicleList();
        UserVehicle userVehicle = userVehicleLinkedList.get(selectVehicleIndex);
        switch (select) {
            case 1:
                userVehicle.setPlateNumber((String) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 2:
                userVehicle.setVehicleType((String) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 3:
                userVehicle.setVehicleModel((String) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 4:
                userVehicle.setVehicleWeight((int) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 5:
                userVehicle.setVehicleLength((int) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 6:
                userVehicle.setVehicleWidth((int) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            case 7:
                userVehicle.setVehicleHeight((int) modifyObject);
                userVehicleLinkedList.set(selectVehicleIndex, userVehicle);
                ((User) this.user).setVehicleList(userVehicleLinkedList);
                return this.userUpdate();
            default:
                return false;
        }
    }

    public boolean addParkingLot(Object parkingLot) {
        Admin admin = (Admin) this.user;
        if (parkingLot instanceof MechanicalParkingLot) {
            MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
            if (admin.addParkingLot(m)) {
                return this.userUpdate();
            }
        } else if (parkingLot instanceof DriveInParkingLot) {
            DriveInParkingLot d = (DriveInParkingLot) parkingLot;
            if (admin.addParkingLot(d)) {
                return this.userUpdate();
            }
        }
        return false;
    }

    public boolean editParkingLotInfo(int selectIndex, int selectItem, Object modifyObject) {
        Admin admin = (Admin) this.user;
        ArrayList<ParkingLot> parkingLotList = admin.getParkingLotList();
        int modifyValue = ((Integer) modifyObject).intValue();
        if (parkingLotList.get(selectIndex) instanceof MechanicalParkingLot) {
            MechanicalParkingLot m = (MechanicalParkingLot) parkingLotList.get(selectIndex);
            switch (selectItem) {
                case 1:
                    m.setWidth(modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
                case 2:
                    m.setHeight(modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
                case 3:
                    m.setLength(modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
                case 4:
                    m.setWeight(modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
                case 5:
                    m.setTotalParkingSpace(modifyValue);
                    m.setRemainingParkingSpace(modifyValue - m.getRegisterCount());
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
                case 6:
                    m.setRegisterCount(modifyValue);
                    m.setRemainingParkingSpace(m.getTotalParkingSpace() - modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, m);
                    this.tp.editTotalParkingLot(m.getMechanicalParkingIdentificationNumber(), m);
                    return this.userUpdate();
            }
        } else if (parkingLotList.get(selectIndex) instanceof DriveInParkingLot) {
            DriveInParkingLot d = (DriveInParkingLot) parkingLotList.get(selectIndex);
            switch (selectItem) {
                case 1:
                    d.setHeight(modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, d);
                    this.tp.editTotalParkingLot(d.getDriveInParkingIdentificationNumber(), d);
                    return this.userUpdate();
                case 2:
                    d.setTotalParkingSpace(modifyValue);
                    d.setRemainingParkingSpace(modifyValue - d.getRegisterCount());
                    ((Admin) this.user).editParkingLotList(selectIndex, d);
                    this.tp.editTotalParkingLot(d.getDriveInParkingIdentificationNumber(), d);
                    return this.userUpdate();
                case 3:
                    d.setRegisterCount(modifyValue);
                    d.setRemainingParkingSpace(d.getTotalParkingSpace() - modifyValue);
                    ((Admin) this.user).editParkingLotList(selectIndex, d);
                    this.tp.editTotalParkingLot(d.getDriveInParkingIdentificationNumber(), d);
                    return this.userUpdate();
            }
        }
        return false;
    }

    public boolean removeParkingLot(int selectParkingLotIndex) {
        Admin admin = (Admin) this.user;
        ArrayList<ParkingLot> parkingLotList = admin.getParkingLotList();
        ParkingLot oldParkingLot = parkingLotList.remove(selectParkingLotIndex);
        if (oldParkingLot instanceof MechanicalParkingLot) {
            tp.removeParkingLot(((MechanicalParkingLot) oldParkingLot).getMechanicalParkingIdentificationNumber(),
                    (MechanicalParkingLot) oldParkingLot);
        } else if (oldParkingLot instanceof DriveInParkingLot) {
            tp.removeParkingLot(((DriveInParkingLot) oldParkingLot).getDriveInParkingIdentificationNumber(),
                    (DriveInParkingLot) oldParkingLot);
        }
        return this.userUpdate();
    }

    // 사용자가 월주차권 구매시 선택한 주차장 종류와 선택한 주차장이 일치하는지 확인
    public boolean checkSelectedParkingLotType(int parkingLotType, int choice) {
        ArrayList<ParkingLot> parkingLotList = this.getTp().getTotalParkingLotList();
        switch (parkingLotType) {
            case 1: // 기계식 주차장
                if (parkingLotList.get(choice) instanceof MechanicalParkingLot) {
                    return true;
                } else return false;
            case 2: // 자주식 주차장
                if (parkingLotList.get(choice) instanceof DriveInParkingLot) {
                    return true;
                } else return false;
            case 3: // 기계식 & 자주식 주차장
                return true;
            default:
                return false;
        }
    }

}

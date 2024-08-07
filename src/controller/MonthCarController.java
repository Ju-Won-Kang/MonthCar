package controller;

import vo.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MonthCarController {
    private TotalParkingLot tp = new TotalParkingLot();
    private UserMain user;

    public int signInUser(String id, String pw) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", id)))) {
            user = (UserMain) ois.readObject();
            if (this.user instanceof User) {
                if (((User) user).getId().equals(id)) {
                    if (((User) user).getPw().equals(pw)) {
                        return 1;
                    }
                    return 2;
                }
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
            if (this.user instanceof User) {
                return 3;
//                if(((User)user).getId().equals(id)){
//                    if(((User)user).getPw().equals(pw)){
//                        return 1;
//                    }
//                    return 2;
//                }
            } else if (this.user instanceof Admin) {
                Admin AdminUser = (Admin) this.user;
                if (((Admin) user).getId().equals(id)) {
                    if (((Admin) user).getPw().equals(pw)) {
                        ArrayList<ParkingLot> parkingLotList = tp.getTotalParkingLotList();
                        ArrayList<ParkingLot> adminParkingLotList = AdminUser.getParkingLot();
                        MechanicalParkingLot adminMParkingLot = null;
                        DriveInParkingLot adminDParkingLot = null;
                        int adminMParkingLotNum = 0,adminDParkingLotNum =0;
                            for (int i = 0; i < adminParkingLotList.size() ; i++) {
                                if(adminParkingLotList.get(i) instanceof MechanicalParkingLot) {
                                    adminMParkingLot = (MechanicalParkingLot) adminParkingLotList.get(i);
                                    adminMParkingLotNum = i;
                                }else if(adminParkingLotList.get(i) instanceof DriveInParkingLot) {
                                    adminDParkingLot = (DriveInParkingLot) adminParkingLotList.get(i);
                                    adminDParkingLotNum = i;
                                }
                            }
//                        for (ParkingLot parkingLot : adminParkingLotList) {
//                            if(parkingLot instanceof MechanicalParkingLot){
//                                adminMParkingLot = (MechanicalParkingLot) parkingLot;
//                            }else if(parkingLot instanceof DriveInParkingLot){
//                                adminDParkingLot = (DriveInParkingLot) parkingLot;
//                            }
//                        }
                        // totalparkingLotList 와 Admin 객체의 ParkingLot 필드 동기화
                        for (ParkingLot parkingLot : parkingLotList) {
                            if (parkingLot instanceof MechanicalParkingLot) {
                                MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;

                                if(m.getParkingLotId() == adminMParkingLot.getParkingLotId()){
                                    adminParkingLotList.set(adminMParkingLotNum, m);
                                }
                            }else if(parkingLot instanceof DriveInParkingLot) {
                                DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                                if(d.getParkingLotId() == adminDParkingLot.getParkingLotId()){
                                    adminParkingLotList.set(adminDParkingLotNum, d);
                                }
                            }
                        }
                        return 1;
                    }
                    return 2;
                }
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
    public void logoutUser(){
        /*
        * User의 [ID].txt 파일을 최신화
        * 로그아웃시 실행하게하여 자동으로  최신화 되게 해줌
        * */
        User userInfo = (User) this.user;
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", userInfo.getId())))) {
            oos.writeObject(userInfo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void logoutAdmin(){
        /*
         * Admin의 [ID].txt 파일을 최신화
         * 로그아웃시 실행하게하여 자동으로  최신화 되게 해줌
         * */
        Admin adminInfo = (Admin) this.user;
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", adminInfo.getId())))) {
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

    public boolean userUpdate(UserVehicle vehicle) {
        if (this.user instanceof User) {
            ((User) this.user).addVehicle(vehicle);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())))) {
                oos.writeObject(this.user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return true;
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
    public void updateVoucherInfo(UserVehicle userVehicle, ParkingLot parkingLot ){
        User user = (User) this.user;
        user.addVoucherList(userVehicle,parkingLot);
        this.user = user;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format("/Users/jun/Documents/KH/MonthCar/User/%s.txt", this.user.getId())));){
            oos.writeObject(this.user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

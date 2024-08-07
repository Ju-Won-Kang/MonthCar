package view;

import controller.MonthCarController;
import vo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class MonthCarMenu {
    Scanner sc = new Scanner(System.in);
    private MonthCarController mc;

    public MonthCarMenu() {
        this.mc = new MonthCarController();
    }

    public void mainMenu() {
        int select;
        while (true) {
            System.out.println("=== 월차 서비스 ===");
            System.out.println("어떤 메뉴를 선택하시겠습니까?");
            System.out.print("[1] 사용자, [2] 관리자, [9] 프로그램 종료 : ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                // 사용자 선택
                case 1:
                    while (true) {
                        System.out.println("로그인/회원가입 메뉴");
                        System.out.print("[1] 로그인, [2]회원가입 : ");
                        int choice = sc.nextInt();
                        sc.nextLine();
                        switch (choice) {
                            case 1:
                                if (!userSignInMenu()) {
                                    break;
                                }
                                userMenu();
                                return;
                            case 2:
                                userSignUp();
                                userMenu();
                                return;
                            default:
                                System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요");
                        }
                    }
                    // 관리자 선택
                case 2:
                    while (true) {
                        System.out.println("로그인/회원가입 메뉴");
                        System.out.print("[1] 로그인, [2]회원가입 : ");
                        int choice = sc.nextInt();
                        sc.nextLine();
                        switch (choice) {
                            case 1:
                                if (!adminSignInMenu()) {
                                    break;
                                }
                                adminMenu();
                                return;
                            case 2:
                                adminSignUp();
                                adminMenu();
                                return;
                            default:
                                System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요");
                                return;
                        }
//                        return;
                    }
                    // 프로그램 종료
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }
        }
    }

    // 사용자 메뉴
    public void userMenu() {
        int select;
        while (true) {
            System.out.println("월차 사용자 서비스 메뉴");
            System.out.println("1. 월주차 이용권 구매");
            System.out.println("2. 월주차 가능 주차장 조회");
            System.out.println("3. 나의 이용권 확인");
            System.out.println("4. 나의 프로필");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    purchaseTicket();
                    break;
                case 2:
                    printParkingLot();
                    break;
                case 3:
                    myVoucherInfo();
                    break;
                case 4:
                    myProfile();
                    break;
                case 9:
                    sc.close();
                    mc.logoutUser();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }
        }
    }

    // 주차장 관리자 메뉴
    public void adminMenu() {
        int select;
        while (true) {
            System.out.println("월차 관리자 서비스 메뉴");
            System.out.println("1. 잔여 월주차권 조회");
            System.out.println("2. 주차장 사용자 리스트 조회");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    remainingParkingTicket();
                    break;
                case 2:
                    printParkingLotUserList();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 9:
                    sc.close();
                    mc.logoutAdmin();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }
        }
    }

    // 사용자 차량 추가
    public void addVehicle() {
        String vehicleType, vehicleModel, licensePlateNumber;
        int carWeight, vehicleLength, vehicleSpan, vehicleHeight;
        char tuning;
        System.out.print("차량 번호(띄어쓰기 x) : ");
        licensePlateNumber = sc.nextLine();
        System.out.print("차량 종류 : ");
        vehicleType = sc.nextLine();
        System.out.print("차량 모델명 : ");
        vehicleModel = sc.nextLine();
        // 아래 값들은 DB로 관리할 수 있음
        System.out.print("차량 무게 : ");
        carWeight = sc.nextInt();
        System.out.print("차량 전장 : ");
        vehicleLength = sc.nextInt();
        System.out.print("차량 전폭 : ");
        vehicleSpan = sc.nextInt();
        System.out.print("차량 높이 : ");
        vehicleHeight = sc.nextInt();
        sc.nextLine();

        // 차량 추가
        if (mc.userUpdate(new UserVehicle(licensePlateNumber, vehicleType, vehicleModel, carWeight, vehicleLength, vehicleSpan, vehicleHeight))) {
            System.out.println("차량 추가가 정상적으로 처리 되었습니다.\n");
        }
    }


    // 사용자 로그인 메뉴
    public boolean userSignInMenu() {
        int select;
        while (true) {
            System.out.print("아이디 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호 : ");
            String pw = sc.nextLine();
            select = mc.signInUser(id, pw);
            switch (select) {
                case 1:
                    try {
                        System.out.printf("%s님 로그인 성공되었습니다.", mc.getUser().getName());
                    } catch (ClassCastException e) {
                        System.out.printf("%s님 로그인 성공되었습니다.", mc.getAdmin().getName());
                    }
                    return true;
                case 2:
                    System.out.println("비밀번호가 틀립니다. 다시 입력하세요.");
                    break;
                case 3:
                    System.out.println("입력하신 아이디의 회원이 존재하지 않습니다.");
                    System.out.print("이전 메뉴로 다시 돌아가시겠습니까? : ");
                    char choice = sc.next().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        return false;
                    }else if(choice == 'n' || choice == 'N') {
                        return true;
                    }else {
                        System.out.println("다시 입력하세요");
                        break;
                    }
            }
        }
    }

    // 사용자 로그인 메뉴
    public boolean adminSignInMenu() {
        int select;
        while (true) {
            System.out.print("아이디 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호 : ");
            String pw = sc.nextLine();
            select = mc.signInAdmin(id, pw);
            switch (select) {
                case 1:
                    try {
                        System.out.printf("%s님 로그인 성공되었습니다.", mc.getUser().getName());
                    } catch (ClassCastException e) {
                        System.out.printf("%s님 로그인 성공되었습니다.", mc.getAdmin().getName());
                    }
                    return true;
                case 2:
                    System.out.println("비밀번호가 틀립니다. 다시 입력하세요.");
                    break;
                case 3:
                    System.out.println("입력하신 아이디의 회원이 존재하지 않습니다.");
                    System.out.print("이전 메뉴로 다시 돌아가시겠습니까? : ");
                    char choice = sc.next().charAt(0);
                    sc.nextLine();
                    if (choice == 'y' || choice == 'Y') {
                        return false;
                    }else if(choice == 'n' || choice == 'N') {
//                        return false;
//                        continue;
                    }else {
                        System.out.println("다시 입력하세요");
//                        break;
                    }
//                default:
            }
        }
    }

    // 관리자 회원가입 메뉴
    public void adminSignUp() {
        String id, pw, name, phoneNumber, parkingLotName, parkingLotAddress;
        int select;
        System.out.println("회원가입을 위해 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        id = sc.nextLine();
        System.out.print("패스워드 : ");
        pw = sc.nextLine();
        System.out.print("이름 : ");
        name = sc.nextLine();
        System.out.print("전화번호 : ");
        phoneNumber = sc.nextLine();
        System.out.print("주차장 이름 : ");
        parkingLotName = sc.nextLine();
        System.out.print("주차장 주소 : ");
        parkingLotAddress = sc.nextLine();

        while (true) {
            System.out.println("주차장 종류를 입력해주세요");
            System.out.println("1. 기계식 주차장");
            System.out.println("2. 자주식 주차장");
            System.out.println("3. 기계식과 자주식 주차장");
            System.out.print("주차장 타입 : ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    int width, height, length, weight, totalParkingSpace, registerCount;
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비 : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이 : ");
                    height = sc.nextInt();
                    System.out.print("제한 길이 : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게 : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    totalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    registerCount = sc.nextInt();
                    mc.signUpAdmin(new Admin(id, pw, name, phoneNumber, new MechanicalParkingLot(mc.getTp().getParkingLotCount(), parkingLotName,
                            parkingLotAddress, width, height, length, weight, totalParkingSpace, registerCount)));
                    return;
                case 2:
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이 : ");
                    height = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    totalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    registerCount = sc.nextInt();
                    mc.signUpAdmin(new Admin(id, pw, name, phoneNumber, new DriveInParkingLot(mc.getTp().getParkingLotCount(), parkingLotName,
                            parkingLotAddress, height, totalParkingSpace, registerCount)));
                    return;
                case 3:
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비 : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이 : ");
                    height = sc.nextInt();
                    System.out.print("제한 길이 : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게 : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    totalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    registerCount = sc.nextInt();
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이 : ");
                    height = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    totalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    registerCount = sc.nextInt();
                    mc.signUpAdmin(new Admin(id, pw, name, phoneNumber,
                            new DriveInParkingLot(mc.getTp().getParkingLotCount(), parkingLotName, parkingLotAddress, height, totalParkingSpace, registerCount),
                            new MechanicalParkingLot(mc.getTp().getParkingLotCount(), parkingLotName, parkingLotAddress, width, height, length, weight, totalParkingSpace, registerCount)));
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요");
            }
        }
    }

    // 사용자 회원가입 메뉴
    public void userSignUp() {
        String id, pw, name, phoneNumber, vehicleType, vehicleModel, licensePlateNumber;
        int carWeight, vehicleLength, vehicleSpan, vehicleHeight;

        System.out.println("회원가입을 위해 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        id = sc.nextLine();
        System.out.print("패스워드 : ");
        pw = sc.nextLine();
        System.out.print("이름 : ");
        name = sc.nextLine();
        System.out.print("전화번호 : ");
        phoneNumber = sc.nextLine();

        System.out.println("차량 정보 입력");
        System.out.print("차량 번호(띄어쓰기 x) : ");
        licensePlateNumber = sc.nextLine();
        System.out.print("차량 종류 : ");
        vehicleType = sc.nextLine();
        System.out.print("차량 모델명 : ");
        vehicleModel = sc.nextLine();
        System.out.print("차량 무게 : ");
        carWeight = sc.nextInt();
        System.out.print("차량 전장 : ");
        vehicleLength = sc.nextInt();
        System.out.print("차량 전폭 : ");
        vehicleSpan = sc.nextInt();
        System.out.print("차량 높이 : ");
        vehicleHeight = sc.nextInt();
        sc.nextLine();
        mc.signUpUser(new User(id, pw, name, phoneNumber, new UserVehicle(licensePlateNumber, vehicleType, vehicleModel, carWeight, vehicleLength, vehicleSpan, vehicleHeight)));

        System.out.println("회원 가입이 정상적으로 처리되었습니다.");
    }

    // 사용자 월주차권 구입
    // 사용자가 월주차권 구입 완료시 User 객체 정보에 월주차권 내역 저장해야함
    public void purchaseTicket() {
        ArrayList<ParkingLot> parkingLotList = mc.getTp().getTotalParkingLotList();

        User user = (User) mc.getUser();
        LinkedList<UserVehicle> userVehicleList = ((User) mc.getUser()).getVehicleList();
        int parkingLotType = 0;
        for (int i = 0; i < userVehicleList.size(); i++) {
            System.out.println(i + " : " + userVehicleList.get(i));
        }
        System.out.print("소유 차량중 어떤 차량을 기준으로 조회할까요? : ");
        int userVehicleSelect = sc.nextInt();
        sc.nextLine();
        UserVehicle userVehicle = userVehicleList.get(userVehicleSelect);

        System.out.println("주차장가능 주차장 리스트");
        for (int i = 0; i < parkingLotList.size(); i++) {
            ParkingLot parkingLot = parkingLotList.get(i);
            if (parkingLot instanceof MechanicalParkingLot) {
                MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
                if (userVehicle.getVehicleHeight() < m.getHeight() &&
                        userVehicle.getVehicleLength() < m.getLength() &&
                        userVehicle.getVehicleSpan() < m.getWidth() &&
                        userVehicle.getCarWeight() < m.getWeight() &&
                        m.getRemainingParkingSpace() != 0) {
                    System.out.printf("%d | [기계식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, m.getParkingLotName(), m.getParkingLotAddress(), m.getRemainingParkingSpace());
                }
            } else if (parkingLot instanceof DriveInParkingLot) {
                DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                if (userVehicle.getVehicleHeight() < d.getHeight() &&
                        d.getRemainingParkingSpace() != 0) {

                    System.out.printf("%d | [자주식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, d.getParkingLotName(), d.getParkingLotAddress(), d.getRemainingParkingSpace());
                }
            }
        }
        while (true) {
            System.out.println("월주차권 구입을 진행할 주차장 종류를 선택");
            System.out.println("1. 기계식 주차장");
            System.out.println("2. 자주식 주차장");
            System.out.println("9. 사용자 메뉴로 돌아가기");
            System.out.print("선택 : ");
            parkingLotType = sc.nextInt();
            switch (parkingLotType) {
                case 1:
                    break;
                case 2:
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
            }

            System.out.print("월주차권 구입을 진행할 주차장 번호를 선택하세요 : ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (parkingLotType == 1) {
                MechanicalParkingLot m = (MechanicalParkingLot) parkingLotList.get(choice);
                /*
                * 전체 주차장 최신화, admin 객체의 정보 최신화 해주거나 로그인 했을때 잔여 주차장 공간을
                * 조회 하기 위해서 totalparkinglot 객체를 가져와서 확인 해주는 방법 필요
                * */
                m.setRegisterCount(m.getRegisterCount() + 1);
                m.setRemainingParkingSpace(m.getRemainingParkingSpace() - 1);
                parkingLotList.set(choice, m);

                m.addParkingLotuserList(new UserInfo(user.getId(),user.getName(),user.getPhoneNumber(),userVehicle.getLicensePlateNumber(),userVehicle.getVehicleType(),userVehicle.getVehicleModel(),LocalDateTime.now(),LocalDateTime.now().plusMonths(1)));
                mc.updateTotalParkingLot();
                mc.updateVoucherInfo(userVehicle,m);
//                m.addParkingLotuserList(user);
                System.out.println("구입이 완료 되었습니다.");
            } else if (parkingLotType == 2) {
                DriveInParkingLot d = (DriveInParkingLot) parkingLotList.get(choice);
//                d.addParkingLotuserList(user);
                d.setRegisterCount(d.getRegisterCount() + 1); // 자주식 주차장의 현재 등록 대수 1 증가
                d.setRemainingParkingSpace(d.getRemainingParkingSpace() - 1); // 자주식 주차장의 잔여 주차 공간 1 감소
                parkingLotList.set(choice, d); // 출력했던 parkingLotList의 선택한 주차장 인덱스를 업데이트 한다.
                d.addParkingLotuserList(new UserInfo(user.getId(),user.getName(),user.getPhoneNumber(),userVehicle.getLicensePlateNumber(),userVehicle.getVehicleType(),userVehicle.getVehicleModel(),LocalDateTime.now(),LocalDateTime.now().plusMonths(1))); // 자주식 주차장의 등록된 유저의 정보를 추가한다.
                mc.updateTotalParkingLot();
                mc.updateVoucherInfo(userVehicle,d);
                System.out.println("구입이 완료 되었습니다.");
            }
        }
    }

    public void printParkingLot() {
        /*
         * 본인 소유 차량과 주차장 제한 조건을 비교해서 가능한 주차장들만 출력해주는 메서드
         * 잔여 주차공간을 합해서 출력해준다.
         * ex) 자주식 주차장 잔여공간 + 기계식 주차장 잔여 공간
         * */
        ArrayList<ParkingLot> tmp = mc.getTp().getTotalParkingLotList();
        User user = mc.getUser();
        LinkedList<UserVehicle> userVehicleList = ((User) mc.getUser()).getVehicleList();

        for (int i = 0; i < userVehicleList.size(); i++) {
            System.out.println(i + " : " + userVehicleList.get(i));
        }
        System.out.print("소유 차량중 어떤 차량을 기준으로 조회할까요? : ");
        int select = sc.nextInt();
        UserVehicle userVehicle = userVehicleList.get(select);
        for (ParkingLot lot : tmp) {
            if (lot instanceof MechanicalParkingLot) {
                MechanicalParkingLot mp = (MechanicalParkingLot) lot;
                if (userVehicle.getVehicleHeight() < mp.getHeight() &&
                        userVehicle.getVehicleLength() < mp.getLength() &&
                        userVehicle.getVehicleSpan() < mp.getWidth() &&
                        userVehicle.getCarWeight() < mp.getWeight() &&
                        mp.getRemainingParkingSpace() != 0) {
                    System.out.printf("[기계식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", mp.getParkingLotName(), mp.getParkingLotAddress(), mp.getRemainingParkingSpace());
                }
            } else if (lot instanceof DriveInParkingLot) {
                DriveInParkingLot dp = (DriveInParkingLot) lot;
                if (userVehicle.getVehicleHeight() < dp.getHeight() &&
                        dp.getRemainingParkingSpace() != 0) {
                    System.out.printf("[자주식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", dp.getParkingLotName(), dp.getParkingLotAddress(), dp.getRemainingParkingSpace());
                }
            }
        }
    }
    /*
     * 사용자가 구매한 월주차권의 정보를 보여주는 메뉴
     * User 객체의 VoucherList를 가져와 출력한다.
     * User클래스의 VoucherList 에는 user의 어떤 차량이 해당 월주차권을 구매했는지, 주차장 종류, 주차장 이름,
     * 주소, 주차권 구입 날짜, 만기 날짜 를 출력해준다.
     * 주차권 구입 날짜, 만기 날짜 필드를 생성해줘야한다.(VoucherList에 구입할 당시 해당 값들을 저장해줘야한다.
     * */
    public void myVoucherInfo() {
        User user = (User) mc.getUser();
        ArrayList<VoucherInfo> voucherList = user.getVoucherList();
        for (int i = 0; i < voucherList.size(); i++) {
            VoucherInfo voucherInfo = voucherList.get(i);
            ParkingLot parkingLot = voucherInfo.getParkingLot();
            if(parkingLot instanceof MechanicalParkingLot){
                System.out.printf("%d\t| 차량 : %s | [%s] | %s | 주소 : %s | 구입 날짜 : %s | 만료 날짜 : %s \n",i,voucherInfo.getUserVehicleInfo().getVehicleModel(),
                        "기계식 주차장",voucherInfo.getParkingLot().getParkingLotName(),voucherInfo.getParkingLot().getParkingLotAddress(),voucherInfo.getVoucherPurchaseDate(),voucherInfo.getVoucherExpirationDate());
            }else if(parkingLot instanceof DriveInParkingLot){
                System.out.printf("%d\t| 차량 : %s | [%s] | %s | 주소 : %s | 구입 날짜 : %s | 만료 날짜 : %s \n",i,voucherInfo.getUserVehicleInfo().getVehicleModel(),
                        "자주식 주차장",voucherInfo.getParkingLot().getParkingLotName(),voucherInfo.getParkingLot().getParkingLotAddress(),voucherInfo.getVoucherPurchaseDate(),voucherInfo.getVoucherExpirationDate());
            }
        }

    }
    /*
    * 비밀번호 변경, 등록 차량 확인, 차량 추가, 차량 삭제, 차량 정보 수정
    * */
    public void myProfile() {
        System.out.println("나의 프로필 메뉴");
        System.out.println("1. 비밀번호 변경");
        System.out.println("2. 전화번호 변경");
        System.out.println("3. 등록 차량 확인");
        System.out.println("4. 차량 추가");
        System.out.println("5. 차량 삭제");
        System.out.println("6. 차량 정보 수정");
        System.out.println("9. 이전 메뉴로 돌아가기");
        System.out.print("메뉴 번호 선택 : ");
        int select = sc.nextInt();
        sc.nextLine();
        switch (select) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
                return;
        }
    }

    public void remainingParkingTicket() {
        /*
         * MonthCarController(mc)에서 주차장에서 발행하는 월주차권(잔여 주차공간 만큼)
         * TotalParkingLot 에서 최신화된 주차장 정보를 가져온다.
         * 관리자가 관리하는 주차장을 식별하여 Admin 정보를 최신화 한다.
         * */
        Admin user = mc.getAdmin();
        ArrayList<ParkingLot> parkingLot = user.getParkingLot();
        int remainingMParkingTickets = 0, remainingDInParkingTickets = 0;
        String parkingLotName = "";
        for (ParkingLot p : parkingLot) {
            if (p instanceof MechanicalParkingLot) {
                parkingLotName = ((MechanicalParkingLot) p).getParkingLotName();
                remainingMParkingTickets += ((MechanicalParkingLot) p).getRemainingParkingSpace();
            } else if (p instanceof DriveInParkingLot) {
                remainingDInParkingTickets += ((DriveInParkingLot) p).getRemainingParkingSpace();
            }
        }
        System.out.println(parkingLotName + " 잔여 월주차권 수량");
        if (remainingDInParkingTickets > 0) {
            System.out.println("기계식 주차장 잔여 월주차권 : " + remainingMParkingTickets);
        }
        if (remainingMParkingTickets > 0) {
            System.out.println("자주식 주차장 잔여 월주차권 : " + remainingDInParkingTickets);
        }
        System.out.println("총 잔여 월주차권 : " + (remainingDInParkingTickets + remainingMParkingTickets));
    }

    // 기본 틀 만 작성됨 수정 필요(사용자가 선택한 차량 식별할 수 있는 번호)
    public void printParkingLotUserList() {
        /*
         * 사용자가 월주차권 구매(선택)시 등록된 전체 주차장 객체의 해당 주차장의 registerCount 를 증가시키고
         * (주차권 구매 사용자를 식별하기 위해서는 각 주차 티켓마다 고유 번호 부여(남은 주차장 수로 관리 가능할 듯)
         * */
        // Admin 유저가 관리하는 주차장 정보를 가져온다
        Admin admin = (Admin) mc.getAdmin();
//        ArrayList<UserVehicle> user
        ArrayList<ParkingLot> parkingLot = admin.getParkingLot();
        MechanicalParkingLot m = null;
        DriveInParkingLot d = null;
        ArrayList<UserInfo> mUserList = null;
        ArrayList<UserInfo> dUserList = null;

        for (ParkingLot p : parkingLot) {
            if (p instanceof MechanicalParkingLot) {
                m = (MechanicalParkingLot) p;
                mUserList = m.getParkingLotUserList();
                System.out.println("기계식 주차장 이용자 리스트");
                if(mUserList != null) {
                    for (int i = 0; i < mUserList.size(); i++) {
                        System.out.printf("%d 사용자 이름 : %s | 전화번호 : %s | 차량 종류 : %s | 차량 모델명 : %s | 차량 번호 : %s | 구입 날짜 : %s | 만료 날짜 : %s\n",
                                i, mUserList.get(i).getName(), mUserList.get(i).getPhoneNumber(),
                                mUserList.get(i).getVehicleType(),
                                mUserList.get(i).getVehicleModel(), mUserList.get(i).getLicensePlateNumber(),
                                mUserList.get(i).getVoucherPurchaseDate(), mUserList.get(i).getVoucherExpirationDate());

                    }
                }else System.out.println("주차장을 이용하는 사용자가 없습니다");
            } else if (p instanceof DriveInParkingLot) {
                d = (DriveInParkingLot) p;
                dUserList = d.getParkingLotUserList();
                System.out.println("자주식 주차장 이용자 리스트");
                if(dUserList != null) {
                    for (int i = 0; i < dUserList.size(); i++) {
                        System.out.printf("%d 사용자 이름 : %s | 전화번호 : %s | 차량 종류 : %s | 차량 모델명 : %s | 차량 번호 : %s | 구입 날짜 : %s | 만료 날짜 : %s\n",
                                i, dUserList.get(i).getName(), dUserList.get(i).getPhoneNumber(),
                                dUserList.get(i).getVehicleType(),
                                dUserList.get(i).getVehicleModel(), dUserList.get(i).getLicensePlateNumber(),
                                dUserList.get(i).getVoucherPurchaseDate(), dUserList.get(i).getVoucherExpirationDate());
                    }
                }else System.out.println("주차장을 이용하는 사용자가 없습니다");
            }
        }

//        ArrayList<User> userList = mc.tmp();

        // 정보에서 parkingLotUserList를 추출한다.
        // 해당 리스트를 출력한다. 포멧( 식별 번호 | 사용자 이름 : 홍길동 | 전화번호 : 010- | 차량 종류 : 세단 | 차량 모델명 : | 차량 번호 :

    }


}

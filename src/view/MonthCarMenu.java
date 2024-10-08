package view;

import controller.MonthCarController;
import vo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                    this.purchaseTicket();
                    break;
                case 2:
                    this.printParkingLot();
                    break;
                case 3:
                    this.myVoucherInfo();
                    break;
                case 4:
                    this.userMyProfile();
                    break;
                case 9:
                    sc.close();
                    mc.updateUserFile();
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
            System.out.println("3. 나의 프로필");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    this.remainingParkingVoucher();
                    break;
                case 2:
                    this.printParkingLotUserList();
                    break;
                case 3:
                    this.adminMyProfile();
                    break;
                case 9:
                    sc.close();
                    mc.updateUserFile();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }
        }
    }

    /*
     * 비밀번호 변경, 등록 차량 확인, 차량 추가, 차량 삭제, 차량 정보 수정
     * */
    // 사용자(프로필 관련 메뉴)
    public void userMyProfile() {
        while (true) {
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
                    this.changePW();
                    break;
                case 2:
                    this.changePhoneNumber();
                    break;
                case 3:
                    this.printVehicle();
                    break;
                case 4:
                    this.addVehicle();
                    break;
                case 5:
                    this.removeVehicle();
                    break;
                case 6:
                    this.modifyVehicle();
                    break;
                case 9:
                    return;
            }
        }
    }

    // 관리자(프로필 관련 메뉴)
    public void adminMyProfile() {
        while (true) {
            System.out.println("나의 프로필 메뉴");
            System.out.println("1. 비밀번호 변경");
            System.out.println("2. 전화번호 변경");
            System.out.println("3. 주차장 상세 정보 조회");
            System.out.println("4. 주차장 추가");
            System.out.println("5. 주차장 정보 수정");
            System.out.println("6. 주차장 삭제");
            System.out.println("9. 이전 메뉴로 돌아가기");
            System.out.print("메뉴 번호 선택 : ");
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    this.changePW();
                    break;
                case 2:
                    this.changePhoneNumber();
                    break;
                case 3:
                    this.viewParkingLotDetails();
                    break;
                case 4:
                    this.addParkingLot();
                    break;
                case 5:
                    this.editParkingLotInfo();
                    break;
                case 6:
                    this.removeParkingLot();
                    break;
                case 9:
                    return;
            }
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
                    } else if (choice == 'n' || choice == 'N') {
                        return true;
                    } else {
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
                    } else if (choice == 'n' || choice == 'N') {
                    } else {
                        System.out.println("다시 입력하세요");
                    }
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
        String message = mc.signUpUser(new User(id, pw, name, phoneNumber, new UserVehicle(licensePlateNumber, vehicleType, vehicleModel, carWeight, vehicleLength, vehicleSpan, vehicleHeight)));
        System.out.println(message);
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
            int width, dheight, mheight, length, weight, mtotalParkingSpace, mregisterCount, dtotalParkingSpace, dregisterCount;
            String message = "";
            System.out.println("주차장 종류를 입력해주세요");
            System.out.println("1. 기계식 주차장");
            System.out.println("2. 자주식 주차장");
            System.out.println("3. 기계식과 자주식 주차장");
            System.out.print("주차장 타입 : ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비(mm) : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이(mm) : ");
                    mheight = sc.nextInt();
                    System.out.print("제한 길이(mm) : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게(kg) : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    mtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    mregisterCount = sc.nextInt();

                    message = mc.signUpAdmin(new Admin(id, pw, name, phoneNumber, new MechanicalParkingLot(mc.getTp().getParkingLotCount(), parkingLotName,
                            parkingLotAddress, width, mheight, length, weight, mtotalParkingSpace, mregisterCount, mc.getTp().getMechanicalParkingLotCount() + 1)));
                    System.out.println(message);
                    return;
                case 2:
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이(mm) : ");
                    dheight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    dtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    dregisterCount = sc.nextInt();
                    message = mc.signUpAdmin(new Admin(id, pw, name, phoneNumber, new DriveInParkingLot(mc.getTp().getParkingLotCount(), parkingLotName,
                            parkingLotAddress, dheight, dtotalParkingSpace, dregisterCount, mc.getTp().getMechanicalParkingLotCount() + 1)));
                    System.out.println(message);
                    return;
                case 3:
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비(mm) : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이(mm) : ");
                    mheight = sc.nextInt();
                    System.out.print("제한 길이(mm) : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게(kg) : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    mtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    mregisterCount = sc.nextInt();
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이(mm) : ");
                    dheight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    dtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    dregisterCount = sc.nextInt();
                    message = mc.signUpAdmin(new Admin(id, pw, name, phoneNumber,
                            new DriveInParkingLot(mc.getTp().getParkingLotCount(), parkingLotName, parkingLotAddress, dheight, dtotalParkingSpace, dregisterCount, mc.getTp().getDriveInParkingLotCount() + 1),
                            new MechanicalParkingLot(mc.getTp().getParkingLotCount(), parkingLotName, parkingLotAddress, width, mheight, length, weight, mtotalParkingSpace, mregisterCount, mc.getTp().getMechanicalParkingLotCount() + 1)));
                    System.out.println(message);
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요");
            }
        }
    }

    // 사용자 월주차권 구입
    /*
     * 소유 차량을 선택 -> 선택된 차량을 기준으로 주차가능한 주차장 리스트 출력 -> 주차장 타입 선택 -> 주차장 선택
     * -> 선택한 주차장의 등록대수 + 1, 잔여 주차공간 - 1 한 결과를 total
     * */
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

        while (true) {
            System.out.println("월주차권 구입을 진행할 주차장 종류를 선택");
            System.out.println("1. 기계식 주차장");
            System.out.println("2. 자주식 주차장");
            System.out.println("3. 기계식 & 자주식 주차장");
            System.out.println("9. 사용자 메뉴로 돌아가기");
            System.out.print("선택 : ");
            parkingLotType = sc.nextInt();
            System.out.println("주차가능 주차장 리스트");
            switch (parkingLotType) {
                case 1:
                    for (int i = 0; i < parkingLotList.size(); i++) {
                        ParkingLot parkingLot = parkingLotList.get(i);
                        if (parkingLot instanceof MechanicalParkingLot) {
                            MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
                            if (userVehicle.getVehicleHeight() <= m.getHeight() &&
                                    userVehicle.getVehicleLength() <= m.getLength() &&
                                    userVehicle.getVehicleWidth() <= m.getWidth() &&
                                    userVehicle.getVehicleWeight() <= m.getWeight() &&
                                    m.getRemainingParkingSpace() != 0) {
                                System.out.printf("%d | [기계식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, m.getParkingLotName(), m.getParkingLotAddress(), m.getRemainingParkingSpace());
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < parkingLotList.size(); i++) {
                        ParkingLot parkingLot = parkingLotList.get(i);
                        if (parkingLot instanceof DriveInParkingLot) {
                            DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                            if (userVehicle.getVehicleHeight() <= d.getHeight() &&
                                    d.getRemainingParkingSpace() != 0) {
                                System.out.printf("%d | [자주식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, d.getParkingLotName(), d.getParkingLotAddress(), d.getRemainingParkingSpace());
                            }
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < parkingLotList.size(); i++) {
                        ParkingLot parkingLot = parkingLotList.get(i);
                        if (parkingLot instanceof MechanicalParkingLot) {
                            MechanicalParkingLot m = (MechanicalParkingLot) parkingLot;
                            if (userVehicle.getVehicleHeight() <= m.getHeight() &&
                                    userVehicle.getVehicleLength() <= m.getLength() &&
                                    userVehicle.getVehicleWidth() <= m.getWidth() &&
                                    userVehicle.getVehicleWeight() <= m.getWeight() &&
                                    m.getRemainingParkingSpace() != 0) {
                                System.out.printf("%d | [기계식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, m.getParkingLotName(), m.getParkingLotAddress(), m.getRemainingParkingSpace());
                            }
                        } else if (parkingLot instanceof DriveInParkingLot) {
                            DriveInParkingLot d = (DriveInParkingLot) parkingLot;
                            if (userVehicle.getVehicleHeight() <= d.getHeight() &&
                                    d.getRemainingParkingSpace() != 0) {

                                System.out.printf("%d | [자주식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", i, d.getParkingLotName(), d.getParkingLotAddress(), d.getRemainingParkingSpace());
                            }
                        }
                    }
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
                    continue;
            }
            break;
        }
        while (true) {
            System.out.print("월주차권 구입을 진행할 주차장 번호를 선택하세요 : ");
            int choice = sc.nextInt();
            sc.nextLine();
            /*
             * 인덱스 외에 번호를 입력했을 경우 예외 처리 필요
             * 주차장 타입, 선택 번호 를 파라미터로 넘겨서 해당 선택이 제대로 된건지 boolean 값으로 리턴
             * 1. 전체 주차장 사이즈 보다 입력한 값이 큰 경우
             * 2. 선택한 주차장 타입 인덱스 인지 확인
             * */
            if (mc.checkSelectedParkingLotType(parkingLotType, choice)) {
                if (parkingLotType == 1) {
                    MechanicalParkingLot m = (MechanicalParkingLot) parkingLotList.get(choice);
                    /*
                     * 전체 주차장 최신화, admin 객체의 정보 최신화 해주거나 로그인 했을때 잔여 주차장 공간을
                     * 조회 하기 위해서 totalparkinglot 객체를 가져와서 확인 해주는 방법 필요
                     * */
                    m.setRegisterCount(m.getRegisterCount() + 1);
                    m.setRemainingParkingSpace(m.getRemainingParkingSpace() - 1);
                    parkingLotList.set(choice, m);
                    m.addParkingLotuserList(new UserInfo(user.getId(), user.getName(), user.getPhoneNumber(), userVehicle.getPlateNumber(), userVehicle.getVehicleType(), userVehicle.getVehicleModel(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1)));
                    mc.updateTotalParkingLot();
                    mc.updateVoucherInfo(userVehicle, m);
                    System.out.println("구입이 완료 되었습니다.");
                    return;
                } else if (parkingLotType == 2) {
                    DriveInParkingLot d = (DriveInParkingLot) parkingLotList.get(choice);
                    d.setRegisterCount(d.getRegisterCount() + 1); // 자주식 주차장의 현재 등록 대수 1 증가
                    d.setRemainingParkingSpace(d.getRemainingParkingSpace() - 1); // 자주식 주차장의 잔여 주차 공간 1 감소
                    parkingLotList.set(choice, d); // 출력했던 parkingLotList의 선택한 주차장 인덱스를 업데이트 한다.
                    d.addParkingLotUserList(new UserInfo(user.getId(), user.getName(), user.getPhoneNumber(), userVehicle.getPlateNumber(), userVehicle.getVehicleType(), userVehicle.getVehicleModel(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1))); // 자주식 주차장의 등록된 유저의 정보를 추가한다.
                    mc.updateTotalParkingLot();
                    mc.updateVoucherInfo(userVehicle, d);
                    System.out.println("구입이 완료 되었습니다.");
                    return;
                }
            } else {
                System.out.println("선택한 주차장과 선택한 타입이 다릅니다. 다시 입력하세요.");
            }
        }
    }

    /*
     * 본인 소유 차량과 주차장 제한 조건을 비교해서 가능한 주차장들만 출력해주는 메서드
     * 잔여 주차공간을 합해서 출력해준다.
     * ex) 자주식 주차장 잔여공간 + 기계식 주차장 잔여 공간
     * */
    public void printParkingLot() {


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
                if (userVehicle.getVehicleHeight() <= mp.getHeight() &&
                        userVehicle.getVehicleLength() <= mp.getLength() &&
                        userVehicle.getVehicleWidth() <= mp.getWidth() &&
                        userVehicle.getVehicleWeight() <= mp.getWeight() &&
                        mp.getRemainingParkingSpace() != 0) {
                    System.out.printf("[기계식 주차장]\t [주차장 이름] : %s | [주소] : %s | [%d]자리 남았습니다.\n", mp.getParkingLotName(), mp.getParkingLotAddress(), mp.getRemainingParkingSpace());
                }
            } else if (lot instanceof DriveInParkingLot) {
                DriveInParkingLot dp = (DriveInParkingLot) lot;
                if (userVehicle.getVehicleHeight() <= dp.getHeight() &&
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
        mc.checkVoucherExpirationDate(); // 월주차권 만료 여부 확인
        User user = (User) mc.getUser();
        ArrayList<VoucherInfo> voucherList = user.getVoucherList();
        for (int i = 0; i < voucherList.size(); i++) {
            VoucherInfo voucherInfo = voucherList.get(i);
            ParkingLot parkingLot = voucherInfo.getParkingLot();
            if (parkingLot instanceof MechanicalParkingLot) {
                System.out.printf("%d\t| 차량 : %s | [%s] | %s | 주소 : %s | 구입 날짜 : %s | 만료 날짜 : %s \n", i, voucherInfo.getUserVehicleInfo().getVehicleModel(),
                        "기계식 주차장", voucherInfo.getParkingLot().getParkingLotName(), voucherInfo.getParkingLot().getParkingLotAddress(), voucherInfo.getVoucherPurchaseDate(), voucherInfo.getVoucherExpirationDate());
            } else if (parkingLot instanceof DriveInParkingLot) {
                System.out.printf("%d\t| 차량 : %s | [%s] | %s | 주소 : %s | 구입 날짜 : %s | 만료 날짜 : %s \n", i, voucherInfo.getUserVehicleInfo().getVehicleModel(),
                        "자주식 주차장", voucherInfo.getParkingLot().getParkingLotName(), voucherInfo.getParkingLot().getParkingLotAddress(), voucherInfo.getVoucherPurchaseDate(), voucherInfo.getVoucherExpirationDate());
            }
        }
    }

    /*
     * MonthCarController(mc)에서 주차장에서 발행하는 월주차권(잔여 주차공간 만큼)
     * TotalParkingLot 에서 최신화된 주차장 정보를 가져온다.
     * 관리자가 관리하는 주차장을 식별하여 Admin 정보를 최신화 한다.
     * */
    public void remainingParkingVoucher() {
        Admin user = mc.getAdmin();
        ArrayList<ParkingLot> parkingLot = user.getParkingLotList();
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
        if (remainingMParkingTickets > 0) {
            System.out.println("기계식 주차장 잔여 월주차권 : " + remainingMParkingTickets);
        }
        if (remainingDInParkingTickets > 0) {
            System.out.println("자주식 주차장 잔여 월주차권 : " + remainingDInParkingTickets);
        }
        System.out.println("총 잔여 월주차권 : " + (remainingDInParkingTickets + remainingMParkingTickets));
    }

    // 기본 틀 만 작성됨 수정 필요(사용자가 선택한 차량 식별할 수 있는 번호)
    /*
     * 사용자가 월주차권 구매(선택)시 등록된 전체 주차장 객체의 해당 주차장의 registerCount 를 증가시키고
     * (주차권 구매 사용자를 식별하기 위해서는 각 주차 티켓마다 고유 번호 부여(남은 주차장 수로 관리 가능할 듯)
     * */
    // Admin 유저가 관리하는 주차장 정보를 가져온다
    public void printParkingLotUserList() {

        Admin admin = (Admin) mc.getAdmin();
        ArrayList<ParkingLot> parkingLot = admin.getParkingLotList();
        MechanicalParkingLot m = null;
        DriveInParkingLot d = null;
        ArrayList<UserInfo> mUserList = null;
        ArrayList<UserInfo> dUserList = null;

        for (ParkingLot p : parkingLot) {
            if (p instanceof MechanicalParkingLot) {
                m = (MechanicalParkingLot) p;
                mUserList = m.getParkingLotUserList();
                System.out.println("기계식 주차장 이용자 리스트");
                if (mUserList != null) {
                    for (int i = 0; i < mUserList.size(); i++) {
                        System.out.printf("%d 사용자 이름 : %s | 전화번호 : %s | 차량 종류 : %s | 차량 모델명 : %s | 차량 번호 : %s | 구입 날짜 : %s | 만료 날짜 : %s\n",
                                i, mUserList.get(i).getName(), mUserList.get(i).getPhoneNumber(),
                                mUserList.get(i).getVehicleType(),
                                mUserList.get(i).getVehicleModel(), mUserList.get(i).getPlateNumber(),
                                mUserList.get(i).getVoucherPurchaseDate(), mUserList.get(i).getVoucherExpirationDate());

                    }
                } else System.out.println("주차장을 이용하는 사용자가 없습니다");
            } else if (p instanceof DriveInParkingLot) {
                d = (DriveInParkingLot) p;
                dUserList = d.getParkingLotUserList();
                System.out.println("자주식 주차장 이용자 리스트");
                if (dUserList != null) {
                    for (int i = 0; i < dUserList.size(); i++) {
                        System.out.printf("%d 사용자 이름 : %s | 전화번호 : %s | 차량 종류 : %s | 차량 모델명 : %s | 차량 번호 : %s | 구입 날짜 : %s | 만료 날짜 : %s\n",
                                i, dUserList.get(i).getName(), dUserList.get(i).getPhoneNumber(),
                                dUserList.get(i).getVehicleType(),
                                dUserList.get(i).getVehicleModel(), dUserList.get(i).getPlateNumber(),
                                dUserList.get(i).getVoucherPurchaseDate(), dUserList.get(i).getVoucherExpirationDate());
                    }
                } else System.out.println("주차장을 이용하는 사용자가 없습니다");
            }
        }
    }

    // 비밀번호 변경
    /*
     * 현재 유저의 비밀번호를 입력받기 -> mc에서 기존 비밀번호와 동일한지 확인
     * -> 새로운 비밀번호 입력 받기 -> mc 에서 입력받은 새로운 비밀번호로 수정 -> User 객체 최신화 하여 파일로 저장
     * */
    public void changePW() {
        System.out.println("비밀번호 변경 메뉴");
        while (true) {
            System.out.print("현재 비밀번호 : ");
            String oldPW = sc.nextLine();
            if (mc.checkPW(oldPW)) {
                System.out.print("새로운 비밀번호 입력 : ");
                String newPW = sc.nextLine();
                if (mc.changePW(newPW)) {
                    System.out.println("비밀번호 수정 성공!");
                    return;
                } else System.out.println("비밀번호 수정에 실패하였습니다. 다시 시도해주세요.");
            } else System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
        }
    }

    // 전화번호 변경
    /*
     * 현재 등록된 핸드폰 번호 출력 -> 바꾸고자 하는 새로운 핸드폰 번호 입력 받기
     * -> mc에 전달하여 전화번호 수정 -> User 객체 최신화 하여 파일로 저장
     * */
    public void changePhoneNumber() {
        while (true) {
            System.out.println("현재 등록된 전화번호 : " + mc.getUser().getPhoneNumber());
            System.out.print("새로운 전화번호 : ");
            String newPhoneNumber = sc.nextLine();
            if (mc.changePhoneNumber(newPhoneNumber)) {
                System.out.println("전화번호 수정 성공!");
                return;
            } else System.out.println("전화번호 수정에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    // 등록 차량 확인
    /*
     * User 객체에 등록된 차량 전달 받아 출력
     * */
    public void printVehicle() {
        User user = (User) mc.getUser();
        LinkedList<UserVehicle> userVehicle = user.getVehicleList();
        for (int i = 0; i < userVehicle.size(); i++) {
            System.out.printf("%d\t | %s\n", i, userVehicle.get(i));
        }

    }

    // 사용자 차량 추가 등록
    /*
     * printVehicle() 메서드를 활용해서 출력 -> 새로운 차량 정보를 입력 받기
     * -> mc 에서 userVehicleList에 추가 -> User 객체 최신화 하여 파일로 저장
     * */
    public void addVehicle() {
        String vehicleType, vehicleModel, licensePlateNumber;
        int carWeight, vehicleLength, vehicleSpan, vehicleHeight;
        char tuning;
        while (true) {
            this.printVehicle(); // 현재 차량 리스트 출력
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
            switch (mc.addVehicle(licensePlateNumber, vehicleType, vehicleModel, carWeight, vehicleLength, vehicleSpan, vehicleHeight)) {
                case -1:
                    System.out.println("이미 등록된 차량 번호입니다. 다시 확인후 시도해주세요.");
                    continue;
                case 0:
                    System.out.println("차량 추가 등록에 실패했습니다. 다시 시도해주세요.");
                    continue;
                case 1:
                    System.out.println("차량 추가 등록이 정상적으로 처리 되었습니다.");
                    return;
            }
        }
    }

    // 사용자 등록 차량 삭제
    /*
     * printVehicle() 메서드를 이용해 출력 -> 삭제할 차량 번호 입력 받기
     * -> mc에서 해당 차량 번호로 대조하여 있으면 삭제 / 없으면 삭제에 실패했습니다 출력 후 다시 차량번호 입력 받기
     * */
    public void removeVehicle() {
        while (true) {
            this.printVehicle();
            System.out.print("삭제할 차량 번호 입력(띄어 쓰기 x) : ");
            String licensePlateNumber = sc.nextLine();
            if (mc.removeVehicle(licensePlateNumber)) {
                System.out.println(licensePlateNumber + " 차량을 삭제 했습니다.");
                return;
            } else System.out.println(licensePlateNumber + "차량이 존재하지 않습니다. 확인후 다시 시도해주세요.");
        }
    }

    // 등록된 차량 정보 수정
    /*
     * printVehicle() 메서드를 이용해 출력 -> 수정하고자 하는 차량 선택
     * -> mc에서 해당 차량 있는지 확인후 있을 경우 수정하고자 하는 정보 선택 -> mc 에서 해당 정보 수정 후
     * User 객체 최신화 하여 파일로 저장
     * */
    public void modifyVehicle() {
        this.printVehicle();
        System.out.print("수정하고자 하는 차량 선택 : ");
        int selectVehicleIndex = sc.nextInt();
        sc.nextLine();
        while (true) {
            System.out.println("수정하고자 하는 항목을 선택하세요.");
            System.out.println("1. 차량 번호");
            System.out.println("2. 차량 종류");
            System.out.println("3. 차량 모델명");
            System.out.println("4. 차량 무게");
            System.out.println("5. 차량 전장");
            System.out.println("6. 차량 전폭");
            System.out.println("7. 차량 높이");
            System.out.println("9. 이전 메뉴로 되돌아가기");
            System.out.print("항목 선택 : ");
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    System.out.print("수정할 차량 번호 (띄어쓰기 x) : ");
                    String licensePlateNumber = sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, licensePlateNumber)) {
                        System.out.println("차량 번호 정보가 수정되었습니다.");
                    } else System.out.println("차량 번호 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 2:
                    System.out.print("수정할 차량 종류 : ");
                    String vehicleType = sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleType)) {
                        System.out.println("차량 종류 정보가 수정되었습니다.");
                    } else System.out.println("차량 종류 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 3:
                    System.out.print("수정할 차량 모델명 : ");
                    String vehicleModel = sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleModel)) {
                        System.out.println("차량 모델명 정보가 수정되었습니다.");
                    } else System.out.println("차량 모델명 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 4:
                    System.out.print("수정할 차량 무게(kg) : ");
                    int vehicleWeight = sc.nextInt();
                    sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleWeight)) {
                        System.out.println("차량 무게 정보가 수정되었습니다.");
                    } else System.out.println("차량 무게 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 5:
                    System.out.print("수정할 차량 전장(mm) : ");
                    int vehicleLength = sc.nextInt();
                    sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleLength)) {
                        System.out.println("차량 전장 정보가 수정되었습니다.");
                    } else System.out.println("차량 전장 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 6:
                    System.out.print("수정할 차량 전폭(mm) : ");
                    int vehicleSpan = sc.nextInt();
                    sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleSpan)) {
                        System.out.println("차량 전폭 정보가 수정되었습니다.");
                    } else System.out.println("차량 전폭 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 7:
                    System.out.print("수정할 차량 높이(mm) : ");
                    int vehicleHeight = sc.nextInt();
                    sc.nextLine();
                    if (mc.modifyVehicle(selectVehicleIndex, select, vehicleHeight)) {
                        System.out.println("차량 높이 정보가 수정되었습니다.");
                    } else System.out.println("차량 높이 정보 수정에 실패하였습니다. 다시 시도해주세요.");
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    // 주차장 상세 정보 조회
    /*
     * mc 에서 Admin의 parkingLotList를 가져와 출력한다.
     * */
    public void viewParkingLotDetails() {
        Admin admin = mc.getAdmin();
        ArrayList<ParkingLot> parkingLotList = admin.getParkingLotList();
        for (int i = 0; i < parkingLotList.size(); i++) {
            if (parkingLotList.get(i) instanceof MechanicalParkingLot) {
                MechanicalParkingLot m = (MechanicalParkingLot) parkingLotList.get(i);
                System.out.printf("%d \t| %s", i, m);
            } else if (parkingLotList.get(i) instanceof DriveInParkingLot) {
                DriveInParkingLot d = (DriveInParkingLot) parkingLotList.get(i);
                System.out.printf("%d \t| %s", i, d);
            }
        }
    }

    // 주차장 추가
    public void addParkingLot() {
        Admin admin = mc.getAdmin();
        ParkingLot existingParkingInfo = admin.getParkingLotList().get(0);
        this.viewParkingLotDetails();
        int select;
        while (true) {
            int width, mheight, dheight, length, weight, dtotalParkingSpace, dregisterCount, mtotalParkingSpace, mregisterCount;
            System.out.println("주차장 종류를 입력해주세요");
            System.out.println("1. 기계식 주차장");
            System.out.println("2. 자주식 주차장");
            System.out.println("3. 기계식과 자주식 주차장");
            System.out.print("주차장 종류 : ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비 : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이 : ");
                    mheight = sc.nextInt();
                    System.out.print("제한 길이 : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게 : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    mtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    mregisterCount = sc.nextInt();
                    if (mc.addParkingLot(new MechanicalParkingLot(existingParkingInfo.getParkingLotId(), existingParkingInfo.getParkingLotName(), existingParkingInfo.getParkingLotAddress(),
                            width, mheight, length, weight, mtotalParkingSpace, mregisterCount, mc.getTp().getMechanicalParkingLotCount() + 1))) {
                        System.out.println("기계식 주차장 등록에 성공했습니다.");
                    } else System.out.println("기계식 주차장 등록에 실패하였습니다. 다시 입력해주세요.");
                    return;
                case 2:
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이 : ");
                    dheight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    dtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    dregisterCount = sc.nextInt();
                    if (mc.addParkingLot(new DriveInParkingLot(existingParkingInfo.getParkingLotId(), existingParkingInfo.getParkingLotName(), existingParkingInfo.getParkingLotAddress(),
                            dheight, dtotalParkingSpace, dregisterCount, mc.getTp().getDriveInParkingLotCount() + 1))) {
                        System.out.println("자주식 주차장 등록에 성공했습니다.");
                    } else System.out.println("자주식 주차장 등록에 실패하였습니다. 다시 입력해주세요.");
                    return;
                case 3:
                    System.out.println("기계식 주차장의 제한 사항");
                    System.out.print("제한 너비 : ");
                    width = sc.nextInt();
                    System.out.print("제한 높이 : ");
                    mheight = sc.nextInt();
                    System.out.print("제한 길이 : ");
                    length = sc.nextInt();
                    System.out.print("제한 무게 : ");
                    weight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    mtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    mregisterCount = sc.nextInt();
                    System.out.println("자주식 주차장의 제한 사항");
                    System.out.print("제한 높이 : ");
                    dheight = sc.nextInt();
                    System.out.print("총 주차 면 수 : ");
                    dtotalParkingSpace = sc.nextInt();
                    System.out.print("현재 등록 대수 : ");
                    dregisterCount = sc.nextInt();
                    if (mc.addParkingLot(new MechanicalParkingLot(existingParkingInfo.getParkingLotId(), existingParkingInfo.getParkingLotName(), existingParkingInfo.getParkingLotAddress(),
                            width, mheight, length, weight, mtotalParkingSpace, mregisterCount, mc.getTp().getMechanicalParkingLotCount() + 1))) {
                        System.out.println("기계식 주차장 등록에 성공했습니다.");
                    } else System.out.println("기계식 주차장 등록에 실패하였습니다. 다시 입력해주세요.");
                    if (mc.addParkingLot(new DriveInParkingLot(existingParkingInfo.getParkingLotId(), existingParkingInfo.getParkingLotName(), existingParkingInfo.getParkingLotAddress(),
                            dheight, dtotalParkingSpace, dregisterCount, mc.getTp().getDriveInParkingLotCount() + 1))) {
                        System.out.println("자주식 주차장 등록에 성공했습니다.");
                    } else System.out.println("자주식 주차장 등록에 실패하였습니다. 다시 입력해주세요.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요");
            }
        }
    }

    // 주차장 정보 수정
    /*
     * viewParkingLotDetails() 메서드로 주차장을 출력 -> 수정하고자하는  주차장 선택
     * -> 수정하고자하는 항목 선택 -> 수정할 정보 입력 -> 수정
     * */
    public void editParkingLotInfo() {
        this.viewParkingLotDetails();
        int selectItem;
        Admin admin = mc.getAdmin();
        ArrayList<ParkingLot> parkingLotList = admin.getParkingLotList();
        System.out.print("수정할 주차장 선택 : ");
        int selectIndex = sc.nextInt();
        sc.nextLine();
        while (true) {
            System.out.println("수정할 항목을 선택하세요.");
            if (parkingLotList.get(selectIndex) instanceof MechanicalParkingLot) {
                System.out.println("1. 제한 너비");
                System.out.println("2. 제한 높이");
                System.out.println("3. 제한 길이");
                System.out.println("4. 제한 무게");
                System.out.println("5. 총 주차 공간");
                System.out.println("6. 현재 등록 대수");
                System.out.println("9. 이전 메뉴로 이동");
                System.out.print("항목 선택 : ");
                selectItem = sc.nextInt();
                switch (selectItem) {
                    case 1:
                        System.out.println("--제한 너비 수정--");
                        System.out.print("수정할 제한 너비(mm) : ");
                        Integer width = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, width)) {
                            System.out.println("제한 너비를 수정했습니다.");
                        } else System.out.println("제한 너비 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 2:
                        System.out.println("--제한 높이 수정--");
                        System.out.print("수정할 제한 높이(mm) : ");
                        Integer height = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, height)) {
                            System.out.println("제한 높이를 수정했습니다.");
                        } else System.out.println("제한 높이 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 3:
                        System.out.println("--제한 길이 수정--");
                        System.out.print("수정할 제한 길이(mm) : ");
                        Integer length = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, length)) {
                            System.out.println("제한 길이를 수정했습니다.");
                        } else System.out.println("제한 길이 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 4:
                        System.out.println("--제한 무게 수정--");
                        System.out.print("수정할 제한 무게(kg) : ");
                        Integer weight = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, weight)) {
                            System.out.println("제한 무게를 수정했습니다.");
                        } else System.out.println("제한 무게 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 5:
                        System.out.println("--총 주차 공간 수정--");
                        System.out.print("수정할 총 주차 공간 : ");
                        Integer totalParkingSpace = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, totalParkingSpace)) {
                            System.out.println("총 주차 공간을 수정했습니다.");
                        } else System.out.println("총 주차 공간 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 6:
                        System.out.println("--현재 등록 대수 수정--");
                        System.out.print("수정할 현재 등록 대수 : ");
                        Integer registerCount = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, registerCount)) {
                            System.out.println("현재 등록 대수를 수정했습니다.");
                        } else System.out.println("현재 등록 대수 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 9:
                        sc.nextLine();
                        return;
                }
            } else if (parkingLotList.get(selectIndex) instanceof DriveInParkingLot) {
                System.out.println("1. 제한 높이");
                System.out.println("2. 총 주차 공간");
                System.out.println("3. 현재 등록 대수");
                System.out.println("9. 이전 메뉴로 이동");
                selectItem = sc.nextInt();
                sc.nextLine();
                switch (selectItem) {
                    case 1:
                        System.out.println("--제한 높이 수정--");
                        System.out.print("수정할 제한 높이(mm) : ");
                        Integer height = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, height)) {
                            System.out.println("제한 높이를 수정했습니다.");
                        } else System.out.println("제한 높이 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 2:
                        System.out.println("--총 주차 공간 수정--");
                        System.out.print("수정할 총 주차 공간 : ");
                        Integer totalParkingSpace = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, totalParkingSpace)) {
                            System.out.println("총 주차 공간을 수정했습니다.");
                        } else System.out.println("총 주차 공간 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 3:
                        System.out.println("--현재 등록 대수 수정--");
                        System.out.print("수정할 현재 등록 대수 : ");
                        Integer registerCount = sc.nextInt();
                        if (mc.editParkingLotInfo(selectIndex, selectItem, registerCount)) {
                            System.out.println("현재 등록 대수를 수정했습니다.");
                        } else System.out.println("현재 등록 대수 수정에 실패했습니다. 다시 시도해주세요.");
                        break;
                    case 9:
                        sc.nextLine();
                        return;
                }
            }
        }
    }

    // 주차장 삭제
    /*
     * 주차장을 구매한 바우처도 함께 삭제 필요
     * */
    public void removeParkingLot() {
        while (true) {
            this.viewParkingLotDetails();
            System.out.print("삭제할 주차장 선택 : ");
            int selectParkingLot = sc.nextInt();
            if (mc.removeParkingLot(selectParkingLot)) {
                System.out.println("해당 주차장을 삭제 했습니다.");
                return;
            } else System.out.println("해당 주차장 삭제에 실패했습니다. 다시 시도해주세요.");
        }
    }
}

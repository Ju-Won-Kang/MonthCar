package vo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TotalParkingLot implements Serializable {
    private ArrayList<ParkingLot> totalParkingLotList = new ArrayList();
    private int parkingLotCount;

    public TotalParkingLot() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/jun/Documents/KH/MonthCar/ParkingLot/totalParkingLot.txt"))) {
            this.totalParkingLotList.addAll((Collection<? extends ParkingLot>) ois.readObject());
            this.parkingLotCount = this.totalParkingLotList.size();
        } catch (FileNotFoundException e) {
            System.out.println("등록된 주차장이 없습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        totalParkingLotList.add(new Admin("a", "a", "홍길동", "010-1234-1234", new MechanicalParkingLot(2800, 2500, 4800, 1800, 0)));
//        totalParkingLotList.add(new Admin("b", "b", "고길동", "010-1234-1234", new DriveInParkingLot(2200,100,0)));
//        totalParkingLotList.add(new Admin("c", "c", "차무식", "010-4321-1234", new DriveInParkingLot(2200,100,0), new MechanicalParkingLot(2600,2200,4800,1700,80)));
    }

    public TotalParkingLot(List totalParkingLotList) {
        this.totalParkingLotList.addAll(totalParkingLotList);
    }
    public void updateTotalParkingLot(ArrayList<ParkingLot> parkingLot ) {
        this.totalParkingLotList.addAll(parkingLot);
        File totalParkingLotDirectory = new File("/Users/jun/Documents/KH/MonthCar/ParkingLot");
        if(!totalParkingLotDirectory.exists()) {
            if(!totalParkingLotDirectory.mkdirs()) {
                System.out.println("전체 주차장 디렉터리 생성 실패 : " + totalParkingLotDirectory.getAbsolutePath());
            }
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/jun/Documents/KH/MonthCar/ParkingLot/totalParkingLot.txt"))) {
            oos.writeObject(this.totalParkingLotList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ParkingLot> getTotalParkingLotList() {
        return totalParkingLotList;
    }

    public void setTotalParkingLotList(ArrayList<ParkingLot> totalParkingLotList) {
        this.totalParkingLotList = totalParkingLotList;
    }

    public int getParkingLotCount() {
        return parkingLotCount;
    }

    public void setParkingLotCount(int parkingLotCount) {
        this.parkingLotCount = parkingLotCount;
    }
    public void updateTotalParkingLot() {
        File totalParkingLotDirectory = new File("/Users/jun/Documents/KH/MonthCar/ParkingLot");
        if(!totalParkingLotDirectory.exists()) {
            if(!totalParkingLotDirectory.mkdirs()) {
                System.out.println("전체 주차장 디렉터리 생성 실패 : " + totalParkingLotDirectory.getAbsolutePath());
            }
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/jun/Documents/KH/MonthCar/ParkingLot/totalParkingLot.txt"))) {
            oos.writeObject(this.totalParkingLotList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

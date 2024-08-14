package vo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TotalParkingLot implements Serializable {
    private ArrayList<ParkingLot> totalParkingLotList = new ArrayList();
    private int parkingLotCount;
    private int mechanicalParkingLotCount = 0;
    private int drivingParkingLotCount = 0;

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
        for(ParkingLot parkingLot : this.totalParkingLotList) {
            if(parkingLot instanceof MechanicalParkingLot){
                this.mechanicalParkingLotCount++;
            }else if(parkingLot instanceof DriveInParkingLot){
                this.drivingParkingLotCount++;
            }
        }
    }

    public TotalParkingLot(List totalParkingLotList) {
        this.totalParkingLotList.addAll(totalParkingLotList);
    }
    public void addTotalParkingLot(ArrayList<ParkingLot> parkingLot ) {
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

    public boolean editTotalParkingLot(int parkingLotIdentificationNumber, ParkingLot parkingLot ) {
        int tmpMCount =0, tmpDCount =0, listIndex;
        if(parkingLot instanceof MechanicalParkingLot) {
            for (ParkingLot p : this.totalParkingLotList) {
                if (p instanceof MechanicalParkingLot) {
                    if(tmpMCount == parkingLotIdentificationNumber){
                        listIndex = tmpMCount;
                        MechanicalParkingLot mechanicalParkingLot = (MechanicalParkingLot) parkingLot;
                        this.totalParkingLotList.set(listIndex, mechanicalParkingLot);
                        break;
                    }
                }
            }
        }else if (parkingLot instanceof DriveInParkingLot) {
            for(ParkingLot p : this.totalParkingLotList) {
                if( p instanceof DriveInParkingLot) {
                    if(tmpDCount == parkingLotIdentificationNumber){
                        listIndex = tmpDCount;
                        DriveInParkingLot driveInParkingLot = (DriveInParkingLot) parkingLot;
                        this.totalParkingLotList.set(listIndex, driveInParkingLot);
                        break;
                    }
                }
            }
        }
        this.updateTotalParkingLot();
        return true;
    }

    public void removeParkingLot(int parkingLotIdentificationNumber, ParkingLot parkingLot ) {
        if(parkingLot instanceof MechanicalParkingLot) {
            for (int i = 0; i < this.totalParkingLotList.size(); i++) {
                if(totalParkingLotList.get(i) instanceof MechanicalParkingLot) {
                    if(((MechanicalParkingLot)totalParkingLotList.get(i)).getMechanicalParkingIdentificationNumber() ==parkingLotIdentificationNumber){
                        this.totalParkingLotList.remove(i);
                    }
                }
            }
        }else if(parkingLot instanceof DriveInParkingLot) {
            for (int i = 0; i < this.totalParkingLotList.size(); i++) {
                if(totalParkingLotList.get(i) instanceof DriveInParkingLot) {
                    if((((DriveInParkingLot) totalParkingLotList.get(i)).getDriveInParkingIdentificationNumber() == parkingLotIdentificationNumber)){
                        this.totalParkingLotList.remove(i);
                    }
                }
            }
        }
    }
    public int getMechanicalParkingLotCount() {
        return mechanicalParkingLotCount;
    }

    public void setMechanicalParkingLotCount(int mechanicalParkingLotCount) {
        this.mechanicalParkingLotCount = mechanicalParkingLotCount;
    }

    public int getDrivingParkingLotCount() {
        return drivingParkingLotCount;
    }

    public void setDrivingParkingLotCount(int drivingParkingLotCount) {
        this.drivingParkingLotCount = drivingParkingLotCount;
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

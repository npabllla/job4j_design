package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParkingLotTest {
    @Test
    public void whenAddTrue() {
        List<Car> cars = new ArrayList<>();
        List<ParkingSpace> spaces = new ArrayList<>();
        Car motorCar = new Motorcar();
        Car truck = new Truck();
        cars.add(motorCar);
        cars.add(truck);
        ParkingSpace motorCarSpaceFirst = new MotorcarSpace(1, 1);
        ParkingSpace motorCarSpaceSecond = new MotorcarSpace(1, 2);
        ParkingSpace motorCarSpaceThird = new MotorcarSpace(1, 3);
        ParkingSpace truckCarSpaceFirst = new TruckSpace(2, 4);
        ParkingSpace truckCarSpaceSecond = new TruckSpace(2, 5);
        spaces.add(motorCarSpaceFirst);
        spaces.add(motorCarSpaceSecond);
        spaces.add(motorCarSpaceThird);
        spaces.add(truckCarSpaceFirst);
        spaces.add(truckCarSpaceSecond);
        ParkingLot parkingLot = new ParkingLot(cars, spaces);
        Car testMotorCar = new Motorcar();
        assertThat(parkingLot.addCar(testMotorCar), is(true));
    }

    @Test
    public void whenAddFalse() {
        List<Car> cars = new ArrayList<>();
        List<ParkingSpace> spaces = new ArrayList<>();
        Car motorCar = new Motorcar();
        Car truck = new Truck();
        cars.add(motorCar);
        cars.add(truck);
        ParkingSpace motorCarSpaceFirst = new MotorcarSpace(1, 1);
        ParkingSpace truckCarSpaceSecond = new TruckSpace(2, 2);
        spaces.add(motorCarSpaceFirst);
        spaces.add(truckCarSpaceSecond);
        ParkingLot parkingLot = new ParkingLot(cars, spaces);
        Car testMotorCar = new Motorcar();
        assertThat(parkingLot.addCar(testMotorCar), is(false));
    }

    @Test
    public void whenDeleteTrue() {
        List<Car> cars = new ArrayList<>();
        List<ParkingSpace> spaces = new ArrayList<>();
        Car motorCar = new Motorcar();
        Car truck = new Truck();
        cars.add(motorCar);
        cars.add(truck);
        ParkingSpace motorCarSpaceFirst = new MotorcarSpace(1, 1);
        ParkingSpace motorCarSpaceSecond = new MotorcarSpace(1, 2);
        ParkingSpace motorCarSpaceThird = new MotorcarSpace(1, 3);
        ParkingSpace truckCarSpaceFirst = new TruckSpace(2, 4);
        ParkingSpace truckCarSpaceSecond = new TruckSpace(2, 5);
        spaces.add(motorCarSpaceFirst);
        spaces.add(motorCarSpaceSecond);
        spaces.add(motorCarSpaceThird);
        spaces.add(truckCarSpaceFirst);
        spaces.add(truckCarSpaceSecond);
        ParkingLot parkingLot = new ParkingLot(cars, spaces);
        Car testMotorCar = new Motorcar();
        parkingLot.addCar(testMotorCar);
        assertThat(parkingLot.deleteCar(testMotorCar), is(true));
    }

    @Test
    public void whenDeleteFalse() {
        List<Car> cars = new ArrayList<>();
        List<ParkingSpace> spaces = new ArrayList<>();
        Car motorCar = new Motorcar();
        Car truck = new Truck();
        ParkingLot parkingLot = new ParkingLot(cars, spaces);
        Car testMotorCar = new Motorcar();
        assertThat(parkingLot.deleteCar(testMotorCar), is(false));
    }
}
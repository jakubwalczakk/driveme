package pl.jakub.walczak.driveme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.gastype.GasType;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

@SpringBootApplication
public class DrivemeApplication {

    public static void main(String[] args) {

        SpringApplication.run(DrivemeApplication.class, args);
    }
}

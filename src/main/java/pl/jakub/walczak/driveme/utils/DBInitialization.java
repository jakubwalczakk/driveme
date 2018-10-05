package pl.jakub.walczak.driveme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;
import pl.jakub.walczak.driveme.repos.course.CourseRepository;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Component
public class DBInitialization {

    private final static String DEFAULT_PASSWORD = "password";
    private final static Random random = new Random();

    private Generator generator;

    private DrivingCityRepository drivingCityRepository;
    private CarRepository carRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private InstructorRepository instructorRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    private Set<DrivingCity> drivingCities;
    private Set<Car> cars;
    private Set<Address> addresses;
    private Set<Instructor> instructors;
    private Set<Student> students;
    private Set<Course> courses;

    @Autowired
    public DBInitialization(Generator generator, DrivingCityRepository drivingCityRepository, CarRepository carRepository,
                            AddressRepository addressRepository, UserRepository userRepository,
                            InstructorRepository instructorRepository, StudentRepository studentRepository,
                            CourseRepository courseRepository) {
        this.generator = generator;

        this.drivingCityRepository = drivingCityRepository;
        this.carRepository = carRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;

        this.drivingCities = new HashSet<>();
        this.cars = new HashSet<>();
        this.addresses = new HashSet<>();
        this.instructors = new HashSet<>();
        this.students = new HashSet<>();
        this.courses = new HashSet<>();
    }

    @PostConstruct
    public void initialize() {
        initializeDrivingCities();
        initializeCars();
        initializeAddresses();
        initializeUsers();
        initializeCourses();
    }

    private void initializeDrivingCities() {

        DrivingCity katowice = DrivingCity.builder().name("Katowice").build();
        drivingCities.add(katowice);
        DrivingCity czwa = DrivingCity.builder().name("Częstochowa").build();
        drivingCities.add(czwa);
        DrivingCity sosnowiec = DrivingCity.builder().name("Sosnowiec").build();
        drivingCities.add(sosnowiec);
        DrivingCity gliwice = DrivingCity.builder().name("Gliwice").build();
        drivingCities.add(gliwice);
        DrivingCity zabrze = DrivingCity.builder().name("Zabrze").build();
        drivingCities.add(zabrze);
        DrivingCity bielsko = DrivingCity.builder().name("Bielsko-Biała").build();
        drivingCities.add(bielsko);
        DrivingCity bytom = DrivingCity.builder().name("Bytom").build();
        drivingCities.add(bytom);
        DrivingCity ruda = DrivingCity.builder().name("Ruda Śląska").build();
        drivingCities.add(ruda);
        DrivingCity rybnik = DrivingCity.builder().name("Rybnik").build();
        drivingCities.add(rybnik);
        DrivingCity tychy = DrivingCity.builder().name("Tychy").build();
        drivingCities.add(tychy);

        drivingCityRepository.saveAll(drivingCities);
    }

    private void initializeCars() {
        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(punto);
        Car punto2 = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(punto2);
        Car punto3 = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(punto3);
        Car micra = Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(micra);
        Car micra2 = Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(micra2);
        Car colt = Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(colt);
        Car colt2 = Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(colt2);
        Car yaris = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris);
        Car yaris2 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris2);
        Car yaris3 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris3);
        Car yaris4 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris4);
        Car yaris5 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris5);
        Car yaris6 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(yaris6);
        Car corsa = Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(corsa);
        Car corsa2 = Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(corsa2);
        Car clio = Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(clio);
        Car clio2 = Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(clio2);
        Car hyundai = Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(hyundai);
        Car hyundai2 = Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(hyundai2);
        Car rio = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(rio);
        Car rio2 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(rio2);
        Car rio3 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(rio3);
        Car rio4 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(rio4);
        Car rio5 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).build();
        cars.add(rio5);

        carRepository.saveAll(cars);
    }

    private void initializeAddresses() {

        Address address = Address.builder().city("Gliwice").street("Kujawska").zipCode("44-100").houseNumber("142").build();
        addresses.add(address);

        Address address2 = Address.builder().city("Katowice").street("Szewska").zipCode("40-320").houseNumber("11").build();
        addresses.add(address2);

        Address address3 = Address.builder().city("Bytom").street("Radowska").zipCode("41-600").houseNumber("15").build();
        addresses.add(address3);

        Address address4 = Address.builder().city("Zabrze").street("Gliwicka").zipCode("41-800").houseNumber("219").build();
        addresses.add(address4);

        Address address5 = Address.builder().city("Rybnik").street("Miejska").zipCode("44-200").houseNumber("2").build();
        addresses.add(address5);

        Address address6 = Address.builder().city("Chorzów").street("Krzywa").zipCode("40-100").houseNumber("12").build();
        addresses.add(address6);

        Address address7 = Address.builder().city("Sosnowiec").street("Środuli").zipCode("40-200").houseNumber("148").build();
        addresses.add(address7);

        Address address8 = Address.builder().city("Gliwice").street("Chorzowska").zipCode("44-100").houseNumber("200").build();
        addresses.add(address8);

        Address address9 = Address.builder().city("Zabrze").street("Wolności").zipCode("41-800").houseNumber("49").build();
        addresses.add(address9);

        Address address10 = Address.builder().city("Pszczyna").street("Wiejska").zipCode("43-200").houseNumber("73").build();
        addresses.add(address10);

        addressRepository.saveAll(addresses);
    }

    private void initializeUsers() {
        initializeAdministrators();
        initializeInstructors();
        initializeStudents();
    }

    //2
    private void initializeAdministrators() {

        User admin1 = User.builder().name("Jadwiga").surname("Bąk").email("jadwiga.bak@driveme.pl").password(DEFAULT_PASSWORD)
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.ADMINISTRATOR).build();
        userRepository.save(admin1);

        User admin2 = User.builder().name("Monika").surname("Krajewska").email("monika.krajewska@driveme.pl").password(DEFAULT_PASSWORD)
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.ADMINISTRATOR).build();
        userRepository.save(admin2);
    }

    //5
    private void initializeInstructors() {

        Instructor instructor1 = Instructor.builder().name("Jerzy").surname("Kowalski").email("jerzy.kowalski@driveme.pl")
                .availableHours(30).takenHours(0).password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).build();
        instructors.add(instructor1);

        Instructor instructor2 = Instructor.builder().name("Edward").surname("Majewski").email("edward.majewski@driveme.pl")
                .availableHours(40).takenHours(0).password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).build();
        instructors.add(instructor2);

        Instructor instructor3 = Instructor.builder().name("Tomasz").surname("Majewski").email("tomasz.majewski@driveme.pl")
                .availableHours(20).takenHours(0).password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).build();
        instructors.add(instructor3);

        Instructor instructor4 = Instructor.builder().name("Karol").surname("Gaj").email("karol.gaj@driveme.pl")
                .availableHours(30).takenHours(0).password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).build();
        instructors.add(instructor4);

        Instructor instructor5 = Instructor.builder().name("Bartosz").surname("Bielski").email("bartosz.bielski@driveme.pl")
                .availableHours(40).takenHours(0).password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).build();
        instructors.add(instructor5);

        instructorRepository.saveAll(instructors);
    }

    //10
    private void initializeStudents() {

        Student student1 = Student.builder().name("Jakub").surname("Walczak").email("jakub.walczak@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student1);

        Student student2 = Student.builder().name("Patrycja").surname("Dąb").email("patrycja.dab@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student2);

        Student student3 = Student.builder().name("Dawid").surname("Pobierny").email("dawid.pobierny@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student3);

        Student student4 = Student.builder().name("Magda").surname("Król").email("magda.krol@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student4);

        Student student5 = Student.builder().name("Adam").surname("Mamrot").email("adam.mamrot@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student5);

        Student student6 = Student.builder().name("Marcel").surname("Zelek").email("marcel.zelek@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student6);

        Student student7 = Student.builder().name("Agnieszka").surname("Chmura").email("agnieszka.chmura@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student7);

        Student student8 = Student.builder().name("Natalia").surname("Szewczyk").email("natalia.szewczyk@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student8);

        Student student9 = Student.builder().name("Bartłomiej").surname("Kawka").email("bartlomiej.kawka@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student9);

        Student student10 = Student.builder().name("Karolina").surname("Słoik").email("karolina.sloik@driveme.pl")
                .password(DEFAULT_PASSWORD).phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addresses.iterator().next()).registrationDate(new Date()).build();
        students.add(student10);

        studentRepository.saveAll(students);
    }

    private void initializeCourses() {

        for (Student student : students) {

            Set<TheoreticalExam> theoreticalExams = initializeTheoreticalExams();

            PracticalExam practicalExam = initializePracticalExam();

            LocalDate startDate = LocalDate.now().minusDays(random.nextInt(60));
            //TODO
            //ADJUST TAKEN HOURS BY START DATE
            int takenDrivingHours = 15;

            Course course = Course.builder()
                    //.student(student)
                    .takenDrivingHours(takenDrivingHours)
                    .startDate(startDate)
                    .practicalExam(practicalExam)
                    .theoreticalExams(theoreticalExams)
                    .build();
            student.setCourse(course);

            courses.add(course);
            studentRepository.save(student);
        }
        //courseRepository.saveAll(courses);
    }

    private PracticalExam initializePracticalExam() {

        List<Instructor> instructorList = new ArrayList<>(instructors);
        List<Car> carList = new ArrayList<>(cars);

        return PracticalExam.builder()
                .car(carList.get(random.nextInt(carList.size())))
                .instructor(instructorList.get(random.nextInt(instructorList.size())))
                .dateOfExam(new Date())
                .build();
    }

    private Set<TheoreticalExam> initializeTheoreticalExams() {
        Set<TheoreticalExam> theoreticalExams = new HashSet<>();
        boolean status = false;
        while (status == false) {
            final int scoredPoints = 60 + random.nextInt(15);
            if (scoredPoints >= 68) {
                status = true;
            }
            TheoreticalExam theoreticalExam = TheoreticalExam.builder()
                    .scoredPoints(scoredPoints)
                    .result(scoredPoints * 1.0 / TheoreticalExam.MAXIMUM_POINTS_AMOUNT)
                    .passed(status)
                    .dateOfExam(new Date())
                    .build();
            theoreticalExams.add(theoreticalExam);
        }

        return theoreticalExams;
    }
}

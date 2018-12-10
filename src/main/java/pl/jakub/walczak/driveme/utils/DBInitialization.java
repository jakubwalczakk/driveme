package pl.jakub.walczak.driveme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.enums.*;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.address.AddressRepository;
import pl.jakub.walczak.driveme.repos.car.CarRepository;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;
import pl.jakub.walczak.driveme.repos.course.CourseRepository;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;
import pl.jakub.walczak.driveme.repos.user.StudentRepository;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class DBInitialization {

    private final static String DEFAULT_PASSWORD = "password";
    private final static Random RANDOM = new Random();
    private final static Long ONE_DAY_IN_SECONDS = 24 * 60 * 60L;
    private final static Integer HALF_HOUR_IN_SECONDS = 30 * 60;
    private final static ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private final static String LOREM_IPSUM = "Lorem ipsum dolor sit amet, ad nam debet copiosae, eu omnesque scriptorem mel. " +
            "Elit persius apeirian duo ea, iisque minimum eam ea. Est ad iudico persecuti, ea usu aliquip phaedrum. " +
            "Ex consul voluptaria eum, mel esse natum altera te, ne vide appellantur ius.";

    private Generator generator;
    private ImageUploader imageUploader;

    private DrivingCityRepository drivingCityRepository;
    private CarRepository carRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private InstructorRepository instructorRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private DrivingRepository drivingRepository;
    private ReservationRepository reservationRepository;
    private PasswordEncoder passwordEncoder;

    private List<DrivingCity> drivingCities;
    private List<Car> cars;
    private List<Address> addresses;
    private List<Instructor> instructors;
    private List<Student> students;
    private List<Course> courses;

    @Autowired
    public DBInitialization(Generator generator, ImageUploader imageUploader, DrivingCityRepository drivingCityRepository, CarRepository carRepository,
                            AddressRepository addressRepository, InstructorRepository instructorRepository,
                            UserRepository userRepository, CourseRepository courseRepository, StudentRepository studentRepository,
                            DrivingRepository drivingRepository, ReservationRepository reservationRepository, PasswordEncoder passwordEncoder) {
        this.generator = generator;
        this.imageUploader = imageUploader;

        this.drivingCityRepository = drivingCityRepository;
        this.carRepository = carRepository;
        this.addressRepository = addressRepository;
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.drivingRepository = drivingRepository;
        this.reservationRepository = reservationRepository;
        this.passwordEncoder = passwordEncoder;

        this.drivingCities = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    @PostConstruct
    public void initialize() {
        initializeDrivingCities();
        initializeCars();
        initializeAddresses();
        initializeUsers();
        initializeCourses();
    }

    //10
    private void initializeDrivingCities() {

        DrivingCity katowice = DrivingCity.builder().name("Katowice").image(imageUploader.uploadFile("signKatowice.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(katowice);
        DrivingCity czwa = DrivingCity.builder().name("Częstochowa").image(imageUploader.uploadFile("signCzestochowa.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(czwa);
        DrivingCity sosnowiec = DrivingCity.builder().name("Sosnowiec").image(imageUploader.uploadFile("signSosnowiec.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(sosnowiec);
        DrivingCity gliwice = DrivingCity.builder().name("Gliwice").image(imageUploader.uploadFile("signGliwice.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(gliwice);
        DrivingCity zabrze = DrivingCity.builder().name("Zabrze").image(imageUploader.uploadFile("signZabrze.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(zabrze);
        DrivingCity bielsko = DrivingCity.builder().name("Bielsko-Biała").image(imageUploader.uploadFile("signBielsko.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(bielsko);
        DrivingCity bytom = DrivingCity.builder().name("Bytom").image(imageUploader.uploadFile("signBytom.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(bytom);
        DrivingCity ruda = DrivingCity.builder().name("Ruda Śląska").image(imageUploader.uploadFile("signRuda.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(ruda);
        DrivingCity rybnik = DrivingCity.builder().name("Rybnik").image(imageUploader.uploadFile("signRybnik.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(rybnik);
        DrivingCity tychy = DrivingCity.builder().name("Tychy").image(imageUploader.uploadFile("signTychy.png"))
                .description(LOREM_IPSUM).active(true).build();
        drivingCities.add(tychy);

        drivingCityRepository.saveAll(drivingCities);
    }

    //24
    private void initializeCars() {

        String grandePunto = "grande_punto.jpg";
        String nissanMicra = "nissan_micra.jpg";
        String mitsubishiColt = "mitsubishi_colt.jpg";
        String toyotaYaris = "toyota_yaris.jpg";
        String opelCorsa = "opel_corsa.jpg";
        String renaultClio = "renault_clio.jpg";
        String hyundaiI20 = "hyundai_i20.jpg";
        String kiaRio = "kia_rio.jpg";


        Car punto = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(grandePunto)).build();
        cars.add(punto);
        Car punto2 = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(grandePunto)).build();
        cars.add(punto2);
        Car punto3 = Car.builder().brand(CarBrand.FIAT).model("Grande Punto").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(grandePunto)).build();
        cars.add(punto3);
        Car micra = Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(nissanMicra)).build();
        cars.add(micra);
        Car micra2 = Car.builder().brand(CarBrand.NISSAN).model("Micra").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(nissanMicra)).build();
        cars.add(micra2);
        Car colt = Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(mitsubishiColt)).build();
        cars.add(colt);
        Car colt2 = Car.builder().brand(CarBrand.MITSHUBISHI).model("Colt").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(mitsubishiColt)).build();
        cars.add(colt2);
        Car yaris = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris);
        Car yaris2 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris2);
        Car yaris3 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris3);
        Car yaris4 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris4);
        Car yaris5 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris5);
        Car yaris6 = Car.builder().brand(CarBrand.TOYOTA).model("Yaris").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(toyotaYaris)).build();
        cars.add(yaris6);
        Car corsa = Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(opelCorsa)).build();
        cars.add(corsa);
        Car corsa2 = Car.builder().brand(CarBrand.OPEL).model("Corsa").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(opelCorsa)).build();
        cars.add(corsa2);
        Car clio = Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(renaultClio)).build();
        cars.add(clio);
        Car clio2 = Car.builder().brand(CarBrand.RENAULT).model("Clio").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(renaultClio)).build();
        cars.add(clio2);
        Car hyundai = Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(hyundaiI20)).build();
        cars.add(hyundai);
        Car hyundai2 = Car.builder().brand(CarBrand.HYUNDAI).model("i20").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(hyundaiI20)).build();
        cars.add(hyundai2);
        Car rio = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(kiaRio)).build();
        cars.add(rio);
        Car rio2 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.OIL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(kiaRio)).build();
        cars.add(rio2);
        Car rio3 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(kiaRio)).build();
        cars.add(rio3);
        Car rio4 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.PETROL)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(kiaRio)).build();
        cars.add(rio4);
        Car rio5 = Car.builder().brand(CarBrand.KIA).model("Rio").gasType(GasType.GAS)
                .licensePlate(generator.generateLicensePlate()).active(true)
                .carPhoto(imageUploader.uploadFile(kiaRio)).build();
        cars.add(rio5);

        carRepository.saveAll(cars);
    }

    private void initializeAddresses() {

        Address address = Address.builder().city("Gliwice").street("Kujawska").zipCode("44-100").houseNo("142").build();
        addresses.add(address);

        Address address2 = Address.builder().city("Katowice").street("Szewska").zipCode("40-320").houseNo("11").build();
        addresses.add(address2);

        Address address3 = Address.builder().city("Bytom").street("Radowska").zipCode("41-600").houseNo("15").build();
        addresses.add(address3);

        Address address4 = Address.builder().city("Zabrze").street("Gliwicka").zipCode("41-800").houseNo("219").build();
        addresses.add(address4);

        Address address5 = Address.builder().city("Rybnik").street("Miejska").zipCode("44-200").houseNo("2").build();
        addresses.add(address5);

        Address address6 = Address.builder().city("Chorzów").street("Krzywa").zipCode("40-100").houseNo("12").build();
        addresses.add(address6);

        Address address7 = Address.builder().city("Sosnowiec").street("Środuli").zipCode("40-200").houseNo("148").build();
        addresses.add(address7);

        Address address8 = Address.builder().city("Gliwice").street("Chorzowska").zipCode("44-100").houseNo("200").build();
        addresses.add(address8);

        Address address9 = Address.builder().city("Zabrze").street("Wolności").zipCode("41-800").houseNo("49").build();
        addresses.add(address9);

        Address address10 = Address.builder().city("Pszczyna").street("Wiejska").zipCode("43-200").houseNo("73").build();
        addresses.add(address10);
    }

    private void initializeUsers() {
        initializeAdministrators();
        initializeInstructors();
        initializeStudents();
    }

    //2
    private void initializeAdministrators() {

        User admin1 = User.builder().name("Jadwiga").surname("Bąk").email("jadwiga.bak@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.ADMINISTRATOR).active(true).build();
        userRepository.save(admin1);

        User admin2 = User.builder().name("Monika").surname("Krajewska").email("monika.krajewska@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.ADMINISTRATOR).active(true).build();
        userRepository.save(admin2);
    }

    //5
    private void initializeInstructors() {

        String instructorPhotoFileName = "instructor.jpg";

        Instructor instructor1 = Instructor.builder().name("Jerzy").surname("Kowalski").email("jerzy.kowalski@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).active(true).instructorPhoto(imageUploader.uploadFile(instructorPhotoFileName))
                .build();
        instructors.add(instructor1);
        instructorRepository.save(instructor1);

        Instructor instructor2 = Instructor.builder().name("Edward").surname("Majewski").email("edward.majewski@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).active(true).instructorPhoto(imageUploader.uploadFile(instructorPhotoFileName))
                .build();
        instructors.add(instructor2);
        instructorRepository.save(instructor2);

        Instructor instructor3 = Instructor.builder().name("Tomasz").surname("Majewski").email("tomasz.majewski@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).active(true).instructorPhoto(imageUploader.uploadFile(instructorPhotoFileName))
                .build();
        instructors.add(instructor3);
        instructorRepository.save(instructor3);

        Instructor instructor4 = Instructor.builder().name("Karol").surname("Gaj").email("karol.gaj@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).active(true).instructorPhoto(imageUploader.uploadFile(instructorPhotoFileName))
                .build();
        instructors.add(instructor4);
        instructorRepository.save(instructor4);

        Instructor instructor5 = Instructor.builder().name("Bartosz").surname("Bielski").email("bartosz.bielski@driveme.pl")
                .password(passwordEncoder.encode(DEFAULT_PASSWORD)).phoneNumber(generator.generatePhoneNumber())
                .userRole(UserRole.INSTRUCTOR).active(true).instructorPhoto(imageUploader.uploadFile(instructorPhotoFileName))
                .build();
        instructors.add(instructor5);
        instructorRepository.save(instructor5);
    }

    //10
    private void initializeStudents() {

        List<Address> addressList = new ArrayList<>(addresses);

        Student student1 = Student.builder()
                .name("Jakub").surname("Walczak").email("jakub.walczak@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(0)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student1);

        Student student2 = Student.builder()
                .name("Patrycja").surname("Dąb").email("patrycja.dab@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(1)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student2);

        Student student3 = Student.builder()
                .name("Dawid").surname("Pobierny").email("dawid.pobierny@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(2)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student3);

        Student student4 = Student.builder()
                .name("Magda").surname("Król").email("magda.krol@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(3)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student4);

        Student student5 = Student.builder()
                .name("Adam").surname("Mamrot").email("adam.mamrot@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(4)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student5);

        Student student6 = Student.builder()
                .name("Marcel").surname("Zelek").email("marcel.zelek@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(5)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student6);

        Student student7 = Student.builder()
                .name("Agnieszka").surname("Chmura").email("agnieszka.chmura@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(6)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student7);

        Student student8 = Student.builder()
                .name("Natalia").surname("Szewczyk").email("natalia.szewczyk@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(7)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student8);

        Student student9 = Student.builder()
                .name("Bartłomiej").surname("Kawka").email("bartlomiej.kawka@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(8)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student9);

        Student student10 = Student.builder()
                .name("Karolina").surname("Słoik").email("karolina.sloik@driveme.pl").password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).address(addressList.get(9)).active(true)
                .registrationDate(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * RANDOM.nextInt(50)))
                .build();
        students.add(student10);

        studentRepository.saveAll(students);
    }

    private void initializeCourses() {

        for (Student student : students) {

            Instant registrationDate = student.getRegistrationDate();
            Instant courseStartDate = registrationDate.plusSeconds(RANDOM.nextInt(8) * ONE_DAY_IN_SECONDS);
            LocalDate startDate = courseStartDate.atZone(DEFAULT_ZONE_ID).toLocalDate();

            int takenDrivingHours;
            if (courseStartDate.isAfter(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * 7))) {
                takenDrivingHours = RANDOM.nextInt(12);
            } else if (courseStartDate.isAfter(Instant.now().minusSeconds(ONE_DAY_IN_SECONDS * 14))) {
                takenDrivingHours = RANDOM.nextInt(12) + 10;
            } else {
                takenDrivingHours = RANDOM.nextInt(16) + 15;
            }

            Course course = Course.builder()
                    .takenDrivingHours(takenDrivingHours)
                    .startDate(startDate)
                    .currentPayment(0.0)
                    .status(CourseStatus.IN_PROGRESS)
                    .build();
            student.setCourse(course);
            initializePayments(student);
            initializeTheoreticalExams(student);
            initializePracticalExam(student);
            initializeReservations(student);
            initializeDrivings(student);

            courses.add(course);
            studentRepository.save(student);
        }
    }

    private void initializePayments(Student student) {
        Set<Payment> payments = new HashSet<>();
        Course course = student.getCourse();
        final Double coursePrice = 1500.0;
        Double sumOfAmounts = 0.0;
        do {
            Instant paymentDate = Instant.now().minusSeconds((1 + RANDOM.nextInt(30)) * ONE_DAY_IN_SECONDS);
            Double amount = (RANDOM.nextInt(15) + 1) * 100.0;
            if ((sumOfAmounts + amount) > coursePrice) {
                amount = coursePrice - sumOfAmounts;
            }
            Payment payment = Payment.builder().date(paymentDate)/*.student(student)*/.amount(amount).build();
            payments.add(payment);
            sumOfAmounts += amount;
        } while (!sumOfAmounts.equals(coursePrice));
        course.setCurrentPayment(sumOfAmounts);
        course.setPayments(payments);
    }

    private void initializePracticalExam(Student student) {
        Course course = student.getCourse();
        Instructor instructor = instructors.get(RANDOM.nextInt(instructors.size()));

        boolean status = RANDOM.nextInt(100) % 2 == 0 ? true : false;
        PracticalExam practicalExam = PracticalExam.builder()
                .active(true)
                .student(student)
                .car(cars.get(RANDOM.nextInt(cars.size())))
                .instructor(instructor)
                .dateOfExam(Instant.now().minusSeconds(RANDOM.nextInt(4) * ONE_DAY_IN_SECONDS))
                .passed(status)
                .build();
        course.setPracticalExam(practicalExam);
    }

    private void initializeTheoreticalExams(Student student) {
        Course course = student.getCourse();
        Set<TheoreticalExam> theoreticalExams = new HashSet<>();
        boolean status = false;
        while (status == false) {
            final int scoredPoints = 60 + RANDOM.nextInt(15);
            if (scoredPoints >= 68) {
                status = true;
            }
            TheoreticalExam theoreticalExam = TheoreticalExam.builder()
                    .active(true)
                    .student(student)
                    .scoredPoints(scoredPoints)
                    .result(scoredPoints * 1.0 / TheoreticalExam.MAXIMUM_POINTS_AMOUNT)
                    .passed(status)
                    .dateOfExam(Instant.now().minusSeconds(RANDOM.nextInt(6) * ONE_DAY_IN_SECONDS))
                    .build();
            theoreticalExams.add(theoreticalExam);
        }
        course.setTheoreticalExams(theoreticalExams);
    }

    private void initializeReservations(Student student) {
        Course course = student.getCourse();
        HashSet<Reservation> reservations = new HashSet<>();

        for (int i = 0; i < 10; i++) {

            Instructor instructor = instructors.get(RANDOM.nextInt(instructors.size()));
            Instant startDate = Instant.now().plusSeconds(RANDOM.nextInt(120) * 60 * 60);
            Instant finishDate = startDate.plusSeconds((RANDOM.nextInt(6) + 2) * HALF_HOUR_IN_SECONDS);
            Reservation reservation =
                    Reservation.builder()
                            .student(student)
                            .instructor(instructor)
                            .car(cars.get(RANDOM.nextInt(cars.size())))
                            .startDate(startDate)
                            .finishDate(finishDate)
                            .drivingCity(drivingCities.get(RANDOM.nextInt(drivingCities.size())))
                            .status(true)
                            .build();
            reservations.add(reservation);
        }
        course.setReservations(reservations);
    }

    private void initializeDrivings(Student student) {
        Course course = student.getCourse();
        HashSet<Driving> drivings = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Rating rating;
            if (i < 3) {
                rating = Rating.AVERAGE;
            } else if (i >= 3 && i < 8) {
                rating = Rating.GREAT;
            } else {
                rating = Rating.DISAPPOINTING;
            }

            final String drivingTitle = "DEFAULT DRIVINGS TITLE";
            final String drivingComment = "DEFAULT DRIVINGS COMMENT";

            Instructor instructor = instructors.get(RANDOM.nextInt(instructors.size()));
            Instant startDate = Instant.now().plusSeconds(RANDOM.nextInt(120) * 60 * 60);
            Instant finishDate = startDate.plusSeconds((RANDOM.nextInt(6) + 2) * HALF_HOUR_IN_SECONDS);
            Driving driving =
                    Driving.builder()
                            .student(student)
                            .instructor(instructor)
                            .car(cars.get(RANDOM.nextInt(cars.size())))
                            .startDate(startDate)
                            .finishDate(finishDate)
                            .drivingCity(drivingCities.get(RANDOM.nextInt(drivingCities.size())))
                            .title(drivingTitle)
                            .comment(drivingComment)
                            .rating(rating).build();
            drivings.add(driving);
        }
        course.setDrivings(drivings);
    }
}

package com.booking.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
    public static void createReservation(List<Reservation> reservationList, List<Person> personList, List<Service> serviceList) {
        PrintService.showAllCustomer(personList);
        Scanner scanner = new Scanner(System.in);
    
        String custId = scanner.nextLine();
        Person custInstance = findCustID(personList, custId);
    
        if (custInstance == null) {
            System.out.println("Customer Id tidak ditemukan.");
            return;  
        }
    
        PrintService.showAllEmployee(personList);
        String empId = scanner.nextLine();
        Person empInstance = findEmpID(personList, empId);
    
        if (empInstance == null) {
            System.out.println("Employee Id tidak ditemukan.");
            return;  
        }
    
        PrintService.showServices(serviceList);
        String servID = scanner.nextLine();
        Service servInstance = findServID(serviceList, servID);
    
        if (servInstance == null) {
            System.out.println("Service Id tidak ditemukan.");
            return;  
        }

        Reservation reservation = new Reservation((Customer)custInstance, (Employee)empInstance, Arrays.asList(servInstance));
        
        reservation.calculateReservationPrice();
        reservationList.add(reservation);
    
    
        System.out.println("Reservation created successfully.");
    }
    
    private static Service findServID(List<Service> serviceList, String servID) {
        return serviceList.stream()
        .filter(serv -> serv.getServiceId().equalsIgnoreCase(servID))
        .findFirst()
        .orElse(null);
    }

    private static Person findEmpID(List<Person> personList, String empId) {
        return personList.stream()
        .filter(emp -> emp.getId().equalsIgnoreCase(empId))
        .findFirst()
        .orElse(null);
    }

    private static Person findCustID(List<Person> personList, String custId) {
        return personList.stream()
        .filter(cust ->cust.getId().equalsIgnoreCase(custId))
        .findFirst()
        .orElse(null);
    }

    public static void editReservationWorkstage(){
        
    }

    public static double calcualteKeuntungan(List<Reservation> reservationList) {
        double totalKeuntungan = 0;
        
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equals(Reservation.Workstage.FINISH.name())) {
                totalKeuntungan += reservation.getReservationPrice();
            }
        }
        return totalKeuntungan;
    }


    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}

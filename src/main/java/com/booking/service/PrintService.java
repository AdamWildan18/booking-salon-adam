package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList, List<Person> personList, List<Service> serviceList){
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            // if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            // }
        }
        System.out.println("================================================================================================================");
    }

    public static void showAllCustomer(List<Person> personList) {
        System.out.println("================================================================================================================");
        System.out.printf("| %9s | %-40s | %-25s | %-12s | %-10s |\n", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("================================================================================================================");
    
        for (Person dataPerson : personList) {
            if (dataPerson instanceof Customer) {
                Customer customer = (Customer) dataPerson;
                System.out.printf("| %9s | %-40s | %-25s | %-12s | %-10s |\n", 
                                  customer.getId(), 
                                  customer.getName(), 
                                  customer.getAddress(),
                                  customer.getMember().getMembershipName(),
                                  customer.getWallet()
                );
            }
        }
        System.out.println("================================================================================================================");
    }
    


    public static void showAllEmployee(List<Person> personList){
        System.out.println("===================================================================================================");
        System.out.printf("| %9s | %-40s | %-25s | %-12s |\n", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("===================================================================================================");
    
        for (Person dataPerson : personList) {
            if (dataPerson instanceof Employee) {
                Employee employee = (Employee) dataPerson;
                System.out.printf("| %9s | %-40s | %-25s | %-12s |\n", 
                                employee.getId(), 
                                employee.getName(), 
                                employee.getAddress(),
                                employee.getExperience()
                );
            }
        }
        System.out.println("===================================================================================================");
    }

    public static void showHistoryReservation(List<Reservation> reservationList, List<Person> personList, List<Service> serviceList){
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-13s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out.println("+================================================================================+");
        for (Reservation reservation : reservationList) {
            // if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-12s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            // }
        }
        double totalKeuntungan = ReservationService.calcualteKeuntungan(reservationList);

        System.out.printf("| %-47s |", "Total Keuntungan");
        System.out.printf("%-29s |\n", totalKeuntungan);
        System.out.println("==================================================================================");
    }

    public static void showServices(List<Service> serviceList) {
        System.out.println("===================================================================================================");
        System.out.printf("| %9s | %-40s | %-25s | %-12s |\n", "No", "ID", "Nama", "Harga");
        System.out.println("===================================================================================================");
        
        int num = 1;
        for(Service service : serviceList){
            System.out.printf("| %9s | %-40s | %-25s | %-12s |\n",
                            num,
                            service.getServiceId(),
                            service.getServiceName(),
                            service.getPrice(),
                            num++
                            );
        }
    }
}

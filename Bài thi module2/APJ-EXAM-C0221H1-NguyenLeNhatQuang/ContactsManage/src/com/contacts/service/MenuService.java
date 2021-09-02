package com.contacts.service;

import java.io.IOException;
import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);
    ContactService contactService = new ContactService();

    public void showMenu(){
        System.out.println("------CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ------");
        System.out.println("Chọn chức năng theo số để tiếp tục: ");
        System.out.println("\t 1. Xem danh sách");
        System.out.println("\t 2. Thêm mới");
        System.out.println("\t 3. Cập nhật");
        System.out.println("\t 4. Xóa");
        System.out.println("\t 5. Tìm kiếm");
        System.out.println("\t 6. Đọc từ file");
        System.out.println("\t 7. Ghi vào file");
        System.out.println("\t 8. Thoát");
        System.out.println("Chọn chức năng: ");
    }

    public void continueConfirm() {
        System.out.println("Nhấn Y để tiếp tục, nhấn N để thoát.");
        String choice;
        while (true) {
            choice = sc.nextLine();
            switch (choice){
                case "Y": {
                    performContact();
                    break;
                }
                case "N": {
                    System.out.println("Thoát.");
                    System.exit(0);
                }
                default:
                    System.out.println("Bạn nhập sai!! ");
                    continueConfirm();
            }
        }
    }

    public void searchContact() {
        System.out.println("Nhấn P để tìm kiếm theo SĐT, nhấn N để tìm kiếm theo tên.");
        String choice;
        while (true) {
            choice = sc.nextLine();
            switch (choice){
                case "P": {
                    System.out.println("Nhập số điện thoại: ");
                    contactService.searchContactPhoneNumber(sc.nextLine());
                    continueConfirm();
                    break;
                }
                case "N": {
                    System.out.println("Nhập họ tên: ");
                    contactService.searchContactName(sc.nextLine());
                    continueConfirm();
                    break;
                }
                default:
                    System.out.println("Bạn nhập sai!! ");
                    continueConfirm();
            }
        }
    }

    public void performContact(){
        String choose;
        while (true){
            showMenu();
            choose = sc.nextLine();
            switch (choose){
                case "1":
                    contactService.printContactNonEmail();
                    continueConfirm();
                    break;
                case "2":
                    try {
                        contactService.addContact();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        contactService.updateContact();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try {
                        contactService.deleteContact();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    searchContact();
                    continueConfirm();
                    break;
                case "6":
                    contactService.printContact();
                    continueConfirm();
                    break;
                case "7":
                    try {
                        contactService.writeFile();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Yêu cầu nhập lại: ");
            }
        }
    }
}

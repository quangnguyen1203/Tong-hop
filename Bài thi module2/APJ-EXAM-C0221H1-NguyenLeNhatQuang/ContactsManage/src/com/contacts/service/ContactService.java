package com.contacts.service;

import com.contacts.dal.ContactDB;
import com.contacts.model.Contact;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactService {
    public static ContactDB contactDB = new ContactDB();
    public static Scanner sc = new Scanner(System.in);

    public void writeFile() throws IOException {
        contactDB.saveFile();
        System.out.println("Bạn đã ghi vào file thành công!!!");
    }

    public void loadFile() throws IOException {
        contactDB.readFile();
    }

    public Contact findExists(String string){
        for (int i = 0; i < contactDB.contactList.size(); i++) {
            if(contactDB.contactList.get(i).getPhoneNumber().equals(string) || contactDB.contactList.get(i).getEmail().equals(string)){
                return contactDB.contactList.get(i);
            }
        }
        return null;
    }

    public String inputPhoneNumber(){
        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại: ");
            phoneNumber = sc.nextLine();
            while (findExists(phoneNumber) != null) {
                System.out.println("Đã tồn tại số điện thoại. Vui lòng nhập lại");
                phoneNumber = sc.nextLine();
            }
        }while (!checkPhoneNumber(phoneNumber));
        return phoneNumber;
    }

    public boolean checkPhoneNumber(String phoneNumber){
        String regex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    public String inputGroupContact(){
        System.out.println("Nhập nhóm danh bạ: ");
        return toUpperCase(sc.nextLine());
    }

    public String inputName(){
        String name;
        do {
            System.out.println("Nhập họ tên: ");
            name = toUpperCase(sc.nextLine());
        }while (!checkName(name));
        return name;
    }

    public boolean checkName(String name){
        String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public String inputGender(){
        System.out.println("Nhập giới tính:");
        return toUpperCase(sc.nextLine());
    }

    public String inputAddress(){
        System.out.println("Nhập địa chỉ: ");
        return toUpperCase(sc.nextLine());
    }

    public String inputDOB(){
        String dob;
        do {
            System.out.println("Nhập ngày sinh: ");
            dob = toUpperCase(sc.nextLine());
        }while (!checkDOB(dob));
        return dob;
    }

    public boolean checkDOB(String dob){
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.find();
    }

    public String inputEmail(){
        String email;
        do {
            System.out.println("Nhập email: ");
            email = sc.nextLine();
            while (findExists(email) != null) {
                System.out.println("Đã tồn tại email. Vui lòng nhập lại");
                email = sc.nextLine();
            }
        }while (!checkEmail(email));
        return email;
    }

    public boolean checkEmail(String email){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@{1}\\w+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public void addContact() throws IOException {
        String phoneNumber = inputPhoneNumber();
        String groupContact = inputGroupContact();
        String name = inputName();
        String gender = inputGender();
        String address = inputAddress();
        String dob = inputDOB();
        String email = inputEmail();
        Contact contact = new Contact(phoneNumber,groupContact,name,gender,address,dob,email);
        contactDB.add(contact);
        System.out.println("Bạn đã thêm mới thành công!!!");
    }

    public void updateContact() throws IOException {
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = sc.nextLine();
        boolean check = false;
        for (int i = 0; i < contactDB.contactList.size() ; i++) {
            if (contactDB.contactList.get(i).getPhoneNumber().equals(phoneNumber)){
                displayFormat();
                contactDB.contactList.get(i).displayContactEmail();
                check = true;
                contactDB.contactList.get(i).setGroupContact(inputGroupContact());
                contactDB.contactList.get(i).setName(inputName());
                contactDB.contactList.get(i).setGender(inputGender());
                contactDB.contactList.get(i).setAddress(inputAddress());
                contactDB.contactList.get(i).setDob(inputDOB());
                contactDB.contactList.get(i).setEmail(inputEmail());
                System.out.println("Bạn đã cập nhật thành công!!!");
            }
        }
        if(!check){
            System.out.println("Không tìm được danh bạ với số điện thoại trên.");
        }
    }

    public void deleteContact() throws IOException {
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = sc.nextLine();
        Contact contact = null;
        int size = contactDB.contactList.size();
        for (int i = 0; i < size; i++) {
            if (contactDB.contactList.get(i).getPhoneNumber().equals(phoneNumber)) {
                contact = contactDB.contactList.get(i);
                break;
            }
        }
        if (contact != null) {
            contactDB.contactList.remove(contact);
            System.out.println("Bạn đã xóa thành công!!!");
        } else {
            System.out.println("Không tìm thấy số điện thoại cần xóa trong danh bạ.");
        }
    }

    public void searchExist(String string){
        boolean check = false;
        for (int i = 0; i < contactDB.contactList.size() ; i++) {
            if(contactDB.contactList.get(i).getPhoneNumber().equals(string)||contactDB.contactList.get(i).getName().equals(string)){
                check = true;
                displayFormat();
                contactDB.contactList.get(i).displayContactEmail();
            }
        }
        if(!check){
            System.out.println("Không tìm thấy trong danh bạ.");
        }
    }

    public void searchContactPhoneNumber(String phoneNumber){
        searchExist(phoneNumber);
    }

    public void searchContactName(String name){
        searchExist(name);
    }

    public String toUpperCase(String string){
        char[] charArray = string.toCharArray();
        boolean foundSpace = true;
        for(int i = 0; i < charArray.length; i++) {
            charArray[i] = Character.toLowerCase(charArray[i]);
            if(Character.isLetter(charArray[i])) {
                if(foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }
            else {
                foundSpace = true;
            }
        }
        String outputString = String.valueOf(charArray);
        return outputString;
    }

    public void printContactNonEmail(){
        if(contactDB.contactList.size()==0){
            System.out.println("Danh sách hiện tại còn trống.");
        } else {
            displayFormatNonEmail();
            for (int i = 0; i < contactDB.contactList.size() ; i++) {
                if(i==0 || i%5 != 0){
                    contactDB.contactList.get(i).displayContactNonEmail();
                } else {
                    System.out.print("Nhấn enter để tiếp tục hiển thị danh sách");
                    switch (sc.nextLine()){
                        case "":
                            displayFormatNonEmail();
                            contactDB.contactList.get(i).displayContactNonEmail();
                            break;
                        default:
                            System.out.println("Exit.");
                            return;
                    }
                }
            }
            System.out.println("Đã hiển thị toàn bộ danh bạ!!!");
        }
    }

    public void printContact(){
        if(contactDB.contactList.size()==0){
            System.out.println("Danh sách hiện tại còn trống.");
        } else {
            displayFormat();
            for (int i = 0; i < contactDB.contactList.size() ; i++) {
                contactDB.contactList.get(i).displayContactEmail();
            }
        }
    }

    public void displayFormat(){
        System.out.printf("|| %15s | %20s | %30s | %11s | %20s | %15s | %30s ||","Số điện thoại","Nhóm danh bạ","Họ tên","Giới tính","Địa chỉ","Ngày sinh","Email");
        System.out.println();
    }

    public void displayFormatNonEmail(){
        System.out.printf("|| %15s | %20s | %30s | %11s | %20s | %15s ||","Số điện thoại","Nhóm danh bạ","Họ tên","Giới tính","Địa chỉ","Ngày sinh");
        System.out.println();
    }
}
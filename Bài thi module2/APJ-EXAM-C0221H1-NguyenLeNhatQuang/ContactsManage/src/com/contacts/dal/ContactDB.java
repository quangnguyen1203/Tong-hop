package com.contacts.dal;

import com.contacts.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactDB {
    public static Scanner sc = new Scanner(System.in);
    public List<Contact> contactList = new ArrayList<>();
    public static final String FILE_CONTACT = "C:\\Bài thi module2\\APJ-EXAM-C0221H1-NguyenLeNhatQuang\\ContactsManage\\data\\contacts.csv";

    public void saveFile() throws IOException {
        File file = new File(FILE_CONTACT);
        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(FILE_CONTACT);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        for (int i = 0; i <contactList.size() ; i++) {
            bos.write(contactList.get(i).toStringCSV().getBytes());
        }
        bos.flush();
        bos.close();
        fos.close();
    }

    public void readFile() throws IOException {
        File file = new File(FILE_CONTACT);
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_CONTACT));
            String line;
            while ((line=br.readLine())!= null){
                String[] str = line.split(",");
                Contact contact = new Contact(str[0],str[1],str[2],str[3],str[4],str[5],str[6]);
                contactList.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public void add(Contact contact){
        contactList.add(contact);
    }
}

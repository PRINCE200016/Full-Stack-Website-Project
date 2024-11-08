package com.itrainu.Module;

import com.itrainu.Bean.TableBean;
import com.itrainu.Bean.UserBean;

public class UserModuletest {
    public static void main(String[] args) throws Exception {
        // Uncomment the methods to test them
        // testAdd();
        // testDelete();
        findByPk();
        // testUpdate();
        // testAuth();
    }

    private static void testAdd() throws Exception {
        TableBean bean = new TableBean();
        bean.setName("Kirti");
        bean.setSalary(9899);
        UserModule module = new UserModule();
        module.add(bean);
        System.out.println("Data added successfully!");
    }

    private static void testDelete() throws Exception {
        UserModule module = new UserModule();
        module.delete(23);
        System.out.println("Data deleted successfully!");
    }

    private static void findByPk() throws Exception {
        UserModule module = new UserModule();
        TableBean bean = module.findbypk(3);
        if (bean != null) {
            System.out.println("ID: " + bean.getId());
            System.out.println("Name: " + bean.getName());
            System.out.println("Salary: " + bean.getSalary());
        } else {
            System.out.println("ID not available!");
        }
    }

    private static void testUpdate() throws Exception {
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        UserBean bean = new UserBean();
        bean.setId(3);
        bean.setName("Shagun");
     //   bean.setSalary(9999);
        UserModule module = new UserModule();
        module.update(bean);
        System.out.println("Data updated successfully!");
    }

    private static void testAuth() throws Exception {
        // Implement authentication test logic here
    }
}

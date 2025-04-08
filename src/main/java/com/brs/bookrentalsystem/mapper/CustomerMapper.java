package com.brs.bookrentalsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.brs.bookrentalsystem.dto.Customer;

@Mapper
public interface CustomerMapper {

        @Select("SELECT * FROM customers")
        List<Customer> getAllCustomers();

        @Select("SELECT * FROM customers WHERE cust_id = #{custId}")
        Customer getCustomerById(int custId);

        @Insert("INSERT INTO customers (cust_fname, cust_mname, cust_lname, cust_nid_number, cust_phone, cust_email) "
                        + "VALUES (#{custFname}, #{custMname}, #{custLname}, #{custNidNumber}, #{custPhone}, #{custEmail})")
        @Options(useGeneratedKeys = true, keyProperty = "custId")
        void insertCustomer(Customer customer);

        @Update("UPDATE customers SET cust_fname = #{custFname}, cust_mname = #{custMname}, cust_lname = #{custLname}, "
                        + "cust_nid_number = #{custNidNumber}, cust_phone = #{custPhone}, cust_email = #{custEmail} "
                        + "WHERE cust_id = #{custId}")
        void updateCustomer(Customer customer);

        @Delete("DELETE FROM customers WHERE cust_id =#{custId}")
        void deleteCustomer(int custId);
}

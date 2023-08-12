package com.spring.capstone.DAO;

import com.spring.capstone.DTO.cfnumbersDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class cfnumbersDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/cju_capstone?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Cjucapstone23";

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private static final String SELECT_ALL_CF_NUMBERS = "SELECT cf_id, cf_number, cf_name, cf_area FROM cf_numbers";

    @PostConstruct
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<cfnumbersDTO> getAllCFNumbers() {
        List<cfnumbersDTO> cfNumbersList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(SELECT_ALL_CF_NUMBERS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cfnumbersDTO cfNumbersDto = new cfnumbersDTO();
                cfNumbersDto.setCfId(rs.getInt("cf_id"));
                cfNumbersDto.setCfNumber(rs.getString("cf_number"));
                cfNumbersDto.setCfName(rs.getString("cf_name"));
                cfNumbersDto.setCfArea(rs.getString("cf_area"));
                cfNumbersList.add(cfNumbersDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cfNumbersList;
    }
}

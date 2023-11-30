package car;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;

public class CarDAO extends JDBConnect {

	public CarDAO() {
		super();
	}
	
	// 검색있으면 포함해서 가져오기
	public List<CarDTO> selectList(String searchField, String searchWord) {
        List<CarDTO> dtoList = new ArrayList<>();
        String sql = "select * from car";

        if (searchWord != null) {
        	sql += " WHERE " + searchField + " LIKE '%" + searchWord + "%'";
        }

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CarDTO dto = new CarDTO();
                dto.setC_manufacture(rs.getString(1));
                dto.setC_name(rs.getString(2));
                dto.setC_color(rs.getString(3));
                dto.setC_price(rs.getString(4));
                dto.setC_releaseDate(rs.getString(5));
                dto.setC_information(rs.getString(6));
                dto.setC_image(rs.getString(7));

                // 날짜 비교하여 결과 필터링
                if (isVisible(dto.getC_releaseDate())) {
                    dtoList.add(dto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("selectList 오류");
        }

        return dtoList;
    }
	
	private boolean isVisible(String c_releaseDate) {
	    try {
	    	
	        // 등록 날짜를 LocalDate로 변환
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDate registrationLocalDate = LocalDate.parse(c_releaseDate, formatter);

	        // 1년 후의 날짜 계산
	        LocalDate oneYearLater = registrationLocalDate.plusYears(1);

	        // 현재 날짜
	        LocalDate currentDate = LocalDate.now();

	        // 현재 날짜가 1년 후의 날짜보다 이후인지 여부를 반환
	        return currentDate.isBefore(oneYearLater);
	        
	    } catch (DateTimeParseException e) {
	        // 예외 발생 시 로그 출력 또는 다른 예외 처리 작업 수행
	        e.printStackTrace();
	        System.out.println("Error Message: " + e.getMessage());
	        System.out.println("오류!");
	        return false;
	    }
	}
	
}

package com.example.demo2.dta;

import com.example.demo2.dto.SportsLocationDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface BasicDta {

    @Insert("insert into testtable values(#{ftypeNm},#{faciNm},#{faciTel)}")
    void insertList(SportsLocationDto sdto);

    @Select("select * from testtable where ftypeNm = #{sdto}")
    List<SportsLocationDto> find(@Param("sdto") String sdto);
}

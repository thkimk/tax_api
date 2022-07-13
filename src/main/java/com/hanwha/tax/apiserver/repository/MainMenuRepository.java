package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.MainMenu;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {

    @Query(value="select b.cust_grade as custGrade, b.banner_id as id, b.`order`, c.`type`, c.title, c.sub_copy as subCopy, c.url, c.url_type as urlType, c.image_url as imageUrl, c.button_name as buttonName "+
            "from main_menu a inner join main_menu_info b inner join main_menu_banner c " +
            "where a.view_yn = 'Y' and b.cust_grade = :custGrade and a.id = b.main_menu_id and b.banner_id = c.banner_id and " +
            "(now() between c.begin_dt and c.end_dt) order by b.`order` desc" , nativeQuery=true)
    List<MainMenuVo> selectMainMenu(@Param("custGrade") String custGrade);

}



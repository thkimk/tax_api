package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.MainMenu;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {

    @Query(value="select b.cust_grade as custGrade, b.banner_id as id, b.`order`, c.`type`, c.title, c.sub_copy as subCopy, c.url, c.url_type as urlType, c.button_name as buttonName, d.id as imageId, d.url as imageUrl, d.bg_color as bgColor, d.height, d.width "+
            "from main_menu a, main_menu_info b, main_menu_banner c left outer join image_mng d "+
            "on c.image_id = d.id "+
            "where (a.id = b.main_menu_id and b.banner_id = c.banner_id) and a.view_yn = 'Y' and b.cust_grade = '01' and (now() between c.begin_dt and c.end_dt) order by b.`order` asc ", nativeQuery=true)
    List<MainMenuVo> selectMainMenu(@Param("custGrade") String custGrade);

}



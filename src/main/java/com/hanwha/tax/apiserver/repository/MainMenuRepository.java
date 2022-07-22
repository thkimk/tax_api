package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.entity.MainMenu;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    
    @Query(value = "SELECT banner.banner_id as bannerId, banner.`type` as type, banner.flag as flag, banner.title as title, banner.sub_copy as subTitle, banner.url_type as urlType, banner.url as url, banner.button_name as buttonName, image.url as imageUrl, image.bg_color as bgColor, image.anmt_cnt as aniCnt, image.width as width, image.height as height" +
            "         FROM main_menu_banner banner " +
            "    LEFT JOIN image_mng image " +
            "           ON banner.image_id  = image.id " +
            "        WHERE banner.banner_id in (SELECT banner_id " +
            "                                     FROM main_menu_info  " +
            "                                    WHERE main_menu_id in (SELECT id " +
            "                                                             FROM main_menu mm " +
            "                                                            WHERE view_yn  = 'Y')" +
            "                                      AND cust_grade  = :custGrade " +
            "                                    ORDER BY `order` ASC )" +
            "          AND CURRENT_DATE() BETWEEN begin_dt AND end_dt ",
            countQuery = "SELECT count(*)" +
                    "         FROM main_menu_banner banner " +
                    "    LEFT JOIN image_mng image " +
                    "           ON banner.image_id  = image.id " +
                    "        WHERE banner.banner_id in (SELECT banner_id " +
                    "                                     FROM main_menu_info  " +
                    "                                    WHERE main_menu_id in (SELECT id " +
                    "                                                             FROM main_menu mm " +
                    "                                                            WHERE view_yn  = 'Y')" +
                    "                                      AND cust_grade  = :custGrade " +
                    "                                    ORDER BY `order` ASC )" +
                    "          AND CURRENT_DATE() BETWEEN begin_dt AND end_dt",
            nativeQuery = true)
    Page<MainBannerInterface> selectAllMainBanner(@Param("custGrade") String custGrade, Pageable pageable);
}



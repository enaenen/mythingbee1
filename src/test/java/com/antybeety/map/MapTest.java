package com.antybeety.map;


import com.antybeety.map.controller.FacilityController;
import com.antybeety.map.model.dao.ConvenienceDetailDAO;
import com.antybeety.map.model.dao.ConvenienceMarkDAO;
import com.antybeety.map.model.dao.PoliceMarkDAO;
import com.antybeety.map.way.model.dao.EdgeInfoDAO;
import com.antybeety.map.way.model.service.SafetySettingService;
import com.antybeety.map.way.model.vo.EdgeInfoVO;
import com.antybeety.stats.model.dao.CrimeStatsDAO;
import com.antybeety.stats.model.vo.CrimeRankedVO;
import com.antybeety.stats.model.vo.CrimeStatsVO;
import com.antybeety.stats.service.CrimeStatsService;
import com.antybeety.stats.service.CrimeStatsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
@WebAppConfiguration
public class MapTest {
    @Autowired
    private SafetySettingService safetySettingService;
    @Autowired
    private ConvenienceMarkDAO dao;

    @Autowired
    private ConvenienceDetailDAO list;

    @Autowired
    private PoliceMarkDAO policeMarkDAO;

    @Autowired
    private EdgeInfoDAO edgeInfoDAO;

    @Test
    public void 시설물테스트(){
        Map<String, Object> map = new HashMap<>();
        map.put("la",37.0);
        map.put("ka",37.5);
        map.put("ea",127.0);
        map.put("ja",127.5);
        System.out.println(dao.searchFacilities(map));
    }
    @Test
    public void 데이터전처리(){
        safetySettingService.settingSafetyData();
    }
    @Test
    public void 상세정보테스트(){
        System.out.println(list.searchDetail("CS3876"));
    }

    @Test
    public void 시설물의위도경도가져오기(){
        System.out.println(policeMarkDAO.getLatLng());
    }

    @Test
    public void edge_위도경도가져오기(){
        System.out.println(edgeInfoDAO.getEdgeInfo().size());
    }
}

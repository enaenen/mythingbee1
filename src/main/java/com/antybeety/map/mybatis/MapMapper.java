package com.antybeety.map.mybatis;

import com.antybeety.map.model.vo.*;
import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeData;

import java.util.List;
import java.util.Map;

public interface MapMapper {
//    맵에 시설물 표시
    List<FacilityMarkVO> selectBell(Map<String, Object> map);
    List<FacilityMarkVO> selectPolice(Map<String, Object> map);
    List<FacilityMarkVO> selectConvenience(Map<String, Object> map);
    List<FacilityMarkVO> selectCCTV(Map<String, Object> map);
    List<FacilityMarkVO> selectLight(Map<String, Object> map);
    List<FacilityMarkVO> selectProtectHouse(Map<String, Object> bounds);

    //    선택한 시설물 상셍정보 표시
    FacilityDetailVO getBellDetail(String code);
    FacilityDetailVO getPoliceDetail(String code);
    FacilityDetailVO getConvenienceDetail(String code);
    FacilityDetailVO getCCTVDetail(String code);

    FacilityDetailVO getLightDetail(String code);
    List<String> searchAllCCTVRoadAddr();
    List<String> searchAllBellRoadAddr();
    List<String> searchAllPoliceRoadAddr();
    List<String> searchAllConvenienceRoadAddr();

    List<String> searchAllLightRoadAddr();
}

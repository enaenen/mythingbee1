package com.antybeety.map.way.model.service;

import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.way.model.dao.EdgeDAO;
import com.antybeety.map.way.model.dao.NodeDAO;
import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapSettingService {

    @Autowired
    private FacilityDisplayService fDisplay;

    @Autowired
    private DistanceCalcService distanceCalc;

    @Autowired
    private EdgeDAO edgeDAO;

    @Autowired
    private NodeDAO nodeDAO;

    // 두 노드 사이에 안전 가중치를 구하는 메서드
    public double setSafetyValue (Map<String,Object> bounds){
        // 영역 내의 모든 시설물 가져오기
        List<FacilityMarkVO> allFacility = fDisplay.searchAroundFacilities(bounds);

        int safetyValueSum = 0;

        // 영역 내의 총 안전수치 점수 . 지금은 개당 1점이지만 안전 시설물마다 점수를 다르게 매겨야 함
        for(FacilityMarkVO v : allFacility){
            switch (v.getCode().substring(0,2)){
                case "CC":
                    safetyValueSum += 8;
                    break;
                case "LI":
                    safetyValueSum += 3;
                    break;
                case "PD":
                    safetyValueSum += 50;
                    break;
                case "CS":
                    safetyValueSum += 20;
                    break;
                case "BE":
                    safetyValueSum ++;
                    break;
            }
        }
        return  safetyValueSum / distanceCalc.calcArea(bounds);
    }

    // 경로에 안전 가중치 부여하기
    public void setAllSafetyValue (){

    }

    // 모든 경로의 도로명 주소 구하기
    public void setAllRoadAddr() {
        List<EdgeVO> edgeList = edgeDAO.getAllEdge();
        List<NodeVO> nodeList = nodeDAO.getAllNode();

        Map<String, Object> locationList = new HashMap<>();

        double startLat=0;
        double startLon=0;
        double endLat=0;
        double endLon=0;

        for(EdgeVO e : edgeList){
            locationList.put("id",e.getEdgeId());
            for(NodeVO n : nodeList){
                if( e.getNodeStart() == n.getNodeId()){
                    startLat = n.getLat();
                    startLon = n.getLng();
                } else if ( e.getNodeEnd() == n.getNodeId()){
                    endLat = n.getLat();
                    endLon = n.getLng();
                }
            }

            locationList.put("lat", (startLat + endLat)/2);
            locationList.put("lon", (startLon + endLon)/2);

            edgeDAO.setLocation(locationList);
        }
    }

    // 일단 패스
    public void setAllDistance(){
        List<EdgeVO> edgeList = edgeDAO.getAllEdge();

    }
}

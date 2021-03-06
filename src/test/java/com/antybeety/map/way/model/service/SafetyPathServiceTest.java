package com.antybeety.map.way.model.service;


import com.antybeety.map.way.model.vo.EdgeVO;
import com.antybeety.map.way.model.vo.NodeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
public class SafetyPathServiceTest {

     @Autowired
     SafetyPathService service;

     @Autowired
    DistanceCalcService distService;

    @Test
    public void test_좌표로부터_가장가까운_노드찾기(){
        NodeVO node = service.matchNode(37.48669756159295,126.95819580920832);
        System.out.println(node);
    }

    @Test
    public void test_영역안의_노드와엣지찾기(){
        Map<String, List<?>> res = service.searchNodeEdgeByArea(37.47686563082442, 126.95416760502995,
                37.478168073615855, 126.95543304469112);

        List<NodeVO> nodes = (List<NodeVO>) res.get("nodes");
        List<EdgeVO> edges = (List<EdgeVO>) res.get("edges");

        System.out.println(""+nodes+nodes.size());
        System.out.println(""+edges+edges.size());
    }

    @Test
    public void test_그래프초기화를위한_노드와엣지찾기(){
        Map<String, List<?>> res = service.searchNodeEdgeForGraph(37.47686563082442, 126.95416760502995,
                37.478168073615855, 126.95543304469112);

        List<NodeVO> nodes = (List<NodeVO>) res.get("nodes");
        List<EdgeVO> edges = (List<EdgeVO>) res.get("edges");

        System.out.println(""+nodes+nodes.size());
        System.out.println(""+edges+edges.size());
    }

    @Test
    public void test_안전경로찾기(){

        NodeVO startNode = service.matchNode(37.481155806833094,126.95250635567398);
        NodeVO endNode = service.matchNode(37.47923658669725,126.9525814028913);
        service.initService(startNode.getLat(), startNode.getLng(), endNode.getLat(), endNode.getLng());
        System.out.println(service.astar(startNode.getId(),endNode.getId()));

    }

    @Test
    public void test_안전경로_ai(){
        NodeVO startNode = service.matchNode(37.481155806833094,126.95250635567398);
        NodeVO endNode = service.matchNode(37.47923658669725,126.9525814028913);
        service.initService_ai(startNode.getLat(), startNode.getLng(), endNode.getLat(), endNode.getLng());
        System.out.println(service.astar(startNode.getId(),endNode.getId()));
    }

    @Test
    public void test_distance(){
        System.out.println(distService.calcDistance(37.476870208154466, 126.95332029114627, 37.479133839685325,126.95363408772076 ));
    }

    @Test
    public void initGraphTest(){
        NodeVO startNode = service.matchNode(37.481155806833094,126.95250635567398);
        NodeVO endNode = service.matchNode(37.47923658669725,126.9525814028913);
        service.initService(startNode.getLat(), startNode.getLng(), endNode.getLat(), endNode.getLng());
    }

}
package com.antybeety.map.model.dao;

import com.antybeety.map.model.vo.FacilityDetailVO;
import com.antybeety.map.mybatis.MapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LightDetailDAO  extends  FacilityDetailDAOImpl{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public FacilityDetailVO searchDetail(String code) {
        MapMapper mapper = sqlSession.getMapper(MapMapper.class);
        return mapper.getLightDetail(code);
    }

    @Override
    public String getFacilName() {
        return "LI";
    }

    @Override
    public List<String> searchAllRoadAddr() {
        MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
        return mapMapper.searchAllLightRoadAddr();
    }
}

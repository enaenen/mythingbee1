package com.antybeety.news.model.service;

import com.antybeety.district.model.dao.DistrictDAO;
import com.antybeety.news.model.dao.ArticleInfoDAO;
import com.antybeety.news.model.vo.ArticleInfoKVO;
import com.antybeety.news.model.vo.ArticleInfoVO;
import com.antybeety.news.model.vo.KeywordVO;
import com.antybeety.press.model.dao.PressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsAdServiceImpl implements NewsAdService{
    @Autowired
    private ArticleInfoDAO articleDao;
    @Autowired
    private PressDAO pressDao;
    @Autowired
    private DistrictDAO districtDao;


    private ArticleInfoVO parseKvoToVo(ArticleInfoKVO article){
        ArticleInfoVO vo = new ArticleInfoVO();
        vo.setCode(article.getCode());
        vo.setTitle(article.getTitle());
        vo.setImgURL(article.getImgURL());
        vo.setUrl(article.getUrl());
        vo.setSummary(article.getSummary());
        vo.setDistrictName(article.getDistrictName());
        vo.setPressName(article.getPressName());
        vo.setArticleTime(article.getArticleTime());
        vo.setViewCnt(article.getViewCnt());

        //키워드 형식 파싱. KeywordVo-> "키워드1,키워드2,키워드3"
        List<KeywordVO> kws=article.getKeywords();
        String keywordArray="";
        for(KeywordVO k : kws){
            keywordArray+=k.getName()+",";
        }
         //마지막 쉼표 제거
        keywordArray =keywordArray.substring(0,keywordArray.length()-1);

        vo.setKeywordName(keywordArray);

        return vo;
    }


    @Override
    public int addArticle(ArticleInfoKVO article) {
        /*
        만들어야할것: code, pressName, districtName, keywordName
        그대로써야핟것: title, summary, url, imgURL,
        sql에서 자동추가: articleTime, viewCnt
         */
        article.setCode(makeArticleCode()); //일련번호 만들어서 반환
        String pressCode = pressDao.searchPressCode(article.getPressName());
        String districtCode = districtDao.searchDistrictCodeByName(article.getDistrictName());

        article.setPressName(pressCode);    //이름을 코드로 변환
        article.setDistrictName(districtCode);  //이름을 코드로 변환
        articleDao.addArticle(parseKvoToVo(article)); // db에 article_tb, img_tb에 기사 추가

        //기사 키워드 추가하기기
       return 0;
    }

    public String makeArticleCode(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String res="";
        String now = format.format(new Date()); //오늘 날짜정보

        String lastArtiCode= articleDao.searchLastDate();

        String lastArtiDate= lastArtiCode.substring(0,6);//

        if(lastArtiDate.equals(now)){   //오늘 추가된 기사가 있을 경우 오늘 추가된 기사의 코드 인덱스 +1
            String lastArtiIndex= lastArtiCode.substring(6);
            res= now+String.format("%04d", Integer.parseInt(lastArtiIndex)+1);
        }else{
            res= now+"0001";
        }
        return res;
    }


    @Override
    public ArticleInfoVO searchArticle(String article){
        return null;
    }

    @Override
    public List<String> searchAllPresses() {
        return null;
    }

    @Override
    public List<String> searchAllDistricts() {
        return null;
    }

    @Override
    public int updateArticle(ArticleInfoKVO article) {
        return 0;
    }
}

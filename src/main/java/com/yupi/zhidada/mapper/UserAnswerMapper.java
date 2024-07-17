package com.yupi.zhidada.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.zhidada.model.entity.UserAnswer;
import com.yupi.zhidada.model.statistic.AppAnswerCountDTO;
import com.yupi.zhidada.model.statistic.AppAnswerResultCountDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 周宇涵
* @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
* @createDate 2024-07-10 11:18:04
* @Entity generator.domain.UserAnswer
*/
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {

    @Select("select appId, count(userId) as answerCount from user_answer\n" +
            "    group by appId order by answerCount desc limit 10;")
    List<AppAnswerCountDTO> doAppAnswerCount();


    @Select("select resultName,count(resultName) as resultCount from user_answer\n" +
                "where appId = #{appId}\n" +
                "group by resultName order by resultCount desc ")
    List<AppAnswerResultCountDTO> doAppAnswerResultCount(Long appId);

}





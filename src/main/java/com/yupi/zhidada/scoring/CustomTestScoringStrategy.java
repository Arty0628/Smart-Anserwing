package com.yupi.zhidada.scoring;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yupi.zhidada.common.ErrorCode;
import com.yupi.zhidada.exception.ThrowUtils;
import com.yupi.zhidada.model.dto.question.QuestionContentDTO;
import com.yupi.zhidada.model.entity.App;
import com.yupi.zhidada.model.entity.Question;
import com.yupi.zhidada.model.entity.ScoringResult;
import com.yupi.zhidada.model.entity.UserAnswer;
import com.yupi.zhidada.model.vo.QuestionVO;
import com.yupi.zhidada.service.AppService;
import com.yupi.zhidada.service.QuestionService;
import com.yupi.zhidada.service.ScoringResultService;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义测评
 */
public class CustomTestScoringStrategy implements ScoringStrategy {
    @Resource
    private QuestionService questionService;
    @Resource
    private ScoringResultService scoringResultService;
    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        //1.根据id查询到题目和结果的信息
        //查询数据库中Question表中的一条记录，条件是Appid字段等于app.getId()的值。如果查询结果为空，则抛出异常。
        Long appId = app.getId();
        Question question = questionService.getOne(
                Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId)
        );
        List<ScoringResult> scoringResultList = scoringResultService.list(
                Wrappers.lambdaQuery(ScoringResult.class)
                        .eq(ScoringResult::getAppId, appId)
        );
        //2.统计用户每个选项统计的属性个数
        //先转换成VO
        QuestionVO questionVO = QuestionVO.objToVo(question);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContent();
        // 初始化一个Map，用于存储每个选项的计数
        Map<String, Integer> optionCount = new HashMap<>();
        for (QuestionContentDTO questionContentDTO : questionContent) {
            // 遍历答案列表
            for (String answer : choices) {
                // 遍历题目中的选项
                for (QuestionContentDTO.Option option : questionContentDTO.getOptions()) {
                    // 如果答案和选项的key匹配
                    if (option.getKey().equals(answer)) {
                        // 获取选项的result属性
                        String result = option.getResult();

                        // 如果result属性不在optionCount中，初始化为0
                        if (!optionCount.containsKey(result)) {
                            optionCount.put(result, 0);
                        }

                        // 在optionCount中增加计数
                        optionCount.put(result, optionCount.get(result) + 1);
                    }
                }
            }
        }
        //3.遍历每种评分结果，计算哪个结果的评分更高
        // 初始化最高分数和最高分数对应的评分结果
        int maxScore = 0;
        ScoringResult maxScoringResult = scoringResultList.get(0);
        //遍历评分结果列表
        for(ScoringResult scoringResult : scoringResultList){
            List<String> resultProp = JSONUtil.toList(scoringResult.getResultProp(), String.class);
            //计算当前评分结果的分数 [I,E] ==> [10,5] ==>15
            int score = resultProp.stream()
                    .mapToInt(prop -> optionCount.getOrDefault(prop,0))
                    .sum();
            if(score > maxScore){
                maxScore = score;
                maxScoringResult = scoringResult;
            }
        }
        //4.构造返回值，填充答案对象
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAppId(appId);
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(maxScoringResult.getId());
        userAnswer.setResultName(maxScoringResult.getResultName());
        userAnswer.setResultDesc(maxScoringResult.getResultDesc());
        userAnswer.setResultPicture(maxScoringResult.getResultPicture());
        return userAnswer;
    }
}

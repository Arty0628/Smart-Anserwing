package com.yupi.zhidada.controller;

import com.yupi.zhidada.model.dto.question.AiGenerateQuestionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionControllerTest {
    @Resource
    private QuestionController questionController;

    @Test
    void aiGenerateQuestionSSETest() throws InterruptedException {
        //模拟普通用户
        AiGenerateQuestionRequest aiGenerateQuestionRequest = new AiGenerateQuestionRequest();
        aiGenerateQuestionRequest.setAppId(3L);
        aiGenerateQuestionRequest.setQuestionNumber(10);
        aiGenerateQuestionRequest.setOptionNumber(2);

        //普通用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,false);
        //普通用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,false);
        //vip用户
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest,true);

        // 模拟主线程一直启动
        Thread.sleep(1000000L);
    }
}
//package com.yupi.zhidada.controller;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.yupi.zhidada.common.BaseResponse;
//import com.yupi.zhidada.common.ErrorCode;
//import com.yupi.zhidada.common.ReviewRequest;
//import com.yupi.zhidada.model.entity.App;
//import com.yupi.zhidada.model.entity.User;
//import com.yupi.zhidada.model.enums.ReviewStatusEnum;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.http.HttpServletRequest;
//import com.yupi.zhidada.service.AppService;
//import com.yupi.zhidada.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AppControllerTest {
//    @InjectMocks
//    private AppController appReviewController;
//
//    @Mock
//    private AppService appService;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    void doAppReview() {
//        // 测试用例1：正常审核应用
//        ReviewRequest reviewRequest1 = new ReviewRequest();
//        reviewRequest1.setId(1L);
//        reviewRequest1.setReviewStatus(ReviewStatusEnum.PASS.getValue());
//        reviewRequest1.setReviewMessage("审核通过");
//
//        App oldApp1 = new App();
//        oldApp1.setId(1L);
//        oldApp1.setReviewStatus(ReviewStatusEnum.REVIEWING.getValue());
//
//        when(appService.getById(1L)).thenReturn(oldApp1);
//        when(userService.getLoginUser(request)).thenReturn(new User());
//        when(appService.updateById(any(App.class))).thenReturn(true);
//
//        BaseResponse<Boolean> response1 = appReviewController.doAppReview(reviewRequest1, request);
//        assertEquals(HttpStatus.OK, response1.getCode());
//        assertEquals(ErrorCode.SUCCESS.getCode(), response1.getMessage());
//    }
//}
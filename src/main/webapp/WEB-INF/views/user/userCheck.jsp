<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <section>
        <!--Toggleable / Dynamic Tabs긁어옴-->
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-lg-9 infoCheck">
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    <div class="alert alert-danger text-center">
                        <strong>개인정보 도용으로 인한 피해를 예방하기 위하여 비밀번호를 확인합니다.</strong><br>
                        <strong>비밀번호는 타인에게 노출되지 않도록 주의해주세요.</strong>
                    </div>
                    <form action="" class="infoCheck-form">
                        <ul>
                            <li>
                                <span class="lab">아이디</span>
                                <input type="text" class="form-control" readonly>
                            </li>
                            <li>
                                <span class="lab">비밀번호</span>
                                <input type="password" class="form-control">
                            </li>

                        </ul>
                    </form>
                    <div class="infoCheck-btn">
                        <button type="button" class="btn btn-primary">확인</button>
                        <button type="button" class="btn btn-info">취소</button>
                    </div>
                </div>
            </div>    
        </div>
    </section>

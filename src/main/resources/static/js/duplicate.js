// =============== 회원가입 중복 기능 ===============

// 페이지 로드 시 실행되는 함수
window.onload = function() {
    // 페이지가 로드될 때 한 번만 실행되므로, 이곳에서 초기 값을 가져옴
    const initialNickName = document.getElementById('nickName').value;
    const initialEmail = document.getElementById('email').value;

    // 이후에 사용할 수 있도록 변수에 저장
    window.initialNickName = initialNickName;
    window.initialEmail = initialEmail;
};
function checkDuplicate(target) {
    // onclick 이벤트 발생 시 해당 버튼(this)의 부모 요소인 .form-group을 찾음
    const formGroupTag = $(target).closest('.form-group');

    // 그 다음 .form-group 내에서 input 태그를 찾음
    const inputTag = formGroupTag.find('input');

    // input 태그의 값 가져오기
    const inputValue = inputTag.val();

    // 버튼 작동 시 어떤 중복확인인지 Input 태그의 'id' 이름(문자열)을 가져오기
    const inputId = inputTag.attr('id');

    // ---------------------------------------------------------------- //
    // 그 div태그를 html문서마다 구별하기위해 한번더 div태그로 묶어주고 //
    // 그 한번더 묶은 div의 id를 식별자로 사용하기 위함                 //
    // ---------------------------------------------------------------- //

    // div태그(formGroupTag)의 상위 div태그를 'id'를 보유하고있는지로 찾음.
    const divTag = formGroupTag.closest('[id]');
    console.log("checkDuplicate() divTag : ", divTag);

    // 찾은 div태그의 id값을 저장(자바스크립트 메소드를
    // html페이지별로 다르게 기능하기위한 식별자 기능)
    const divTagId = divTag.attr('id');
    console.log("checkDuplicate() divTagId : ", divTag.attr('id'));

    $.ajax({
        url: '/users/checkDuplicate',
        method: 'POST',
        dataType: 'json',
        data: { infoValue: inputValue, key: inputId },
        success: function(result) {
            displayData(result, formGroupTag, inputId);

            if(result) {
                btnDeactive(target);
            } else {
                btnActive(target);
            }

            if(divTagId=='signup'){
                console.error('조건 성공. divTagId :', divTagId);
                updateBtnState();
            }
        },
        error: function(error) {
            console.error('데이터를 가져오는 중 에러 발생:', error);
        }
    });
}

function displayData(result, inputFormTag, inputId) {
    // 클래스명form-group이라는 formTag 내에서 result-class를 찾음
    const resultContainer = inputFormTag.find('.result-class');

    // 어떤 중복확인인지 id값을 inputId 문자열로 받아서 대문자처리
    const upperDynamicId = inputId.toUpperCase();

    // 문자열 템플릿은 백틱(`)으로 감싸져 있다.
    if(result) {
        resultContainer.html(
            `<span style="font-size: 15px; color: #01DF01;">사용 할 수 있는 ${upperDynamicId} 입니다.</span>`
        );
    } else {
        resultContainer.html(
            `<span style="font-size: 15px; color: red;">중복된 ${upperDynamicId} 입니다.</span>`
        );
    }
}

function displayReset(inputElement) {

    const formTag = $(inputElement).closest('.form-group');

    // 클래스명form-group이라는 formTag 내에서 result-class를 찾음
    const resultContainer = formTag.find('.result-class');

    resultContainer.empty();
}

function btnDeactive(targetBtn) {
    targetBtn.disabled = true;
    targetBtn.style.opacity=0.4;
    targetBtn.style.cursor="default";
}

function btnActive(targetBtn) {
    targetBtn.disabled = false;
    targetBtn.style.opacity = 1;
    targetBtn.style.cursor = "pointer";
}

function updateBtnState() {
    // 각 버튼의 상태를 확인
    const idButton = document.querySelector('#id + button');
    const emailButton = document.querySelector('#email + button');
    const nickNameButton = document.querySelector('#nickName + button');

    // ------- 각 버튼상태 콘솔로그로 확인용(지워도됨) ---------------------
    if(idButton!=null){
        console.log("ID 중복확인버튼 비활성화 여부:", idButton.disabled);
    }
    console.log("이메일 중복확인버튼 비활성화 여부:", emailButton.disabled);
    console.log("닉넴 중복확인버튼 비활성화 여부:", nickNameButton.disabled);
    // ----------------------------------------------------------------------

    // 회원가입(submit) 버튼
    const registBtn = document.getElementById('submitBtn');

    var allTrue = allTrue = idButton.disabled && emailButton.disabled && nickNameButton.disabled;

    if(allTrue) {
        btnActive(registBtn);
    } else {
        btnDeactive(registBtn);
    }
}

function inputCheckAfter(inputElement) {
    // 입력받은 객체의 상위 div태그 구함
    const formGroupTag = inputElement.closest('.form-group');
    console.log("formGroupTag : ", formGroupTag);

    // ---------------------------------------------------------------- //
    // 그 div태그를 html문서마다 구별하기위해 한번더 div태그로 묶어주고 //
    // 그 한번더 묶은 div의 id를 식별자로 사용하기 위함                 //
    // ---------------------------------------------------------------- //

    // div태그(formGroupTag)의 상위 div태그를 'id'를 보유하고있는지로 찾음.
    const divTag = formGroupTag.closest('[id]');
    console.log("divTag : ", divTag);

    // 찾은 div태그의 id값을 저장(자바스크립트 메소드를
    // html페이지별로 다르게 기능하기위한 식별자 기능)
    const divTagId = divTag.id;
    console.log("divTagId : ", divTagId);

    // 입력받은 객체의 최종 submit버튼 가져오기
    const targetBtn = formGroupTag.querySelector('button');

    // 지금 입력하는 부분의 상태(중복상태) 출력
    displayReset(inputElement);

    // 중복확인 버튼 활성화(검사받고 비활성화됀 상태여도
    // 건드리는순간 무조건 다시 검사를 위해)
    btnActive(targetBtn);

    // 회원 정보 수정 시 기존의 정보와 변경할 정보가 같을때 중복확인 피하기 위한 기능
    // editProfile(회원정보수정)일때 입력된값(inputElement.value)이
    // 초기 닉네임값이거나 초기 이메일값인지 비교(초기값은 상단 처음에 선언)
    if( (divTagId == 'editProfile') &&
        ((inputElement.value == window.initialNickName) ||
        (inputElement.value == window.initialEmail)) ) {
        btnDeactive(targetBtn);
    }

    // 회원 가입만 사용 하는 '가입' 버튼 활성화 기능
    if(divTagId=='signup'){
        updateBtnState();
    }
}

function checkAndPerform() {
    // 현재 html문서의 id + 버튼 으로 각 기능의 버튼 가져오기
    const emailButton = document.querySelector('#email + button');
    const nickNameButton = document.querySelector('#nickName + button');

    // 중복확인 버튼은 무조건 둘다 비활성화(중복검사확인된상태)여야함
    var allTrue = emailButton.disabled && nickNameButton.disabled;

    // 리턴 안해주면 경고창 떠도 submit 진행됨
    if (!allTrue) {
        alert("중복확인을 확인해주세요.");
        return false; // submit을 막기 위해 false 반환
    }

    return true; // submit 허용
}




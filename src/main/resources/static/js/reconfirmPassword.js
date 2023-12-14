 // 비밀번호 체크 기능

function checkPasswordMatch() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("pswdCheck").value;
    var matchMessage = document.getElementById("passwordMatchMessage");

    if (password === confirmPassword) {
        matchMessage.innerHTML = "비밀번호 일치";
        matchMessage.style.color = "#01DF01";
    } else {
        matchMessage.innerHTML = "비밀번호 불일치";
        matchMessage.style.color = "red";
    }
}

function submitCheckPassword() {

    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("pswdCheck").value;

    // 리턴 안해주면 경고창 떠도 submit 진행됨
    if (password != confirmPassword) {
        alert("비밀번호를 확인해주세요.");
        return false; // submit을 막기 위해 false 반환
    }

    return true; // submit 허용
}
/**
 * 
 */

function find(){
	new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('memberZipcode').value = data.zonecode;
                document.getElementById("memberAddress").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("memberAddressDetail").focus();
            }
        }).open();
}

// 이용 약관
const $all = $("#term");
const $terms = $(".terms");

// 전체 동의 클릭
$all.on("click", function(){
	//각각의 약관의 checked 프로퍼티를 모두 전체동의의 checked 상태로 변경시켜준다.
	// 전체 동의가 true면 나머지 다 true
	$terms.prop("checked", $(this).is(":checked"));
});

// 각각의 약관 동의 클릭
$terms.on("click", function(){
	// 각각의 약관의 checked 프로퍼티가 true인 개수를 가져온 뒤
	// 2개 모두 true일 경우 전체 동의도 true이다.
	$all.prop("checked", $terms.filter(":checked").length == $terms.length);
});

// 약관 펼쳐보기
const $aTags = $("a.showTerm");
let $temp;
let checks = new Array(-1, -1); 

$aTags.on("click", function(e){
	e.preventDefault();
	$("#"+ $(this).attr("href")).slideToggle(function(){
		checks[$(this).data("index")] *= -1;
		$(this).prev().find("a").text(checks[$(this).data("index")] > 0 ? "닫기 X" : "펼쳐보기")
		console.log($(this).data("index"));
	});
});


$("#memberId").on("blur", function(){checkId($(this).val());});

let check = false;	
function checkId(memberId){
	check = false;
	if(!memberId){
		$("#result").text("아이디를 입력해주세요.");
		$("#result").css("color", "red");
		return;
	}
	
	$.ajax({
		url: "/member/checkId.me",
		data: {"memberId": memberId},
		success: function(result){
			let message, color;
			
			if(result == "true"){
				message = "사용 가능한 아이디입니다.";
				color = "blue";
				check = true;
			}else{
				message = "중복된 아이디입니다.";
				color = "red";
			}
			
			$("#result").css("color", color);
			$("#result").text(message);
		},
		error: function(a, b, c){
			console.log(a, b, c);
		}
	});
}

function send(){
	/*let pwRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	
	if(!pwRegex.test(joinForm.memberPw)){
		
	}*/
	
	if(!check){
		return;
	}
	
	if(!$all.is(":checked")){
		return;
	}
	joinForm.submit();
}





































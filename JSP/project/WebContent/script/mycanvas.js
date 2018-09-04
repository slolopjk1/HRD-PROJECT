function draw(count, name) {
	var canvas = document.getElementById("chartcanvas");
	var context = canvas.getContext("2d");
	var sw = canvas.width;
	var sh = canvas.height;
	var padding = 100;
	
	var data =[30.3, 24.6, 19.3, 16.3, 2.3];
	
	var colors = ["#7cfc00", "#0000ff", "#ff1493", "#66CDAA", "#ff00ff"];
	
	var center_X = sw/2; // 원의 중심 x 좌표
	var center_Y = sh/2; // 원의 중심 y 좌표
	
	var radius = Math.min(sw-(padding*2), sh-(padding*2))/2; // 두 계산값 중 작은 값을 원의 반지름으로 설정
	var angle = 0;
	var total = 0;
	for(var i in data) { total += data[i]; } // 데이터의 총합 계산
	
	for(var i= 0; i<data.length; i++) { 
		context.fillStyle = colors[i];
		context.beginPath();
			context.moveTo(center_X, center_Y); //원의 중심으로 이동
			context.arc(center_X, center_Y, radius, angle, angle + (Math.PI*2*(data[i]/total)));
		context.lineTo(center_X, center_Y);
			context.fill();
			angle += Math.PI*2*(data[i]/total);
	}
	//title 나타내기
	var title = "부서별 차트";
	context.textAlign="center";
	context.fillStyle="black";
	context.font ="14pt"
	context.filleText(title, sw/2, 30);
	
	//
}



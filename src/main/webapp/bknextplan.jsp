<html ng-app="listApp">
<!-- Standard Meta -->
<meta http-equiv="content-type" charset="utf-8">

<link rel="shortcut icon" type="image/png" href="favicon.ico">
<link rel="stylesheet" type="text/css" href="dist/semantic.min.css">

<script src="jquery/jquery-3.1.1.min.js"></script>
<script src="dist/components/form.min.js"></script>
<script src="dist/components/transition.min.js"></script>
<script src="dist/semantic.min.js"></script>
<script>
initdata=[];
initdata.userid = '<%=request.getAttribute("userid")%>';
initdata.username = '<%=request.getAttribute("username")%>';
initdata.appointdate = '<%=request.getAttribute("appointdate")%>';
initdata.content = '<%=request.getAttribute("content")%>';
initdata.status = '<%=request.getAttribute("status")%>';
</script>

<script src="jquery/jquery-3.1.1.min.js"></script>
<script src="dist/semantic.min.js"></script>
<script src="angularjs/angular.min.js"></script>
<script type="text/javascript">
  var app = angular.module('listApp',[]);
  
  app.config(function($provide){
          
      $provide.factory("transFormFactory",function(){
          return {
              transForm : function(obj){
                  var str = [];  
                  for(var p in obj){  
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));  
                  } 
                  return str.join("&");  
              }
          };
      });
  });
  
app.controller('ListController', function($scope,$http,transFormFactory) {
  var list = this;
  list.message ="";
  list.content = "";
  list.userid=initdata.userid;
  list.username=initdata.username;
  list.content=initdata.content;
  list.userlist = [
	  {'userid':'U0000001' ,'username' : '本田慶応'},
	  {'userid':'U0000002' ,'username' : '豊田慶応'}]
  list.appointmentinfolist=[
	  {'userid':list.userid , 'username':list.username , 'date':initdata.appointdate , 'content':list.content , 'status':initdata.status },
	  {'userid':'U0000002' , 'username':'本田慶応' , 'date':'2018-09-08' , 'content':'予定の日付に病院に来てください。' , 'status':'未確認' }];
  
  list.historyinfolist=[
	  {'userid':'U0000002' , 'username':'本田慶応' , 'date':'2018-09-08' , 'content':'予定の日付に病院に来てください。' , 'status':'確認済' }];
  
  list.onitemclick = function (){
	  if(this.content == null || this.content.length == 0){
		 this.message = "通知内容は入力必須です。";
         $('#cmodal') .modal('show');
         return;
	  } else {
		  //var birthday=list.getformatbirthday();
		  window.location.href = 'bknextplan.do?mode=submit&sex=n';
	  }
  }
  
});
</script>

<body ng-controller="ListController as list">
　　　<div id="cmodal" class="ui small test modal transition hidden">
		<i class="close icon"></i>
		<div class="content">
			<p id="errmsg">{{list.message}}</p>
		</div>
	</div>
	<div style="width: 80%; margin-left: auto; margin-right: auto;">
		<jsp:include page="bkheader.jsp" />
		
		<div class="ui attached segment" style="margin-top: －5px">
			<div class="ui form">
				<div class="three fields">
					<div class="field">
						<label>通知ユーザ</label> 
						<select class="ui dropdown">
							<option ng-repeat="eachitem in list.userlist" ng-model="eachitem.userid">{{eachitem.username}}</option>
						</select>
					</div>
					<div class="field">
						<label>日付</label> <input type="date" placeholder="Middle Name">
					</div>
				</div>
				<div class="field">
					<label>通知内容</label>
					<textarea ng-model="list.content"></textarea>
				</div>
			</div>

			<div class="ui middle aligned divided list">
				<div class="item">
					<button class="ui basic submit button" ng-click ="list.onitemclick()">
						<i class="icon user"></i> 通知
					</button>
				</div>
			</div>
		</div>
		<table class="ui celled table" style="margin-top: 5px">
			<tbody>
				<tr bgcolor="#FAFAFA" height="30px">
					<th width="30%" style="text-align: center">ユーザ名前</th>
					<th width="30%" style="text-align: center">日付</th>
					<th width="20%" style="text-align: center">お知らせ内容</th>
					<th width="20%" style="text-align: center">ユーザ確認状態</th>
				</tr>
				<tr ng-repeat="eachitem in list.appointmentinfolist">
					<td id="week0"><a href=bkhistorylist.do?userid={{eachitem.userid}}>{{eachitem.username}}</a></td>
					<td><a href=bkhistorylist.do?userid={{eachitem.userid}}>{{eachitem.date}}</a></td>
					<td><a href=bkhistorylist.douserid={{eachitem.content}}>{{eachitem.content}}</a></td>
					<td><a href=bkhistorylist.douserid={{eachitem.status}}>{{eachitem.status}}</a></td>
				</tr>
				
			</tbody>
		</table>
		<h4 class="ui horizontal divider header">
				<i class="bar chart icon"></i> 過去検査お知らせ
		</h4>
		<table class="ui celled table" style="margin-top: 5px">
			<tbody>
				<tr ng-repeat="eachitem in list.historyinfolist">
					<td width="30%" id="week0"><a href=bkhistorylist.do?userid={{eachitem.userid}}>{{eachitem.username}}</a></td>
					<td width="30%"><a href=bkhistorylist.do?userid={{eachitem.userid}}>{{eachitem.date}}</a></td>
					<td width="20%"><a href=bkhistorylist.douserid={{eachitem.content}}>{{eachitem.content}}</a></td>
					<td width="20%"><a href=bkhistorylist.douserid={{eachitem.status}}>{{eachitem.status}}</a></td>
				</tr>
				
			</tbody>
		</table>
		<div width="5px"></div>
	</div>
</body>
</html>

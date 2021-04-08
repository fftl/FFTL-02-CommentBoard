<template>
	<div>
		<h1>게시판 상세보기</h1>

		<div class="AddWrap">
			<form>
				<table class="tbAdd">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>제목</th>
						<td>{{title}}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>{{writer}}</td>
					</tr>
					<tr>
						<th>날짜</th>
						<td>{{regdate}}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="txt_cont" v-html="content"></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="btnWrap">
			<a href="javascript:;" @click="fnList" class="btn">목록</a>
			<a href="javascript:;" @click="fnMod" class="btnAdd btn">수정</a>
		</div>	
	</div>
</template>

<script>
export default {
	data() {
		return {
			body:this.$route.query.boardId
			,title:''
			,writer:''
            ,regdate:''
			,view:''
            ,content:''
            ,boardId:this.$route.query.boardId
		}
	}
	,mounted() {
        console.log(this.$route)
		this.fnGetView();
	}
	,methods:{
		fnGetView() {
			this.$http.get('http://localhost:3000/board/'+this.body, {params:this.body})
			.then((res)=>{
                console.log(res)
				this.body = res.data;
				this.title = this.body.title;
				this.writer = this.body.writer;
				this.regdate = this.body.regdate;
				this.content = this.body.content.replace(/(\n)/g,'<br/>');
			})
			.catch((err)=>{
				console.log(err);
			})
		}
		,fnList(){
			delete this.body.num;
			this.$router.push({path:'./list', query : this.body});
		}
        ,fnMod() {
            console.log(this.boardId)
            console.log("------------------------- View")
			this.$router.push({path:'./write', query : { boardId : this.boardId }}); //등록화면으로 이동하면서 파라미터를 넘겨준다.
		}
    }
}
</script>

<style scoped>
	.tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; box-sizing:border-box; text-align:left;}
	.tbAdd td.txt_cont{height:300px; vertical-align:top;}
	.btnWrap{text-align:center; margin:20px 0 0 0;}
	.btnWrap a{margin:0 10px;}
	.btnAdd {background:#43b984}
	.btnDelete{background:#f00;}
</style>